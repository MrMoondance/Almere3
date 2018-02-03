package data;
import java.util.ArrayList;

public class Plant {
	
public String naam;
public String plaatjes;
public String omschrijving;
public ArrayList<Action> acties;


	public Plant(String naam,String plaatjes, String omschrijving, ArrayList<Action> acties) {
		this.naam = naam;				
		this.plaatjes = plaatjes;
		this.omschrijving = omschrijving;		
		this.acties = acties;
	}
}
