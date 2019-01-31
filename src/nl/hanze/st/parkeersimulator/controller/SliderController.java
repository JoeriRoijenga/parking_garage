package nl.hanze.st.parkeersimulator.controller;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nl.hanze.st.parkeersimulator.model.Garage;

/**
 * Class SliderController
 * 
 * This class is the SliderController class that extends the parent controller.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class SliderController implements ChangeListener {
	/**
	 * This param has the model of the Garage.
	 */
	private Garage garage;

	/**
	 * Constructor
	 * 
	 * @param model This param contains the model that's used in the view / controller.
	 */
	public SliderController(Garage model) {
		this.garage = model;
	}

	/**
	 * This method changes the speed of the garage.
	 * 
	 * @param e This param contains the change event.
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if (!source.getValueIsAdjusting()) {
			int fps = (int) source.getValue();
			garage.setTickPause(fps);
		}
	}

}
