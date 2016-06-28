package com.hzmc.nbgsyn.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hzmc.nbgsyn.business.DbManager;
import com.hzmc.nbgsyn.util.BiConnectionManager;
import com.hzmc.nbgsyn.util.DataSyncManager;

@Service
public class DbManagerImpl implements DbManager {


	public JSONObject dataUpdate(String table, JSONObject data){
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		boolean isOK = false;
		JSONObject jo = new JSONObject();
		JSONObject data1 = JSONObject.fromObject(data.getString("applyData"));
		String sql = "update " + table + " set COUNTRY_CODE = ? where RD_COUNTRYId = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data1.get("COUNTRY_CODE").toString());
			pstmt.setString(2, data1.get("RD_COUNTRYId").toString());
			pstmt.execute();
			isOK = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		if (isOK) {
			jo.accumulate("msgId", "000");
			jo.accumulate("msgDESC", "操作成功");
			jo.accumulate("result", "");
		} else {
			jo.accumulate("msgId", "002");
			jo.accumulate("msgDESC", "修改失败");
			jo.accumulate("result", "");
		}
		return jo;
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
	public JSONObject dataCreate(String table, JSONObject data) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		boolean isOK = false;
		JSONObject jo = new JSONObject();
		JSONObject data1 = JSONObject.fromObject(data.getString("applyData"));
		String sql = "insert into " + table + "(RD_COUNTRYId,COUNTRY_CODE) values(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, data1.get("COUNTRY_CODE").toString());
			pstmt.setString(1, data1.get("RD_COUNTRYId").toString());
			pstmt.execute();
			isOK = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		if (isOK) {
			jo.accumulate("msgId", "000");
			jo.accumulate("msgDESC", "操作成功");
			jo.accumulate("result", "");
		} else {
			jo.accumulate("msgId", "002");
			jo.accumulate("msgDESC", "新增失败");
			jo.accumulate("result", "");
		}
		return jo;
	}


	@Override
	public JSONObject dataSyn(String str) {
		DataSyncManager dsm = new DataSyncManager();
		String rt = dsm.dataSync(str);
		
		return JSONObject.fromObject(rt);
	}


}
