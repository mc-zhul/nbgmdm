package com.hzmc.nbgmdm.business;

import java.util.List;

import com.hzmc.nbgmdm.domain.persistence.StationBean;

/**
 * Bi数据库数据
 * @author ZhuLi
 *
 */
public interface BiManager {
	/**
	 * 自行车所有站点信息
	 * @param area
	 * @param time
	 * @return
	 */
	public List<StationBean> getStationAll(String area,long rows);
	
	/**
	 * 自行车具体某一站点详细信息
	 * @param net_id
	 * @param time
	 * @param timeperiod
	 * @return
	 */
}
