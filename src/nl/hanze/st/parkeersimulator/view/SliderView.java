package nl.hanze.st.parkeersimulator.view;

import static nl.hanze.st.parkeersimulator.controller.ParkingController.EVENT_ID_HOUR;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.controller.SliderController;

public class SliderView extends View {

	private JPanel sliderPanel;

	private JSlider slider;
	
	private JLabel sliderHeader;

	private SliderController changeListener;

	
	public SliderView(SliderController listener) {
		this.changeListener = listener;
		

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		sliderPanel = new JPanel();
		sliderPanel.setBackground(Color.YELLOW);
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

	@Override
	protected void update(Model model) {
		// TODO Auto-generated method stub

	}

	public void setListener(SliderController listener) {
		changeListener = listener;
	}

}
