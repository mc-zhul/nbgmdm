package com.hzmc.hzbicycle.web;

import net.sf.json.JSONObject;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.mule.module.http.internal.ParameterMap;


public class StationAllCall implements Callable {

	
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		JSONObject reJson = new JSONObject();
		ParameterMap map = (ParameterMap)(eventContext.getMessage().getPayload());
		reJson.put("a", map);
		return reJson;
	}

}
