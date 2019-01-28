package nl.hanze.st.parkeersimulator;

import javax.swing.*;

import nl.hanze.st.parkeersimulator.controller.ParkingController;
import nl.hanze.st.parkeersimulator.model.Garage;
import nl.hanze.st.parkeersimulator.view.ParkingView;

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

		parkingView.setController(parkingController);
		model.addView(parkingView);
		
		window.setContentPane(parkingView);
		window.pack();
		window.setVisible(true);
		
		model.tickThread();
	}
}
