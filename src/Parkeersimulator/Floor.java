package Parkeersimulator;

/**
 * Class Floor
 * 
 * This class is made for the floor located in the parking garage.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels 
 * @version 0.1 (16-1-2019)
 */

public abstract class Floor extends Actor {
	private int floor;
	
	public Floor() {
		
	}
	
//	public String getLocation() {
//		return floor + "," + getRow() + "," getPlace();
//	}
	
	/**
	 * Getter for the floor
	 * 
	 * @return int floor
	 */
	public int getFloor() {
		return floor;
	}

}
