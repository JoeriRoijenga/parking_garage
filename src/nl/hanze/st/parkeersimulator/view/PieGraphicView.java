package nl.hanze.st.parkeersimulator.view;

import java.awt.*;
import java.awt.geom.*;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;
import nl.hanze.st.parkeersimulator.model.RegularCar;
import nl.hanze.st.parkeersimulator.model.SubscriptionCar;

public class PieGraphicView extends View {	
    private Image pieGraphicImage;
	private Dimension size;
	private double degreeRegular;
	private double degreeSubscription;
	private double degreeReservation;
	
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
		
		int regular = garage.getNumberOfTakenSpotsByRegular();
		int subscription = garage.getNumberOfTakenSpotsBySubscription();
		int reservation = garage.getNumberOfTakenSpotsByReservation();
		int total = subscription + regular + reservation;
		
		double percentageRegular = ((double) regular / (double) total) * 100;
		double percentageSubscription = ((double) subscription / (double) total) * 100;
		double percentageReservation = ((double) reservation / (double) total) * 100;
		
		degreeRegular = Math.round(360 * (percentageRegular / 100)); // 253.0
		degreeSubscription = Math.round(360 * (percentageSubscription / 100)); // 253.0				
		degreeReservation = Math.round(360 * (percentageReservation / 100)); // 253.0
		repaint();
	}
	
	private void drawGraphic(Graphics g) {   
		Graphics2D g2 = (Graphics2D) g;
		
		Arc2D.Double arc = new Arc2D.Double(Arc2D.PIE);
		arc.setFrame(100, 20, 300, 300);
		
		arc.setAngleStart(0);
	    arc.setAngleExtent(degreeRegular);
	    g2.setColor(Color.white);
	    g2.draw(arc);
	    g2.setColor(RegularCar.color);
	    g2.fill(arc);
	    
	    arc.setAngleStart(degreeRegular);
	    arc.setAngleExtent(degreeSubscription);
	    g2.setColor(Color.white);
	    g2.draw(arc);
	    g2.setColor(SubscriptionCar.color);
	    g2.fill(arc);
	    
	    arc.setAngleStart(degreeSubscription);
	    arc.setAngleExtent(degreeReservation);
	    g2.setColor(Color.white);
	    g2.draw(arc);
	    g2.setColor(Color.green);
	    g2.fill(arc);
	}
	
	public void paintComponent(Graphics g) {
		drawGraphic(g);
	}	
}
