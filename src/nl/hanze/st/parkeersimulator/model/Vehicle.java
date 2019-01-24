package nl.hanze.st.parkeersimulator.model;

import java.awt.Color;
import java.util.Random;

public abstract class Vehicle extends Actor {
	private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;
    private int stayTime;
    
	public Vehicle() {
    	Random random = new Random();
    	int stayMinutes = (int) (15 + random.nextFloat() * 3 * 60);
        this.setMinutesLeft(stayMinutes);

	}
	
	public void setStayTime(int stayTime) {
		this.stayTime = stayTime;
        this.setMinutesLeft(stayTime);
    }
	
	public int getStayTime() {
		return stayTime;
    }
	
    public int getMinutesLeft() {
        return minutesLeft;
    }

    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    public boolean getIsPaying() {
        return isPaying;
    }

    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    public boolean getHasToPay() {
        return hasToPay;
    }

    public void setHasToPay(boolean hasToPay) {
        this.hasToPay = hasToPay;
    }
    
    public void tick() {
        minutesLeft--;
    }
    
    public abstract Color getColor();
}
