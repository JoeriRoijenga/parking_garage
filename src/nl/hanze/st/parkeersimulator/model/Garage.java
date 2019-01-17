package nl.hanze.st.parkeersimulator.model;

import java.util.Random;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.Model;

public class Garage extends Model {
	private static final String REGULAR = "1";
	private static final String SUBSCRIPTION = "2";
	
	private Vehicle[][][] vehicles;
	private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    
    private CustomerQueue entranceCarQueue;
    private CustomerQueue entrancePassQueue;
    private CustomerQueue paymentCarQueue;
    private CustomerQueue exitCarQueue;
    
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;

    int weekDayArrivals= 100; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals= 50; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute

    
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
	
    public void tickThread() {
    	advanceTime();
    	handleExit();
    	updateViews();
    	// Pause.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	handleEntrance();
    }
    
    private void advanceTime(){
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute -= 60;
            hour++;
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

    }

    private void handleEntrance(){
    	carsArriving();
    	carsEntering(entrancePassQueue);
    	carsEntering(entranceCarQueue);  	
    }
    
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    private void updateViews(){
    	tick();
        // Update the car park view.
        notifyView();	
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
    
    private void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, REGULAR);    	
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, SUBSCRIPTION);    	
    }

    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && 
    			getNumberOfOpenSpots()>0 && 
    			i<enterSpeed) {
            Car car = queue.removeCar();
            Location freeLocation = getFirstFreeLocation();
            setCarAt(freeLocation, car);
            i++;
        }
    }

    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Vehicle vehicle = getFirstLeavingCar();
        while (vehicle!=null) {
        	if (vehicle.getHasToPay()){
        		vehicle.setIsPaying(true);
	            paymentCarQueue.addCar(vehicle);
        	}
        	else {
        		carLeavesSpot(vehicle);
        	}
        	vehicle = getFirstLeavingCar();
        }
    }

    
    private void carsPaying(){
        // Let cars pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(car);
            i++;
    	}
    }
    
    private void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
    	}	
    }
    
    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);	
    }
    
    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
    	switch(type) {
    	case REGULAR: 
            for (int i = 0; i < numberOfCars; i++) {
            	entranceCarQueue.addCar(new RegularCar());
            }
            break;
    	case SUBSCRIPTION:
            for (int i = 0; i < numberOfCars; i++) {
            	entrancePassQueue.addCar(new SubscriptionCar());
            }
            break;	            
    	}
    }
    
    private void carLeavesSpot(Vehicle vehicle){
    	removeCarAt(vehicle.getLocation());
        exitCarQueue.addCar(vehicle);
    }

