package nl.hanze.st.parkeersimulator.model;

import java.awt.Color;
import nl.hanze.st.mvc.Model;

/**
 * Abstract Class Actor
 * 
 * This class is the Actor class that will be the parent for multiple classes.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public abstract class Actor extends Model {
	/**
	 * @param location This param contains the locations class.
	 */
	private Location location;

    /**
     * Constructor for objects of class Car
     */
    public Actor() {

    }

    /**
     * This method will retrieve the current location.
     * 
     * @return location This return will return the current location.
     */
    public Location getLocation() {
        return location;
    }
    
	/**
	 * This method will set the current location.
	 * 
	 * @param location This param will contain the current location.
	 */
    public void setLocation(Location location) {
        this.location = location;
    }
}
