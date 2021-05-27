import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//SelectTest6.java

/*
 * Assignement4:: find empno, ename, job and sal from emp table where employee salary is minimum
 * SQL query for this is as below
 * select empno, ename, job, sal from emp where sal=(select min(sal) from emp);
 */

public class Assignment4 {

	public static void main(String[] args) {
		
		Connection   con = null;
		Statement     st = null;
		ResultSet     rs = null;
		try {
			//Load JDBC driver class
				//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the JDBC Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "hadoop");
			//create JDBC object
			if(con != null) {
				st = con.createStatement();
			}
			
			//prepare SQL query
				//select empno, ename, job, sal from emp where sal=(select min(sal) from emp);
			String query = "SELECT EMPNO, ENAME, JOB, SAL FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP)";
			System.out.println(query);
			
			//send execute the SQL query (0 or more records)
			if(st != null) {
				rs = st.executeQuery(query);
			}
			
			//process the ResultSet
			if(rs != null) {
				boolean recordFound = false;
				while(rs.next()) {
					recordFound = true;
					System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"    "+rs.getString(3)+"    "+rs.getFloat(4));
				}//while
				
				if(recordFound == false) {
					System.out.println("Opps! Records not found");
				}
			}//if
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.fillInStackTrace();
		}
		finally {
			//close JDBC Objs
			try {
				if(rs != null)
					rs.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(st != null)
					st.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con != null)
					con.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}
			
		}//finally

	}//main

}//class