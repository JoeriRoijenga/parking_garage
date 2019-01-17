package nl.hanze.st.parkeersimulator.model;

import java.util.*;

/**
 * Class QueueVehicle
 * 
 * This class is made for editing and creating a queue, in which Vehicles are.
 * And can be used to get information from the queue.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels. 
 * @version 0.1 (16-1-2019)
 */

public abstract class QueueVehicle {
	private Queue<Car> queue = new LinkedList<>();

	/**
	 * Adds a Vehicle to the queue.
	 * 
	 * @param car
	 * @return boolean
	 */
/*
    public boolean addVehicle(Car car) {
        return queue.add(car);
    }

    /**
     * Removes a Vehicle from the queue
     * 
     * @return Car
     */
    public Car removeVehicle() {
        return queue.poll();
    }

    /**
     * Return the size of the queue
     * 
     * @return integer
     */
    public int vehiclesInQueue(){
    	return queue.size();
    }
    */
}
