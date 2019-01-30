package nl.hanze.st.parkeersimulator.model;

import java.awt.Color;

/**
 * Abstract Class RegularCar
 * 
 * This class is the RegularCar class that contains all the properties of the regular cars.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class RegularCar extends Car {
	/**
	 * @param color This param contains the color of the regular car.
	 */
	public static final Color color = Color.RED;

	/**
	 * Constructor
	 */
	public RegularCar() {
		this.setHasToPay(true);
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
