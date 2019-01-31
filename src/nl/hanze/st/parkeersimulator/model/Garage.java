package nl.hanze.st.parkeersimulator.model;

import java.util.List;
import java.util.Random;
import java.util.*;
import java.awt.*;

import nl.hanze.st.mvc.Model;

/**
 * Class Garage
 *
 * This class will contain all the business logic that we will need to use in
 * every view. In will also inherit all the other models.
 *
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries.
 * @version 0.1 (18-1-2019)
 */
public class Garage extends Model implements Runnable {
	/**
	 * @param REGULAR This param contains a string object with the number used for
	 *                regular cars.
	 */
	private static final String REGULAR = "1";

	/**
	 * @param SUBSCRIPTION This param contains a string object with the number used
	 *                     for subscription cars.
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
	 * @param numberOfOpenSpots This param contains the amount of open spots in the
	 *                          garage.
	 */
	private int numberOfOpenSpots;

	private int numberOfTakenSpotsByRegular;

	private int numberOfTakenSpotsBySubscription;

	private int numberOfTakenSpotsByReservation;

	private int placesForSubscriptionCars = 120;

	/**
	 * @param entranceCarQueue This param contains all the regular cars at the
	 *                         entrance.
	 */
	private CustomerQueue entranceCarQueue;

	/**
	 * @param entrancePassQueue This param contains all the subscription cars at the
	 *                          entrance.
	 */
	private CustomerQueue entrancePassQueue;

	/**
	 * @param paymentCarQueue This param contains the queue of all the cars that
	 *                        want to pay.
	 */
	private CustomerQueue paymentCarQueue;

	private CustomerQueue reservationCarQueue;

	private Reservation reservation;

	/**
	 * @param exitCarQueue This param contains the queue of all the cars that want
	 *                     to leave.
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
	 * @param week This param contains the week as an array.
	 */
	private String[] dayArray = { "Maandag", "Dinsdag", "Woensdag", "Donderdag", "Vrijdag", "Zaterdag", "Zondag" };

	/**
	 * @param tickPause This param contains the pause between every tick.
	 */
	private int tickPause = 100;

	/**
	 * @param weekDayArrivals This param contains the average number of regular car
	 *                        arrivals on weekdays per hour.
	 */
	int weekDayArrivals = 100;

	/**
	 * @param weekendArrivals this param contains the average number of regular car
	 *                        arrivals in the weekends per hour.
	 */
	int weekendArrivals = 200;

	/**
	 * @param weekDayPassArrivals this param contains the average number of
	 *                            subscription car arrivals in the weekends per
	 *                            hour.
	 */
	int weekDayPassArrivals = 50;

	int multiReservationChance = 20;
	int reservationChance = 12; // x in 1 change a reservation
	int amountOfReservationCars = 0;
	int maxReservationSpots = 40;

	/**
	 * @param weekendPassArrivals this param contains the average number of
	 *                            subscription car arrivals in the weekends per
	 *                            hour.
	 */
	int weekendPassArrivals = 25;

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

	private boolean running;

	private int period;

	boolean automatic = true;

	int defaultQueueSize = 10;

	/**
	 * Constructor
	 *
	 * @param numberOfFloors This param contains the number of floors.
	 * @param numberOfRows   This param contains the number of rows per floor.
	 * @param numberOfPlaces This param contains the number of places per row.
	 */
	public Garage(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
		entranceCarQueue = new CustomerQueue(defaultQueueSize);
		entrancePassQueue = new CustomerQueue(defaultQueueSize);
		paymentCarQueue = new CustomerQueue(defaultQueueSize);
		reservationCarQueue = new CustomerQueue(defaultQueueSize);
		exitCarQueue = new CustomerQueue(defaultQueueSize);

		this.numberOfFloors = numberOfFloors;
		this.numberOfRows = numberOfRows;
		this.numberOfPlaces = numberOfPlaces;
		this.numberOfOpenSpots = numberOfFloors * numberOfRows * numberOfPlaces;

		this.numberOfTakenSpotsByRegular = 0;
		this.numberOfTakenSpotsBySubscription = 0;

		vehicles = new Vehicle[numberOfFloors][numberOfRows][numberOfPlaces];

		/**
		 * Makes a new reservation - parking garage Can be filled with companies
		 */
		this.reservation = new Reservation();

		ArrayList<Location> locations = new ArrayList<>();
		locations = getReservationSpots(locations, numberOfFloors, numberOfRows, this.maxReservationSpots);

		reservation.makeReservation(locations, "Shell inc", 3);
		reservation.setColor("Shell inc", Color.GREEN);

		reservation.makeReservation(locations, "Hope for paws", 1);
		reservation.setColor("Hope for paws", Color.YELLOW);

		reservation.makeReservation(locations, "KPN", 10);
		reservation.setColor("KPN", Color.GREEN);
	}

