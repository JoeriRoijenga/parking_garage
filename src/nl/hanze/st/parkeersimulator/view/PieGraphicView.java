package nl.hanze.st.parkeersimulator.view;

import java.awt.*;
import java.awt.geom.*;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;

public class PieGraphicView extends View {	
    private Image pieGraphicImage;
	private Dimension size;
	/**
     * Constructor for objects of class CarPark
     */
    public PieGraphicView() {
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
            pieGraphicImage = createImage(size.width, size.height);
        }
		
		Graphics g = pieGraphicImage.getGraphics();
        
		drawGraphic(g, garage.getNumberOfOpenSpots());
	}
	
	private void drawGraphic(Graphics g, int a) {
		double total = a + 100;	
		
		double percentage = (a / total) * 100;
		int degree = (int) Math.round((360 / 100) * percentage); // 253.0
		int leftover = Math.round(360 - degree); // 107.0

		Graphics2D g2 = (Graphics2D) g;
		
		Arc2D arc = new Arc2D.Float(Arc2D.PIE);
		arc.setFrame(100, 20, 300, 300);
		
		arc.setAngleStart(0);
	    arc.setAngleExtent(degree);
	    g2.setColor(Color.gray);
	    g2.draw(arc);
	    g2.setColor(Color.red);
	    g2.fill(arc);
	 
	    arc.setAngleStart(degree);
	    arc.setAngleExtent(leftover);
	    g2.setColor(Color.gray);
	    g2.draw(arc);
	    g2.setColor(Color.blue);
	    g2.fill(arc);
	}
	
	public void paintComponent(Graphics g) {
		if (pieGraphicImage == null) {
            return;
        }
		
		drawGraphic(g, 0);
	}	
}
