package nl.hanze.st.parkeersimulator.model;

import java.util.*;

public class QueueCar extends QueueVehicle {

	private Queue<Car> queue = new LinkedList<>();

	/**
	 * Adds a Vehicle to the queue.
	 * 
	 * @param car
	 * @return boolean
	 */
    public boolean addCar(Car car) {
        return queue.add(car);
    }

    /**
     * Removes a Vehicle from the queue
     * 
     * @return Car
     */
    public Car removeCar() {
        return queue.poll();
    }

    /**
     * Return the size of the queue
     * 
     * @return integer
     */
    public int CarsInQueue(){
    	return queue.size();
    }

}
