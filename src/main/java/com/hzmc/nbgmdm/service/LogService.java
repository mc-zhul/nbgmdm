package com.hzmc.nbgmdm.service;

public interface LogService {
	/**
	 * 插入日志
	 * 
	 * @param log
	 */
	public void insertLog(long userid, int sourceid, long costtime, long period, String reslut, String params,String result);

	/**
	 * 判断时间权限
	 * 
	 * @param user_id
	 * @param type
	 * @return
	 */
	public long checkPeriodFromCache(long timeP, int type, String time);

	/**
	 * 获取并判断时间权限
	 * 
	 * @param user_id
	 * @param type
	 * @param starttime
	 * @return
	 */
	public long checkPeriod(long user_id, int type, String starttime);

	/**
	 * 获取并判断时间权限
	 * 
	 * @param user_id
	 * @param type
	 * @param peroid
	 * @return
	 */
	public long checkPeriod(long user_id, int type, long peroid);
}
