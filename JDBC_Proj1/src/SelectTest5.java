import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//SelectTest5.java

public class SelectTest5 {

	public static void main(String[] args) {
		
		Connection   con = null;
		Statement     st = null;
		ResultSet	  rs = null;
		
		try {
			//Load JDBC Driver class
				//Class.forName("oracle.jdbc.OracleDriver");
			
			//Establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","hadoop");
			
			//create JDBC statement obj
			if(con != null) {
				st = con.createStatement();
			}
			
			//prepare SQL query to be executed
			//select count(*) from student;
			String query = "SELECT COUNT(*) FROM STUDENT";
			System.out.println(query);
			
			//send execute the SQL query
			if (st != null) {
				rs = st.executeQuery(query);
			}
			
			//process the ResultSet (1 record)
			if (rs != null) {
				rs.next();
				int count = rs.getInt("COUNT(*)");
				System.out.println("Number of records in the Student DB table is ::"+count);
			}//if
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close JDBC objs
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