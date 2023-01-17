/**
 * <h1>Command Interface</h1>
 * This interface implemented by all classes in Command Package.
 * 
 * @author Alireza Seifi Shalamzari 
 * 
 * @see AddBooking
 * @see AddCustomer
 * @see AddFlight
 * @see CancelBooking
 * @see DeleteCustomer
 * @see DeleteFlight
 * @see EditBooking
 * @see Help
 * @see ListCustomers
 * @see ListFlights
 * @see LoadGUI
 * @see ShowCustomer
 * @see ShowFlight
 */
package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public interface Command {

    public static final String HELP_MESSAGE = "Commands:\n"
        + "\tlistflights                               print all flights\n"
        + "\tlistcustomers                             print all customers\n"
        + "\taddflight                                 add a new flight\n"
        + "\taddcustomer                               add a new customer\n"
        + "\tshowflight [flight id]                    show flight details\n"
        + "\tdeleteflight [flight id]                  delete flight\n"
        + "\tshowcustomer [customer id]                show customer details\n"
        + "\tdeletecustomer [customer id]              delete customer\n"
        + "\taddbooking [customer id] [flight id]      add a new booking\n"
        + "\tcancelbooking [customer id] [flight id]   cancel a booking\n"
        + "\teditbooking [booking id] [flight id]      update a booking\n"
        + "\tloadgui                                   loads the GUI version of the app\n"
        + "\thelp                                      prints this help message\n"
        + "\texit                                      exits the program";

    /**
	 * This method will Override {@link FlightBookingSystem} execute method.
	 * 
	 * @param FlightBookingSystem flightBookingSystem
	 * @return None
	 * 
	 * @see FlightBookingSystem
	 * @throws FlightBookingSystemException
	 */
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException;
    
}
