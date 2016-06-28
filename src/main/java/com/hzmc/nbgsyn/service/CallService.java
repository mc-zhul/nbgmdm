package com.hzmc.nbgmdm.service;

import org.mule.api.MuleEventContext;

import net.sf.json.JSONObject;

public interface CallService {
	
	public JSONObject mdCall(MuleEventContext eventContext, int type);
//	public JSONObject zxcCall(MuleEventContext eventContext, int type);
}
