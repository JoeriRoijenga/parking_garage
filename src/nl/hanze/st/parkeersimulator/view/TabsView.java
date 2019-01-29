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
	
	public TabsView(Model model) {					
		JComponent panel1 = new JPanel();
		
		panel1.add(managementInformationView);
		addTab("Tab 1", null, panel1, "Management Information");
		
		model.addView(managementInformationView);
		
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
}
