package nl.hanze.st.parkeersimulator.model;

import java.awt.Color;
import java.util.Random;

/**
 * Abstract Class Actor
 * 
 * This class is the Vehicle class that will be the parent for multiple classes and will extend from Actor.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public abstract class Vehicle extends Actor {
	/**
	 * @param minutesLeft This param contains an int with the amount of minutes left to park.
	 */
	private int minutesLeft;
	
	/**
	 * @param isPaying This param contains a boolean if someone is paying.
	 */
    private boolean isPaying;
    
    /**
     * @param hasToPay This param contains a boolean if someone has to pay.
     */
    private boolean hasToPay;
    private int stayTime;
    
    private int moodLevel;
    
    /**
     * Constructor
     */
	public Vehicle() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
    	moodLevel = random.nextInt(15) + 2;
        this.setMinutesLeft(stayMinutes);

	}
	
	public void setStayTime(int stayTime) {
		this.stayTime = stayTime;
        this.setMinutesLeft(stayTime);
    }
	
	public int getStayTime() {
		return stayTime;
    }
	
	/**
	 * This method retrieves the minutes that are left to park.
	 * 
	 * @return minutesLeft This return will return the amount of minutes that are left to park as an int.
	 */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    
    /**
     * This method will set the amount of minutes that are left to park.
     * 
     * @param minutesLeft This param contains a int with the amount of minutes left to park.
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    /**
     * Method that will retrieve the param isPaying, which will be a boolean.
     * 
     * @return isPaying Return will return a boolean called isPaying.
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     * Method that will set the isPaying state to true or false.
     * 
     * @param isPaying This param will contains a boolean and will determine if isPaying is true or false.
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * Method that will retrieve the param hasToPay, which is a boolean.
     * 
     * @return hasToPay Return will return a boolean called hasToPay. 
     */
    public boolean getHasToPay() {
        return hasToPay;
    }

    /**
     * Method that will set the param hasToPay on true or false.
     * 
     * @param hasToPay This param will contains a boolean and will determine if hasToPay is true or false.
     */
    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }
    
    /**
     * Method that will make the parking minutes go down.
     */
    public void tick() {
        minutesLeft--;
    }
    
    /**
     * Abstract method defined so parent will inherit it.
     * 
     * @return Color expected to retrieve the color.
     */
    public abstract Color getColor();
    
    public void decreaseMoodLevel() {
    	moodLevel--;
    }
    
    public int getMoodLevel() {
    	return moodLevel;
    }
}
