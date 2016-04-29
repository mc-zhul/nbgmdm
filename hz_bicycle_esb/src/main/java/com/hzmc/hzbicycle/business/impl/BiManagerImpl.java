package com.hzmc.hzbicycle.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.hzmc.hzbicycle.business.BiManager;
import com.hzmc.hzbicycle.domain.persistence.NearbyStationsBean;
import com.hzmc.hzbicycle.domain.persistence.NotReturnBean;
import com.hzmc.hzbicycle.domain.persistence.RentTotalBean;
import com.hzmc.hzbicycle.domain.persistence.ReturnStationBean;
import com.hzmc.hzbicycle.domain.persistence.ReturnTotalBean;
import com.hzmc.hzbicycle.domain.persistence.StationBean;
import com.hzmc.hzbicycle.domain.persistence.StationDetailBean;
import com.hzmc.hzbicycle.domain.persistence.RentStationBean;
import com.hzmc.hzbicycle.util.BiConnectionManager;

public class BiManagerImpl implements BiManager {

	
	static SimpleDateFormat sFormat = new SimpleDateFormat("yyyymmdd");
	static SimpleDateFormat sFormat2 = new SimpleDateFormat("yyyymmdd hh:mi");
	
	@Override
	public List<StationBean> getStationAll(String area) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<StationBean> stationList = new ArrayList<>();
		String sql = "select t.net_uid,t.subarea_name2,t.subarea_name,t.net_name,t.net_address,t.bike_num,t.net_longitude,t.net_latitude from t_net_dm t where t.subarea_name =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, area);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				StationBean stationBean = new StationBean();
				stationBean.setNet_uid(rslt.getLong(1));
				stationBean.setSubarea(rslt.getString(2));
				stationBean.setArea(rslt.getString(3));
				stationBean.setNet_name(rslt.getString(4));
				stationBean.setNet_address(rslt.getString(5));
				stationBean.setBike_num(rslt.getLong(6));
				stationBean.setLongitud(rslt.getLong(7));
				stationBean.setLatitude(rslt.getLong(8));
				stationList.add(stationBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		return stationList;
	}

