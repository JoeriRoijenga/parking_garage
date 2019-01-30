package nl.hanze.st.parkeersimulator.view;

import static nl.hanze.st.parkeersimulator.controller.SettingsController.EVENT_ID_SAVE;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;

public class MenuSettingsView extends View {
	private JFrame settingsFrame;
	private Garage model;
	
	public MenuSettingsView(Model model) {
		this.model = (Garage) model;
		buildSettings();
	}
	
	private void buildSettings() {
		settingsFrame = new JFrame("Setting");
		
		settingsFrame.setSize(500,500);
		settingsFrame.setLayout(new GridLayout(1,1));
		
		settingsFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowsEvent) {
				settingsFrame.dispose();
			}
		});
		
		JTextField floorTextField = new JTextField("" + model.getNumberOfFloors());
		floorTextField.setName("floors");
		JLabel floorLabel = new JLabel("Floor");
		JPanel window = new JPanel();
		
		window.setLayout(new BorderLayout());
		
		JPanel settings = new JPanel();
//		settings.setLayout(new GridLayout(0,2));
		settings.add(floorLabel);
		settings.add(floorTextField);
		
		window.add(settings, BorderLayout.CENTER);
		
		JButton saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(200, 80));
		saveButton.addActionListener((e) -> notifyController(EVENT_ID_SAVE));
		window.add(saveButton, BorderLayout.SOUTH);
		
		settingsFrame.add(window);
		settingsFrame.setVisible(true);
	}

	@Override
	protected void update(Model model) {
		// TODO Auto-generated method stub
	}
}
