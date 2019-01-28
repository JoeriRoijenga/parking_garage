package nl.hanze.st.parkeersimulator.controller;

import nl.hanze.st.mvc.Controller;
import nl.hanze.st.mvc.View;
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
	 * Constructor
	 * 
	 * @param model This param contains the Garage model.
	 */
	public ParkingController(Garage model) {
		super(model);
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
		// TODO Auto-generated method stub
		return false;
	}
}
