package edu.gatech.GTTutors.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {

	private static Connection con;
    public final static String DB_URL = "localhost:5775/campania_status";
	
	public DatabaseController() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = (Connection) DriverManager.getConnection("jdbc:mysql://" + DB_URL);
			if(con.isClosed()) {
				throw new IllegalStateException("Failed to Connect");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null)
					con.close();
			} catch(SQLException e){}
		}
	}
	
	public static ResultSet sendQuery(String query) {
		if(con == null) return null;
		
		try {
			Statement s = con.createStatement();
			return s.executeQuery(query);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
