package nl.hanze.st.parkeersimulator.view;

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

public class SliderView extends View {

	private JPanel sliderPanel;

	private JSlider slider;

	public SliderView() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		sliderPanel = new JPanel();
		sliderPanel.setBackground(Color.YELLOW);
		add(sliderPanel);

		
		
		slider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 50);
		slider.setPreferredSize(new Dimension(1000, 50));
		

		 Hashtable<Integer, JLabel> labels = new Hashtable<>();
	        labels.put(0, new JLabel("1000 ms"));
	        labels.put(500, new JLabel("500 ms"));
	        labels.put(1000, new JLabel("1 ms"));
	        slider.setLabelTable(labels);
	        slider.setPaintLabels(true);
	        

		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					int fps = (int) source.getValue();
					notifyController(fps);
				}
			}

		}

		);

		sliderPanel.add(slider);

	}

	@Override
	protected void update(Model model) {
		// TODO Auto-generated method stub

	}

}
