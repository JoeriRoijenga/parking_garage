package nl.hanze.st.parkeersimulator.view;

import javax.swing.*;

import nl.hanze.st.mvc.Model;

/**
 * Class TabsView
 * 
 * This class is the TabsView class that will extends from JTabbedPane.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class TabsView extends JTabbedPane {
	/**
	 * @param managementInformationView This param contains the view for the management information.
	 */
	ManagementInformationView managementInformationView = new ManagementInformationView();
	
	/**
	 * @param pieGraphicView This param contains the pie graph.
	 */
	PieGraphicView pieGraphicView = new PieGraphicView();
	
	/**
	 * @param barGraphicView This param contains the bar graph.
	 */
	BarGraphicView barGraphicView = new BarGraphicView();
	
	/**
	 * Constructor
	 * This constructor builds the tabs.
	 * 
	 * @param model This param contains the model that's in use.
	 */
	public TabsView(Model model) {					
		JComponent managementInfo = new JPanel();
		JComponent pieGraphic = new JPanel();
		JComponent barGraphic = new JPanel();
		
		managementInfo.add(managementInformationView);
		addTab("Management", null, managementInfo, "Management Information");
		
		pieGraphic.add(pieGraphicView);
		addTab("Pie Graphic", null, pieGraphic, "Pie Graphic");

		barGraphic.add(barGraphicView);
		addTab("Bar Graphic", null, barGraphic, "Bar Graphic");

		model.addView(managementInformationView);
		model.addView(pieGraphicView);
		model.addView(barGraphicView);
		
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
}
