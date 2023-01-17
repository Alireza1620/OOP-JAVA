/**
 * <h1>EditBooking Class</h1>
 * This class is used to Edit a booking using specific Flight ID for a Customer.
 * It also implements {@link Command} Interface.
 * 
 * @author Alireza Seifi Shalamzari
 * 
 * @see Command
 * @see CancelBooking
 * @see AddBooking
 */
package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class EditBooking implements Command{
	
	private int customer;
	private int flight;
	private int id;
	
	/**
     * Constructor is using three parameters: 1. Customer ID 2. Flight ID 3. New ID
     * @param int customer
     * @param int flight
     * @param int ID
     * @return None
     */
	public EditBooking( int customer, int flight, int id) {
		super();
		this.id = id;
		this.customer = customer;
		this.flight = flight;
	}
	/**
	 * This method will Override {@link FlightBookingSystem} execute method. Using methods in class {@link Flight} and {@link Customer} and {@link Flight}, it will Edit booking and passenger.
	 * 
	 * @param FlightBookingSystem flightBookingSystem
	 * @return None
	 * 
	 * @see FlightBookingSystem
	 * @see Flight
	 * @see Customer
	 * @throws FlightBookingSystemException
	 */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		
		Customer oldCust = flightBookingSystem.getCustomerByID(customer);

		Flight oldlFli = flightBookingSystem.getFlightByID(flight);

			
			Flight newFli = flightBookingSystem.getFlightByID(id);
			oldCust.deleteBooking(oldlFli);
			oldlFli.deletePassenger(oldCust);
			Booking book = new Booking(oldCust, newFli, flightBookingSystem.getSystemDate());
			oldCust.addBooking(book);
			newFli.addPassenger(oldCust);
		System.out.println("Booking Edited.");
	}
	

}
