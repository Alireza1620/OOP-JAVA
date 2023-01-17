/**
 * <h1>ListFlights Class</h1>
 * This class is used to List all Flights.
 * It also implements {@link Command} Interface.
 * 
 * @author Alireza Seifi Shalamzari
 * 
 * @see Command
 * @see ShowFlight
 * @see AddFlight
 * @see DeleteFlight
 */
package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.ArrayList;
import java.util.List;

public class ListFlights implements Command {
	/**
	 * This method will Override {@link FlightBookingSystem} execute method. Using methods in class {@link FlightBookingSystem}, it will show Flights.
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
        List<Flight> flights = flightBookingSystem.getFlights();
        List<Flight> visible = new ArrayList<>();
        for (Flight flight : flights) {
        	if(flight.isVisible()==true) {
        		System.out.println(flight.getDetailsShort());
        		visible.add(flight);
        	}
            
        }
        System.out.println(visible.size() + " flight(s)");
    
}
}
