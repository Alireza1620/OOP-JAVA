/**
 * <h1>Help Class</h1>
 * This class is used to Load GUI.
 * It also implements {@link Command} Interface.
 * 
 * @author Alireza Seifi Shalamzari
 * 
 * @see Command
 */
package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class Help implements Command {

	/**
	 * This method will Override {@link FlightBookingSystem} execute method. It will Print {@link Command#HELP_MESSAGE}.
	 * 
	 * @param FlightBookingSystem flightBookingSystem
	 * @return None
	 * 
	 * @see FlightBookingSystem
	 * @throws FlightBookingSystemException
	 */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) {
		System.out.println(Command.HELP_MESSAGE);
	}
}
