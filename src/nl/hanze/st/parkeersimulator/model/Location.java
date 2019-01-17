package nl.hanze.st.parkeersimulator.model;

/**
 * Class Floor
 * 
 * This class is made for the floor located in the parking garage.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels. 
 * @version 0.1 (16-1-2019)
 */

public abstract class Location extends Actor {
	private int place;
	private int row;
	private int floor;
	
	public Location(int place, int row, int floor) {
		this.place = place;
		this.row = row;
		this.floor = floor;
	}
	
	/**
	 * This gives back a String containing the floor, row and place.
	 * 
	 * @return String containing floor, row, place (Location).
	 */
	
	public String getLocation() {
		return floor + "," + row + "," + place;
	}
	
	/**
	 * Getter for the place.
	 * 
	 * @return int place.
	 */
	public int getPlace() {
		return place;
	}
	
	/**
	 * Getter for the row.
	 * 
	 * @return int row.
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Getter for the floor.
	 * 
	 * @return int floor.
	 */
	public int getFloor() {
		return floor;
	}

}
