package Parkeersimulator;

/**
 * Class Row
 * 
 * This class is made for the Row on a floor located in the parking garage.
 * 
 * @autor Timo de Jong, Joeri Roijenga, Tim Perdok, Niels 
 * @version 0.1 (16-1-2019)
 */

public abstract class Row {
	private int row;
	
	public Row(int row) {
		this.row = row;
	}
	
	/**
	 * Getter for the row
	 * 
	 * @return int row
	 */
	public int getRow() {
		return row;
	}
}
