package com.hzmc.nbgmdm.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hzmc.nbgmdm.business.AuthorityManager;
import com.hzmc.nbgmdm.domain.persistence.UserAuthorityBean;
import com.hzmc.nbgmdm.util.ConnectionManager;

@Repository
public class AuthorityManagerImpl implements AuthorityManager {

	@Override
	public List<String> getColumnById(long source_id, long user_id) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<String> columnList = new ArrayList<>();
		String sql = "select t.col_name from mc$user_authority t where t.source_id= ?and t.user_id= ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, source_id);
			pstmt.setLong(2, user_id);
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
	public List<UserAuthorityBean> getTime(){
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<UserAuthorityBean> list = new ArrayList<>();
		String sql = "select t.user_id,t.source_id,t.time_period from mc$time_authority t";
		try {
			pstmt = conn.prepareStatement(sql);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				UserAuthorityBean u = new UserAuthorityBean();
				u.setTime(rslt.getLong(3));
				u.setUser_id(rslt.getLong(1));
				u.setUser_id(rslt.getLong(2));
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		return list;
	}

	@Override
	public List<UserAuthorityBean> getUserColumns() {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<UserAuthorityBean> list = new ArrayList<>();
		String sql = "select t.col_name,t.user_id,t.source_id from mc$user_authority t order by t.user_id,t.source_id";
		try {
			pstmt = conn.prepareStatement(sql);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				UserAuthorityBean u = new UserAuthorityBean();
				u.setCol_name(rslt.getString(1));
				u.setUser_id(rslt.getLong(2));
				u.setUser_id(rslt.getLong(3));
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		return list;
	}
	
	

	@Override
	public List<String> getSource() {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<String> sList = new ArrayList<>();
		String sql = "select t.id from mc$source_param";
		try {
			pstmt = conn.prepareStatement(sql);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				String s = new String(rslt.getString(1));
				sList.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		return sList;
	}

	@Override
	public List<UserAuthorityBean> getAllColumns() {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<UserAuthorityBean> list = new ArrayList<>();
		String sql = "select t.source_id,t.col_name from mc$column t order by t.source_id";
		try {
			pstmt = conn.prepareStatement(sql);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				UserAuthorityBean u = new UserAuthorityBean();
				u.setCol_name(rslt.getString(2));
				u.setUser_id(rslt.getLong(1));
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		return list;
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
