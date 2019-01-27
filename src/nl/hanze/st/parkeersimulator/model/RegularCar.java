package nl.hanze.st.parkeersimulator.model;

import java.awt.Color;

public class RegularCar extends Car {
	public static final Color color = Color.RED;

	public RegularCar() {
		this.setHasToPay(true);
	}

	public Color getColor() {
		return color;
	}
}
