package com.hzmc.hzbicycle.business;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
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
	public long getTimeById(long source_id, long user_id);
	
}
