package nl.hanze.st.parkeersimulator.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MenuSettingsView {
	private JFrame settingsFrame;
	
	public MenuSettingsView() {
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
		
		settingsFrame.setVisible(true);
	}
}
