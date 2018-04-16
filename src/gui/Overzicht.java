package gui;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.Plant;

public class Overzicht {

	public Overzicht(final JFrame mainWindow, final ArrayList<Plant> lijst) {

		ArrayList<Action> actionlijst = ReadAction.getActionlijst();
		

		List<String> lijstB = new ArrayList<String>();
		for (Plant plant : lijst) {
			lijstB.add(plant.plantnaam);
		}
		Collections.sort(lijstB);

		DefaultListModel<String> lijstModel = new DefaultListModel<String>();
		for (String plant : lijstB) {
			lijstModel.addElement(plant);
		}

		final JList<String> bomenLijst = new JList<String>(lijstModel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(758, 62, 321, 546);
		mainWindow.getContentPane().add(scrollPane);
		scrollPane.setViewportView(bomenLijst);

		bomenLijst.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				System.out.println(bomenLijst.getSelectedValue().toString());

			}
		});

		JTextArea omschrijvingField = new JTextArea();
		omschrijvingField.setBounds(1, 1, 730, 1000);
		omschrijvingField.setText(" test");

		JScrollPane omschrijvingScroll = new JScrollPane(omschrijvingField);
		omschrijvingScroll.setBounds(10, 62, 730, 346);
		mainWindow.getContentPane().add(omschrijvingScroll);

		JTextArea actionField = new JTextArea();
		actionField.setBounds(1, 1, 730, 1000);
		actionField.setText(" test");

		JScrollPane actionScroll = new JScrollPane(actionField);
		actionScroll.setBounds(10, 450, 730, 250);
		mainWindow.getContentPane().add(actionScroll);

		mainWindow.repaint();
	}

	private void description(Plant plant) {

		String omschrijving = plant.omschrijving;

	}

	private void action(Plant plant) {
		ArrayList<Action> actionLijst = plant.action;

	}

	// TODO groot tekstveld beschrijving
	// TODO groot tekstveld actielijst
}
