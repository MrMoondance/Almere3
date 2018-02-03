package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class Main {

	private JFrame mainFrame;
	
	public static void main(String[] args) {
		Main main = new Main();
	}
	public Main() {		

		mainFrame = new JFrame();
		mainFrame.setBounds(100, 50, 1107, 781);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setVisible(true);
		
		JToolBar toolBar = makeToolBar();
		mainFrame.getContentPane().add(toolBar);
	}

	JToolBar makeToolBar() {

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(10, 11, 560, 39);		

		JButton btnPlattegrond = new JButton("Plattegrond");
		btnPlattegrond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				while (mainFrame.getContentPane().getComponentCount() > 1) {
					mainFrame.getContentPane().remove(1);
				}
				new Plattegrond(mainFrame);
			}
		});
		toolBar.add(btnPlattegrond);

		JButton btnOverzicht = new JButton("Overzicht");
		btnOverzicht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while (mainFrame.getContentPane().getComponentCount() > 1) {
					mainFrame.getContentPane().remove(1);
				}
				
			}
		});
		toolBar.add(btnOverzicht);

		JButton btnKalender = new JButton("Kalender");
		btnKalender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				while (mainFrame.getContentPane().getComponentCount() > 1) {
					mainFrame.getContentPane().remove(1);
				}
				new Kalender(mainFrame);				
			}
		});
		toolBar.add(btnKalender);

		return toolBar;
	}
}
