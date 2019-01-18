package nl.hanze.st.parkeersimulator.controller;

import nl.hanze.st.mvc.Controller;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;

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
			garage.setRunning(true);
			garage.tickThread();
			return true;
			
		case EVENT_ID_PAUSE:
			garage.setRunning(false);
			return true;

		case EVENT_ID_HOUR:
			garage.setRunning(false);
			garage.setPeriod(60);
			garage.tickThread();
			return true;

		default:
			return false;
		}
		


	}

}
