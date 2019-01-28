package nl.hanze.st.parkeersimulator;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.*;

import nl.hanze.st.parkeersimulator.controller.ParkingController;
import nl.hanze.st.parkeersimulator.controller.SliderController;
import nl.hanze.st.parkeersimulator.model.Garage;
import nl.hanze.st.parkeersimulator.view.ButtonView;
import nl.hanze.st.parkeersimulator.view.ParkingView;
import nl.hanze.st.parkeersimulator.view.SliderView;
import nl.hanze.st.parkeersimulator.view.TimeView;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setTitle("MVC Voorbeeld");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Garage model = new Garage(3, 6, 30);
		
		ParkingController parkingController = new ParkingController(model);
		SliderController sliderController = new SliderController(model);		

		ParkingView parkingView = new ParkingView();
		ButtonView buttonView = new ButtonView();
		SliderView sliderView = new SliderView(sliderController);
		TimeView timeView = new TimeView();
		
		buttonView.setController(parkingController);	
		
		model.addView(parkingView);
		model.addView(buttonView);
		model.addView(sliderView);
		model.addView(timeView);

		Container contentPane = window.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(parkingView);
		contentPane.add(buttonView);
		contentPane.add(sliderView);
		contentPane.add(timeView);
		
		window.pack();
		window.setVisible(true);
	}
}
