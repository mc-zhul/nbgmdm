package com.hzmc.hzbicycle.business;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hzmc.hzbicycle.domain.persistence.AttendenceBean;
import com.hzmc.hzbicycle.domain.persistence.BikeSxjBean;
import com.hzmc.hzbicycle.domain.persistence.CarStatusBean;

/**
 * 自行车方数据库的查询
 * 
 * @author ZhuLi
 *
 */
@Service
public interface ZxcManager {

	/**
	 * 员工考勤查询(POS机签到签退)
	 * 
	 * @param card_id
	 * @param timeperiod
	 * @return
	 */
	public List<AttendenceBean> getAttendence(String card_id, String startTime, String endTime, long rows, long timeperiod);

	/**
	 * 车辆最后状态
	 * 
	 * @param car_id
	 * @param startTime
	 * @param endTime
	 * @param timeperiod
	 * @return
	 */
	public List<CarStatusBean> getCarLastStatus(String[] car_ids, String startTime, String endTime,  long rows,long timeperiod);

	/**
	 * 车辆上下架记录
	 * @param car_id
	 * @param oper_exteid
	 * @param startTime
	 * @param endTime
	 * @param rows
	 * @param timeperiod
	 * @return
	 */
	public List<BikeSxjBean> getBikeSxj(String car_id, String oper_exteid, String startTime, String endTime, long rows, long timeperiod);

}
