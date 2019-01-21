package nl.hanze.st.parkeersimulator.view;

import java.awt.Color;
import java.awt.Dimension;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;

import static nl.hanze.st.parkeersimulator.controller.ParkingController.*;

public class ButtonView extends View {

	private JButton onButton, offButton, periodButton;

	public JPanel buttonPanel;

	public ButtonView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.YELLOW);
		add(buttonPanel);
		
		onButton = new JButton("Aan");
		onButton.setPreferredSize(new Dimension(200, 80));
		onButton.addActionListener((e) -> notifyController(EVENT_ID_START));
		
		buttonPanel.add(onButton);

		offButton = new JButton("Uit");
		offButton.setPreferredSize(new Dimension(200, 80));
		offButton.addActionListener((e) -> notifyController(EVENT_ID_PAUSE));
		buttonPanel.add(offButton);

		periodButton = new JButton("Uur vooruit");
		periodButton.setPreferredSize(new Dimension(200, 80));
		periodButton.addActionListener((e) -> notifyController(EVENT_ID_HOUR));
		buttonPanel.add(periodButton);

	}

	@Override
	protected void update(Model model) {
		// TODO Auto-generated method stub

	}

}
