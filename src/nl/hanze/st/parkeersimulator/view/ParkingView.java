package nl.hanze.st.parkeersimulator.view;

import java.awt.*;

import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;
import nl.hanze.st.parkeersimulator.model.Location;
import nl.hanze.st.parkeersimulator.model.Vehicle;
import nl.hanze.st.mvc.Model;

public class ParkingView extends View {

    private Dimension size;
    private Image carParkImage;    
    //private Garage garage;
    
    /**
     * Constructor for objects of class CarPark
     */
    public ParkingView() {
        size = new Dimension(0, 0);
        size = getPreferredSize();
    }

    /**
     * Overridden. Tell the GUI manager how big we would like to be.
     */
    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }

    /**
     * Overriden. The car park view component needs to be redisplayed. Copy the
     * internal image to screen.
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
     */
    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); // TODO use dynamic size or constants
    }

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
