package com.hzmc.nbgmdm.business;

import java.util.List;

import com.hzmc.nbgmdm.domain.persistence.PortBean;

/**
 * Bi数据库数据
 * @author ZhuLi
 *
 */
public interface BiManager {
	/**
	 * port
	 * @param area
	 * @param time
	 * @return
	 */
	public List<PortBean> getPort(long pagesize, long index);
	
}
