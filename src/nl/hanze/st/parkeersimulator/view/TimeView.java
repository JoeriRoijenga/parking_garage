package nl.hanze.st.parkeersimulator.view;

import static nl.hanze.st.parkeersimulator.controller.ParkingController.EVENT_ID_HOUR;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;

public class TimeView extends View {

	private JPanel timePanel;
	
	private JLabel time, day;
	
	public TimeView() {
		
		JPanel timePanel = new JPanel();
		
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		day = new JLabel("Maandag");
		time = new JLabel("--:--");

		timePanel.add(day);
		timePanel.add(time);
		
		add(timePanel);
	}

	@Override
	protected void update(Model model) {
		Garage garage = (Garage) model;
		
		String dayString = garage.getDay();
		day.setText(dayString);
		
		String timeString = garage.getTime();
		time.setText(timeString);	
	}

}
