package nl.hanze.st.parkeersimulator;

import java.awt.*;

import javax.swing.*;

import nl.hanze.st.parkeersimulator.controller.MenuController;
import nl.hanze.st.parkeersimulator.controller.ParkingController;
import nl.hanze.st.parkeersimulator.controller.SliderController;
import nl.hanze.st.parkeersimulator.model.Garage;
import nl.hanze.st.parkeersimulator.view.ButtonView;
import nl.hanze.st.parkeersimulator.view.ManagementInformationView;
import nl.hanze.st.parkeersimulator.view.MenuView;
import nl.hanze.st.parkeersimulator.view.ParkingView;
import nl.hanze.st.parkeersimulator.view.SliderView;
import nl.hanze.st.parkeersimulator.view.TabsView;
import nl.hanze.st.parkeersimulator.view.TimeView;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setTitle("MVC Voorbeeld");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Garage model = new Garage(3, 6, 30);
		
		ParkingController parkingController = new ParkingController(model);
		SliderController sliderController = new SliderController(model);		
		MenuController menuController = new MenuController(model);
		
		ParkingView parkingView = new ParkingView();
		ButtonView buttonView = new ButtonView();
		SliderView sliderView = new SliderView(sliderController);
		TimeView timeView = new TimeView();
		JMenuBar menubar = new JMenuBar();
		TabsView tabsView = new TabsView(model);
		
		window.setJMenuBar(menubar);
		JMenu settingsMenu = new JMenu("Settings");
		MenuView menuView = new MenuView(settingsMenu);
		menubar.add(settingsMenu);

		buttonView.setController(parkingController);	
		menuView.setController(menuController);
		
		model.addView(parkingView);
		model.addView(buttonView);
		model.addView(sliderView);
		model.addView(timeView);

		Container southPane = new Container();
		southPane.setLayout(new BoxLayout(southPane, BoxLayout.Y_AXIS));
		southPane.add(buttonView);
		southPane.add(sliderView);
		southPane.add(timeView);
		
		Container contentPane = window.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(parkingView, BorderLayout.CENTER);
		contentPane.add(southPane, BorderLayout.SOUTH);
		contentPane.add(tabsView, BorderLayout.EAST);
		
		window.pack();
		window.setVisible(true);
	}
}
