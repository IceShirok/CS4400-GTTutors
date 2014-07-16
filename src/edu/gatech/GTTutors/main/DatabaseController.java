package edu.gatech.GTTutors.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {

    public static final String DB_URL = "jdbc:mysql://academic-mysql.cc.gatech.edu/";
    public static final String GROUP = "cs4400_Group_30";
    public static final String PW = "ArdHSY4u";
    public static final String[] USER_TYPES = {"Student", "Tutor", "Professor", "Administrator"};
		
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
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch(SQLException e) {}
			}
		}
		return null;
	}
}
