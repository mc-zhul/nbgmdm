package com.hzmc.nbgsyn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class Connect {
	private static Connection conn = null;
	private static final Logger LOG = Logger.getLogger(Connect.class);

	public Connection getConn(String db, String user, String password) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(db, user, password);
		} catch (SQLException ex) {
			ex.printStackTrace();
			LOG.error("获取连接异常");
		} catch (ClassNotFoundException ex) {
			LOG.error("加载驱动出错");
			ex.printStackTrace();
		}
		return conn;
	}

	public boolean check(Connection conn, String user, String psw) {
		Statement pstmt = null;
		ResultSet res = null;
		String sql = "select * from WS$user where username = " + user
				+ "and password=" + psw;
		String a = "";
		boolean b = false;
		try {
			pstmt = conn.createStatement();
			res = pstmt.executeQuery(sql);
			while (res.next()) {
				a = res.getString(1);
				if (null != a && !"".equals(a)) {
					b = true;
				}
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return b;
	}
}
