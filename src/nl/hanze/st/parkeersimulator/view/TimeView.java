package nl.hanze.st.parkeersimulator.view;

import static nl.hanze.st.parkeersimulator.controller.ParkingController.EVENT_ID_HOUR;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;

public class TimeView extends View {

	private JPanel timePanel;
	
	private JLabel time;
	
	public TimeView() {
		
		timePanel = new JPanel();
		timePanel.setBackground(Color.GRAY);
		add(timePanel);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		time = new JLabel("Dag: 0 0:00");
		timePanel.add(time);
		
		
	}

	@Override
	protected void update(Model model) {
		Garage garage = (Garage) model;
		String timeString = garage.getTime();
		setTime(timeString);
	}
	
	/**
	 * This method sets the time
	 * 
	 * @param String timeInput the time 
	 */
	public void setTime(String timeInput) {
		time.setText(timeInput);	
	}
	
	
	
}
