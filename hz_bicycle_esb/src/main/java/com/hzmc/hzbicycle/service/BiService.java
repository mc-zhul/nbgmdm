package com.hzmc.hzbicycle.service;

import net.sf.json.JSONObject;



public interface BiService {
	
	/**
	 * 自行车所有站点信息
	 * @param area
	 * @param time
	 * @param user_id
	 * @return
	 */
	public JSONObject getstationall(String area, long user_id);
	
	/**
	 * 自行车具体某一站点详细信息
	 * @param net_id
	 * @param time
	 * @param user_id
	 * @return
	 */
	public JSONObject getstationDetail(String net_id, long time, long user_id);
	
	/**
	 * 全市所有站点在单位时间范围内按还车量排序
	 * @param time
	 * @param user_id
	 * @return
	 */
	public JSONObject getReturnTatol(long time, long user_id);
	
	/**
	 * 全市所有站点在单位时间范围内按租车量排序
	 * @param time
	 * @param user_id
	 * @return
	 */
	public JSONObject getRentTatol(long time, long user_id);
	
	/**
	 * 附近租还车点的信息
	 * @param net_id
	 * @param time
	 * @param user_id
	 * @return
	 */
	public JSONObject getNearbyStations(String net_id, long time, long user_id);
	
}
