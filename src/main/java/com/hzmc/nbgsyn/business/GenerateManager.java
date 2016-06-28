package com.hzmc.nbgsyn.business;

import java.util.List;

import net.sf.json.JSONObject;

import com.hzmc.nbgsyn.domain.persistence.SearchLog;

/**
 * 
 * @author ZhuLi
 *
 */
public interface GenerateManager {
	/**
	 * 校验appkey
	 * @param appKey
	 * @param secret
	 * @param grant_type
	 * @return
	 */
	public long checkUser(String appKey, String secret, String grant_type);
	
	/**
	 * 批量插入日志
	 * @param list
	 */
	public void insertLog(List<SearchLog> list);
	
	/**
	 * 同步数据
	 */
	public void tansforData(JSONObject jo);
}
