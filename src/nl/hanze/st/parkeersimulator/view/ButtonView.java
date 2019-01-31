package nl.hanze.st.parkeersimulator.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;

import static nl.hanze.st.parkeersimulator.controller.ParkingController.*;

/**
 * Class ButtonView
 * 
 * This class is the ButtonView class that will extends from View.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class ButtonView extends View {

	/**
	 * @param onButton This param contains the button to turn the program on.
	 * @param offButton This param contains the button to turn the program off.
	 * @param periodButton This param contains the button for the periods.
	 * @param tickButton This param contains the button for the ticks.
	 */
	private JButton onButton, offButton, periodButton, tickButton;
	
	/**
	 * @param buttonPanel This panel contains the buttons for the view.
	 */
	public JPanel buttonPanel;

	/**
	 * Constructor
	 * This creates the buttons view.
	 */
	public ButtonView() {
		this.setPreferredSize(new Dimension(500,100));
		this.setBackground(Color.LIGHT_GRAY);
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		add(buttonPanel);

		onButton = new JButton("Aan");
		onButton.setPreferredSize(new Dimension(200, 80));
		onButton.addActionListener((e) -> notifyController(EVENT_ID_START));
		buttonPanel.add(onButton);

		offButton = new JButton("Uit");
		offButton.setPreferredSize(new Dimension(200, 80));
		offButton.addActionListener((e) -> notifyController(EVENT_ID_PAUSE));
		buttonPanel.add(offButton);
		offButton.setEnabled(false);

		periodButton = new JButton("Uur vooruit");
		periodButton.setPreferredSize(new Dimension(200, 80));
		periodButton.addActionListener((e) -> notifyController(EVENT_ID_HOUR));
		buttonPanel.add(periodButton);
		
		tickButton = new JButton("Tick");
		tickButton.setPreferredSize(new Dimension(200, 80));
		tickButton.addActionListener((e) -> notifyController(EVENT_ID_TICK));
		buttonPanel.add(tickButton);
	}

	/**
	 * This method will enable or disable the buttons.
	 * 
	 * @param model This param contains the model that's in use by the view.
	 */
	@Override
	protected void update(Model model) {
		Garage garage = (Garage) model;
		if (garage.isRunning()) {
			onButton.setEnabled(false);
			offButton.setEnabled(true);
			periodButton.setEnabled(false);
			tickButton.setEnabled(false);
		} else {
			onButton.setEnabled(true);
			offButton.setEnabled(false);
			periodButton.setEnabled(true);
			tickButton.setEnabled(true);
		}
	}
}
