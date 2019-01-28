package nl.hanze.st.parkeersimulator.controller;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nl.hanze.st.parkeersimulator.model.Garage;

public class SliderController implements ChangeListener {

	private Garage garage;

	public SliderController(Garage model) {
		this.garage = model;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if (!source.getValueIsAdjusting()) {
			int fps = (int) source.getValue();
			garage.setTickPause(fps);
		}
	}

}
