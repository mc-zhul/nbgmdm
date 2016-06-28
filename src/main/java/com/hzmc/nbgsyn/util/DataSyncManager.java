package com.hzmc.nbgsyn.util;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hzmc.nbgsyn.domain.persistence.DataSync;

/**
 * 宁波港主数据服务注册和同步接口
 * 
 * @author fg
 * 
 */

public class DataSyncManager {

	private Jdbc jd;
	private Map<String, Object> invokeResult;// 接口返回对象

	public DataSyncManager() {
		Property property = new Property();
		String jdbcUrl = null;
		String user = null;
		String password = null;
		String driver = null;
		try {
			property.getJDBCProperties("system.properties");
			jdbcUrl = Property.jdbcUrl;
			user = Property.user;
			password = Property.password;
			driver = Property.driverClass;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.invokeResult = new HashMap<String, Object>(0);
		this.jd = new Jdbc(jdbcUrl, user, password, driver);
	}

	// private Jdbc jd=new
	// Jdbc("jdbc:oracle:thin:@172.16.4.118:1578/capaa","nbgmdm","nbgmdm","oracle.jdbc.driver.OracleDriver");

	/**
	 * 数据同步
	 * 
	 * @param dataSync
	 * @return
	 */
	public String dataSync(@RequestParam String jsonstr) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		try {
			DataSync pt = gson.fromJson(jsonstr, DataSync.class);
			if (pt == null) {
				return gson.toJson(failure("JSON数据有误，请重试。", null));
			}

			switch (pt.getType()) {
			case 'C':
				opInsert(pt);
				break;
			case 'U':
				opUpdate(pt);
				break;
			case 'D':
				opDelete(pt);
				break;
			case 'R':
				opRefer(pt);
				break;
			default:
				invokeResult = failure("操作类型不在CRUD范围中...", null);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			invokeResult = failure("服务器错误，操作失败！" + e.getMessage(), null);
		}

		return gson.toJson(invokeResult);
	}

	/**
	 * 添加
	 * 
	 * @param pt
	 * @return
	 */
	private Map<String, Object> opInsert(DataSync pt) {
		Map<String, Object> applyData = pt.getApplyData();
		if (applyData != null && applyData.size() > 0) {
			PreparedStatement pstmt = null;
			try {
				Map<String, String> colTypeMap = getColumnType(pt);
				String sql = "insert into " + pt.getRoot() + " (";
				Iterator<String> it = applyData.keySet().iterator();
				String[] keys = new String[applyData.size()];
				String temp = "";
				int len = 0;
				while (it.hasNext()) {
					keys[len] = it.next();
					sql += keys[len] + ",";
					temp += "?,";
					len++;
				}
				sql = sql.substring(0, sql.length() - 1) + ")VALUES (" + temp.substring(0, temp.length() - 1) + ")";
				// 获取数据库连接
				pstmt = jd.getPreparedStatement(sql);
				String coltype = "";
				for (int i = 1; i < applyData.size()+1; i++) {
					coltype = colTypeMap.get(keys[i-1].toUpperCase());
					pstmt = dealColumnType(pstmt, coltype, i, applyData.get(keys[i-1]));
				}
				pstmt.execute();
				invokeResult = success("数据添加成功！", null);
			} catch (Exception e) {
				e.printStackTrace();
				invokeResult = failure("服务器错误！" + e.getMessage(), null);
			} finally {
				try {
					pstmt.close();
				} catch (SQLException eq) {
					eq.printStackTrace();
					invokeResult = failure("服务器错误！" + eq.getMessage(), null);
				}
			}

		} else {
			invokeResult = failure("数据块为空！", null);
		}
		return invokeResult;
	}

	/**
	 * 修改
	 * 
	 * @param pt
	 * @return
	 */
	private Map<String, Object> opUpdate(DataSync pt) {
		Map<String, Object> applyData = pt.getApplyData();
		Map<String, Object> whereData = pt.getWhereData();
		if (applyData != null && applyData.size() > 0) {
			PreparedStatement pstmt = null;
			try {
				Map<String, String> colTypeMap = getColumnType(pt);
				String sql = "update " + pt.getRoot() + " set ";
				Iterator<String> it = applyData.keySet().iterator();
				String[] keys = new String[applyData.size() + whereData.size()];
				int len = 0;
				while (it.hasNext()) {
					keys[len] = it.next();
					sql += keys[len] + "=?,";
					len++;
				}
				sql = sql.substring(0, sql.length() - 1) + " where ";

				it = whereData.keySet().iterator();
				while (it.hasNext()) {
					keys[len] = it.next();
					sql += dealColumnType(colTypeMap.get(keys[len]), keys[len]);
					if (len < applyData.size() + whereData.size() - 1) {
						sql += " and ";
					}
					len++;
				}
				sql = sql.substring(0, sql.length() - 1);

				// 获取数据库连接
				pstmt = jd.getPreparedStatement(sql);
				String coltype = "";
				for (int i = 1; i < applyData.size() + whereData.size() + 1; i++) {
					coltype = colTypeMap.get(keys[i - 1].toUpperCase());
					pstmt = dealColumnType(pstmt, coltype, i, applyData.get(keys[i - 1]));
				}
				pstmt.executeUpdate();
				invokeResult = success("数据修改成功！", null);
			} catch (Exception e) {
				e.printStackTrace();
				invokeResult = failure("服务器错误！" + e.getMessage(), null);
			} finally {
				try {
					pstmt.close();
				} catch (SQLException eq) {
					eq.printStackTrace();
					invokeResult = failure("服务器错误！" + eq.getMessage(), null);
				}
			}

		} else {
			invokeResult = failure("数据块为空！", null);
		}
		return invokeResult;
	}

	/**
	 * 删除
	 * 
	 * @param pt
	 * @return
	 */
	private Map<String, Object> opDelete(DataSync pt) {
		Map<String, Object> applyData = pt.getApplyData();
		if (applyData != null && applyData.size() > 0) {
			PreparedStatement pstmt = null;
			try {
				Map<String, String> colTypeMap = getColumnType(pt);
				String sql = "delete from " + pt.getOperand() + " where ";
				Iterator<String> it = applyData.keySet().iterator();
				String[] keys = new String[applyData.size()];
				int len = 0;
				while (it.hasNext()) {
					keys[len] = it.next();
					sql += dealColumnType(colTypeMap.get(keys[len]), keys[len]);
					if (len < applyData.size() - 1) {
						sql += " and ";
					}
					len++;
				}
				sql = sql.substring(0, sql.length() - 1);
				// 获取数据库连接
				pstmt = jd.getPreparedStatement(sql);
				String coltype = "";
				for (int i = 1; i < applyData.size() + 1; i++) {
					coltype = colTypeMap.get(keys[i - 1].toUpperCase());
					pstmt = dealColumnType(pstmt, coltype, i, applyData.get(keys[i - 1]));
				}
				pstmt.executeUpdate();
				invokeResult = success("数据删除成功！", null);
			} catch (Exception e) {
				e.printStackTrace();
				invokeResult = failure("服务器错误！" + e.getMessage(), null);
			} finally {
				try {
					pstmt.close();
				} catch (SQLException eq) {
					eq.printStackTrace();
					invokeResult = failure("服务器错误！" + eq.getMessage(), null);
				}
			}

		} else {
			invokeResult = failure("数据块为空！", null);
		}
		return invokeResult;
	}

	/**
	 * 查询
	 * 
	 * @param pt
	 * @return
	 */
	private Map<String, Object> opRefer(DataSync pt) {
		Map<String, Object> applyData = pt.getApplyData();
		if (applyData != null && applyData.size() > 0) {
			PreparedStatement pstmt = null;
			try {
				Map<String, String> colTypeMap = getColumnType(pt);
				String sql = "select * from " + pt.getOperand() + " where ";
				Iterator<String> it = applyData.keySet().iterator();
				String[] keys = new String[applyData.size()];
				int len = 0;
				while (it.hasNext()) {
					keys[len] = it.next();
					sql += dealColumnType(colTypeMap.get(keys[len]), keys[len]);
					if (len < applyData.size() - 1) {
						sql += " and ";
					}
					len++;
				}
				sql = sql.substring(0, sql.length() - 1);
				// 获取数据库连接
				pstmt = jd.getPreparedStatement(sql);
				String coltype = "";
				for (int i = 1; i < applyData.size() + 1; i++) {
					coltype = colTypeMap.get(keys[i - 1].toUpperCase());
					if (coltype != null && !"".equals(coltype)) {
						pstmt = dealColumnType(pstmt, coltype, i, applyData.get(keys[i - 1]));
					} else {
						invokeResult = failure("参数不在操作表中！", null);
					}
				}
				ResultSet rs = pstmt.executeQuery();
				List<Map<String, Object>> list = jd.ResultSetToList(rs);

				invokeResult = success("数据查询成功！", list);

			} catch (Exception e) {
				e.printStackTrace();
				invokeResult = failure("服务器错误！" + e.getMessage(), null);
			} finally {
				try {
					pstmt.close();
				} catch (SQLException eq) {
					eq.printStackTrace();
					invokeResult = failure("服务器错误！" + eq.getMessage(), null);
				}
			}

		} else {
			invokeResult = failure("数据块为空！", null);
		}

		return invokeResult;
	}

	/**
	 * 获取表格列原始类型
	 * 
	 * @param pt
	 */
	private Map<String, String> getColumnType(DataSync pt) throws Exception {
		Map<String, String> cmap = new HashMap<String, String>();
		String sql = "SELECT * FROM user_tab_columns WHERE table_name ='" + pt.getRoot() + "'";
		ResultSet rs = jd.executeQuery(sql);
		while (rs.next()) {
			cmap.put(rs.getString("COLUMN_NAME").toUpperCase(), rs.getString("DATA_TYPE"));
		}
		return cmap;
	}

	/**
	 * 处理表格列类型
	 * 
	 * @param pstmt
	 * @param type
	 * @param len
	 * @param val
	 * @param isWhere
	 *            是否在where条件里面
	 */
	private PreparedStatement dealColumnType(PreparedStatement pstmt, String type, int len, Object val) throws Exception {
		if (type.contains("CHAR") || type.contains("RAW") || "LONG".equals(type))// LONG存可变长字符串最大可以存2GB
			pstmt.setString(len, String.valueOf(val)); 
		if ("BLOB".equals(type))
			pstmt.setBlob(len, (Blob) val);
		if ("CLOB".equals(type))
			pstmt.setClob(len, (Clob) val);
		if ("NCLOB".equals(type))
			pstmt.setNClob(len, (NClob) val);
		if (type.contains("FLOAT"))
			pstmt.setFloat(len, Float.valueOf(val + ""));
		if (type.contains("DOUBLE"))
			pstmt.setDouble(len, Double.valueOf(val + ""));
		if ("INT".equals(type) || "INTEGER".equals(type))
			pstmt.setInt(len, Integer.valueOf(val + ""));
		if ("SMALLINT".equals(type))
			pstmt.setShort(len, Short.valueOf(val + ""));
		if ("NUMBER".equals(type) || type.contains("DECIMAL"))// NUMBER可以存正数、负数、零、定点数、以及精度为38的浮点数
			pstmt.setBigDecimal(len, new BigDecimal(val + ""));
		if (type.contains("DATE"))
			pstmt.setString(len, " TO_DATE('" + val + "', 'yyyy-MM-dd HH24:MI:ss')");
		if (type.contains("TIMESTAMP"))
			pstmt.setString(len, " TO_TIMESTAMP('" + val + "', 'yyyy-MM-dd HH24:MI:ss.ff')");
		return pstmt;
	}

	private String dealColumnType(String type, String colname) throws Exception {
		String sql = colname + "=? ";
		if (("CHAR").equals(type) || ("NCHAR").equals(type))
			sql = "trim(" + colname + ") = trim(?) ";

		return sql;
	}

	/**
	 * 处理接口返回错误
	 * 
	 * @param desc
	 * @param result
	 * @return
	 */
	private Map<String, Object> failure(String desc, Object result) {
		invokeResult.put("msgId", "111");// 三位数字，000 表示成功其他则表示有错误
		invokeResult.put("msgDesc", desc);// 对返回成功或出错结果的具体描述
		invokeResult.put("result", result);// 结果集，可置空
		return invokeResult;
	}

	/**
	 * 处理接口返回成功
	 * 
	 * @param desc
	 * @param result
	 * @return
	 */
	private Map<String, Object> success(String desc, Object result) {
		invokeResult.put("msgId", "000");// 三位数字，000 表示成功其他则表示有错误
		invokeResult.put("msgDesc", desc);// 对返回成功或出错结果的具体描述
		invokeResult.put("result", result);// 结果集，可置空
		return invokeResult;
	}

	public static void main(String[] args) throws Exception {
		Map<String, Object> applyData = new HashMap<String, Object>(0);
		applyData.put("COL_CHAR", "a");

		DataSync data = new DataSync();
		data.setOperand("TEST2");
		data.setType('R');
		data.setApplyData(applyData);
		data.setToken("");

		Map<String,DataSync> testm=new HashMap<String,DataSync>();
		testm.put("jsonstr", data);
		Gson gs=new Gson();
		
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		String jsonstr = gson.toJson(data);
		System.out.println(jsonstr);

		Jdbc jd = new Jdbc("jdbc:oracle:thin:@172.16.4.118:1578/capaa", "address", "address", "oracle.jdbc.driver.OracleDriver");
		String sql2 = "select * from TEST2 where COL_VARCHAR2=?";// trim(COL_NCHAR)=trim(?)";
		// String sql2 =
		// "update TEST2 set COL_CHAR=? where trim(COL_NCHAR)=trim(?)";
		// String sql2="insert into TEST2(COL_NCHAR) values(?)";
		// String sql2="delete from TEST2 where trim(COL_NCHAR)=trim(?)";
		PreparedStatement pstmt = jd.getPreparedStatement(sql2);
		pstmt.setString(1, "aa1");
		// pstmt.setString(2, "c");
		// pstmt.executeUpdate();
		/**/
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.println("========" + rs.getString("COL_CHAR"));
		}
		pstmt.close();

	}

}
