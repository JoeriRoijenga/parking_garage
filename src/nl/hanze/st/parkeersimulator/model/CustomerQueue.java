package nl.hanze.st.parkeersimulator.model;

import java.util.*;

public class CustomerQueue {
	private Queue<Vehicle> queue;

	public CustomerQueue() {
		queue = new LinkedList<Vehicle>();
	}

	public int carsInQueue() {
		return queue.size();
	}

	public Vehicle removeCar() {
		return queue.poll();
	}

	public void addCar(Vehicle vehicle) {
		queue.add(vehicle);
	}

}
