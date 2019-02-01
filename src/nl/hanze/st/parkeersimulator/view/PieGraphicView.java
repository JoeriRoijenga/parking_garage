package nl.hanze.st.parkeersimulator.view;

import java.awt.*;
import java.awt.geom.*;
import java.util.Arrays;
import java.util.Collections;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;
import nl.hanze.st.parkeersimulator.model.RegularCar;
import nl.hanze.st.parkeersimulator.model.ReservationCar;
import nl.hanze.st.parkeersimulator.model.SubscriptionCar;

/**
 * Class PieGraphicView
 * 
 * This class is the PieGraphicView class that will extends from View.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class PieGraphicView extends View {	
	/**
	 * @param degreeRegular This param contains the degrees for the regular cars.
	 */
	private double degreeRegular;
	
	/**
	 * @param degreeSubscription This param contains the degrees for the subscription cars.
	 */
	private double degreeSubscription;
	
	/**
	 * @param degreeReservation This param contains the degrees for the reservation cars.
	 */
	private double degreeReservation;

	/**
	 * @param startRegularDegree This param contains the starting position for regular.
	 */
	private double startRegularDegree;
	
	/**
	 * @param startSubscriptionDegree This param contains the starting position for subscription.
	 */
	private double startSubscriptionDegree;
	
	/**
	 * @param startReservationDegree This param contains the starting position for reservation.
	 */
	private double startReservationDegree;
	
	/**
     * Constructor for objects of class CarPark
     */
    public PieGraphicView() {

    }

    /**
     * Overridden. Tell the GUI manager how big we would like to be.
     */
    public Dimension getPreferredSize() {
        return new Dimension(400, 350);
    }

    /**
     * This method will update the information for the graph.
     * 
     * @param model This param contains the model that's in use for the view.
     */
	@Override
	protected void update(Model model) {
		Garage garage = (Garage) model;
		
		int regular = garage.getNumberOfTakenSpotsByRegular();
		int subscription = garage.getNumberOfTakenSpotsBySubscription();
		int reservation = garage.getNumberOfTakenSpotsByReservation();
		int total = subscription + regular + reservation;
		
		double percentageRegular = ((double) regular / (double) total) * 100;
		double percentageSubscription = ((double) subscription / (double) total) * 100;
		double percentageReservation = ((double) reservation / (double) total) * 100;
		
		degreeRegular = Math.round(360 * (percentageRegular / 100));
		degreeSubscription = Math.round(360 * (percentageSubscription / 100));				
		degreeReservation = Math.round(360 * (percentageReservation / 100));
	
		Double[] degree = { degreeRegular, degreeSubscription, degreeReservation };
		
		Double min = Collections.min(Arrays.asList(degree));
				
		if (min.intValue() == (int) degreeRegular) {
			degreeRegular = degreeRegular * -1;
			startRegularDegree = 0;
			startReservationDegree = 0;
			startSubscriptionDegree = degreeReservation;
		} else if (min.intValue() == (int) degreeSubscription) {
			degreeSubscription = degreeSubscription * -1;
			startSubscriptionDegree = 0;
			startReservationDegree = degreeRegular;
		} else if (min.intValue() == (int) degreeReservation) {
			degreeReservation = degreeReservation * -1;
			startReservationDegree = 0;
			startSubscriptionDegree = degreeRegular;
		}
		
		repaint();
	}
	
	/**
	 * This method will draw the graph.
	 * 
	 * @param g This param contains the graphics of the view.
	 */
	private void drawGraphic(Graphics g) {   
		Graphics2D g2 = (Graphics2D) g;
		
		Arc2D.Double arc = new Arc2D.Double(Arc2D.PIE);
		arc.setFrame(50, 20, 300, 300);
		
		arc.setAngleStart(startRegularDegree);
	    arc.setAngleExtent(degreeRegular);
	    g2.setColor(Color.white);
	    g2.draw(arc);
	    g2.setColor(RegularCar.color);
	    g2.fill(arc);
	    
	    arc.setAngleStart(startSubscriptionDegree);
	    arc.setAngleExtent(degreeSubscription);
	    g2.setColor(Color.white);
	    g2.draw(arc);
	    g2.setColor(SubscriptionCar.color);
	    g2.fill(arc);
	    
	    arc.setAngleStart(startReservationDegree);
	    arc.setAngleExtent(degreeReservation);
	    g2.setColor(Color.white);
	    g2.draw(arc);
	    g2.setColor(ReservationCar.colorCar);
	    g2.fill(arc);
	}
	
	/**
	 * This method will repaint the graph.
	 * 
	 * @param g This param contains the graphics for the view.
	 */
	public void paintComponent(Graphics g) {
		drawGraphic(g);
	}	
}
