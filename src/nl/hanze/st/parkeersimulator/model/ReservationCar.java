package nl.hanze.st.parkeersimulator.model;

import java.awt.Color;

public class ReservationCar extends Car {
	private static final Color color = Color.ORANGE;

	public ReservationCar() {
		this.setHasToPay(false);
	}

	public Color getColor() {
		return color;
	}
}
