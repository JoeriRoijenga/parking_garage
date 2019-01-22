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
        
		drawGraphic(g, garage.getNumberOfTakenSpotsByRegular(), garage.getNumberOfTakenSpotsBySubscription());
	}
	
	private void drawGraphic(Graphics g, double regular, double subscription) {   
		double degreeRegular = 0;
		double degreeSubscription = 0;
		if (regular != 0 & subscription != 0) {
			double total = subscription + regular;
			
			double percentageRegular = (regular / total) * 100;
			double percentageSubscription = (subscription / total) * 100;
			
			degreeRegular = Math.round(360 * (percentageRegular / 100)); // 253.0
			degreeSubscription = Math.round(360 * (percentageSubscription / 100)); // 253.0				
			
			/*
			g.setColor(Color.WHITE);
			g.fillRect(100, 20, 300, 300);
			g.setColor(Color.RED);
			
			g.fillArc(165, 80, 180, 180, 0, 80);
			g.setColor(Color.BLUE);

			g.fillArc(165, 80, 180, 180, 80, 120);
			*/
			/*
			Graphics2D g2 = (Graphics2D) g;
			
			Arc2D arc = new Arc2D.Float(Arc2D.PIE);
			arc.setFrame(100, 20, 300, 300);
			
			arc.setAngleStart(0);
		    arc.setAngleExtent(240);
		    g2.setColor(Color.gray);
		    g2.draw(arc);
		    g2.setColor(Color.red);
		    g2.fill(arc);
			 */
		    /*
		    arc.setAngleStart(degree);
		    arc.setAngleExtent(leftover);
		    g2.setColor(Color.gray);
		    g2.draw(arc);
		    g2.setColor(Color.blue);
		    g2.fill(arc);
		    */
		}
		
		Graphics2D g2 = (Graphics2D) g;
		
		Arc2D arc = new Arc2D.Float(Arc2D.PIE);
		arc.setFrame(100, 20, 300, 300);
		
		arc.setAngleStart(0);
	    arc.setAngleExtent(degreeRegular);
	    g2.setColor(Color.gray);
	    g2.draw(arc);
	    g2.setColor(Color.red);
	    g2.fill(arc);
	    /*
	    arc.setAngleStart(degreeRegular);
	    arc.setAngleExtent(degreeSubscription);
	    g2.setColor(Color.gray);
	    g2.draw(arc);
	    g2.setColor(Color.blue);
	    g2.fill(arc);
	    */
	    
	    System.out.println("Regular: " + degreeRegular);
	    System.out.println("Subscription: " + degreeSubscription);
	    System.out.println("Total: " + (degreeSubscription + degreeRegular));
	    System.out.println("------");
	}
	
	public void paintComponent(Graphics g) {
		if (pieGraphicImage == null) {
            return;
        }
		
		drawGraphic(g, 0, 0);
	}	
}
