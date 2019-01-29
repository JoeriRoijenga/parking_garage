package nl.hanze.st.parkeersimulator.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import nl.hanze.st.mvc.Model;
import nl.hanze.st.mvc.View;
import nl.hanze.st.parkeersimulator.model.Garage;

public class ManagementInformationView extends View {
	private int parkingSpots, regularCars, subsciptionCar, reservationCar, queueEntranceRegularAmount, queueEntranceSubscriptionAmount, queueExitCarAmount, queueReservationAmount, queuePaymentAmount;
	private JPanel panel = new JPanel();
	
	public ManagementInformationView() {
		creatingOverview();
	}
	
	public void creatingOverview() {		
		panel.setLayout(new GridLayout(0,2));

		Border border = BorderFactory.createLineBorder(Color.black);
						
		JLabel openSpots = new JLabel();
		openSpots.setText("Open Spots: " + parkingSpots);
		openSpots.setPreferredSize(new Dimension(300, 40));
		openSpots.setFont(new Font(openSpots.getName(), Font.PLAIN, 25));
		openSpots.setBorder(border);
		
		JLabel parkedRegular = new JLabel();
		parkedRegular.setText("Regular Cars: " + regularCars);
		parkedRegular.setPreferredSize(new Dimension(300, 40));
		parkedRegular.setFont(new Font(parkedRegular.getName(), Font.PLAIN, 25));
		parkedRegular.setBorder(border);
		
		JLabel parkedSubscription = new JLabel();
		parkedSubscription.setText("Subscription Cars: " + subsciptionCar);
		parkedSubscription.setPreferredSize(new Dimension(300, 40));
		parkedSubscription.setFont(new Font(parkedSubscription.getName(), Font.PLAIN, 25));
		parkedSubscription.setBorder(border);
		
		JLabel parkedReservation = new JLabel();
		parkedReservation.setText("Reservation Cars: " + reservationCar);
		parkedReservation.setPreferredSize(new Dimension(300, 40));
		parkedReservation.setFont(new Font(parkedReservation.getName(), Font.PLAIN, 25));
		parkedReservation.setBorder(border);
		
		JLabel queueEntranceRegular = new JLabel();
		queueEntranceRegular.setText("Queue Regular: " + queueEntranceRegularAmount);
		queueEntranceRegular.setPreferredSize(new Dimension(300, 40));
		queueEntranceRegular.setFont(new Font(queueEntranceRegular.getName(), Font.PLAIN, 25));
		queueEntranceRegular.setBorder(border);
		
		JLabel queueEntranceSubscription = new JLabel();
		queueEntranceSubscription.setText("Queue Subscription: " + queueEntranceSubscriptionAmount);
		queueEntranceSubscription.setPreferredSize(new Dimension(300, 40));
		queueEntranceSubscription.setFont(new Font(queueEntranceSubscription.getName(), Font.PLAIN, 25));
		queueEntranceSubscription.setBorder(border);
		
		JLabel queueReservation = new JLabel();
		queueReservation.setText("Queue Reservation: " + queueReservationAmount);
		queueReservation.setPreferredSize(new Dimension(300, 40));
		queueReservation.setFont(new Font(queueReservation.getName(), Font.PLAIN, 25));
		queueReservation.setBorder(border);
		
		JLabel queuePayment = new JLabel();
		queuePayment.setText("Queue Payment: " + queuePaymentAmount);
		queuePayment.setPreferredSize(new Dimension(300, 40));
		queuePayment.setFont(new Font(queuePayment.getName(), Font.PLAIN, 25));
		queuePayment.setBorder(border);
		
		JLabel queueExitCar = new JLabel();
		queueExitCar.setText("Queue Exit: " + queueExitCarAmount);
		queueExitCar.setPreferredSize(new Dimension(300, 40));
		queueExitCar.setFont(new Font(queueExitCar.getName(), Font.PLAIN, 25));
		queueExitCar.setBorder(border);
		
		panel.add(openSpots);
		panel.add(parkedRegular);
		panel.add(parkedSubscription);
		panel.add(parkedReservation);		
		panel.add(queueEntranceRegular);
		panel.add(queueEntranceSubscription);
		panel.add(queueReservation);
		panel.add(queuePayment);
		panel.add(queueExitCar);
	
		add(panel);
	}

	@Override
	protected void update(Model model) {
		Garage garage = (Garage) model;
		
		parkingSpots = garage.getNumberOfOpenSpots();
		
		regularCars = garage.getNumberOfTakenSpotsByRegular();
		subsciptionCar = garage.getNumberOfTakenSpotsBySubscription();
		reservationCar = garage.getNumberOfTakenSpotsByReservation();
		
		queueEntranceRegularAmount = garage.getNumberOfRegularCarsEntranceQueue();
		queueEntranceSubscriptionAmount = garage.getNumberOfSubscriptionCarsEntranceQueue();
		queueReservationAmount = garage.getNumberOfReserverationCarsQueue();
		queuePaymentAmount = garage.getNumberOfPaymentQueue();
		queueExitCarAmount = garage.getNumberOfExitCarsQueue();
		
		panel.removeAll();
		creatingOverview();
	}
	
	
}
