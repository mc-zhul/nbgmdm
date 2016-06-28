package com.hzmc.nbgsyn.service;



import net.sf.json.JSONObject;

public interface ReceiveService {
	
	public JSONObject daoData(String table, JSONObject data);
	
	public JSONObject daoSyn(String str);
}
