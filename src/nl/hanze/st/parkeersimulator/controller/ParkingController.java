package nl.hanze.st.parkeersimulator.controller;

import nl.hanze.st.mvc.*;
import nl.hanze.st.parkeersimulator.model.Garage;

/**
 * Class ParkingController
 * 
 * This class is the ParkingController class that extends the parent controller.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class ParkingController extends Controller {
	/**
	 * This constant param has the event id for the start.
	 */
	public static final int EVENT_ID_START = 1;
	
	/**
	 * This constant param has the event id for the pause.
	 */
	public static final int EVENT_ID_PAUSE = 2;
	
	/**
	 * This constant param has the event id for the hour.
	 */
	public static final int EVENT_ID_HOUR = 3;
	
	/**
	 * This constant param has the event id for the tick.
	 */
	public static final int EVENT_ID_TICK = 4;
	
	/**
	 * This param has the model of the Garage.
	 */
	private Garage garage;
	
	/**
	 * Constructor
	 * 
	 * @param model This param contains the Garage model.
	 */
	public ParkingController(Garage model) {
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
			case EVENT_ID_START:
				garage.setAutomatic(true);
				garage.start();
				return true;
	
			case EVENT_ID_PAUSE:
				garage.setRunning(false);
				garage.notifyView();
				return true;
	
			case EVENT_ID_HOUR:
				garage.setAutomatic(false);
				garage.setRunning(false);
				garage.setPeriod(60);
				garage.start();
				return true;
	
			case EVENT_ID_TICK:
				garage.setAutomatic(false);
				garage.setRunning(false);
				garage.setPeriod(1);
				garage.start();
				return true;
				
			default:
				return false;
		}
	}	
}
