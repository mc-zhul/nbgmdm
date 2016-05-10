package com.hzmc.nbgmdm.service;

import net.sf.json.JSONObject;


public interface BiService {
	
	/**
	 * 港口
	 * @param area
	 * @param user_id
	 * @return
	 */
	public JSONObject getPort(long pagesize, long index);
	
}
