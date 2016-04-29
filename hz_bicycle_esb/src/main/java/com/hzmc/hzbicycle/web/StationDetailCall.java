package com.hzmc.hzbicycle.web;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;


public class StationDetailCall implements Callable {

	
	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		JSONObject reJson = new JSONObject();
		HashMap<String,String> params = new HashMap<String,String>();
		JSONObject json = JSONObject.fromObject(eventContext.getMessage().getPayload());
		List list = null;
		if (json.get("query") != null) {
			JSONObject query = JSONObject.fromObject(json.get("query"));
			params.put("pname", query.getString("pname"));
		}
		JSONArray array = JSONArray.fromObject(list);
		reJson.put("count", array.size());
		reJson.put("fee", array);
		return reJson;
	}

}
