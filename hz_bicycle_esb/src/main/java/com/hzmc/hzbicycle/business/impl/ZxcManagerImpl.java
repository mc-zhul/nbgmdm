package com.hzmc.hzbicycle.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.hzmc.hzbicycle.business.ZxcManager;
import com.hzmc.hzbicycle.domain.persistence.AttendenceBean;
import com.hzmc.hzbicycle.domain.persistence.BikeSxjBean;
import com.hzmc.hzbicycle.domain.persistence.CarStatusBean;
import com.hzmc.hzbicycle.util.ZxcConnectionManager;

public class ZxcManagerImpl implements ZxcManager {

	@Override
	public List<AttendenceBean> getAttendence(String card_id, String startTime, String endTime, long rows, long timeperiod) {
		Connection conn = ZxcConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<AttendenceBean> attendenceBeanList = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		// c.setTime(now);
		// String hhmi = String.valueOf(c.get(Calendar.HOUR)) +
		// String.valueOf(c.get(Calendar.MINUTE));
		// String ymd = sFormat.format(now);
		// long search_time = getTime(timeperiod);
		String sql = "select * from ( select t2.name,temp.net_id,n.tenancy_point,temp.oper_id,temp.oper_name,temp.tran_datetime,temp.flag "
				+ "from (select n.sys_id,h.net_id as net_id,n.oper_id as oper_id,n.oper_name as oper_name,h.flag as flag,h.tran_datetime as tran_datetime from net_oper_view n,his_netoper_tbl h   where n.oper_id = h.oper_id ) temp,net_manage_tbl n,T_AREA t2 "
				+ "where   temp.sys_id=t2.ID and temp.NET_ID = n.net_id and temp.FLAG <> '3'  and temp.oper_id=? and temp.tran_datetime  >=  to_date(?,'yyyymmdd hh:mi:ss')  and temp.tran_datetime  <=   to_date(?,'yyyymmdd hh:mi:ss')   order by temp.oper_id asc, temp.tran_datetime asc ) where rownum <= ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, card_id);
			pstmt.setString(2, startTime);
			pstmt.setString(3, endTime);
			pstmt.setLong(4, rows);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				AttendenceBean attendenceBean = new AttendenceBean();
				attendenceBean.setNet_name(rslt.getString(1));
				attendenceBean.setNet_uid(rslt.getString(2));
				attendenceBean.setPos_id(rslt.getString(3));
				attendenceBean.setCar_id(rslt.getString(4));
				attendenceBean.setCar_id(rslt.getString(4));
				attendenceBean.setSign_time(rslt.getString(6));
				attendenceBeanList.add(attendenceBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}

		return attendenceBeanList;
	}

