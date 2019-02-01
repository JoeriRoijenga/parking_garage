package nl.hanze.st.parkeersimulator.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

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
		managementInfo.setLayout(new BoxLayout(managementInfo, BoxLayout.Y_AXIS));
		
		JComponent pieGraph = new JPanel();
		pieGraph.setLayout(new BoxLayout(pieGraph, BoxLayout.Y_AXIS));
		
		JComponent managementInfoView = new JPanel();
		JComponent pieGraphic = new JPanel();
		JComponent barGraphic = new JPanel();
		JComponent legend1 = new JPanel();
		JComponent legend2 = new JPanel();
		
		legend1.add(createLegend());
		legend2.add(createLegend());
		
		managementInfoView.add(managementInformationView);
		managementInfo.add(managementInfoView);
		managementInfo.add(legend1);
		addTab("Management", null, managementInfo, "Management Information");
		
		pieGraphic.add(pieGraphicView);
		pieGraph.add(pieGraphic);
		pieGraph.add(legend2);
		addTab("Pie Graphic", null, pieGraph, "Pie Graphic");

		barGraphic.add(barGraphicView);
		addTab("Bar Graphic", null, barGraphic, "Bar Graphic");

		model.addView(managementInformationView);
		model.addView(pieGraphicView);
		model.addView(barGraphicView);
		
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
	
	/**
	 * This method will create the legend for the graph.
	 * 
	 * @return colorPane This return will return the colorPane with the legend.
	 */
	public JComponent createLegend() {
		JPanel colorPane = new JPanel();
		colorPane.setLayout(new GridLayout(3, 3));

		JLabel redIcon = new JLabel();
		redIcon.setText("■");
		redIcon.setForeground(Color.RED);
		redIcon.setFont(new Font(redIcon.getName(), Font.PLAIN, 30));		
		JLabel redLabel = new JLabel();
		redLabel.setText("Regular Cars");
		
		JLabel blueIcon = new JLabel();
		blueIcon.setText("■");
		blueIcon.setForeground(Color.BLUE);
		blueIcon.setFont(new Font(blueIcon.getName(), Font.PLAIN, 30));		
		JLabel blueLabel = new JLabel();
		blueLabel.setText("Subscription Cars");
		
		JLabel greenIcon = new JLabel();		
		greenIcon.setText("■");
		greenIcon.setForeground(Color.BLACK);
		greenIcon.setFont(new Font(greenIcon.getName(), Font.PLAIN, 30));		
		JLabel greenLabel = new JLabel();
		greenLabel.setText("Remaining (Reservation) Cars");
		
		colorPane.add(redIcon);
		colorPane.add(redLabel);
		colorPane.add(blueIcon);
		colorPane.add(blueLabel);
		colorPane.add(greenIcon);
		colorPane.add(greenLabel);
		
		return colorPane;
	}
}
