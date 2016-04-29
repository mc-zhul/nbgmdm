package com.hzmc.hzbicycle.business;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hzmc.hzbicycle.domain.persistence.NearbyStationsBean;
import com.hzmc.hzbicycle.domain.persistence.NotReturnBean;
import com.hzmc.hzbicycle.domain.persistence.RentTotalBean;
import com.hzmc.hzbicycle.domain.persistence.ReturnStationBean;
import com.hzmc.hzbicycle.domain.persistence.ReturnTotalBean;
import com.hzmc.hzbicycle.domain.persistence.StationBean;
import com.hzmc.hzbicycle.domain.persistence.StationDetailBean;
import com.hzmc.hzbicycle.domain.persistence.RentStationBean;

/**
 * Bi数据库数据
 * @author ZhuLi
 *
 */
@Service
public interface BiManager {
	/**
	 * 自行车所有站点信息
	 * @param area
	 * @param time
	 * @return
	 */
	public List<StationBean> getStationAll(String area);
	
	/**
	 * 自行车具体某一站点详细信息
	 * @param net_id
	 * @param time
	 * @param timeperiod
	 * @return
	 */
	public StationDetailBean getStationDetail(long net_id, Date now,long timeperiod);
	
	/**
	 * 全市所有站点在单位时间范围内按还车量排序
	 * @param now
	 * @param timeperiod
	 * @return
	 */
	public List<ReturnTotalBean> getReturnTotal(Date now,long timeperiod);
	
	/**
	 * 全市所有站点在单位时间范围内按租车量排序
	 * @param now
	 * @param timeperiod
	 * @return
	 */
	public List<RentTotalBean> getRentTotal(Date now,long timeperiod);
	
	/**
	 * 附近租还车点的信息
	 * @param net_id
	 * @param now
	 * @param timeperiod
	 * @return
	 */
	public List<NearbyStationsBean> getNearbyStations(long net_id, Date now,long timeperiod);
	
	/**
	 * 租车后48小时未还车、下架后24小时未上架车辆的车号
	 * @param now
	 * @param timeperiod
	 * @return
	 */
	public List<NotReturnBean> getNotReturn(Date now,long timeperiod);
	
	/**
	 * 全市所有站点在单位时间范围内按租车量top20排名
	 * @param now
	 * @param timeperiod
	 * @return
	 */
	public List<RentStationBean> getTopRentStation(Date now,long timeperiod);
	
	/**
	 * 全市所有站点在单位时间范围内按租车量bottom20排名
	 * @param now
	 * @param timeperiod
	 * @return
	 */
	public List<RentStationBean> getBottoomRentStation(Date now,long timeperiod);
	
	/**
	 * 全市所有站点在单位时间范围内按还车量top20排名
	 * @param now
	 * @param timeperiod
	 * @return
	 */
	public List<ReturnStationBean> getTopReturnStation(Date now,long timeperiod);
	
	/**
	 * 全市所有站点在单位时间范围内按还车量bottom20排名
	 * @param now
	 * @param timeperiod
	 * @return
	 */
	public List<ReturnStationBean> getBottoomReturnStation(Date now,long timeperiod);
}
