package com.not.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {
		
		Scanner     sc = null;
		Connection con = null;
		Statement   st = null;
		
		try {
			//read inputs
			sc = new Scanner(System.in);
			String city = null;
			if(sc != null) {
				System.out.println("Enter student address (city name)::");
				city = sc.next(); //give hyd
			}
			city = "'"+city+"'"; // give 'hyd'
			
			//register JDBC driver by loading JDBC driver class
				//Calss.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","hadoop");
			
			//create Statement object
			if(con != null) {
				st = con.createStatement();
			}
			
			//prepate SQL query
				//DELETE FROM STUDENT WHERE SADD='delhi';
			String query = "DELETE FROM STUDENT WHERE SADD="+city;
			System.out.println(query);
			
			//send and execute SQL query in DB s/w
			int count = 0;
			if(st != null)
				count = st.executeUpdate(query);
			
			//process the result
			if(count == 0)
				System.out.println("No records found to delete");
			else
				System.out.println("No. of records affected ::"+count);
			
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
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally

	}//main
}//class