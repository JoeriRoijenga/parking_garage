package nl.hanze.st.parkeersimulator.view;

import static nl.hanze.st.parkeersimulator.controller.MenuController.*;

import javax.swing.*;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;

/**
 * Class MenuView
 * 
 * This class is the MenuView class that will extends from View.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class MenuView extends View {
	/**
	 * @param menu This param contains the JMenu with all the menu items.
	 */
	private JMenu menu;
	
	/**
	 * Constructor
	 * 
	 * @param menu This param contains the menu with all his items.
	 */
	public MenuView(JMenu menu) {
		this.menu = menu;
		setMenuItems();
	}
	
	/**
	 * This method will create the menu items.
	 */
	private void setMenuItems() {
		JMenuItem settingsItem = new JMenuItem("Simulator settings");
		settingsItem.addActionListener((e) -> notifyController(EVENT_ID_SETTINGS));
		menu.add(settingsItem);
		
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener((e) -> notifyController(EVENT_ID_ABOUT));
		menu.add(aboutItem);
	}
	

	/**
	 * This method is not in use.
	 * 
	 * @param model This param contains the model that's in use for the view.
	 */
	@Override
	protected void update(Model model) {
		// TODO Auto-generated method stub
	}
}
