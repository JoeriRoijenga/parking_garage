package nl.hanze.st.parkeersimulator.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;

public class MenuAboutView {
	private JFrame aboutFrame;
	
	public MenuAboutView() {
		buildAbout();
	}
	
	private void buildAbout() {
		aboutFrame = new JFrame("About simulator");
		aboutFrame.setSize(500,200);
		aboutFrame.setLayout(new GridLayout(3,3));
		
		JLabel textLabel = new JLabel("Groningen CityPark simulator",JLabel.CENTER);
		textLabel.setFont(new Font("Sans", Font.PLAIN, 20));
		JLabel versionLabel = new JLabel("Version MVC-0.1.0",JLabel.CENTER);
		JLabel authorLabel = new JLabel("©2018, Joeri Roijenga, Niels de Vries, Tim Perdok en Timo de Jong");
		aboutFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowsEvent) {
				aboutFrame.dispose();
			}
		});		
		
		aboutFrame.add(textLabel);
		aboutFrame.add(versionLabel);
		aboutFrame.add(authorLabel);
		aboutFrame.setVisible(true);
	}
}
