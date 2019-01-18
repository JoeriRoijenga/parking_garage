package nl.hanze.st.parkeersimulator.model;

import java.util.Random;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.Model;

/**
 * Class Garage
 * 
 * This class will contain all the business logic that we will need to use in every view. In will also inherit all the other models.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class Garage extends Model {
	/**
	 * @param REGULAR This param contains a string object with the number used for regular cars.
	 */
	private static final String REGULAR = "1";
	
	/**
	 * @param SUBSCRIPTION This param contains a string object with the number used for subscription cars.
	 */
	private static final String SUBSCRIPTION = "2";
	
	/**
	 * @param vehicles This param will contain all the cars in the parking lot.
	 */
	private Vehicle[][][] vehicles;
	
	/**
	 * @param numberOfFloors This param contains all the floors of the parking lot.
	 */
	private int numberOfFloors;
	
	/**
	 * @param numberOfRows This param contains all the rows per floor.
	 */
    private int numberOfRows;
    
    /**
     * @param numberOfPlaces This param contains all the places per row.
     */
    private int numberOfPlaces;
    
    /**
     * @param numberOfOpenSpots This param contains the amount of open spots in the garage.
     */
    private int numberOfOpenSpots;
    
    /**
     * @param entranceCarQueue This param contains all the regular cars at the entrance.
     */
    private CustomerQueue entranceCarQueue;

    /**
     * @param entrancePassQueue This param contains all the subscription cars at the entrance.
     */
    private CustomerQueue entrancePassQueue;

    /**
     * @param paymentCarQueue This param contains the queue of all the cars that want to pay.
     */
    private CustomerQueue paymentCarQueue;
    
    /**
     * @param exitCarQueue This param contains the queue of all the cars that want to leave.
     */
    private CustomerQueue exitCarQueue;
    
    /**
     * @param day This param contains the day.
     */
    private int day = 0;
    
    /**
     * @param hour This param contains the hour of the day.
     */
    private int hour = 0;
    
    /**
     * @param minute This param contains the minute of the hour.
     */
    private int minute = 0;
    
    /**
     * @param tickPause This param contains the pause between every tick.
     */
    private int tickPause = 100;

    /**
     * @param weekDayArrivals This param contains the average number of regular car arrivals on weekdays per hour.
     */
    int weekDayArrivals= 100; 
   
    /**
     * @param weekendArrivals this param contains the average number of regular car arrivals in the weekends per hour. 
     */
    int weekendArrivals = 200;

    /**
     * @param weekDayPassArrivals this param contains the average number of subscription car arrivals in the weekends per hour. 
     */
    int weekDayPassArrivals= 50;

    /**
     * @param weekendPassArrivals this param contains the average number of subscription car arrivals in the weekends per hour. 
     */
    int weekendPassArrivals = 5;

    /**
     * @param enterspeed This param will contain the speed of vehicles entering.
     */
    int enterSpeed = 3;

    /**
     * @param paymentSpeed This param will contain the speed of vehicles paying.
     */
    int paymentSpeed = 7;

    /**
     * @param exitSpeed This param will contain the speed of vehicles leaving.
     */
    int exitSpeed = 5;

    /**
     * Constructor
     * 
     * @param numberOfFloors This param contains the number of floors.
     * @param numberOfRows This param contains the number of rows per floor.
     * @param numberOfPlaces This param contains the number of places per row.
     */
	public Garage(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
		entranceCarQueue = new CustomerQueue();
        entrancePassQueue = new CustomerQueue();
        paymentCarQueue = new CustomerQueue();
        exitCarQueue = new CustomerQueue();
        
		this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
		vehicles = new Vehicle[numberOfFloors][numberOfRows][numberOfPlaces];
	}
	
	/** 
	 * This method will retrieve the number of floors.
	 * 
	 * @return numberOfFloors This return will return the number of floors.
	 */
	public int getNumberOfFloors() {
        return numberOfFloors;
    }

	/** 
	 * This method will retrieve the number of rows per floor.
	 * 
	 * @return numberOfRows This return will return the number of rows per floor.
	 */
    public int getNumberOfRows() {
        return numberOfRows;
    }

	/** 
	 * This method will retrieve the number of places per row.
	 * 
	 * @return numberOfPlaces This return will return the number of places per row.
	 */
    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

	/** 
	 * This method will retrieve the number of open spots in the garage.
	 * 
	 * @return numberOfOpenSpots This return will return the number of open spots in the garage.
	 */
    public int getNumberOfOpenSpots(){
    	return numberOfOpenSpots;
    }
    
	/**
	 * This method will run the simulation of the garage.
	 */
    public void tickThread() {
    	while (true) {
    		advanceTime();    
	    	handleExit();
	    	updateViews();
	    	// Pause.
	        try {
	            Thread.sleep(tickPause);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    	handleEntrance();
    	}
    }
    
    /**
     * This method will increment the minutes and calculate the right time.
     */
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

    /**
     * This method will handle the entrance on entering and arrivals of the vehicles.
     */
    private void handleEntrance(){
    	carsArriving();
    	carsEntering(entrancePassQueue);
    	carsEntering(entranceCarQueue);  	
    }
    
    /**
     * This method will handle the exist on leaving and payments of the vehicles.
     */
    private void handleExit(){
        carsReadyToLeave();
        carsPaying();
        carsLeaving();
    }
    
    /**
     * This method will update the current view.
     */
    private void updateViews(){
    	tick();
        // Update the car park view.
        notifyView();	
    }
    
    /**
     * This method will retrieve the car at the current location.
     * 
     * @param location This param will contain the current location used.
     * @return Vehicle This return will return the vehicle at the current location
     */
	public Vehicle getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return vehicles[location.getFloor()][location.getRow()][location.getPlace()];
    }

	/**
	 * This method will set a new car at the current location.
	 * 
	 * @param location This param contains the current location used.
	 * @param vehicle This param contains the curren vehicle used.
	 * @return boolean This return will return a success or a fail.
	 */
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

    /**
     * This method will remove the car at the current location.
     * 
     * @param location This param contains the current location used.
     * @return Vehicle This return will return the removed vehicle.
     */
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
    

    /**
     * This method will check the first free location.
     * 
     * @return location This method will return the first location that's free.
     */
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

    /** 
     * This method will retrieve the first car that has to leave.
     * 
     * @return This return will return the first car that has to leave.
     */
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

    /**
     * This method contains the tick for the data, it will update all locations and cars.
     */
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
    
    /**
     * This method will check if the location is valid to use.
     * 
     * @param location This param contains the location that will be used.
     * @return boolean This param contains the boolean if a location is valid or not.
     */
    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }
    
    /**
     * This method will check if there are any cars arriving.
     */
    private void carsArriving(){
    	int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, REGULAR);    	
    	numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, SUBSCRIPTION);    	
    }

    /** 
     * This method will let the cars enter the garage.
     * 
     * @param queue This param contains the queue of vehicle that are waiting.
     */
    private void carsEntering(CustomerQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
    	while (queue.carsInQueue()>0 && 
    			getNumberOfOpenSpots()>0 && 
    			i<enterSpeed) {
            Vehicle vehicle = queue.removeCar();
            Location freeLocation = getFirstFreeLocation();
            setCarAt(freeLocation, vehicle);
            i++;
        }
    }

    /**
     * This method will check if there are any vehicles ready to leave.
     */
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

    
    /**
     * This method will check if there are any vehicles paying.
     */
    private void carsPaying(){
        // Let cars pay.
    	int i=0;
    	while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Vehicle vehicle = paymentCarQueue.removeCar();
            // TODO Handle payment.
            carLeavesSpot(vehicle);
            i++;
    	}
    }
    
    /** 
     * This method will check if there are any vehicles leaving.
     */
    private void carsLeaving(){
        // Let cars leave.
    	int i=0;
    	while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
    	}	
    }
    
    /** 
     * This method will check the amount of cars.
     * 
     * @param weekDay This param contains the week day arrivals.
     * @param weekend This param contains the weekend arrivals.
     * @return int This return will return the number of cars arriving.
     */
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
    
    /**
     * This method will add arriving cars to the queues.
     * 
     * @param numberOfCars This param contains the amount of waiting vehicles.
     * @param type This param contains the type of vehicles.
     */
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
    
    /**
     * This method will check if any vehicles will leave their spot.
     * @param vehicle This param contains the current vehicle used.
     */
    private void carLeavesSpot(Vehicle vehicle){
    	removeCarAt(vehicle.getLocation());
        exitCarQueue.addCar(vehicle);
    }
}
