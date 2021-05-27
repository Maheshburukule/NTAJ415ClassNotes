import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//SelectTest5WithMySQL.java

public class SelectTest5WithMySQL {

	public static void main(String[] args) {
		
		Connection  con = null;
		Statement    st = null;
		ResultSet    rs = null;
		
		try {
			//Load JDBC driver class
				//Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establish the connection
			//con = DriverManager.getConnection("jdbc:mysql:///MAHESHDB","root","MySQL@12345");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MAHESHDB","root","MySQL@12345");
			
			//create JDBC Statement
			if(con != null) {
				st = con.createStatement();
			}
			
			//Prepare SQL query
				//SELECT COUNT(*) FROM STUDENT
			String query = "SELECT COUNT(*) FROM STUDENT";
			System.out.println(query);
			
			//Execute the query (1 record)
			if(st != null) {
				rs = st.executeQuery(query);
			}
			
			//process the ResultSet (1 record)
			if(rs != null) {
				rs.next();
				int count = rs.getInt("COUNT(*)");   //int count = rs.getInt(1);
				System.out.println("Number of records found in Student DB Table is ::"+count);
			}//if
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
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