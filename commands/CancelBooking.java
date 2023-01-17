/**
 * <h1>CancelBooking Class</h1>
 * This class is used to Cancel a booking using specific Flight ID and Customer ID.
 * It also implements {@link Command} Interface.
 * 
 * @author Alireza Seifi Shalamzari
 * 
 * @see Command
 * @see AddBooking
 * @see EditBooking
 */
package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class CancelBooking implements Command {
	
	int custid;
	int flyid;
	
	/**
     * Constructor is using two parameters: 1. Customer ID 2. Flight ID
     * @param int customer
     * @param int flight
     * @return None
     */
	public CancelBooking(int custid, int flyid) {
		super();
		this.custid = custid;
		this.flyid = flyid;
	}


	/**
	 * This method will Override {@link FlightBookingSystem} execute method. Using methods in class {@link Flight} and {@link Customer} and {@link Flight}, it will Cancel booking and passenger.
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
		
		Customer cust = flightBookingSystem.getCustomerByID(custid);
		Flight fli= flightBookingSystem.getFlightByID(flyid);
		
		cust.deleteBooking(fli);
		fli.deletePassenger(cust);
		
		System.out.println("Booking deleted Successfully");
		
		
	}
}
