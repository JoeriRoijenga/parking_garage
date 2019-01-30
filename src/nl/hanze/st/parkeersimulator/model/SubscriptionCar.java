package nl.hanze.st.parkeersimulator.model;

import java.awt.Color;

/**
 * Abstract Class SubscriptionCar
 * 
 * This class is the SubscriptionCar class that contains all the properties of the subscription cars.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class SubscriptionCar extends Car {
	/**
	 * @param color This param contains the color of the regular car.
	 */
	public static final Color color = Color.BLUE;

	/** 
	 * Constructor
	 */
	public SubscriptionCar() {
		this.setHasToPay(false);
	}

	/**
	 * This method will retrieve the color.
	 * 
	 * @return color This return will return the current color.
	 */
	public Color getColor() {
		return color;
	}
}
