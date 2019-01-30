package nl.hanze.st.parkeersimulator.controller;

import java.awt.Component;
import java.awt.im.InputContext;

import java.lang.reflect.Field;

import nl.hanze.st.mvc.Controller;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;

public class SettingsController extends Controller {
	public static final int EVENT_ID_SAVE = 1;
	
	private Garage garage;
	
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
				//garage.setNumberOfFloors(view.getClass().getDeclaredField(""));
				try {
					Field[] a = view.getClass().getDeclaredFields();
					Field[] b = a[0].getClass().getDeclaredFields();
					
					System.out.println();
				} catch (Exception e) {
					
				}
				return true;
			default:
				return false;
		}
	}
}
