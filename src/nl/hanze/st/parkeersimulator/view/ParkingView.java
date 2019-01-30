package nl.hanze.st.parkeersimulator.view;

import java.awt.*; 
import javax.swing.*;
import javax.swing.border.BevelBorder;

import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;
import nl.hanze.st.parkeersimulator.model.Location;
import nl.hanze.st.parkeersimulator.model.Vehicle;
import nl.hanze.st.mvc.Model;

/**
 * Class ParkingView
 * 
 * This class will contain the view of the parking lots.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class ParkingView extends View {
	/**
	 * @param size This param will contain the dimension of your screen.
	 */
	private Dimension size;
	
	/**
	 * @param carParkImage This param will contain an image of the parking lots.
	 */
    private Image carParkImage;    
    
    /**
     * Constructor for objects of class CarPark
     */
    public ParkingView() {
        size = new Dimension(0, 0);
        this.setPreferredSize(new Dimension(500,500));
        super.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }

    /**
     * Overridden. Tell the GUI manager how big we would like to be.
     * 
     * @return Dimension This return will return a preferred size of the dimension.
     */
    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }

    /**
     * Overriden. The car park view component needs to be redisplayed. Copy the
     * internal image to screen.
     * 
     * @param g This param will contain the Graphics of your screen.
     */
    public void paintComponent(Graphics g) {
    	if (carParkImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    /**
     * Paint a place on this car park view in a given color.
     * 
     * @param graphics This param will contain the graphics that you will use.
     * @param location This param will contain the location that you will draw.
     * @param color This param will contain the color of the parking lot.
     */
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }

    /**
     * This method will update your view with new graphics.
     * 
     * @param model This param will contain the garage model.
     */
	@Override
	protected void update(Model model) {
		Garage garage = (Garage) model; // cast
		
		// Create a new car park image if the size has changed.
        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }
        Graphics graphics = carParkImage.getGraphics();
        for(int floor = 0; floor < garage.getNumberOfFloors(); floor++) {
            for(int row = 0; row < garage.getNumberOfRows(); row++) {
                for(int place = 0; place < garage.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                	Vehicle vehicle = garage.getCarAt(location);
                    Color color = vehicle == null ? Color.white : vehicle.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }
        repaint();
	}	
}
