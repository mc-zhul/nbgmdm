package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TeiidTest {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	/**
	 * @param args
	 * 
	 */
	/**
	 * @param args
	 */
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn=null;
//		Connection conn1=null;
		
		long t1 = 0;
		long t2 = 0;
		ResultSet rs;
		try {
		
		
      Class.forName("org.teiid.jdbc.TeiidDriver");
//    conn=DriverManager.getConnection("jdbc:jdbcdslog:oracle:thin:@172.16.6.118:1521:mandb;targetDriver=oracle.jdbc.OracleDriver","asset","hzmc321#");
//    conn=DriverManager.getConnection("jdbc:jdbcdslog:oracle:thin:@172.16.6.16:1578:capaa;targetDriver=oracle.jdbc.OracleDriver","asset", "hzmc321#");
    
   
    conn=DriverManager.getConnection("jdbc:teiid:datahis@mm://172.16.6.9:31000;","user","user");
    Statement st= conn.createStatement();
    t1 = System.currentTimeMillis();
   for(int i=0;i<100;i++){
  
//       System.out.println("------success1-----");
//      rs=st.executeQuery("select * from patientinfo where oid='000555555'");
//      rs=st.executeQuery("select * from feeentry  where oid='00213137'");
//      rs=st.executeQuery("select * from experimententry where oid='000555555'");
//       st.execute("update experimententry set abovalue='A型' where oid='000555555'");
       rs=st.executeQuery("select name,count(*) from patientinfo group by name having count(*)>1 order by count(*) desc ");
       


       
   }
   
   t2 = System.currentTimeMillis();
   long time = t2 - t1;
   System.out.println(time+"ms");
       
  
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 


		}
	

	}