	public void handleMood(CustomerQueue customerQueue) {
		LinkedList<Vehicle> queue = customerQueue.getQueue();

		Iterator<Vehicle> itr = queue.iterator();
		while (itr.hasNext()) {
			Vehicle vehicle = (Vehicle) itr.next();

			if (vehicle.getMoodLevel() <= 0) {
				itr.remove();
			}
			vehicle.decreaseMoodLevel();
		}
	}

	/**
	 * This method will retrieve the payment speed of the cars.
	 * 
	 * @return paymentSpeed This return will return the payment speed of the cars.
	 */
	public int getPaymentSpeed() {
		return paymentSpeed;
	}

	/**
	 * This method will set the payment speed of the cars.
	 * 
	 * @param speed This is the new payment speed for the cars.
	 */
	public void setPaymentSpeed(int speed) {
		paymentSpeed = speed;
	}

	/**
	 * This method will retrieve the exit speed of the cars.
	 * 
	 * @return exitSpeed This return will return the exit speed of the cars.
	 */
	public int getExitSpeed() {
		return exitSpeed;
	}

	/**
	 * This method will set the exit speed of the cars.
	 * 
	 * @param speed This is the new exit speed for the cars.
	 */
	public void setExitSpeed(int speed) {
		exitSpeed = speed;
	}

	/**
	 * This method will retrieve the weekend arrivals of regular cars.
	 * 
	 * @return weekendArrivals This return will return the weekend arrivals of
	 *         regular cars.
	 */
	public int getWeekendArrivals() {
		return weekendArrivals;
	}

	/**
	 * This method will set the weekend arrivals of regular cars.
	 * 
	 * @param arrivals This is the new weekend arrivals of regular cars.
	 */
	public void setWeekendArrivals(int arrivals) {
		weekendArrivals = arrivals;
	}

	/**
	 * This method will retrieve the week day arrivals of regular cars.
	 * 
	 * @return weekDayArrivals This return will return the week day arrivals of
	 *         regular cars.
	 */
	public int getWeekDayArrivals() {
		return weekDayArrivals;
	}

	/**
	 * This method will set the week day arrivals of regular cars.
	 * 
	 * @param arrivals This is the new week day arrivals of regular cars.
	 */
	public void setWeekDayArrivals(int arrivals) {
		weekDayArrivals = arrivals;
	}

	/**
	 * This method will retrieve the weekend arrivals of subscription cars.
	 * 
	 * @return weekendPassArrivals This return will return the weekend arrivals of
	 *         subscription cars.
	 */
	public int getWeekendPassArrivals() {
		return weekendPassArrivals;
	}

	/**
	 * This method will set the weekend arrivals of subscription cars.
	 * 
	 * @param arrivals This is the new weekend arrivals of subscription cars.
	 */
	public void setWeekendPassArrivals(int arrivals) {
		weekendPassArrivals = arrivals;
	}

	/**
	 * This method will retrieve the week day arrivals of subscription cars.
	 * 
	 * @return weekDayPassArrivals This return will return the week day arrivals of
	 *         v cars.
	 */
	public int getWeekDayPassArrivals() {
		return weekDayPassArrivals;
	}

	/**
	 * This method will set the week day arrivals of subscription cars.
	 * 
	 * @param arrivals This is the new week day arrivals of subscription cars.
	 */
	public void setWeekDayPassArrivals(int arrivals) {
		weekDayPassArrivals = arrivals;
	}

	/**
	 * This method will retrieve the chance of cars turning cars into reservations.
	 * 
	 * @return reservationChance This return will return the chance of cars turning
	 *         cars into reservations.
	 */
	public int getReservationChance() {
		return reservationChance;
	}

