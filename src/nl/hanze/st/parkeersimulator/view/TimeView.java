package nl.hanze.st.parkeersimulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;

/**
 * Class TimeView
 * 
 * This class is the TimeView class that will extends from View.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class TimeView extends View {
	/**
	 * @param timePanel This param contains the panel with the time.
	 */
	private JPanel timePanel;
	
	/**
	 * @param time This param contains the label for the time.
	 * @param day This param contains the label for the day.
	 */
	private JLabel time, day;
	
	/**
	 * Constructor
	 * This constructor will build the time view.
	 */
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

	/**
	 * This method will change the data of the time view.
	 * 
	 * @param model This param contains the model that's in use for the view.
	 */
	@Override
	protected void update(Model model) {
		Garage garage = (Garage) model;
		
		String dayString = garage.getDay();
		day.setText(dayString);
		
		String timeString = garage.getTime();
		time.setText(timeString);	
	}

}
