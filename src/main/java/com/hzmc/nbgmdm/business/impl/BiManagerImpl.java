package com.hzmc.nbgmdm.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hzmc.nbgmdm.business.BiManager;
import com.hzmc.nbgmdm.domain.persistence.StationBean;
import com.hzmc.nbgmdm.util.BiConnectionManager;

@Service
public class BiManagerImpl implements BiManager {

	
	static SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
	static SimpleDateFormat sFormat2 = new SimpleDateFormat("yyyyMMddHHmm");
	static SimpleDateFormat sFormat3 = new SimpleDateFormat("HH:mm");
	
	@Override
	public List<StationBean> getStationAll(String area,long rows) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<StationBean> stationList = new ArrayList<>();
		String sql = "select t.net_uid,t.subarea_name2,t.subarea_name,t.net_name,t.net_address,t.bike_num,t.net_longitude,t.net_latitude from t_net_dm t where t.subarea_name =? and rownum <?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, area);
			pstmt.setLong(2, rows);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				StationBean stationBean = new StationBean();
				stationBean.setNet_uid(rslt.getLong(1));
				stationBean.setSubarea(rslt.getString(2));
				stationBean.setArea(rslt.getString(3));
				stationBean.setNet_name(rslt.getString(4));
				stationBean.setNet_address(rslt.getString(5));
				stationBean.setBike_num(rslt.getLong(6));
				stationBean.setLongitud(rslt.getString(7));
				stationBean.setLatitude(rslt.getString(8));
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
