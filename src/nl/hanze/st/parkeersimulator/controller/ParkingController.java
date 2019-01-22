package nl.hanze.st.parkeersimulator.controller;

import java.awt.Button;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nl.hanze.st.mvc.Controller;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;
import nl.hanze.st.parkeersimulator.view.ButtonView;

public class ParkingController extends Controller {

	public static final int EVENT_ID_START = 1;
	public static final int EVENT_ID_PAUSE = 2;
	public static final int EVENT_ID_HOUR = 3;

	private Garage garage;
	


	public ParkingController(Garage model) {
		super(model);
		garage = model;


	
	
	}


		

	
	@Override
	protected boolean event(View view, int event_id) {

		switch (event_id) {
		case EVENT_ID_START:
			garage.start();
			return true;

		case EVENT_ID_PAUSE:
			garage.setRunning(false);
			garage.notifyView();
			return true;

		case EVENT_ID_HOUR:
			garage.setRunning(false);
			garage.setPeriod(59);
			garage.start();
			return true;

		default:
			return false;
		}

	}



	
	
	
	
}
