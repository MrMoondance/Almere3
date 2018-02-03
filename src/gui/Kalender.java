package gui;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data.Plant;
import database.ReadDatabase;
import data.Action;
import data.Maand;

public class Kalender {

	JPanel kalender;
	JPanel maandenCheck;
	JPanel lijst;
	
	
	Maand[] maand = new Maand[12];

	public Kalender(final JFrame main) {

		kalender = new JPanel();
		kalender.setBounds(10, 62, 1107, 781);		
		kalender.setLayout(null);
		main.getContentPane().add(kalender);
		maandenCheck = new JPanel();
		maandenCheck.setBounds(800, 62, 300, 700);
		maandenCheck.setLayout(null);		
		kalender.add(maandenCheck);	
		
		String[] maandNaam = { "januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september",
				"oktober", "november", "december" };
		ArrayList<ArrayList<Action>> maandActies = ReadDatabase.getTable("maand");
		for (int i = 0; i < 12; i++) {			
			JCheckBox cb = new JCheckBox(maandNaam[i]);
			cb.setBounds(10, 60 + i * 40, 100, 40);
			maandenCheck.add(cb);			
			ArrayList<Action> actionList = new ArrayList<Action>();			
			Maand m = new Maand(i + 1, maandNaam[i], cb, maandActies.get(i));
			maand[i] = m;
			cb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					kalender.remove(lijst);
					createList();
					kalender.add(lijst);
					kalender.repaint();
				}
			});
		}

		Calendar cal = Calendar.getInstance();
		int nu = cal.get(Calendar.MONTH);
		maand[nu].cb.setSelected(true);

		if (nu == 11) {
			nu = nu - 12;
		}
		maand[nu + 1].cb.setSelected(true);
		
		createList();
		kalender.add(lijst);
		kalender.repaint();
	}

	private void createList() {

		lijst = new JPanel();
		lijst.setBounds(10, 0, 730, 546);
		lijst.setLayout(null);

		int x = 1;
		int y = 10;

		for (int i = 0; i < 12; i++) {
			Maand m = maand[i];
			if (m.cb.isSelected()) {

				if (y > 500) {
					y = 10;
					x = x + 260;
				}
				JLabel label = new JLabel(maand[i].naam);
				label.setBounds(x, y, 80, 20);
				y = y + 20;

				lijst.add(label);

				ArrayList<Action> actionList = m.actionList;
				for (Action a : actionList) {

					actionButton(x, y, a.plant, a.actie);

					y = y + 45;
					if (y > 500) {
						y = 10;
						x = x + 260;
					}
				}
				y = y + 30;
			}
		}

	}

	private void actionButton(int x, int y, String plant, String actie) {

		final JButton button = new JButton();
		button.setText(plant);
		button.setOpaque(false);
		button.setBorderPainted(false);
		button.setBackground(Color.WHITE);
		button.setBounds(x, y, 250, 20);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		lijst.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String naam = button.getText();
				System.out.println(naam);
			}
		});
		JLabel label = new JLabel(actie);
		label.setBounds(x + 17, y + 20, 235, 20);
		lijst.add(label);
	}
}
