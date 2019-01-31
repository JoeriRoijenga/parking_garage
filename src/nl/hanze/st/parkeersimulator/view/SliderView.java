package nl.hanze.st.parkeersimulator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Hashtable;

import javax.swing.*;

import nl.hanze.st.mvc.*;
import nl.hanze.st.parkeersimulator.controller.SliderController;

/**
 * Class SliderView
 * 
 * This class is the SliderView class that will extends from View.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class SliderView extends View {
	/**
	 * @param sliderPanel This param contains the panel with the slider.
	 */
	private JPanel sliderPanel;

	/**
	 * @param slider This param contains the JSlider.
	 */
	private JSlider slider;
	
	/** 
	 * @param changeListener This param contains the slider controller.
	 */
	private SliderController changeListener;

	/**
	 * Constructor
	 * This constructor will build the slider view.
	 * 
	 * @param listener This param contains the slider controller.
	 */
	public SliderView(SliderController listener) {
		this.changeListener = listener;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		sliderPanel = new JPanel();
		sliderPanel.setBackground(Color.LIGHT_GRAY);
		add(sliderPanel);
		
		slider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 50);
		slider.setValue(750);
		slider.setPreferredSize(new Dimension(500, 50));

		Hashtable<Integer, JLabel> labels = new Hashtable<>();
		labels.put(0, new JLabel("Traag"));
		labels.put(500, new JLabel("Matig"));
        labels.put(1000, new JLabel("Snel"));
	        
        slider.setLabelTable(labels);
        slider.setPaintLabels(true);
        slider.setBackground(Color.YELLOW);
	       
		slider.addChangeListener(changeListener);

		sliderPanel.add(slider);

	}

	/**
	 * This method is unused.
	 * 
	 * @param model This param contains the model that's in use for the view.
	 */
	@Override
	protected void update(Model model) {
		// TODO Auto-generated method stub

	}

	/**
	 * This method will set the slider controller.
	 * 
	 * @param listener This param contains the slider controller.
	 */
	public void setListener(SliderController listener) {
		changeListener = listener;
	}

}
