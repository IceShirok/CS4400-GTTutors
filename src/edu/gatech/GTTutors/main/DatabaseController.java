package edu.gatech.GTTutors.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseController {

    private static final String DB_URL = "jdbc:mysql://academic-mysql.cc.gatech.edu/";
    private static final String GROUP = "cs4400_Group_30";
    private static final String PW = "ArdHSY4u";
		
	public DatabaseController() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Connection getConnection() {
		Connection con = null;
		try {
			con = (Connection) DriverManager.getConnection(DB_URL + GROUP, GROUP, PW);
			if(con.isClosed()) {
				throw new IllegalStateException("Failed to Connect");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static ResultSet sendQuery(String query) {
		Connection con = getConnection();
		if(con == null){
			System.out.println("ASDFALSDKJFASLDFJ");
			return null;
		}
		
		try {
			Statement s = con.createStatement();
			return s.executeQuery(query);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
