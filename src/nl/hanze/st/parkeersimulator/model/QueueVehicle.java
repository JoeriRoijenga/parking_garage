package nl.hanze.st.parkeersimulator.model;

import java.util.*;

public abstract class QueueVehicle {

    public boolean addVehicle(Car car) {
        return queue.add(car);
    }

    public Car removeVehicle() {
        return queue.poll();
    }

    public int vehiclesInQueue(){
    	return queue.size();
    }
}
