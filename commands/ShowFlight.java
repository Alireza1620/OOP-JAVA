/**
 * <h1>ShowCustomer Class</h1>
 * This class is used to Show Details of a Flight using  Flight ID.
 * It also implements {@link Command} Interface.
 * 
 * @author Alireza Seifi Shalamzari
 * 
 * @see Command
 * @see AddCustomer
 * @see DeleteCustomer
 * @see ListCustomers
 */
package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ShowFlight implements Command{
	
	int id;

	/**
     * Constructor is using one parameters: 1. Customer ID
     * @param int id
     * @return None
     */
	public ShowFlight(int id) {
		this.id = id;
	}
	
	/**
	 * This method will Override {@link FlightBookingSystem} execute method. Using methods in class {@link FlightBookingSystem}, it will show specific Flight.
	 * It will check if the Flight exist(Visible) or not.
	 * 
	 * @param FlightBookingSystem flightBookingSystem
	 * @return None
	 * 
	 * @see FlightBookingSystem
	 * @see Flight
	 * @throws FlightBookingSystemException
	 */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		Flight fli = flightBookingSystem.getFlightByID(id);
		if (fli.isVisible() == true) {
			System.out.println(fli.getDetailsLong());
		}else {
			throw new FlightBookingSystemException("No Flight With This ID");
		}
		
	
	}

}
