package com.hzmc.nbgsyn.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

/**
 * 数据连接
 * 
 * @author fg
 */
public class Jdbc {

	private Connection con = null;
	private Statement st;

	private String dbUrl;

	private String user;

	private String password;

	private static final Logger log = Logger.getLogger(Jdbc.class);

	/**
	 * 获取连接
	 * 
	 * @param dbUrl
	 * @param user
	 * @param password
	 * @param driver
	 */
	public Jdbc(String dbUrl, String user, String password, String driver) {
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(dbUrl, user, password);

			this.dbUrl = dbUrl;
			this.user = user;
			this.password = password;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("jdbc:" + e);
		}
	}

	// 重连
	private void reConnect() {
		// close();
		try {
			con = DriverManager.getConnection(this.dbUrl, this.user, this.password);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("jdbc:" + e);
		}
	}

	private void getStatement() {
		if (st != null) {
			try {
				st.close();
				st = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PreparedStatement getPreparedStatement(String sql) throws SQLException {
		return con.prepareStatement(sql);
	}

	public PreparedStatement getPreparedStatementLarge(String sql) throws SQLException {
		return con.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
	}

	/**
	 * 执行update
	 * 
	 * @param sql
	 * @return 是否执行
	 * @throws SQLException
	 */
	public boolean executeUpdate(String sql) throws SQLException {
		getStatement();
		try {
			st.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("executeUpdate:" + e);
			return false;
		}
	}

	/**
	 * @param sql
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet executeQuery(String sql) {
		long start = System.currentTimeMillis();
		ResultSet rs = null;
		getStatement();
		try {
			st.setQueryTimeout(5);// 设置5秒超时
			rs = st.executeQuery(sql);
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("============e.getMessage():" + e.getMessage());
			if (e.getMessage() != null && e.getMessage().indexOf("Software caused connection abort: recv failed") > 0) {
				log.info("executeQuery:reConnect");
				reConnect();
				return executeQuery(sql);
			}
			log.error("executeQuery:" + e);

			log.error(sql);
		}
		// DebugOut.print("execute query:"+sql+" use time:"+(System.currentTimeMillis()-start));
		return rs;
	}

	/**
	 * 转ResultSet 为 map的list
	 * 
	 * @param rs
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> ResultSetToList(ResultSet rs) {

		try {

			ResultSetMetaData md = rs.getMetaData(); // 获得结果集结构信息（字段数、字段名等）

			int columnCount = md.getColumnCount();

			List list = new ArrayList();

			while (rs.next()) {

				Map<String, Object> rowData = new HashMap<String, Object>();

				for (int i = 1; i <= columnCount; i++) {

					rowData.put(md.getColumnName(i), rs.getObject(i));

				}

				list.add(rowData);

			}

			return list;

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return null;
	}

	/**
	 * 转ResultSet 为 Object的list
	 * 
	 * @param rs
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("unchecked")
	public List ResultSetToList(ResultSet rs, Class classname) {

		try {

			ResultSetMetaData md = rs.getMetaData(); // 获得结果集结构信息（字段数、字段名等）

			int columnCount = md.getColumnCount();

			List list = new ArrayList();

			while (rs.next()) {
				Object obj = classname.newInstance();
				if (obj instanceof String) {
					obj = rs.getObject(1);
				} else {

					// Map rowData = new HashMap();

					for (int i = 1; i <= columnCount; i++) {

						// rowData.put(md.getColumnName(i), rs.getObject(i));
						BeanUtils.copyProperty(obj, ("" + md.getColumnName(i)).toLowerCase(), rs.getObject(i));
					}
					// obj=getJdbcObject(rowData,obj);
				}

				list.add(obj);

			}

			return list;

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 转值map为真实对象
	 * 
	 * @param hashmap
	 * @param obj
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Object getJdbcObject(Map hashmap, Object obj) throws InstantiationException, IllegalAccessException {
		Iterator iterator = hashmap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			Object value = hashmap.get(key);
			try {
				BeanUtils.copyProperty(obj, key.toLowerCase(), value);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	/**
	 * @param sql
	 * @return ResultSet
	 * @throws SQLException
	 */
	public boolean execute(String sql) throws SQLException {
		try {
			getStatement();
			st.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("execute:" + e);
			return false;
		}
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		try {
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 批量执行
	 * 
	 * @param sqls
	 * @throws SQLException
	 */
	public void executeBatch(List<String> sqls) throws SQLException {
		getStatement();
		final int batchSize = 1000;
		int count = 0;
		for (String sql : sqls) {
			st.addBatch(sql);
			if (++count % batchSize == 0) {
				st.executeBatch();
			}
		}
		st.executeBatch(); // insert remaining records
	}

	public static void main(String[] args) throws Exception {

		// Jdbc jd=new Jdbc("jdbc:oracle:thin:@172.16.4.118:1578/capaa",
		// "address", "address","oracle.jdbc.driver.OracleDriver");
		// org.apache.derby.jdbc.EmbeddedDriver
		// Jdbc jd=new Jdbc("jdbc:derby:mydbs/capaa", null,
		// null,"org.apache.derby.jdbc.ClientDriver");
		Jdbc jd = new Jdbc("jdbc:derby:memory:address;restoreFrom=E:\\db-derby-10.11.1.1-bin\\backup\\capaa", null, null,
				"org.apache.derby.jdbc.EmbeddedDriver");

		long start = System.currentTimeMillis();
		ResultSet rs = jd.executeQuery("select * from mc.address_taizhou_new t where name='临海市' or name||keyword='临海市'");
		System.out.println("use time" + (System.currentTimeMillis() - start));

		while (rs.next()) {
			System.out.println("test");
		}

	}
}