	@Override
	public StationDetailBean getStationDetail(long net_id, Date now,long timeperiod) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rslt = null;
		ResultSet rslt2 = null;
		StationDetailBean stationDetailBean = new StationDetailBean();
		String sql = "select t.net_uid,t.subarea_name2,t.subarea_name,t.net_name,t.net_address,t.bike_num,t.net_longitude,t.net_latitude from t_net_dm t where t.net_uid=?";
		//租还车
		String return_column = "RETURN_NUM";
		String rent_column = "RENT_NUM";
		String free_locks = "FREE_LOCKS";
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		String hhmi = String.valueOf(c.get(Calendar.HOUR)) + String.valueOf(c.get(Calendar.MINUTE));
		String ymd = sFormat.format(now);
		long search_time = getTime(timeperiod);
		if (search_time > 0) {
			return_column = "P_" + String.valueOf(search_time) + "_RENT";
			rent_column = "P_" + String.valueOf(search_time) + "_RENT";
			free_locks = "P_" + String.valueOf(search_time) + "_FREE_LOCKS";
		}
		String sql2 = "select t."+ rent_column +", t." +return_column+ ", t." + free_locks +" from t_return_ag t,t_minute_dm m where t.minute_uid=m.minute_uid and m.minute_name=? and t.date_uid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, net_id);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				stationDetailBean.setNet_uid(rslt.getLong(1));
				stationDetailBean.setSubarea(rslt.getString(2));
				stationDetailBean.setArea(rslt.getString(3));
				stationDetailBean.setNet_name(rslt.getString(4));
				stationDetailBean.setNet_address(rslt.getString(5));
				stationDetailBean.setBike_num(rslt.getLong(6));
				stationDetailBean.setLongitud(rslt.getLong(7));
				stationDetailBean.setLatitude(rslt.getLong(8));
				stationDetailBean.setLocks(rslt.getLong(6));
			}
			//按时间范围查询租还车量
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, hhmi);
			pstmt2.setString(2, ymd);
			rslt2 = pstmt2.executeQuery();
			while (rslt.next()) {
				stationDetailBean.setRent_num(rslt2.getLong(1));
				stationDetailBean.setRestore_num(rslt2.getLong(2));
				stationDetailBean.setFree_locks(rslt2.getLong(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		return stationDetailBean;
	}

	@Override
	public List<ReturnTotalBean> getReturnTotal(Date now, long timeperiod) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<ReturnTotalBean> returnTotalList = new ArrayList<>();
		String return_num = "RETURN_NUM";
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		String hhmi = String.valueOf(c.get(Calendar.HOUR)) + String.valueOf(c.get(Calendar.MINUTE));
		String ymd = sFormat.format(now);
		long search_time = getTime(timeperiod);
		if (search_time > 0) {
			return_num = "P_" + String.valueOf(search_time) + "_RETURN";
		}
		String sql = "select n.net_uid,n.subarea_name2,n.subarea_name,n.net_name,n.net_address,t."+return_num+" from t_return_ag t,t_minute_dm m ,t_net_dm nwhere t.minute_uid=m.minute_uid and m.minute_name=? and t.date_uid=? and t.net_uid=n.net_uid order by t."+return_num+" asc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hhmi);
			pstmt.setString(2, ymd);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				ReturnTotalBean returnTotalBean = new ReturnTotalBean();
				returnTotalBean.setNet_uid(rslt.getLong(1));
				returnTotalBean.setSubarea(rslt.getString(2));
				returnTotalBean.setArea(rslt.getString(3));
				returnTotalBean.setNet_name(rslt.getString(4));
				returnTotalBean.setNet_address(rslt.getString(5));
				returnTotalBean.setReturn_num(rslt.getLong(6));
				returnTotalList.add(returnTotalBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		
		return returnTotalList;
	}
	
	
	@Override
	public List<RentTotalBean> getRentTotal(Date now, long timeperiod) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<RentTotalBean> rentTotalList = new ArrayList<>();
		String return_num = "RENT_NUM";
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		String hhmi = String.valueOf(c.get(Calendar.HOUR)) + String.valueOf(c.get(Calendar.MINUTE));
		String ymd = sFormat.format(now);
		long search_time = getTime(timeperiod);
		if (search_time > 0) {
			return_num = "P_" + String.valueOf(search_time) + "_RENT";
		}
		String sql = "select n.net_uid,n.subarea_name2,n.subarea_name,n.net_name,n.net_address,t."+return_num+" from t_return_ag t,t_minute_dm m ,t_net_dm n where t.minute_uid=m.minute_uid and m.minute_name=? and t.date_uid=? and t.net_uid=n.net_uid order by t."+return_num+" asc";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hhmi);
			pstmt.setString(2, ymd);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				RentTotalBean rentTotalBean = new RentTotalBean();
				rentTotalBean.setNet_uid(rslt.getLong(1));
				rentTotalBean.setSubarea(rslt.getString(2));
				rentTotalBean.setArea(rslt.getString(3));
				rentTotalBean.setNet_name(rslt.getString(4));
				rentTotalBean.setNet_address(rslt.getString(5));
				rentTotalBean.setRent_num(rslt.getLong(6));
				rentTotalList.add(rentTotalBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		
		return rentTotalList;
	}
	
	
	@Override
	public List<NearbyStationsBean> getNearbyStations(long net_id, Date now, long timeperiod) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rslt = null;
		ResultSet rslt2 = null;
		List<NearbyStationsBean> nearbyStationsBeanList = new ArrayList<>();
		//get lon lat
		String sql = "select d.net_latitude,d.net_longitude from t_net_dm d where d.net_uid=?";
		long lat = 0;
		long lon = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, net_id);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				lat = rslt.getLong(1);
				lon = rslt.getLong(2);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		if (lat < 20 || lon < 100) {// 点不存在，就退出
			return nearbyStationsBeanList;
		}
		//get nearby net
		String free_locks = "FREE_LOCKS";
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		String hhmi = String.valueOf(c.get(Calendar.HOUR)) + String.valueOf(c.get(Calendar.MINUTE));
		String ymd = sFormat.format(now);
		long search_time = getTime(timeperiod);
		if (search_time > 0) {
			free_locks = "P_" + String.valueOf(search_time) + "_FREE_LOCKS";
		}
		String sql2="select t.net_uid,t.subarea_name2,t.subarea_name,t.net_name,t.net_address,t.bike_num,t.net_longitude,t.net_latitude,a."+free_locks+" from t_net_dm t,t_return_ag a,t_minute_dm m where a.minute_uid=m.minute_uid and a.net_uid=t.net_uid and m.minute_name =?  and a.date_uid=? and fun_getdistance(30.30901,120.38256,t.net_latitude,t.net_longitude) <2";
		try {
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, hhmi);
			pstmt2.setString(2, ymd);
			rslt = pstmt2.executeQuery();
			while (rslt.next()) {
				NearbyStationsBean nearbyStationsBean = new NearbyStationsBean();
				nearbyStationsBean.setNet_uid(rslt.getLong(1));
				nearbyStationsBean.setSubarea(rslt.getString(2));
				nearbyStationsBean.setArea(rslt.getString(3));
				nearbyStationsBean.setNet_name(rslt.getString(4));
				nearbyStationsBean.setNet_address(rslt.getString(5));
				nearbyStationsBean.setBike_num(rslt.getLong(6));
				nearbyStationsBean.setLongitud(rslt.getLong(7));
				nearbyStationsBean.setLatitude(rslt.getLong(8));
				nearbyStationsBean.setFree_locks(rslt.getLong(9));
				nearbyStationsBeanList.add(nearbyStationsBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		
		return nearbyStationsBeanList;
	}
	
	@Override
	public List<RentStationBean> getTopRentStation(Date now, long timeperiod) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<RentStationBean> rentList = new ArrayList<>();
		String rent_num = "RENT_NUM";
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		String hhmi = String.valueOf(c.get(Calendar.HOUR)) + String.valueOf(c.get(Calendar.MINUTE));
		String ymd = sFormat.format(now);
		long search_time = getTime(timeperiod);
		if (search_time > 0) {
			rent_num = "P_" + String.valueOf(search_time) + "_RENT";
		}
		String sql = "select s.*,rownum from (select n.net_uid,n.subarea_name2,n.subarea_name,n.net_name,n.net_address,n.net_longitude,n.net_latitude,t."+rent_num+" from t_return_ag t,t_minute_dm m ,t_net_dm n where t.minute_uid=m.minute_uid and m.minute_name=? and t.date_uid=? and t.net_uid=n.net_uid order by t."+rent_num+" asc ) s where rownum <21";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hhmi);
			pstmt.setString(2, ymd);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				RentStationBean rentStationBean = new RentStationBean();
				rentStationBean.setNet_uid(rslt.getLong(1));
				rentStationBean.setSubarea(rslt.getString(2));
				rentStationBean.setArea(rslt.getString(3));
				rentStationBean.setNet_name(rslt.getString(4));
				rentStationBean.setNet_address(rslt.getString(5));
				rentStationBean.setLongitud(rslt.getLong(6));
				rentStationBean.setLatitude(rslt.getLong(7));
				rentStationBean.setRent_num(rslt.getLong(8));
				rentStationBean.setRank(rslt.getLong(9));
				rentList.add(rentStationBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		
		return rentList;
	}

	@Override
	public List<RentStationBean> getBottoomRentStation(Date now, long timeperiod) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<RentStationBean> rentList = new ArrayList<>();
		String rent_num = "RENT_NUM";
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		String hhmi = String.valueOf(c.get(Calendar.HOUR)) + String.valueOf(c.get(Calendar.MINUTE));
		String ymd = sFormat.format(now);
		long search_time = getTime(timeperiod);
		if (search_time > 0) {
			rent_num = "P_" + String.valueOf(search_time) + "_RENT";
		}
		String sql = "select s.*,rownum from (select n.net_uid,n.subarea_name2,n.subarea_name,n.net_name,n.net_address,n.net_longitude,n.net_latitude,t."+rent_num+" from t_return_ag t,t_minute_dm m ,t_net_dm n where t.minute_uid=m.minute_uid and m.minute_name=? and t.date_uid=? and t.net_uid=n.net_uid order by t."+rent_num+" desc ) s where rownum <21";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hhmi);
			pstmt.setString(2, ymd);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				RentStationBean rentStationBean = new RentStationBean();
				rentStationBean.setNet_uid(rslt.getLong(1));
				rentStationBean.setSubarea(rslt.getString(2));
				rentStationBean.setArea(rslt.getString(3));
				rentStationBean.setNet_name(rslt.getString(4));
				rentStationBean.setNet_address(rslt.getString(5));
				rentStationBean.setLongitud(rslt.getLong(6));
				rentStationBean.setLatitude(rslt.getLong(7));
				rentStationBean.setRent_num(rslt.getLong(8));
				rentStationBean.setRank(rslt.getLong(9));
				rentList.add(rentStationBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		
		return rentList;
	}
	
	
	@Override
	public List<ReturnStationBean> getTopReturnStation(Date now, long timeperiod) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<ReturnStationBean> returnList = new ArrayList<>();
		String return_num = "RETURN_NUM";
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		String hhmi = String.valueOf(c.get(Calendar.HOUR)) + String.valueOf(c.get(Calendar.MINUTE));
		String ymd = sFormat.format(now);
		long search_time = getTime(timeperiod);
		if (search_time > 0) {
			return_num = "P_" + String.valueOf(search_time) + "_RETURN";
		}
		String sql = "select s.*,rownum from (select n.net_uid,n.subarea_name2,n.subarea_name,n.net_name,n.net_address,n.net_longitude,n.net_latitude,t."+return_num+" from t_return_ag t,t_minute_dm m ,t_net_dm n where t.minute_uid=m.minute_uid and m.minute_name=? and t.date_uid=? and t.net_uid=n.net_uid order by t."+return_num+" asc ) s where rownum <21";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hhmi);
			pstmt.setString(2, ymd);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				ReturnStationBean returnStationBean = new ReturnStationBean();
				returnStationBean.setNet_uid(rslt.getLong(1));
				returnStationBean.setSubarea(rslt.getString(2));
				returnStationBean.setArea(rslt.getString(3));
				returnStationBean.setNet_name(rslt.getString(4));
				returnStationBean.setNet_address(rslt.getString(5));
				returnStationBean.setLongitud(rslt.getLong(6));
				returnStationBean.setLatitude(rslt.getLong(7));
				returnStationBean.setReturn_num(rslt.getLong(8));
				returnStationBean.setRank(rslt.getLong(9));
				returnList.add(returnStationBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		
		return returnList;
	}

	@Override
	public List<ReturnStationBean> getBottoomReturnStation(Date now, long timeperiod) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<ReturnStationBean> returnList = new ArrayList<>();
		String return_num = "RETURN_NUM";
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		String hhmi = String.valueOf(c.get(Calendar.HOUR)) + String.valueOf(c.get(Calendar.MINUTE));
		String ymd = sFormat.format(now);
		long search_time = getTime(timeperiod);
		if (search_time > 0) {
			return_num = "P_" + String.valueOf(search_time) + "_RETURN";
		}
		String sql = "select s.*,rownum from (select n.net_uid,n.subarea_name2,n.subarea_name,n.net_name,n.net_address,n.net_longitude,n.net_latitude,t."+return_num+" from t_return_ag t,t_minute_dm m ,t_net_dm n where t.minute_uid=m.minute_uid and m.minute_name=? and t.date_uid=? and t.net_uid=n.net_uid order by t."+return_num+" desc ) s where rownum <21";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hhmi);
			pstmt.setString(2, ymd);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				ReturnStationBean returnStationBean = new ReturnStationBean();
				returnStationBean.setNet_uid(rslt.getLong(1));
				returnStationBean.setSubarea(rslt.getString(2));
				returnStationBean.setArea(rslt.getString(3));
				returnStationBean.setNet_name(rslt.getString(4));
				returnStationBean.setNet_address(rslt.getString(5));
				returnStationBean.setLongitud(rslt.getLong(6));
				returnStationBean.setLatitude(rslt.getLong(7));
				returnStationBean.setReturn_num(rslt.getLong(8));
				returnStationBean.setRank(rslt.getLong(9));
				returnList.add(returnStationBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		
		return returnList;
	}

	@Override
	public List<NotReturnBean> getNotReturn(Date now, long timeperiod) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<NotReturnBean> notReturnList = new ArrayList<>();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		String hhmi = String.valueOf(c.get(Calendar.HOUR)) + String.valueOf(c.get(Calendar.MINUTE));
		String ymd = sFormat.format(now);
		long search_time = getTime(timeperiod);
		String sql = "select t.car_num,t.rent_time from t_term_tran_ft t where (t.return_time - t.rent_time > 2 or t.return_dateuid is null) and round(to_number(to_date(?,'yyyymmdd hh:mi')  - t.return_time)*1440) < ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,sFormat.format(now));
			pstmt.setLong(2, search_time);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				NotReturnBean notReturnBean = new NotReturnBean();
				notReturnBean.setBike_id(rslt.getLong(1));
				notReturnBean.setRent_time(rslt.getDate(2));
				notReturnList.add(notReturnBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		
		return notReturnList;
	}
	
	/**
	 * 时间权限判断
	 * @param time
	 * @return
	 */
	private long getTime(long time){
		long return_time = 0;
		if(time<5){
			return_time = 0;
		} else if (time < 10 && 4 < time) {
			return_time = 5;
		} else if (9 < time && time < 30) {
			return_time = 10;
		} else if (29 < time && time < 60) {
			return_time = 30;
		} else if (59 < time && time < 90) {
			return_time = 60;
		} else if (89 < time && time < 120) {
			return_time = 90;
		} else if (119 < time) {
			return_time = 120;
		}
		return return_time;
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


	


}
