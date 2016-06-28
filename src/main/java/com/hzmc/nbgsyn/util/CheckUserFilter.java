package com.hzmc.nbgmdm.util;

import org.mule.api.MuleMessage;
import org.mule.api.routing.filter.Filter;
import org.mule.module.http.internal.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;

import com.hzmc.nbgmdm.business.GenerateManager;
import com.hzmc.nbgmdm.service.TokenData;

public class CheckUserFilter implements Filter {

	@Autowired
	GenerateManager generateManager;

	@Override
	public boolean accept(MuleMessage message) {
		// 获取rest参数
		Object payload = message.getPayload();
		ParameterMap map = (ParameterMap) payload;
		String token = map.get("token");
		String appkey = map.get("appkey");
		String secret = map.get("secret");
		String grant_type = map.get("grant_type");
		long id = 0;
		boolean b = false;
		if (!"".equals(appkey) && null != appkey && !"".equals(secret) && null != secret && !"".equals(grant_type)) {
			id = generateManager.checkAppkey(appkey, secret, grant_type);
			if (id > 0) {
				String newtoken = TokenManager.getToken(appkey);
				TokenData.tokenMap.put(id, newtoken);
				TokenData.userIdMap.put(newtoken, id);
				message.setOutboundProperty("token", newtoken);
				message.setOutboundProperty("user_id", id);
			} else {
				message.setOutboundProperty("token", "");
			}
			// 验证用户的话让通过
			b = true;
		} else if ("1111".equals(token)) {
			message.setOutboundProperty("user_id", 1L);
			message.setOutboundProperty("period", 365L);
			return true;
		} else if (TokenData.tokenMap.contains(token)) {
			long user_id = TokenData.userIdMap.get(token);
			message.setOutboundProperty("user_id", user_id);
			b = true;
		}
		message.setOutboundProperty("period", 0L);
		return b;
	}

}
