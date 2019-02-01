package nl.hanze.st.parkeersimulator.controller;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import nl.hanze.st.mvc.Controller;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;
import nl.hanze.st.parkeersimulator.view.MenuSettingsView;

/**
 * Class SettingsController
 * 
 * This class is the SettingsController class that extends the parent controller.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class SettingsController extends Controller {
	/**
	 * This constant param has the event id for the save.
	 */	
	public static final int EVENT_ID_SAVE = 1;
	
	/**
	 * This param has the model of the Garage.
	 */
	private Garage garage;
	
	/**
	 * This param contains the frame of the settings.
	 */
	private JFrame settingsView;
	
	/**
	 * Constructor
	 * 
	 * @param model This param contains the Garage model.
	 */
	public SettingsController(Garage model) {
		super(model);
		garage = (Garage) model;
	}

	/**
	 * This method is triggered when ane vent has happened
	 * 
	 * @param view This param contains the View given for the event.
	 * @param event_id This param contains the id for the event that has been triggered.
	 * @return boolean The return returns a boolean.
	 */
	@Override
	protected boolean event(View view, int event_id) {
		switch (event_id) {
			case EVENT_ID_SAVE:
				try {
					settingsView = ((MenuSettingsView) view).settingsFrame;
					JPanel settings = (JPanel) ((JPanel) settingsView.getContentPane().getComponent(0)).getComponent(0);
					
					JTextField exitSpeed = (JTextField) settings.getComponent(1);
					JTextField paymentSpeed = (JTextField) settings.getComponent(3);
					
					JTextField weekDayArrivals = (JTextField) settings.getComponent(5);
					JTextField weekendArrivals = (JTextField) settings.getComponent(7);
					
					JTextField weekDayPassArrivals = (JTextField) settings.getComponent(9);
					JTextField weekendPassArrivals = (JTextField) settings.getComponent(11);
					
					JTextField reservationChance = (JTextField) settings.getComponent(13);
					
					garage.setExitSpeed(Integer.parseInt(exitSpeed.getText()));
					garage.setPaymentSpeed(Integer.parseInt(paymentSpeed.getText()));
					
					garage.setWeekDayArrivals(Integer.parseInt(weekDayArrivals.getText()));
					garage.setWeekendArrivals(Integer.parseInt(weekendArrivals.getText()));
					
					garage.setWeekDayPassArrivals(Integer.parseInt(weekDayPassArrivals.getText()));
					garage.setWeekendPassArrivals(Integer.parseInt(weekendPassArrivals.getText()));
					
					garage.setReservationChance(Integer.parseInt(reservationChance.getText()));				
					
					settingsView.dispose();
					return true;
				} catch(Exception e) {
					JFrame exceptionFrame = new JFrame("Failed");
					exceptionFrame.setSize(500,200);
					exceptionFrame.setLayout(new GridLayout(3,3));
					
					JLabel textLabel = new JLabel("Save Failed!",JLabel.CENTER);
					textLabel.setFont(new Font("Sans", Font.PLAIN, 20));
					JLabel errorLabel = new JLabel("One of the values is invalid.",JLabel.CENTER);
					exceptionFrame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent windowsEvent) {
							exceptionFrame.dispose();
						}
					});		
					
					exceptionFrame.add(textLabel);
					exceptionFrame.add(errorLabel);					
					exceptionFrame.setVisible(true);
				}
			default:
				return false;
		}
	}
}
