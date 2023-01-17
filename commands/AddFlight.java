/**
 * <h1>AddFlight Class</h1>
 * This class is used to add a Flight using  Flight ID, Flight Number, Origin and Destination, Departure Date, Number of Seats and Ticket Price for a Flight.
 * It also implements {@link Command} Interface.
 * 
 * @author Alireza Seifi Shalamzari
 * 
 * @see Command
 * @see ShowFlight
 * @see DeleteFlight
 * @see ListFlights
 */
package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.time.LocalDate;

public class AddFlight implements  Command {

    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final LocalDate departureDate;
    private final int numSeat;
    private final int price;

    /**
     * Constructor is using six parameters: 1. flightNumber 2. origin 3. destination 4. departureDate 5. Number of Seats 6. Ticket Price
     * @param String flightNumber
     * @param String origin
     * @param String destination
     * @param LocalDate departureDate
     * @param int numSeat
     * @param int price
     * @return None
     */
    public AddFlight(String flightNumber, String origin, String destination, LocalDate departureDate, int numSeat, int price) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.numSeat = numSeat;
        this.price = price;
    }
	/**
	 * This method will Override {@link FlightBookingSystem} execute method. Using methods in class {@link FlightBookingSystem} and {@link Flight}, it will add a new Flight.
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
        int maxId = 0;
        if (flightBookingSystem.getFlights().size() > 0) {
            int lastIndex = flightBookingSystem.getFlights().size() - 1;
            maxId = flightBookingSystem.getFlights().get(lastIndex).getId();
        }
        
        Flight flight = new Flight(++maxId, flightNumber, origin, destination, departureDate, numSeat, price, true);
        flightBookingSystem.addFlight(flight);
        System.out.println("Flight #" + flight.getId() + " added.");
    }
}
