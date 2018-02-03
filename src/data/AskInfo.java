package data;

import java.util.ArrayList;

import database.ReadDatabase;

public class AskInfo {
	
	static ArrayList<Plant> missNaam = new ArrayList<Plant>();
	static ArrayList<Plant> missOmschr = new ArrayList<Plant>();
	static ArrayList<Plant> missPlaatjes = new ArrayList<Plant>();	
	static int naam = 0;
	static int omschrijving = 0;
	static int plaatjes = 0;		
	static ArrayList<Plaats> missXY = new ArrayList<Plaats>();
	static int xy = 0;
	static ArrayList<Action> missActie = new ArrayList<Action>();
	static ArrayList<Action> missMaand = new ArrayList<Action>();
	static int actie = 0;
	static int maand = 0;
	
	public static void main(String[] args) {
		
		ArrayList<Plant> planten = ReadDatabase.getTable("plant");		
		for (Plant plant : planten) {			
			if (plant.naam == null ) {				
				naam++;
				missNaam.add(plant);
			}
			if (plant.omschrijving == null ) {
				omschrijving++;
				missOmschr.add(plant);
			}
			if (plant.plaatjes == null ) {
				plaatjes++;
				missPlaatjes.add(plant);
			}			
		}			
		
		ArrayList<Plaats> plaatsen = ReadDatabase.getTable("plaats");	
		for (Plaats plaats : plaatsen) {	
			if (plaats.x == 999) {
				xy++;
				missXY.add(plaats);
			} else if (plaats.y == 999) {
				xy++;
				missXY.add(plaats);
			}		
		}		
		
		ArrayList<Action> acties = ReadDatabase.getTable("actie");
		for (Action action : acties) {
			if (action.actie == null) {
				actie++;
				missActie.add(action);
			}
			if (action.maand == 999) {
				maand++;
				missMaand.add(action);
			}			
		}
	}
}
