package nl.hanze.st.parkeersimulator.view;

import java.awt.*;
import java.awt.geom.*;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;

public class BarGraphicView extends View {	
    private Image BarGraphicImage;
	private Dimension size;
	private double[] values;
	private String[] labels;
	private Color[] colors;
	private String title;
	
	/**
     * Constructor for objects of class CarPark
     */
    public BarGraphicView() {
    	size = new Dimension(0, 0);
    }

    /**
     * Overridden. Tell the GUI manager how big we would like to be.
     */
    public Dimension getPreferredSize() {
        return new Dimension(800, 400);
    }

	@Override
	protected void update(Model model) {
		Garage garage = (Garage) model;
		
		if (!size.equals(getSize())) {
            size = getSize();
            BarGraphicImage = createImage(size.width, size.height);
        }
		title = "Parked Cars";
	    values = new double[]{ garage.getNumberOfTakenSpotsByRegular(), garage.getNumberOfTakenSpotsBySubscription()};
	    labels = new String[]{"Regular","Subscription"};
	    colors = new Color[]{	        
    		Color.red,
	        Color.blue
	    };
	    
		repaint();
	}
	
	private void drawGraphic(Graphics g) {   
		super.paintComponent(g);
	    if (values == null || values.length == 0) {
	      return;
	    }
	 
	    double minValue = 0;
	    double maxValue = 540;
	    		
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
	    
	    // We don't need dynamic barsize, hardcodes is fine.
	    //int barWidth = panelWidth / (values.length + 4);
	    
	    int barWidth = 80;
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
	      g.fillRect(valueP, valueQ, barWidth, height); //3rd value used to be -2
	      g.setColor(Color.black);
	      g.drawRect(valueP, valueQ, barWidth, height);
	 
	      int labelWidth = labelFontMetrics.stringWidth(labels[j]);
	      stringWidth = j * barWidth + (barWidth - labelWidth) / 2;
	      g.drawString(labels[j], stringWidth, stringHeight);
	    }
	}
	
	public void paintComponent(Graphics g) {
		drawGraphic(g);
	}	
}
