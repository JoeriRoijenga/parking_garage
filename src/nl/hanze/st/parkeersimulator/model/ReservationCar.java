package nl.hanze.st.parkeersimulator.model;

import java.awt.Color;

public class ReservationCar extends Car {
	private static final Color color = Color.GRAY;
	private String company;
	
	public ReservationCar(String company) {
		this.company = company;
	}
	
	String getCompany() {
		return company;
	}

	public Color getColor() {
		return color;
	}
}
