package com.hzmc.nbgmdm.business;

import java.util.List;

import com.hzmc.nbgmdm.domain.persistence.SearchLog;

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
	public long checkAppkey(String appKey, String secret, String grant_type);
	
	/**
	 * 批量插入日志
	 * @param list
	 */
	public void insertLog(List<SearchLog> list);
}
