package nl.hanze.st.parkeersimulator.view;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;
import nl.hanze.st.parkeersimulator.model.RegularCar;
import nl.hanze.st.parkeersimulator.model.SubscriptionCar;

import java.awt.image.*;
import java.awt.*;
import javax.swing.*;

public class LineGraphicView extends View {
	private static final double SCALE_FACTOR = 0.8;

    // An internal image buffer that is used for painting. For
    // actual display, this image buffer is then copied to screen.
    private BufferedImage lineGraphicImage;
    private int lastVal1, lastVal2;
    private int yMax;
    
    private static final Color LIGHT_GRAY = new Color(0, 0, 0, 40);
    
//    private static JLabel stepLabel;
//    private static JLabel countLabel;
    
//    private int step;
    
    private int regular;
    private int subscription;
//    private int y;
//    private int x;
//    private int height;
//	private int width;
     
    private Dimension size;
    
    /**
     * Create a new, empty GraphPanel.
     */
    public LineGraphicView()
    {   
    	lineGraphicImage = new BufferedImage(800, 400, BufferedImage.TYPE_INT_RGB);    	
        clearImage();
        lastVal1 = 400;
        lastVal2 = 400;
        yMax = 25;
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 400);
    }

    /**
     * Dispay a new point of data.
     */
    protected void update(Model model) {
    	Garage garage = (Garage) model;
    	    
    	regular = garage.getNumberOfTakenSpotsByRegular();
        subscription = garage.getNumberOfTakenSpotsBySubscription();

        repaint();

        //countLabel.setText("" + garage.getNumberOfTakenSpotsByRegular() + garage.getNumberOfTakenSpotsBySubscription());
    }

    /**
     * Scale the current graph down vertically to make more room at the top.
     */
    public void scaleDown(Graphics g) {
        BufferedImage tmpImage = new BufferedImage(lineGraphicImage.getWidth(), (int)(lineGraphicImage.getHeight()*SCALE_FACTOR), BufferedImage.TYPE_INT_RGB);
        Graphics2D gtmp = (Graphics2D) tmpImage.getGraphics();

        gtmp.scale(1.0, SCALE_FACTOR);
        gtmp.drawImage(lineGraphicImage, 0, 0, null);

        int oldTop = (int) (lineGraphicImage.getHeight() * (1.0-SCALE_FACTOR));

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, lineGraphicImage.getWidth(), oldTop);
        g.drawImage(tmpImage, 0, oldTop, null);

        yMax = (int) (yMax / SCALE_FACTOR);
        lastVal1 = oldTop + (int) (lastVal1 * SCALE_FACTOR);
        lastVal2 = oldTop + (int) (lastVal2 * SCALE_FACTOR);

        repaint();
    }

    /**
     * Clear the image on this panel.
     */
    final public void clearImage()
    {
        Graphics g = lineGraphicImage.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, lineGraphicImage.getWidth(), lineGraphicImage.getHeight());
        //repaint();
    }
    
    /**
     * This component needs to be redisplayed. Copy the internal image 
     * to screen. (This method gets called by the Swing screen painter 
     * every time it want this component displayed.)
     * 
     * @param g The graphics context that can be used to draw on this component.
     */
    public void paintComponent(Graphics g)
    {
    	//if (height != 0 && width != 0) {
		drawGraphic(g);
    	//}
//    	g.drawImage(lineGraphicImage, 0, 0, null);
    }
    
    /**
     * 		
     * @param g
     */
    public void drawGraphic(Graphics g) {
    	
    	int height = lineGraphicImage.getHeight();
        int width = lineGraphicImage.getWidth();
        
    	g.copyArea(1, 0, width-1, height, -1, 0);
	
        int y = height - ((height * regular) / yMax) - 1;
        while (y<0) {
            scaleDown(g);
            y = height - ((height * regular) / yMax) - 1;
        }
        
        // calculate y, check whether it's out of screen. scale down if necessary.
        
        g.setColor(LIGHT_GRAY);
        g.drawLine(width-2, y, width-2, height);
        g.setColor(Color.red);
        g.drawLine(width-3, lastVal1, width-2, y);
        lastVal1 = y;
        
        y = height - ((height * subscription) / yMax) - 1;
        while (y<0) {
            scaleDown(g);
            y = height - ((height * subscription) / yMax) - 1;
        }
        
        g.setColor(LIGHT_GRAY);
        g.drawLine(width-2, y, width-2, height);
        g.setColor(Color.blue);
        g.drawLine(width-3, lastVal2, width-2, y);
        lastVal2 = y;
        
//        g.drawline
//        g.drawImage(lineGraphicImage, 0, 0, null);
    }
}


