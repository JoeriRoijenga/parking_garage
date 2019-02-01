package nl.hanze.st.parkeersimulator.view;

import java.awt.*;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;
import nl.hanze.st.parkeersimulator.model.RegularCar;
import nl.hanze.st.parkeersimulator.model.ReservationCar;
import nl.hanze.st.parkeersimulator.model.SubscriptionCar;

/**
 * Class BarGraphicView
 * 
 * This class is the BarGraphicView class that will extends from View.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class BarGraphicView extends View {	
	/**
	 * @param values This param contains the values for the graph.
	 */
	private double[] values;
	
	/**
	 * @param labels This param contains the labels for the graph.
	 */
	private String[] labels;
	
	/**
	 * @param colors This param contains the colors for the graph.
	 */
	private Color[] colors;
	
	/**
	 * @param title This param contains the title for the graph.
	 */
	private String title;
	
	/**
     * Constructor for objects of class CarPark
     */
    public BarGraphicView() {
    
    }

    /**
     * Overridden. Tell the GUI manager how big we would like to be.
     *
     * @return Dimension This return will return a preferred dimension.
     */
    public Dimension getPreferredSize() {
        return new Dimension(400, 400);
    }

    
    /**
     * This method will update the data in the graph.
     * 
     * @param model This param contains the model with all the data.
     */
	@Override
	protected void update(Model model) {
		Garage garage = (Garage) model;
		
		title = "Parked Cars";
	    values = new double[]{garage.getNumberOfTakenSpotsByRegular(), garage.getNumberOfTakenSpotsBySubscription(), garage.getNumberOfTakenSpotsByReservation()};
	    labels = new String[]{"Regular " + garage.getNumberOfTakenSpotsByRegular(),"Subscription " + garage.getNumberOfTakenSpotsBySubscription(), "Reservation " + garage.getNumberOfTakenSpotsByReservation()};
	    colors = new Color[]{	        
    		RegularCar.color,
    		SubscriptionCar.color,
    		ReservationCar.colorCar,
	    };
	    
		repaint();
	}
	
	/**
	 * This method will draw the graph.
	 * 
	 * @param g This param contains the graphics of the view.
	 */
	private void drawGraphic(Graphics g) {   
		super.paintComponent(g);
	    if (values == null || values.length == 0) {
	      return;
	    }
	 
	    double minValue = 0;
	    double maxValue = 300;
	    for (int i = 0; i < values.length; i++) {
	      if (minValue > values[i]) {
	        minValue = values[i];
	      }
	      if (maxValue < values[i]) {
	        maxValue = values[i];
	      }
	    }
	 
	    Dimension dim = getSize();
	    int panelWidth = dim.width;
	    int panelHeight = dim.height;
	    int barWidth = panelWidth / (values.length);
	 
	    Font titleFont = new Font("Book Antiqua", Font.BOLD, 15);
	    FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
	 
	    Font labelFont = new Font("Book Antiqua", Font.PLAIN, 14);
	    FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
	 
	    int titleWidth = titleFontMetrics.stringWidth(title);
	    int stringHeight = titleFontMetrics.getAscent();
	    int stringWidth = (panelWidth - titleWidth) / 2;
	    g.setFont(titleFont);
	    g.drawString(title, stringWidth, stringHeight);
	 
	    int top = titleFontMetrics.getHeight() + 50;
	    int bottom = labelFontMetrics.getHeight();
	    if (maxValue == minValue) {
	      return;
	    }
	    double scale = (panelHeight - top - bottom) / (maxValue - minValue);
	    stringHeight = panelHeight - labelFontMetrics.getDescent();
	    g.setFont(labelFont);
	    for (int j = 0; j < values.length; j++) {
	      int valueP = j * barWidth + 1;
	      int valueQ = top;
	      int height = (int) (values[j] * scale);
	      if (values[j] >= 0) {
	        valueQ += (int) ((maxValue - values[j]) * scale);
	      } else {
	        valueQ += (int) (maxValue * scale);
	        height = -height;
	      }
	 
	      g.setColor(colors[j]);
	      g.fillRect(valueP, valueQ, barWidth - 2, height);
	      g.setColor(Color.black);
	      g.drawRect(valueP, valueQ, barWidth - 2, height);
	 
	      int labelWidth = labelFontMetrics.stringWidth(labels[j]);
	      stringWidth = j * barWidth + (barWidth - labelWidth) / 2;
	      g.drawString(labels[j], stringWidth, stringHeight);
	    }
	}
	
	/**
	 * This method will repaint the component.
	 * 
	 * @param g This param contains the graphics of the view.
	 */
	public void paintComponent(Graphics g) {
		drawGraphic(g);
	}	
}
