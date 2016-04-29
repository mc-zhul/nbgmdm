package com.hzmc.hzbicycle.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hzmc.hzbicycle.business.GenerateManager;
import com.hzmc.hzbicycle.util.ConnectionManager;

public class GenerateManagerImpl implements GenerateManager {

	@Override
	public boolean checkAppkey(String appKey, String secret, String grant_type) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		boolean b = false;
		int count = 0;
		String sql = "select count(*) from mc$user t where t.grant_type =? and t.appkey=? and t.secret=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, grant_type);
			pstmt.setString(2, appKey);
			pstmt.setString(3, secret);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				count = rslt.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		if (count > 0) {
			b = true;
		}
		return b;
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
