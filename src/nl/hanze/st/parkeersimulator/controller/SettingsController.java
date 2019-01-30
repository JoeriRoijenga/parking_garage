package nl.hanze.st.parkeersimulator.controller;

import java.awt.Component;
import java.awt.Container;
import java.awt.im.InputContext;

import java.lang.reflect.Field;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import nl.hanze.st.mvc.Controller;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;
import nl.hanze.st.parkeersimulator.view.MenuSettingsView;

public class SettingsController extends Controller {
	public static final int EVENT_ID_SAVE = 1;
	
	private Garage garage;
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
			default:
				return false;
		}
	}
}
