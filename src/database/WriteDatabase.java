package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class WriteDatabase {
	public static void main(String[] args) {		
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {	
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:tuin.db");			
			String SQL = "INSERT INTO plant values(?,?,?)";
			pstmt = connection.prepareStatement(SQL);
			pstmt.setQueryTimeout(30);
			ArrayList<String> plantenlijst = getPlantenlijst();
			for(String plant : plantenlijst){
				System.out.println(plant);
				pstmt.setString(1, plant);
				pstmt.setString(2, null);
				pstmt.setString(3, null);
				pstmt.executeUpdate();
			}
			pstmt.close();
			
			SQL = "INSERT INTO plaats values(?,?,?)";
			pstmt = connection.prepareStatement(SQL);
			pstmt.setQueryTimeout(30);
			
			for(int i = 0; i<15; i++) {
				pstmt.setString(1, plantenlijst.get(i));
				pstmt.setInt(2, i);
				pstmt.setInt(3, i);
				pstmt.executeUpdate();
			}
			pstmt.close();
			
			SQL = "INSERT INTO actie values(?,?,?)";
			pstmt = connection.prepareStatement(SQL);
			pstmt.setQueryTimeout(30);
			
			for(int i = 0; i<15; i++) {
				pstmt.setString(1,  plantenlijst.get(i));
				pstmt.setString(2, i + "de");
				pstmt.setInt(3, i);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} finally {			
			try { if (pstmt != null) pstmt.close(); } catch (Exception e) {};
			try { if (connection != null) connection.close(); } catch (Exception e) {};
		}		
	}

	private static ArrayList<String> getPlantenlijst() {
		
		ArrayList<String> plantenlijst = new ArrayList<String>();
		
		int rows = 1000; // No of rows	    	    
	    for(int r = 2; r < rows; r++) {
	    	
	    	if (readExcel(r,0) == null) {
	    		break;	    		
	    	}
	    	String plantnaam = readExcel(r,0);	  	
		    	    	
		    if (plantnaam != null) {
		    		
		    	plantenlijst.add(plantnaam);
		    }
		    	
	    	
	    }
	    return plantenlijst;
	}

	private static String readExcel(int r, int c) {

		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("Voorstel plantenlijst.xls"));
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;

			row = sheet.getRow(r);
			if (row != null) {
				if (row.getCell(c) != null) {					
					return row.getCell(c).toString();
				}
			}			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
