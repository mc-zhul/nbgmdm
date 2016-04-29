package com.hzmc.nbgmdm.service;

import java.util.Date;

import net.sf.json.JSONObject;


public interface BiService {
	
	/**
	 * 自行车所有站点信息
	 * @param area
	 * @param user_id
	 * @return
	 */
	public JSONObject getStation(String area, long user_id, long rows);
	
}
