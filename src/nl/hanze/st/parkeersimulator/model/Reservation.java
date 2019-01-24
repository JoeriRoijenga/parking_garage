package nl.hanze.st.parkeersimulator.model;

import java.awt.*;
import java.util.*;

public class Reservation {
	
	private static HashMap<String, ArrayList<Location>> reservations;
	private static HashMap<String, Color> colors;
	
	public Reservation() {
		reservations = new HashMap<>();
		colors = new HashMap<>();
	}
	
	public void setColor(String company, Color color) {
		colors.put(company, color);
	}
	
	public Color getColor(String company) {
		return colors.get(company);
	}
	
	public void makeReservation(ArrayList<Location> locations, String company) {
		reservations.put(company, locations);
	}
	
	public HashMap<String, ArrayList<Location>> getReservation() {
		return reservations;
	}
	
	public ArrayList<Location> getLocation() {
		ArrayList<Location> Locations = new ArrayList<>();
		
		for (ArrayList<Location> location : reservations.values()) {
			Locations.addAll(location);
		}
		
		return Locations;
	}
	
	public HashMap<String, ArrayList<Location>> getReservations() {
		return reservations;
	}
	
	public ArrayList<Location> getCompanyLocations(String company){
        return reservations.get(company);
    }
}
