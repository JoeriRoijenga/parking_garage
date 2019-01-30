package nl.hanze.st.parkeersimulator.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;

public class TabsView extends JTabbedPane {
	ManagementInformationView managementInformationView = new ManagementInformationView();
	PieGraphicView pieGraphicView = new PieGraphicView();
	BarGraphicView barGraphicView = new BarGraphicView();
	
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
