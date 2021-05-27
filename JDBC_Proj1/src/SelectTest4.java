//SelectTest4.java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class SelectTest4 {

	public static void main(String[] args) {
		Scanner     sc = null;
		Connection con = null;
		Statement   st = null;
		ResultSet   rs = null;
		try {
			//read inputs from keyboard
			sc = new Scanner(System.in);
			int dno = 0;
			if(sc != null) {
				System.out.println("Enter the Dept number ::");
				dno = sc.nextInt();
			}
			//load JDBC Driver
				//Class.forName("oracle.jdbc.driver.OracleDriver);
			
			//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","hadoop");
			//create JDBC Statement obj
			if(con != null) {
				st = con.createStatement();
			}
			//prepare SQL Statement "SELECT DEPTNO, DNAME, LOC FROM DEPT WHERE DEPTNO=40"
			String query = "SELECT DEPTNO, DNAME, LOC FROM DEPT WHERE DEPTNO="+dno;
			System.out.println(query);
				
			//execute the SQL query
				if(st != null) {
					rs = st.executeQuery(query);
				}
				
				//process the ResultSet (in this case it's 0 or 1 record)
				if(rs != null) {
					if (rs.next()) {
						System.out.println(rs.getInt(1)+"    "+rs.getString(2)+"    "+rs.getString(3));
					}
					else {
						System.out.println("Oops! no records found");
					}
				}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close JDBC Objects
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
			
			try {
				if(sc != null)
					sc.close();
			}//try
			catch(Exception se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class