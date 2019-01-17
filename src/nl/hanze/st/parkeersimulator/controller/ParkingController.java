package nl.hanze.st.parkeersimulator.controller;

import nl.hanze.st.mvc.Controller;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;

public class ParkingController extends Controller {
	public ParkingController(Garage model) {
		super(model);
	}

	@Override
	protected boolean event(View view, int event_id) {
		// TODO Auto-generated method stub
		return false;
	}
}
