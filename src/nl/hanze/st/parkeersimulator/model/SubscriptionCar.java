package nl.hanze.st.parkeersimulator.model;

import java.awt.Color;

public class SubscriptionCar extends Car {
	public static final Color color = Color.BLUE;

	public SubscriptionCar() {
		this.setHasToPay(false);
	}

	public Color getColor() {
		return color;
	}
}
