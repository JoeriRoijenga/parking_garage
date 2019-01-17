package nl.hanze.st.parkeersimulator.model;

import nl.hanze.st.mvc.Model;

public class Garage extends Model{
	private Vehicle[][][] vehicles;
	private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    
	public Garage(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
		this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
		vehicles = new Vehicle[numberOfFloors][numberOfRows][numberOfPlaces];
	}
	
	public void start() {
		notifyView();
	}
	
	public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getNumberOfOpenSpots(){
    	return numberOfOpenSpots;
    }
	
	public Vehicle getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return vehicles[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, Vehicle vehicle) {
        if (!locationIsValid(location)) {
            return false;
        }
        Vehicle oldVehicle = getCarAt(location);
        if (oldVehicle == null) {
            vehicles[location.getFloor()][location.getRow()][location.getPlace()] = vehicle;
            vehicle.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    public Vehicle removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Vehicle vehicle = getCarAt(location);
        if (vehicle == null) {
            return null;
        }
        vehicles[location.getFloor()][location.getRow()][location.getPlace()] = null;
        vehicle.setLocation(null);
        numberOfOpenSpots++;
        return vehicle;
    }
    

    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    public Vehicle getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Vehicle vehicle = getCarAt(location);
                    if (vehicle != null && vehicle.getMinutesLeft() <= 0 && !vehicle.getIsPaying()) {
                        return vehicle;
                    }
                }
            }
        }
        return null;
    }

    public void tick() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Vehicle vehicle = getCarAt(location);
                    if (vehicle != null) {
                    	vehicle.tick();
                    }
                }
            }
        }
    }
    
    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
}
