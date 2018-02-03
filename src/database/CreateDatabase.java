package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {
	 public static void main(String[] args){		
		Connection connection = null;
		Statement statement = null;
		try {			
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:tuin.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.			
			
			statement.executeUpdate("CREATE TABLE plant (plantnaam STRING, plaatjes STRING, omschrijving STRING)");	
			statement.executeUpdate("CREATE TABLE plaats (plantnaam STRING, coordinaatx INT, coordinaaty INT)");
			statement.executeUpdate("CREATE TABLE actie (plantnaam STRING, actie STRING, maand INT)");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());			
		} finally {			
			try { if (statement != null) statement.close(); } catch (Exception e) {};
			try { if (connection != null) connection.close(); } catch (Exception e) {};		
		}
	}
}
