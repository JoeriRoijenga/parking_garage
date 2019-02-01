package nl.hanze.st.parkeersimulator.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * Class ExceptionMessageView
 * 
 * This class is the ExceptionMessageView class that will create the error message.
 * 
 * @author Timo de Jong, Joeri Roijenga, Tim Perdok, Niels de Vries. 
 * @version 0.1 (18-1-2019)
 */
public class ExceptionMessageView extends JFrame {
	/** 
	 * Constructor
	 */
	public ExceptionMessageView() {
		createMessage();
	}
	
	/** 
	 * This method will create the error message.
	 */
	public void createMessage() {
		setSize(500,200);
		setLayout(new GridLayout(3,3));
		
		JLabel textLabel = new JLabel("Save Failed!",JLabel.CENTER);
		textLabel.setFont(new Font("Sans", Font.PLAIN, 20));
		JLabel errorLabel = new JLabel("One of the values is invalid.",JLabel.CENTER);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowsEvent) {
				dispose();
			}
		});		
		
		add(textLabel);
		add(errorLabel);					
		setVisible(true);
	}
}
