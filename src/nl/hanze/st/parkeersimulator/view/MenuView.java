package nl.hanze.st.parkeersimulator.view;

import static nl.hanze.st.parkeersimulator.controller.MenuController.*;

import javax.swing.*;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;

public class MenuView extends View {
	private JMenu menu;
	
	public MenuView(JMenu menu) {
		this.menu = menu;
		setMenuItems();
	}
	
	private void setMenuItems() {
		JMenuItem settingsItem = new JMenuItem("Simulator settings");
		settingsItem.addActionListener((e) -> notifyController(EVENT_ID_SETTINGS));
		menu.add(settingsItem);
		
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener((e) -> notifyController(EVENT_ID_ABOUT));
		menu.add(aboutItem);
	}

	@Override
	protected void update(Model model) {
		// TODO Auto-generated method stub
	}
}
