package nl.hanze.st.parkeersimulator.controller;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import nl.hanze.st.mvc.Controller;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;
import nl.hanze.st.parkeersimulator.view.MenuAboutView;
import nl.hanze.st.parkeersimulator.view.MenuSettingsView;

public class MenuController extends Controller {
	public static final int EVENT_ID_ABOUT = 1;
	public static final int EVENT_ID_SETTINGS = 2;
	
	private Garage garage;
	/**
	 * Constructor
	 * 
	 * @param model This param contains the Garage model.
	 */
	public MenuController(Garage model) {
		super(model);
		garage = model;
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
			case EVENT_ID_ABOUT:
				new MenuAboutView();
				return true;
			case EVENT_ID_SETTINGS:
				new MenuSettingsView();
				return true;
			default:
				return false;
		}
	}
}