	private void close(ResultSet rslt, PreparedStatement pstmt, Connection conn) {
		try {
			if (rslt != null) {
				rslt.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CarStatusBean> getCarLastStatus(String[] car_ids, String startTime, String endTime,  long rows,long timeperiod) {
		Connection conn = ZxcConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<CarStatusBean> carStatusList = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		// c.setTime(now);
		// String hhmi = String.valueOf(c.get(Calendar.HOUR)) +
		// String.valueOf(c.get(Calendar.MINUTE));
		// String ymd = sFormat.format(now);
		// long search_time = getTime(timeperiod);
		String sql = "select * from (select t1.car_num,t1.car_flag,t1.rent_netid,t1.rent_instcard,t1.rent_operid,t1.return_netid,t1.return_instcard,t1.return_operid,t2.address as rent_addr,t3.address as return_addr, t1.rent_pire,t1.return_pire, t4.name as rent_area_name,t5.name as return_area_name,t1.rent_date ||t1.rent_time as rent_time,t1.return_date ||t1.return_time as return_time, t2.tenancy_point as rent_station,t3.tenancy_point as return_station"
				+ "from car_state_tbl  t1, net_manage_tbl t2,net_manage_tbl t3,t_area t4,t_area t5"
				+ "where t1.rent_netid = t2.net_id(+)and t1.return_netid = t3.net_id(+) and t2.area_id = t4.id(+) and t3.area_id = t5.id(+) and t1.CAR_NUM  in  (?) and t1.RENT_DATE||t1.RENT_TIME  >=to_date(?,'yyyymmdd hh:mi:ss') and t1.RENT_DATE||t1.RENT_TIME <= to_date(?,'yyyymmdd hh:mi:ss') ) where rownum <= ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, car_ids);
			pstmt.setString(2, startTime);
			pstmt.setString(3, endTime);
			pstmt.setLong(4, 100);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				CarStatusBean carStatusBean = new CarStatusBean();
				carStatusBean.setCar_id(rslt.getString(1));
				String s = rslt.getString(2);
				carStatusBean.setStatus(s);
				if("1".equals(s)||"15".equals(s)){
					carStatusBean.setTime(rslt.getString(15));
					carStatusBean.setCard_id(rslt.getString(5));
					carStatusBean.setNet_uid(rslt.getString(3));
					carStatusBean.setArea(rslt.getString(13));
					carStatusBean.setNet_address(rslt.getString(9));
					carStatusBean.setNet_name(rslt.getString(17));
					carStatusBean.setCar_num(rslt.getString(11));
				}else if ("2".equals(s)||"16".equals(s)){
					carStatusBean.setTime(rslt.getString(16));
					carStatusBean.setCard_id(rslt.getString(8));
					carStatusBean.setNet_uid(rslt.getString(6));
					carStatusBean.setArea(rslt.getString(14));
					carStatusBean.setNet_address(rslt.getString(10));
					carStatusBean.setNet_name(rslt.getString(18));
					carStatusBean.setCar_num(rslt.getString(12));
				}
				carStatusList.add(carStatusBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		return carStatusList;
	}

	@Override
	public List<BikeSxjBean> getBikeSxj(String car_id, String oper_exteid, String startTime, String endTime, long rows, long timeperiod) {
		Connection conn = ZxcConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<BikeSxjBean> sxjList = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		// c.setTime(now);
		// String hhmi = String.valueOf(c.get(Calendar.HOUR)) +
		// String.valueOf(c.get(Calendar.MINUTE));
		// String ymd = sFormat.format(now);
		// long search_time = getTime(timeperiod);
		String term1 = "and t1.OPER_EXTEID = ";
		String term2 =" and t1.car_num = ";
		String term = "";
		if (!"".equals(car_id) && null != car_id) {
			term = term + term2 + car_id;
		}
		if (!"".equals(oper_exteid) && null != oper_exteid) {
			term = term + term2 + oper_exteid;
		}
		String sql = "select * from ( select t1.net_id,t1.stall_num,t1.car_num,t1.oper_instid,t1.up_time,t1.enter_time,t1.oper_exteid,case when t1.OPER_TYPE='1' then '下架' when t1.OPER_TYPE='2' then '上架' when t1.OPER_TYPE='3' then '上架' else t1.OPER_TYPE end as oper_type,t2.tenancy_point,t3.name, t2.address "
				+ "from bike_sxj_tbl t1,net_manage_tbl t2,t_area t3 "
				+ "where t1.net_id =t2.net_id and t2.area_id=t3.id"
				+ term
				+ "and t1.UP_TIME  >=  ?  and t1.UP_TIME  <=  ? order by enter_time desc ) " + "where rownum <= ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startTime);
			pstmt.setString(2, endTime);
			pstmt.setLong(3, rows);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				BikeSxjBean bikeSxjBean = new BikeSxjBean();
				bikeSxjBean.setNet_uid(rslt.getString(1));
				bikeSxjBean.setArea(rslt.getString(9));
				bikeSxjBean.setCar_num(rslt.getString(2));
				bikeSxjBean.setCar_id(rslt.getString(3));
				bikeSxjBean.setCard_id(rslt.getString(6));
				bikeSxjBean.setTime(rslt.getString(5));
				bikeSxjBean.setType(rslt.getString(7));
				bikeSxjBean.setNet_name(rslt.getString(8));
				bikeSxjBean.setNet_address(rslt.getString(10));
				sxjList.add(bikeSxjBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}

		return sxjList;
	}
}
