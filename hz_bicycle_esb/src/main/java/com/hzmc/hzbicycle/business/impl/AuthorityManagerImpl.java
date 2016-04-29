package com.hzmc.hzbicycle.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzmc.hzbicycle.business.AuthorityManager;
import com.hzmc.hzbicycle.util.BiConnectionManager;

public class AuthorityManagerImpl implements AuthorityManager {

	@Override
	public List<String> getColumnById(long source_id, long user_id) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<String> columnList = new ArrayList<>();
		String sql = "select t.col_name from mc$user_authority t where t.source_id= ?and t.user_id= ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, source_id);
			pstmt.setLong(1, user_id);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				String s = new String(rslt.getString(1));
				columnList.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		return columnList;
	}

	@Override
	public long getTimeById(long source_id, long user_id) {
		
		return 0;
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
