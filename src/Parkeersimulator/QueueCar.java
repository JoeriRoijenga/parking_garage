package Parkeersimulator;

import java.util.*;

public class QueueCar extends QueueVehicle {

	
	//private Queue<Car> carqueue;
	private Queue<Car> carqueue;
	

	public QueueCar() {
		 carqueue = (Queue<Car>)vehicleQueue;
	}

}
