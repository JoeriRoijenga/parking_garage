package nl.hanze.st.parkeersimulator.model;

import java.awt.*;
import java.util.*;

/**
 * Class Reservation
 * 
 * This class will contain all the information for creating a reservation for a business.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */

public class Reservation {
	
	/**
	 * @param reservations This param contains a map of all the reservations and their unique key
	 */
	private static HashMap<Integer, String> reservations;
	
	/**
	 * @param reservations This param contains a map with locations per company.
	 */
	private static HashMap<Integer, ArrayList<Location>> locations;
	
	/**
	 * @param colors This param contains a map with a color per company.
	 */
	private static HashMap<Integer, Color> colors;
	
	/**
	 * @param amountOfCars This param contains the amount of cars.
	 */
	private static HashMap<Integer, Integer> amOfCars;
	
	private static HashMap<Integer, Integer> arHour;
	
	private static HashMap<Integer, Integer> arMinute;
	
	private static HashMap<Integer, String> arDay;
	
	private static HashMap<Integer, Integer> leHour;
	
	private static HashMap<Integer, Integer> leMinute;
	
	private static HashMap<Integer, String> leDay;
	
	/**
	 * Constructor
	 */
	public Reservation() {
		reservations = new HashMap<>();
		locations = new HashMap<>();
		colors = new HashMap<>();
		amOfCars = new HashMap<>();
		arHour = new HashMap<>();
		arMinute = new HashMap<>();
		arDay = new HashMap<>();
		leHour = new HashMap<>();
		leMinute = new HashMap<>();
		leDay = new HashMap<>();
	}	
	
	/**
	 * This method adds a new reservation.
	 * 
	 * @param location where the reservation will be made
	 * @param company the name of the company that made the reservation
	 * @param amountOfCars This param contains the amount of cars.
	 */
	public void makeReservation(int key ,ArrayList<Location> complocations, String company, int amountOfCars, 
			String arivingDay, int arivingHour, int arivingMinute,
			String leavingDay, int leavingHour, int leavingMinute ) {
		
		reservations.put(key, company);
		locations.put(key, complocations);
		amOfCars.put(key, amountOfCars);
		arMinute.put(key, arivingMinute);
		arHour.put(key, arivingHour);
		arDay.put(key, arivingDay);
		leMinute.put(key, leavingMinute);
		leHour.put(key, leavingHour);
		leDay.put(key, leavingDay);
	}
	
	/**
	 * This method will return a HashMap with reservations.
	 * 
	 * @return HashMap of the reservations made
	 */
	public HashMap<Integer, ArrayList<Location>> getReservation() {
		return locations;
	}
	
	/**
	 * This method will remove a passed by reservation
	 * 
	 * @param key the unique key of the reservation
	 */
	public void removeReservation(int key) {
		reservations.remove(key);
		locations.remove(key);
		amOfCars.remove(key);
		arMinute.remove(key);
		arHour.remove(key);
		arDay.remove(key);
		leMinute.remove(key);
		leHour.remove(key);
		leDay.remove(key);
	}
	
	
	/**
	 * This method return the locations of the reservations.
	 * 
	 * @return ArrayList containing the locations
	 */
	public ArrayList<Location> getLocation() {
		ArrayList<Location> Locations = new ArrayList<>();
		
		for (ArrayList<Location> location : locations.values()) {
			Locations.addAll(location);
		}
		
		return Locations;
	}
	
	/**
	 * This method will return all the reservations.
	 * 
	 * @return HashMap with the reservations.
	 */
	public HashMap<Integer, String> getReservations() {
		return reservations;
	}
	
	/**
	 * This method return the location of a company.
	 * 
	 * @param company This param contains the company name.
	 * @return ArrayList locations of the company.
	 */
	public ArrayList<Location> getCompanyLocations(int company){
        return locations.get(company);
    }

	/**
	 * This method will retrieve the amount of cars.
	 * 
	 * @return amountOfCars This return will return the amound of cars.
	 */
	public int getAmountOfCars(int company) {
		return amOfCars.get(company);
	}
	
	/**
	 * This method will set the color.
	 * 
	 * @param company This param contains the company name.
	 * @param color This param contains the color for the company.
	 */
	public void setColor(int company, Color color) {
		colors.put(company, color);
	}
	
	/**
	 * This method will retrieve the color.
	 * 
	 * @param company This param contains the company key.
	 * @return color This return will return the current color.
	 */
	public Color getColor(int company) {
		return colors.get(company);
	}
	
	public String getCompanyName(int company) {
		return reservations.get(company);
	}

	public int getArHour(int company) {
		return arHour.get(company);
	}
	
	public int getArMinute(int company) {
		return arMinute.get(company);
	}
	
	public String getArDay(int company) {
		return arDay.get(company);
	}
	
	public int getLeHour(int company) {
		return leHour.get(company);
	}
	
	public int getLeMinute(int company) {
		return leMinute.get(company);
	}
	
	public String getLeDay(int company) {
		return leDay.get(company);
	}
	
	
}