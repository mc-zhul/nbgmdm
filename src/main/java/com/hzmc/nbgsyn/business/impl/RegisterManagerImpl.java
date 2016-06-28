package com.hzmc.nbgsyn.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hzmc.nbgsyn.business.RegisterManager;
import com.hzmc.nbgsyn.domain.persistence.RegisterBean;
import com.hzmc.nbgsyn.util.BiConnectionManager;

@Service
public class RegisterManagerImpl implements RegisterManager {


//	@Override
//	public List<PortBean> getPort(long pagesize, long index) {
//		Connection conn = BiConnectionManager.getInstance().getConnection();
//		PreparedStatement pstmt = null;
//		ResultSet rslt = null;
//		List<PortBean> portList = new ArrayList<>();
//		String sql = "select t.port_id,t.port_code,t.port_cname,t.port_ename,t.port_country_code,t.port_un_code,t.port_navigation_area,t.port_active_flag,t.port_cn_flag from md_location_port t where rownum >? and rownum <?";
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setLong(2, index);
//			pstmt.setLong(1, pagesize + index);
//			rslt = pstmt.executeQuery();
//			while (rslt.next()) {
//				PortBean portBean = new PortBean();
//				portBean.setPort_id(rslt.getLong(1));
//				portBean.setPort_code(rslt.getString(2));
//				portBean.setPort_cname(rslt.getString(3));
//				portBean.setPort_ename(rslt.getString(4));
//				portBean.setPort_country_code(rslt.getString(5));
//				portBean.setPort_un_code(rslt.getString(6));
//				portBean.setPort_navigation_area(rslt.getString(7));
//				portBean.setPort_active_flag(rslt.getString(8));
//				portBean.setPort_cn_flag(rslt.getString(9));
//				portList.add(portBean);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			close(rslt, pstmt, conn);
//		}
//		return portList;
//	}

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
	public JSONObject registerC(RegisterBean registerBean) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		boolean isOK = false;
		JSONObject jo = new JSONObject();
		String sql = "insert into map_service_register(id, md_code,sys_code,interface_code,service_url,username,password,register_time) "
				+ "values(seq_map_service_register.nextval,?,?,?,?,?,?,sysdate)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, registerBean.getMd_code());
			pstmt.setString(2, registerBean.getSys_code());
			pstmt.setString(3, registerBean.getInterface_code());
			pstmt.setString(4, registerBean.getService_url());
			pstmt.setString(5, registerBean.getUsername());
			pstmt.setString(6, registerBean.getPassword());
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
			jo.accumulate("msgId", "001");
			jo.accumulate("msgDESC", "注册失败");
			jo.accumulate("result", "");
		}
		return jo;
	}

	@Override
	public JSONObject registerU(RegisterBean registerBean) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		boolean isOK = false;
		JSONObject jo = new JSONObject();
		String sql = "update map_service_register(service_url,username,password) "
				+ "values(?,?,?) where md_code=? and sys_code=? and interface_code=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, registerBean.getService_url());
			pstmt.setString(2, registerBean.getUsername());
			pstmt.setString(3, registerBean.getPassword());
			pstmt.setString(4, registerBean.getMd_code());
			pstmt.setString(5, registerBean.getSys_code());
			pstmt.setString(6, registerBean.getInterface_code());
			pstmt.execute(sql);
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

	@Override
	public JSONObject registerD(RegisterBean registerBean) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		boolean isOK = false;
		JSONObject jo = new JSONObject();
		String sql = "delete map_service_register where md_code=? and sys_code=? and interface_code=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, registerBean.getMd_code());
			pstmt.setString(2, registerBean.getSys_code());
			pstmt.setString(3, registerBean.getInterface_code());
			pstmt.execute(sql);
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
			jo.accumulate("msgId", "003");
			jo.accumulate("msgDESC", "删除失败");
			jo.accumulate("result", "");
		}
		return jo;
	}

	@Override
	public List<RegisterBean> registerList(RegisterBean registerBean) {
		Connection conn = BiConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		List<RegisterBean> rbList = new ArrayList<RegisterBean>();
		String sql = "select service_url,username,password from map_service_register "
				+ "where  interface_code=?";
				//md_code=? and sys_code=? and
		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, registerBean.getMd_code());
//			pstmt.setString(2, registerBean.getSys_code());
			pstmt.setString(1, registerBean.getInterface_code());
			rslt = pstmt.executeQuery(sql);
			while(rslt.next()){
				RegisterBean rb = new RegisterBean();
				rb.setService_url(rslt.getString(1));
				rb.setUsername(rslt.getString(2));
				rb.setPassword(rslt.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(rslt, pstmt, conn);
		}
		return rbList;
	}
}
