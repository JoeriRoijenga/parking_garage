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
	
	private static HashMap<String, ArrayList<Location>> reservations;
	private static HashMap<String, Color> colors;
	
	/**
	 * Constructor
	 */
	public Reservation() {
		reservations = new HashMap<>();
		colors = new HashMap<>();
	}
	
	/**
	 * This method will set the color.
	 */
	public void setColor(String company, Color color) {
		colors.put(company, color);
	}
	
	/**
	 * This method will retrieve the color.
	 * 
	 * @return color This return will return the current color.
	 */
	public Color getColor(String company) {
		return colors.get(company);
	}
	
	/**
	 * This method adds a new reservation.
	 * 
	 * @param location where the reservation will be made
	 * @param company the name of the company that made the reservation
	 */
	public void makeReservation(ArrayList<Location> locations, String company) {
		reservations.put(company, locations);
	}
	
	/**
	 * This method will return a HashMap with reservations.
	 * 
	 * @return HashMap of the reservations made
	 */
	public HashMap<String, ArrayList<Location>> getReservation() {
		return reservations;
	}
	
	/**
	 * This method return the locations of the reservations.
	 * 
	 * @return ArrayList containing the locations
	 */
	public ArrayList<Location> getLocation() {
		ArrayList<Location> Locations = new ArrayList<>();
		
		for (ArrayList<Location> location : reservations.values()) {
			Locations.addAll(location);
		}
		
		return Locations;
	}
	
	
	/**
	 * This method will return all the reservations.
	 * 
	 * @return HashMap with the reservations.
	 */
	public HashMap<String, ArrayList<Location>> getReservations() {
		return reservations;
	}
	
	/**
	 * This method return the location of a company.
	 * 
	 * @return ArrayList locations of the company.
	 */
	public ArrayList<Location> getCompanyLocations(String company){
        return reservations.get(company);
    }
}
