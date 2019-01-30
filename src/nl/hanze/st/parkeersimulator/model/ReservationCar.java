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
	private Color color;
	private String company;
	
	/**
	 * Constructor
	 * 
	 * @param company name of the company the car is part of
	 */
	public ReservationCar(String company, Color color) {
		this.company = company;
		this.color = color;
	}
	
	/**
	 * This method returns the name of the company the car is part of.
	 * 
	 * @return name of the company
	 */
	String getCompany() {
		return company;
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