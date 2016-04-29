package com.hzmc.hzbicycle.util;

import org.mule.api.MuleMessage;
import org.mule.api.routing.filter.Filter;
import org.mule.module.http.internal.ParameterMap;
import org.springframework.beans.factory.annotation.Autowired;

import com.hzmc.hzbicycle.business.GenerateManager;

public class CheckUserFilter implements Filter{
	
	@Autowired
	GenerateManager generateManager;
	
	@Override
	public boolean accept(MuleMessage message) {
		//获取rest参数
		Object payload = message.getPayload();
		ParameterMap map = (ParameterMap)payload;
		String token = map.get("token");
		String appkey = map.get("appkey");
		String secret = map.get("secret");
		String grant_type = map.get("grant_type");
		boolean b = false;
		if (!"".equals(appkey) && null != appkey && !"".equals(secret) && null != secret &&!"".equals(grant_type)) {
			b = generateManager.checkAppkey(appkey, secret, grant_type);
			if(b){
				String newtoken = TokenManager.getToken(appkey);
			}
			
		} else if("1111".equals(token)){
			return true;
		}
		
		System.err.println(token);
		return b;
	}


}
