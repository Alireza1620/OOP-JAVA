/**
 * <h1>AddBooking Class</h1>
 * This class is used to add a booking using specific Flight ID for a Customer.
 * It also implements {@link Command} Interface.
 * 
 * @author Alireza Seifi Shalamzari
 * 
 * @see Command
 * @see CancelBooking
 * @see EditBooking
 */
package bcu.cmp5332.bookingsystem.commands;


import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class AddBooking implements Command {

	private int customer;
    private int flight;
    /**
     * Constructor is using two parameters: 1. Customer ID 2. Flight ID
     * @param int customer
     * @param int flight
     * @return None
     */
	public AddBooking(int customer, int flight) {
		this.customer = customer;
		this.flight = flight;
	}

	/**
	 * This method will Override {@link FlightBookingSystem} execute method. Using methods in class {@link Flight} and {@link Customer}, it will add booking and passenger.
	 * If the flight is fully booked, adding passenger method is no longer available for flight.
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
		// TODO Auto-generated method stub
		Customer cust= flightBookingSystem.getCustomerByID(customer);
		Flight fli= flightBookingSystem.getFlightByID(flight);
		Booking book = new Booking(cust,fli,flightBookingSystem.getSystemDate());
		if (fli.getNumSeat()<1) {
			throw new FlightBookingSystemException("Flight if Full. Can not Book More Passengers");
		}
		cust.addBooking(book);
		fli.addPassenger(cust);	
		System.out.println("Booking added!");
	}
}
