package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.Action;
import data.Maand;
import data.Plaats;
import data.Plant;

public class ReadDatabase {

	public static void main(String[] args) {
		
		ArrayList<Plant> planten = getTable("plant");			
		System.out.println("plant");		
		for (Plant p : planten) {
			System.out.println("naam = " + p.naam);
			System.out.println("omschrijving = " + p.omschrijving);
			System.out.println("plaatjes = " + p.plaatjes);
		}
		
		ArrayList<Plaats> plaatsen = getTable("plaats");			
		System.out.println("plaats");		
		for (Plaats p : plaatsen) {
			System.out.println("plant = " + p.plant);
			System.out.println("coordinaatx = " + p.x);
			System.out.println("coordinaaty = " + p.y);
		}
		
		ArrayList<Action> acties = getTable("actie");			
		System.out.println("actie");		
		for (Action a : acties) {
			System.out.println("plant = " + a.plant);
			System.out.println("actie = " + a.actie);
			System.out.println("maand = " + a.maand);
		}
	}

	public static ArrayList getTable(String data) {
		
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;			
		
		try {
			Class.forName("org.sqlite.JDBC");
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:tuin.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);				
			
			if (data.equals("plant")) {			
				resultSet = statement.executeQuery("SELECT * from plant");				
				String SQL = "SELECT * from actie WHERE plantnaam = ?";
				PreparedStatement pstmt = connection.prepareStatement(SQL);				
				ArrayList<Plant> planten = new ArrayList<Plant>();			
				while (resultSet.next()) {				
					String naam = resultSet.getString("plantnaam");
					String plaatjes = resultSet.getString("plaatjes");
					String omschrijving = resultSet.getString("omschrijving");					
					
					pstmt.setString(1, naam);			
					pstmt.setQueryTimeout(30);	
					ResultSet actieResultSet = pstmt.executeQuery();					
					ArrayList<Action> acties = new ArrayList<Action>();	
					while (actieResultSet.next()) {										
						String actie = actieResultSet.getString("actie");
						int maand = actieResultSet.getInt("maand");						
						Action action = new Action(naam, actie, maand);
						acties.add(action);
					}
					Plant plant = new Plant(naam, plaatjes, omschrijving, acties);		
					planten.add(plant);
				}
				return planten;
			}
			
			
			if (data.equals("plaats")) {	
				resultSet = statement.executeQuery("SELECT * from plaats");
				ArrayList<Plaats> plaatsen = new ArrayList<Plaats>();
				while (resultSet.next()) {	
					String naam = resultSet.getString("plantnaam");
					int x = resultSet.getInt("coordinaatx");
					int y = resultSet.getInt("coordinaaty");
					Plaats plaats = new Plaats(naam, x, y);
					plaatsen.add(plaats); 				
				}
				return plaatsen;
			}
			
			if (data.equals("actie")) {			
				resultSet = statement.executeQuery("SELECT * from actie");
				ArrayList<Action> acties = new ArrayList<Action>();
				while (resultSet.next()) {	
					String naam = resultSet.getString("plantnaam");
					String actie = resultSet.getString("actie");
					int maand = resultSet.getInt("maand");
					Action action = new Action(naam, actie, maand);
					acties.add(action); 				
				}				
				return acties;
			}
			
			if (data.equals("maand")) {			
				String SQL = "SELECT * from actie WHERE maand = ?";
				PreparedStatement pstmt = connection.prepareStatement(SQL);					
				
				ArrayList<ArrayList<Action>> maanden = new ArrayList<ArrayList<Action>>();				
				for (int i = 0; i < 12; i++) {
					pstmt.setInt(1, i+1);
					pstmt.setQueryTimeout(30);	
					resultSet = pstmt.executeQuery();	
					ArrayList<Action> maandActions = new ArrayList<Action>();
					while (resultSet.next()) {	
						String naam = resultSet.getString("plantnaam");
						String actie = resultSet.getString("actie");
						int maand = resultSet.getInt("maand");	
						Action action = new Action(naam, actie, maand);
						maandActions.add(action);
					}
					maanden.add(maandActions);
				}
				return maanden;	
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {			
			System.err.println(e.getMessage());
		} finally {
			try { if (resultSet != null) resultSet.close(); } catch (Exception e) {};
			try { if (statement != null) statement.close(); } catch (Exception e) {};
			try { if (connection != null) connection.close(); } catch (Exception e) {};
		}
		return null;
	}	
}
