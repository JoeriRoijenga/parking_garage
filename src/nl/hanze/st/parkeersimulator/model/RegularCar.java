package nl.hanze.st.parkeersimulator.model;

import java.awt.Color;

public class RegularCar extends Car {
	private static final Color color = Color.RED;
	
	public RegularCar() {
		
	}
	
	public Color getColor() {
		return color;
	}
}
