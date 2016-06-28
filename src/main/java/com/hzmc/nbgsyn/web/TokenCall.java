package com.hzmc.nbgsyn.web;

import net.sf.json.JSONObject;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

/**
 * 获取token
 * 
 * @author ZhuLi
 *
 */
public class TokenCall implements Callable {

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		JSONObject reJson = new JSONObject();
		String token = eventContext.getMessage().getOutboundProperty("token");
		if (null != token && !"".equals(token)) {
			reJson.accumulate("access_token", token);
			reJson.accumulate("expires_in", 720);
		} else {
			reJson.accumulate("0044", "身份验证失败");
		}
		return reJson;
	}

}
