package data;

import java.util.ArrayList;

import javax.swing.JCheckBox;

public class Maand {
	public int i;
	public String naam;
	public ArrayList<Action> actionList;
	public JCheckBox cb;

	public Maand(int i, String naam, JCheckBox cb, ArrayList<Action> actionList) {
		this.i = i;
		this.naam = naam;
		this.cb = cb;
		this.actionList = actionList;		
	}	
}
