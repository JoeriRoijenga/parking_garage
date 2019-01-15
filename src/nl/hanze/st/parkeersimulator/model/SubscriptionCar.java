package nl.hanze.st.parkeersimulator.model;

import java.awt.Color;

public class SubscriptionCar extends Car {
	private static final Color color = Color.BLUE;
	
	public SubscriptionCar() {
	
	}
	
	public Color getColor() {
		return color;
	}
}
