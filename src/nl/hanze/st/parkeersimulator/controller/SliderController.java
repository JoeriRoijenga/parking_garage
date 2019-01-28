package nl.hanze.st.parkeersimulator.controller;

import javax.swing.JButton;

import nl.hanze.st.mvc.Controller;
import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;

public class SliderController extends Controller {

	private Garage garage;
	
	private JButton onButton;
	
	public SliderController(Model model) {
		super(model);
		garage = (Garage) model;

	}

	@Override
	protected boolean event(View view, int event_id) {
		garage.setTickPause(event_id);
		return true;
	}

}
