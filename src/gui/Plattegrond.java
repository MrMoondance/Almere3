package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.Plaats;
import data.Plant;
import database.ReadDatabase;

public class Plattegrond {
	
	JPanel panel;

	public Plattegrond(final JFrame main) {
	
		panel = new JPanel();
		panel.setBounds(10, 62, 1107, 781);
		panel.setLayout(null);		
		main.getContentPane().add(panel);
		final JLabel bomen = new JLabel("");
		bomen.setBounds(0, 0, 600, 600);			
		panel.add(bomen);		
		final ImageIcon bomenIcon = new ImageIcon("plattegrond.jpg");
		bomen.setIcon(bomenIcon);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(748, 0, 321, 546);
		panel.add(scrollPane);		
		
		DefaultListModel<String> lijst = new DefaultListModel<String>();
		ArrayList<Plant> planten = ReadDatabase.getTable("plant");
		for (int i = 0; i < planten.size(); i++) {
			lijst.addElement(planten.get(i).naam);				
		}
		final JList<String> plantenLijst = new JList<String>(lijst);
		scrollPane.setViewportView(plantenLijst);
		JButton reset = new JButton("Reset");
		reset.setBounds(630, 40, 100, 50);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.repaint();
			}
		});
		panel.add(reset);

		plantenLijst.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {					
				String selectedString = plantenLijst.getSelectedValue().toString();				
				ArrayList<Plaats> plaatsen = ReadDatabase.getTable("plaats");									
				Graphics g = bomen.getGraphics();
				g.setColor(Color.red);
				for (Plaats plaats : plaatsen) {
					if (selectedString.equals(plaats.plant)) {												
						g.drawOval(plaats.x, plaats.y, 45, 45);
					}
				}
			}
		});
		
		bomen.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX() - 22;
				int y = e.getY() - 22;
				muisklik(x, y);			
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});			
		main.repaint();
	}	

	public void muisklik(int x, int y) {		
	}

}
