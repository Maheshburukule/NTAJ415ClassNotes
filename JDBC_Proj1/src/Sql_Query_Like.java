import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//Sql_Query_like.java

public class Sql_Query_Like {

	public static void main(String[] args) {
		
		Scanner     sc = null;
		Connection con = null;
		Statement   st = null;
		ResultSet   rs = null;
		try {
			//read inputs
			sc = new Scanner(System.in);
			
			String initialChars = null;
			if(sc != null) {
				System.out.println("Enter initial characters of employee name ::");
				initialChars = sc.next(); //gives the first few characters of the employee name
			}
			//convert input values as required by the SQL query
			initialChars = "'"+initialChars+"%'"; //gives 's%'
			
			//register JDBC driver by loading JDBC driver class
				//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","hadoop");
			
			//create Statement object
			if(con != null) {
				st = con.createStatement();
			}
			
			//prepare SQL Query
			
			// SELECT EMPNO, ENAME, JOB, SAL FROM EMP WHERE ENAME LIKE 'M%'
			String query = "SELECT EMPNO, ENAME, JOB, SAL FROM EMP WHERE ENAME LIKE"+initialChars;
			System.out.println(query);
			
			//send and execute SQL query in DB s/w
			if(st != null) {
				rs = st.executeQuery(query);
			}	
			
			//process the ResultSet object
			if(rs != null) {
				boolean recordFound = false; //status of queried record found in the database
				while(rs.next()) {
					recordFound = true;
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				}//while
				
				if(recordFound == false) {
					System.out.println("Oops, No records found!!");
				}//if
				
			}//if
			
		}//try
		catch(SQLException se) {
			if(se.getErrorCode() >= 900 && se.getErrorCode() <= 999 ) {
				System.out.println("Invalid column names or table names or SQL keyword");
			}
			
			se.printStackTrace();
		}//catch
		catch (Exception e) {
			e.printStackTrace();
		}//catch
		finally {
			//close JDBC objects
			
			try {
				if(rs != null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(st != null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con != null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc != null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class