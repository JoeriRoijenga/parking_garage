package nl.hanze.st.parkeersimulator.model;

<<<<<<< HEAD:src/nl/hanze/st/parkeersimulator/model/Vehicle.java
public abstract class Vehicle extends Actor {
=======
import java.awt.Color;

public abstract class Vehicle {
>>>>>>> origin/timo_parking_garage:src/Parkeersimulator/Vehicle.java
	private int minutesLeft;
    private boolean isPaying;
    private boolean hasToPay;
    
	public Vehicle() {
		
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
