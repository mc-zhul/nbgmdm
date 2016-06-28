package com.hzmc.nbgsyn.business;

import net.sf.json.JSONObject;


/**
 * 
 * @author ZhuLi
 *
 */
public interface DbManager {
	
	
	public JSONObject dataUpdate(String table, JSONObject data);
	
	public JSONObject dataCreate(String table, JSONObject data);
	
	public JSONObject dataSyn(String str);
	
}
