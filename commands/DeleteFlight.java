/**
 * <h1>DeleteFlight Class</h1>
 * This class is used to Delete a Flight using  Flight ID
 * It also implements {@link Command} Interface.
 * 
 * @author Alireza Seifi Shalamzari
 * 
 * @see Command
 * @see ShowFlight
 * @see AddFlight
 * @see ListFlights
 */
package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class DeleteFlight implements Command {

	int id;

	/**
	 * Constructor is using one parameters: 1. Flight ID
	 * 
	 * @param int id
	 * @return None
	 */
	public DeleteFlight(int id) {
		this.id = id;
	}
	/**
	 * This method will Override {@link FlightBookingSystem} execute method. Using methods in class {@link FlightBookingSystem}, it will Delete a Flight.
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
		flightBookingSystem.deleteFlight(id);
		System.out.println("Flight Deleted!");
	}
}
