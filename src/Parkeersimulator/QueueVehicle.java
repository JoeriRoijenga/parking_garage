package Parkeersimulator;
import java.util.*;

public abstract class QueueVehicle {

	protected Queue<Vehicle> vehicleQueue;
	
	
	
    protected boolean addVehicle(Vehicle vehicle) {
        return vehicleQueue.add(vehicle);
    }

    protected Vehicle removeVehicle() {
        return vehicleQueue.poll();
    }

    protected int vehiclesInQueue(){
    	return vehicleQueue.size();
    }
}
