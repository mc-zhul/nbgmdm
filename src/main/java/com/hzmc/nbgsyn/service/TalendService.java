package com.hzmc.nbgsyn.service;

import net.sf.json.JSONObject;

public interface TalendService {
	
	/**
	 * 调talend
	 * @param Map
	 * @return
	 */
	public JSONObject talendWS(String type, String model,String cluster,String xmls);

	
}
