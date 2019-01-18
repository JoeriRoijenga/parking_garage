package nl.hanze.st.parkeersimulator.model;

/**
 * Class Location
 * 
 * This class is made for the floor located in the parking garage.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries.
 * @version 0.1 (18-1-2019)
 */
public class Location {
	/**
	 * @param floor This param is used to store the floors of the garage.
	 */
	private int floor;
	
	/**
	 * @param row This param is used to store the rows per floor of the garage.
	 */
	private int row;
	
	/**
	 * @param place This param is used to store the places per row of the garage.
	 */
	private int place;
	
	/**
	 * Constructor
	 * @param floor This param will contain the amount of floors of the garage.
	 * @param row This param will contain the amount of rows per floor of the garage.
	 * @param place	This param will contain the amount of places per row of the garage.
	 */
	public Location(int floor, int row, int place) {
		this.floor = floor;
		this.row = row;
		this.place = place;
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
	 * Getter for the floor.
	 * 
	 * @return int floor.
	 */
	public int getFloor() {
		return floor;
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
	 * Getter for the place.
	 * 
	 * @return int place.
	 */
	public int getPlace() {
		return place;
	}

}