	/**
	 * This method will set the chance of cars turning cars into reservations.
	 * 
	 * @param arrivals This is the new chance of cars turning cars into
	 *                 reservations.
	 */
	public void setReservationChance(int chance) {
		reservationChance = chance;
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
	 * @return numberOfOpenSpots This return will return the number of open spots in
	 *         the garage.
	 */
	public int getNumberOfOpenSpots() {
		return numberOfOpenSpots;
	}

	public int getNumberOfTakenSpotsBySubscription() {
		return numberOfTakenSpotsBySubscription;
	}

	public int getNumberOfTakenSpotsByRegular() {
		return numberOfTakenSpotsByRegular;
	}

	public int getNumberOfTakenSpotsByReservation() {
		return numberOfTakenSpotsByReservation;
	}

	public int getNumberOfRegularCarsEntranceQueue() {
		return entranceCarQueue.carsInQueue();
	}

	public int getNumberOfSubscriptionCarsEntranceQueue() {
		return entrancePassQueue.carsInQueue();
	}

	public int getNumberOfPaymentQueue() {
		return paymentCarQueue.carsInQueue();
	}

	public int getNumberOfReserverationCarsQueue() {
		return reservationCarQueue.carsInQueue();
	}

	public int getNumberOfExitCarsQueue() {
		return exitCarQueue.carsInQueue();
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
	private void advanceTime() {
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
	 * This method will handle the entrance on entering and arrivals of the
	 * vehicles.
	 */
	private void handleEntrance() {
		carsArriving();
		carsEntering(reservationCarQueue);
		carsEntering(entrancePassQueue);
		carsEntering(entranceCarQueue);

		handleMood(entrancePassQueue);
		handleMood(entranceCarQueue);
		handleMood(reservationCarQueue);
	}

	/**
	 * This method will handle the exist on leaving and payments of the vehicles.
	 */
	private void handleExit() {
		carsReadyToLeave();
		carsPaying();
		carsLeaving();
	}

	/**
	 * This method will update the current view.
	 */
	private void updateViews() {
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
	 * @param vehicle  This param contains the current vehicle used.
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

			if (vehicle instanceof RegularCar) {
				numberOfTakenSpotsByRegular++;
			} else if (vehicle instanceof SubscriptionCar) {
				numberOfTakenSpotsBySubscription++;
			} else if (vehicle instanceof ReservationCar) {
				numberOfTakenSpotsByReservation++;
			}

			numberOfOpenSpots--;
			return true;
		}

		return false;
	}

	/**
	 * Returns the first free locations starting in the last Row of the top floor
	 *
	 * @param locations Empty locations ArrayList.
	 * @param floors    Amount of floors.
	 * @param rows      Amount of rows.
	 *
	 * @return ArrayList of locations.
	 */
	public ArrayList<Location> getReservationSpots(ArrayList<Location> locations, int floors, int rows, int maxSpots) {
		int amountOfSpots = maxSpots;

		for (int r = rows; r > 0; r--) {
			for (int p = 0; p < 30; p++) {
				amountOfSpots--;
				if (amountOfSpots <= 0) {
					break;
				}
				locations.add(new Location(floors - 1, r - 1, p));

			}
		}

		return locations;
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

		if (vehicle instanceof RegularCar) {
			numberOfTakenSpotsByRegular--;
		} else if (vehicle instanceof SubscriptionCar) {
			numberOfTakenSpotsBySubscription--;
		} else if (vehicle instanceof ReservationCar) {
			numberOfTakenSpotsByReservation--;
		}

		numberOfOpenSpots++;
		return vehicle;
	}

	/**
	 * This method will check the first free location.
	 *
	 * @return location This method will return the first location that's free.
	 */
	public Location getFirstFreeLocation(Vehicle vehicle) {
		int subspaces = 0;
		for (int floor = 0; floor < getNumberOfFloors(); floor++) {
			for (int row = 0; row < getNumberOfRows(); row++) {
				for (int place = 0; place < getNumberOfPlaces(); place++) {
					subspaces++;
					if ((vehicle instanceof SubscriptionCar)) {
						Location location = new Location(floor, row, place);

						if (getCarAt(location) == null) {
							return location;
						}
					}
					if (subspaces > placesForSubscriptionCars) {
						Location location = new Location(floor, row, place);

						if (getCarAt(location) == null) {
							return location;
						}
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
	 * This method contains the tick for the data, it will update all locations and
	 * cars.
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
	 * @return boolean This param contains the boolean if a location is valid or
	 *         not.
	 */
	private boolean locationIsValid(Location location) {
		
		//NULL POINTER EXCEPTION hier
		
		int floor = location.getFloor();
		int row = location.getRow();
		int place = location.getPlace();
		if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0
				|| place > numberOfPlaces) {
			return false;
		}
		return true;
	}

	/**
	 * This method will check if there are any cars arriving.
	 */
	private void carsArriving() {
		Random random = new Random();
		int amount = 1;
		int reservationProbability = 1;
		int multiReservationProbability = 1;

		if (reservationChance != 0) {
			reservationProbability = random.nextInt(this.reservationChance);
		}

		if (reservationProbability == 0) {
			List<String> keys = new ArrayList<String>(reservation.getReservation().keySet());
			String randomCompany = keys.get(random.nextInt(reservation.getReservation().size()));

			if (multiReservationChance != 0) {
				multiReservationProbability = random.nextInt(this.multiReservationChance);
			}

			if (multiReservationProbability == 0) {
				amount = reservation.getAmountOfCars();
			}

			for (int i = 0; i < amount; i++) {
				reservationCarQueue.addCar(new ReservationCar(randomCompany, reservation.getColor(randomCompany)));
			}

		} else {
			int numberOfCars = getNumberOfCars(weekDayArrivals, weekendArrivals);
			addArrivingCars(numberOfCars, REGULAR);
			numberOfCars = getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
			addArrivingCars(numberOfCars, SUBSCRIPTION);
		}
	}

	/**
	 * This method will let the cars enter the garage.
	 *
	 * @param queue This param contains the queue of vehicle that are waiting.
	 */
	private void carsEntering(CustomerQueue queue) {
		Random random = new Random();
		// Remove reservation car from queue and give a space
		for (int i = 0; i < enterSpeed; i++) {
			ReservationCar vehicle = (ReservationCar) reservationCarQueue.removeCar();

			if (vehicle == null) {
				break;
			}

			String company = vehicle.getCompany();
			ArrayList<Location> companyLocations = reservation.getCompanyLocations(company);

			for (Location companyLocation : companyLocations) {
				if (getCarAt(companyLocation) == null) {
					int stayMinutes = (int) (15 + random.nextFloat() * 10 * 60);
					vehicle.setStayTime(stayMinutes);
					setCarAt(companyLocation, vehicle);
					break;
				}
			}

		}

		int i = 0;
		// Remove car from the front of the queue and assign to a parking space.
		while (queue.carsInQueue() > 0 && getNumberOfOpenSpots() > 0 && i < enterSpeed) {
			Vehicle vehicle = queue.removeCar();
			Location freeLocation = getFirstFreeLocation(vehicle);
			setCarAt(freeLocation, vehicle);
			i++;
		}
	}

	/**
	 * This method will check if there are any vehicles ready to leave.
	 */
	private void carsReadyToLeave() {
		// Add leaving cars to the payment queue.
		Vehicle vehicle = getFirstLeavingCar();

		while (vehicle != null) {
			if (vehicle.getHasToPay()) {
				vehicle.setIsPaying(true);
				paymentCarQueue.addCar(vehicle);
			} else {
				carLeavesSpot(vehicle);
			}
			vehicle = getFirstLeavingCar();
		}
	}

	/**
	 * This method will check if there are any vehicles paying.
	 */
	private void carsPaying() {
		// Let cars pay.
		int i = 0;
		while (paymentCarQueue.carsInQueue() > 0 && i < paymentSpeed) {
			Vehicle vehicle = paymentCarQueue.removeCar();
			// TODO Handle payment.
			carLeavesSpot(vehicle);
			i++;
		}
	}

	/**
	 * This method will check if there are any vehicles leaving.
	 */
	private void carsLeaving() {
		// Let cars leave.
		int i = 0;
		while (exitCarQueue.carsInQueue() > 0 && i < exitSpeed) {
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
        float minuteFloat = minute;
        float minutePct = (minuteFloat/60);
        float timePct = hour + minutePct;
        double rushValue = 0;
        
        


        
        
        if (day == 3 && timePct > 17 && 20 > timePct) {
        	rushValue = ((-(timePct*timePct) + (24 * timePct))/40);	
        } else if(timePct > 1 && 5.5 > timePct) {	
        	rushValue = (((0.001*(timePct*timePct) + (timePct)))/20)+0.01;
    	} else {
        	rushValue = ((-(timePct*timePct) + (24 * timePct))/80);
        }

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        
		double numberOfCarsPerHour = ((averageNumberOfCarsPerHour * rushValue) + random.nextGaussian() * standardDeviation);
		
		if (numberOfCarsPerHour <= 0) {
			numberOfCarsPerHour = 0;
		}
		
		return (int)Math.round(numberOfCarsPerHour / 60);
    }

	/**
	 * This method will add arriving cars to the queues.
	 *
	 * @param numberOfCars This param contains the amount of waiting vehicles.
	 * @param type         This param contains the type of vehicles.
	 */
	private void addArrivingCars(int numberOfCars, String type) {
		// Add the cars to the back of the queue.
		switch (type) {
		case REGULAR:
			for (int i = 0; i < numberOfCars; i++) {
				if (entranceCarQueue.carsInQueue() <= CustomerQueue.getMaxQueueSize()) {
					entranceCarQueue.addCar(new RegularCar());
				}
			}
			break;
		case SUBSCRIPTION:
			for (int i = 0; i < numberOfCars; i++) {
				if (entrancePassQueue.carsInQueue() <= CustomerQueue.getMaxQueueSize()) {
					entrancePassQueue.addCar(new SubscriptionCar());
				}
			}
			break;
		}
	}

	/**
	 * This method will return a reservation.
	 *
	 * @return reservation a reservation
	 */
	public Reservation getReservations() {
		return reservation;
	}

	/**
	 * This method will check if any vehicles will leave their spot.
	 * 
	 * @param vehicle This param contains the current vehicle used.
	 */

	private void carLeavesSpot(Vehicle vehicle) {
		removeCarAt(vehicle.getLocation());
		exitCarQueue.addCar(vehicle);
	}

	/**
	 * This method creates and starts a new Thread.
	 */
	public void start() {
		new Thread(this).start();

	}

	@Override
	public void run() {
		int i = 0;
		running = true;

		while ((running) && (i < period || automatic)) {

//            printTime();

			advanceTime();

			handleExit();

			updateViews();

			try {
				Thread.sleep(tickPause);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			handleEntrance();

			if (period > 0) {
				i++;
			}

			if (i >= period && automatic == false) {
				running = false;
			}

		}
		notifyView();

		setPeriod(0);
	}

	/**
	 * This method will print the time in the console.
	 */
	private void printTime() {
		if (minute < 10) {
			System.out.println("Dag: " + day + " " + hour + ":" + "0" + minute);
		} else {
			System.out.println("Dag: " + day + " " + hour + ":" + minute);
		}
	}

	/**
	 * This method sets the period in minutes
	 *
	 * @param int periodMinutes the period in minutes you want to set
	 */
	public void setPeriod(int periodMinutes) {
		period = periodMinutes;
	}

	/**
	 * This method will set if the program is running
	 *
	 * @param b true or false
	 */
	public void setRunning(boolean b) {
		running = b;
	}

	/**
	 * This method looks if the program is running.
	 *
	 * @return boolean running true or false
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * This method make the simulation go quicker or slower by setting the
	 * tickPause.
	 *
	 * @param int fps with how much to decrease the speed.
	 */
	public void setTickPause(int fps) {
		tickPause = 1001 - fps;
	}

	/**
	 * This method set if it is automatic or not.
	 *
	 * @param b true or false.
	 */
	public void setAutomatic(boolean b) {
		automatic = b;
	}

	/**
	 * This method is for getting the time.
	 *
	 * @return String sends back the time
	 */
	public String getTime() {
		String timeString;
		if (minute < 10) {
			timeString = "Dag: " + day + " " + hour + ":" + "0" + minute;
		} else {
			timeString = "Dag: " + day + " " + hour + ":" + minute;
		}

		return timeString;
	}

	/**
	 * This method is for getting the day.
	 * 
	 * @return String sends back the day
	 */
	public String getDay() {
		return dayArray[day];
	}
}