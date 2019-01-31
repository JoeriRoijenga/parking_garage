package nl.hanze.st.parkeersimulator.view;

import static nl.hanze.st.parkeersimulator.controller.SettingsController.EVENT_ID_SAVE;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;

/**
 * Class MenuSettingsView
 * 
 * This class is the MenuSettingsView class that will create the settings view and extends view..
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class MenuSettingsView extends View {
	/**
	 * @param model This param contains the model of the garage.
	 */
	private Garage model;
	
	/**
	 * @param settingsFrame This param contains the frame with all the settings.
	 */
	public JFrame settingsFrame;
	
	/** 
	 * Constructor which will build the settings the first time.
	 * 
	 * @param model contains the model that is in use.
	 */
	public MenuSettingsView(Model model) {
		this.model = (Garage) model;
		buildSettings();
	}
	
	/**
	 * Private method that will build the settings frame.
	 */
	private void buildSettings() {		
		settingsFrame = new JFrame("Setting");
		
		settingsFrame.setSize(500,500);
		settingsFrame.setLayout(new GridLayout(1,1));
		
		settingsFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowsEvent) {
				settingsFrame.dispose();
			}
		});
		
		JPanel window = new JPanel();
		window.setLayout(new BorderLayout());
		
		JPanel settings = new JPanel();
		window.add(settings, BorderLayout.CENTER);
		settings.setLayout(new GridLayout(0,2));		
		
		// Basic settings
		JLabel exitSpeedLabel = new JLabel("Exit Speed");
		JTextField exitSpeedTextField = new JTextField("" + model.getExitSpeed());
		
		settings.add(exitSpeedLabel);
		settings.add(exitSpeedTextField);
		
		JLabel paymentSpeedLabel = new JLabel("Payment Speed");
		JTextField paymentSpeedTextField = new JTextField("" + model.getPaymentSpeed());
		
		settings.add(paymentSpeedLabel);
		settings.add(paymentSpeedTextField);
		
		// Regular car settings
		JLabel weekDayArrivalsLabel = new JLabel("Week Day Arrivals");
		JTextField weekDayArrivalsTextField = new JTextField("" + model.getWeekDayArrivals());
		
		settings.add(weekDayArrivalsLabel);
		settings.add(weekDayArrivalsTextField);
		
		JLabel weekendArrivalsLabel = new JLabel("Weekend Arrivals");
		JTextField weekendArrivalsTextField = new JTextField("" + model.getWeekendArrivals());
		
		settings.add(weekendArrivalsLabel);
		settings.add(weekendArrivalsTextField);
		
		// Subscription car settings
		JLabel weekDayPassArrivalsLabel = new JLabel("Week Day Subscription Arrivals");
		JTextField weekDayPassArrivalsTextField = new JTextField("" + model.getWeekDayPassArrivals());
		
		settings.add(weekDayPassArrivalsLabel);
		settings.add(weekDayPassArrivalsTextField);

		JLabel weekendPassArrivalsLabel = new JLabel("Weekend Subscription Arrivals");
		JTextField weekendPassArrivalsTextField = new JTextField("" + model.getWeekendPassArrivals());
		
		settings.add(weekendPassArrivalsLabel);
		settings.add(weekendPassArrivalsTextField);
		
		// Reservations car settings
		JLabel reservetionChanceLabel = new JLabel("Reservation Chance");
		JTextField reservationChanceTextField = new JTextField("" + model.getReservationChance());
		
		settings.add(reservetionChanceLabel);
		settings.add(reservationChanceTextField);
		
		// Button save
		JButton saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(200, 80));
		saveButton.addActionListener((e) -> notifyController(EVENT_ID_SAVE));
		window.add(saveButton, BorderLayout.SOUTH);

		settingsFrame.add(window);
		settingsFrame.setVisible(true);
	}

	/**
	 * Update method is unused here.
	 * 
	 * @param model contains the model that is in use for the view.
	 */
	@Override
	protected void update(Model model) {
		// TODO Auto-generated method stub
	}
}
