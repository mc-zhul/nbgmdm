package com.hzmc.nbgmdm.business.impl;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hzmc.nbgmdm.business.GenerateManager;
import com.hzmc.nbgmdm.domain.persistence.SearchLog;
import com.hzmc.nbgmdm.util.ConnectionManager;

@Service
public class GenerateManagerImpl implements GenerateManager {

	@Override
	public long checkAppkey(String appKey, String secret, String grant_type) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		long id = 0;
		String status = "";
		String sql = "select t.id,t.status from mc$user t where t.grant_type =? and t.appkey=? and t.secret=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, grant_type);
			pstmt.setString(2, appKey);
			pstmt.setString(3, secret);
			rslt = pstmt.executeQuery();
			while (rslt.next()) {
				id = rslt.getLong(1);
				status = rslt.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		if ("0".equals(status)) {
			id = -999;
		}
		return id;
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
	public void insertLog(List<SearchLog> list) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "insert into mc$log(ID,USER_ID,OP_TIME,SOURCE_ID,COST_TIME,SEARCH_PERIOD,RESULT_STATUS,PARAMS,RESULT) values(seq_log.nextval,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			for (SearchLog log : list) {
				ps.setLong(1, log.getUser_id());
				ps.setTimestamp(2, new Timestamp(log.getOp_time().getTime()));
				ps.setLong(3, log.getSource_id());
				ps.setLong(4, log.getCost_time());
				ps.setLong(5, log.getSearch_period());
				ps.setString(6, log.getResult_status());
				ps.setString(7, log.getParams());
				try {
					String result = log.getResult();
					Blob blob = conn.createBlob();
					byte[] b = result.getBytes("GBK");
					ByteArrayInputStream stream = new ByteArrayInputStream(b);
					ps.setBlob(8, stream, b.length);
					stream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ps.addBatch();
			}
			ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}
	}

}
