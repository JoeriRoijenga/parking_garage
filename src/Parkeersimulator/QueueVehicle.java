package Parkeersimulator;
import java.util.*;

public abstract class QueueVehicle {

	protected Queue<Vehicle> vehicleQueue;
	
	
	
    protected boolean addVehicle(Vehicle vehicle) {
        return queue.add(vehicle);
    }

    protected Vehicle removeVehicle() {
        return queue.poll();
    }

    protected int vehiclesInQueue(){
    	return queue.size();
    }
}
