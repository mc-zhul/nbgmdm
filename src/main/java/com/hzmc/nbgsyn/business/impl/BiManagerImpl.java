package com.hzmc.nbgmdm.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hzmc.nbgmdm.business.BiManager;
import com.hzmc.nbgmdm.domain.persistence.PortBean;
import com.hzmc.nbgmdm.util.BiConnectionManager;

@Service
public class BiManagerImpl implements BiManager {

	
	private static SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat sFormat2 = new SimpleDateFormat("yyyyMMddHHmm");
	private static SimpleDateFormat sFormat3 = new SimpleDateFormat("HH:mm");
	
	@Override
	public List<PortBean> getPort(long pagesize, long index) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<PortBean> portList = new ArrayList<>();
		String sql = "select t.port_id,t.port_code,t.port_cname,t.port_ename,t.port_country_code,t.port_un_code,t.port_navigation_area,t.port_active_flag,t.port_cn_flag from md_location_port t where rownum >? and rownum <?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(2, index);
			pstmt.setLong(1, pagesize + index);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				PortBean portBean = new PortBean();
				portBean.setPort_id(rslt.getLong(1));
				portBean.setPort_code(rslt.getString(2));
				portBean.setPort_cname(rslt.getString(3));
				portBean.setPort_ename(rslt.getString(4));
				portBean.setPort_country_code(rslt.getString(5));
				portBean.setPort_un_code(rslt.getString(6));
				portBean.setPort_navigation_area(rslt.getString(7));
				portBean.setPort_active_flag(rslt.getString(8));
				portBean.setPort_cn_flag(rslt.getString(9));
				portList.add(portBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		return portList;
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
