package nl.hanze.st.parkeersimulator.model;

import java.awt.Color;

/**
 * Class ReservationCar
 * 
 * This class will contain all the information of the reservationCars that we will need to use in every view. In will also inherit all the other models.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */

public class ReservationCar extends Car {
	/**
	 * @param color This param contains the standard color of the reservation car.
	 */
	public static final Color colorCar = Color.GREEN;
	
	/**
	 * This param contains the color per company.
	 */
	private Color color;
	
	/**
	 * This String contains the company.
	 */
	private String company;
	
	/**
	 * This int contains the key
	 */
	private int key;
	
	/**
	 * Constructor
	 * 
	 * @param company name of the company the car is part of
	 */
	public ReservationCar(String company, Color color, int key) {
		this.company = company;
		this.color = color;
		this.key = key;
		this.setHasToPay(true);
	}
	
	/**
	 * This method returns the name of the company the car is part of.
	 * 
	 * @return name of the company
	 */
	public String getCompany() {
		return company;
	}
	
	public int getKey() {
		return key;
	}
	
	/**
	 * This method returns the color of the company.
	 * 
	 * @return Color the color of the car 
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * This method sets the color for the reservation car
	 * 
	 * @param color the color of the car
	 */
	public void setColor(Color color) {
		this.color = color;
	}
}