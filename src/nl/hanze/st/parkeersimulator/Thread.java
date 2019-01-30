package nl.hanze.st.parkeersimulator;

import java.awt.*;

import javax.swing.*;

import nl.hanze.st.parkeersimulator.controller.ParkingController;
import nl.hanze.st.parkeersimulator.model.Garage;
import nl.hanze.st.parkeersimulator.view.BarGraphicView;
import nl.hanze.st.parkeersimulator.view.LineGraphicView;
import nl.hanze.st.parkeersimulator.view.ParkingView;
import nl.hanze.st.parkeersimulator.view.PieGraphicView;

/**
 * Class Thread
 * 
 * This class is the view class that we will use for every child view.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class Thread {
	/**
	 * Main function to run the program.
	 * 
	 * @param args This param is used in case you will use extra params.
	 */
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setTitle("MVC Voorbeeld");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Garage model = new Garage(3, 6, 30);
		ParkingView parkingView = new ParkingView();
		ParkingController parkingController = new ParkingController(model);
		BarGraphicView pieView = new BarGraphicView();
		
		parkingView.setController(parkingController);
		model.addView(parkingView);
		model.addView(pieView);
		
		JPanel parkingViewPanel = new JPanel();
		parkingViewPanel.setLayout(new BorderLayout());		
		
		JPanel pieViewPanel = new JPanel();
		pieViewPanel.setLayout(new BorderLayout());
		pieViewPanel.add(pieView, BorderLayout.CENTER);
		
		parkingViewPanel.add(pieViewPanel, BorderLayout.EAST);
		parkingViewPanel.add(parkingView, BorderLayout.CENTER);
		
		pieViewPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		parkingViewPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		window.setContentPane(parkingViewPanel);
		window.pack();
		window.setVisible(true);
		
		model.tickThread();
	}
}
