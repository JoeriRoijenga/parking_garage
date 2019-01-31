package nl.hanze.st.parkeersimulator.model;

import java.util.*;

/**
 * Class CustomerQueue
 * 
 * This class will contain the queue properties.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class CustomerQueue {
	
	/**
	 * @param maxQueueSize This param is a shared int with the maximum queue size.
	 */
	private static int maxQueueSize;
	
	/**
	 * @param queue This param will contain a list of vehicles.
	 */
	private Queue<Vehicle> queue;

	/**
	 * Constructor
	 */
	public CustomerQueue(int maxCarsInQueue) {
		queue = new LinkedList<Vehicle>();
		maxQueueSize = maxCarsInQueue;
	}

	/**
	 * This method will retrieve the queue size.
	 * 
	 * @return int This return will return the size of the queue.
	 */
	public int carsInQueue() {
		return queue.size();
	}
	
	/**
	 * This method will remove the car from the queue.
	 * 
	 * @return Vehicle This return will return the removed car.
	 */
	public Vehicle removeCar() {
		return queue.poll();
	}

	/**
	 * This method will add a new car to the queue.
	 * 
	 * @param vehicle This param will contain the new vehicle.
	 */
	public void addCar(Vehicle vehicle) {
		queue.add(vehicle);
	}
	
	/**
	 * This method will return the collection with all the vehicles.
	 * 
	 * @return This return will return the collection with all the vehicles.
	 */
	public LinkedList<Vehicle> getQueue() {
		return (LinkedList<Vehicle>) queue;
	}
	
	/**
	 * This method will return the maxQueueSize.
	 * 
	 * @return maxQueueSize This return will return the maxQueueSize.
	 */
	public static int getMaxQueueSize() {
		return maxQueueSize;
	}

}
