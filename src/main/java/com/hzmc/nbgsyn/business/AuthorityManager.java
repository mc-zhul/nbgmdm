package com.hzmc.nbgmdm.business;

import java.util.List;

import com.hzmc.nbgmdm.domain.persistence.UserAuthorityBean;

public interface AuthorityManager {
	
	/**
	 * 用户字段权限
	 * @param id
	 * @return
	 */
	public List<String> getColumnById(long source_id, long user_id);
	
	/**
	 * 用户时间权限
	 * @param id
	 * @return
	 */
	public List<UserAuthorityBean> getTime();
	
	public List<UserAuthorityBean> getAllColumns();
	
	/**
	 * 获取user所有字段
	 * @param source_id
	 * @return
	 */
	public List<UserAuthorityBean> getUserColumns();
	
	/**
	 * 获取所有接口
	 * @return
	 */
	public List<String> getSource();
}
