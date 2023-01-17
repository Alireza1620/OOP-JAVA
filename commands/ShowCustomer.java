/**
 * <h1>ShowCustomer Class</h1>
 * This class is used to Show Details of a Customer using  Customer ID.
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
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ShowCustomer implements Command {
	int id;

	/**
     * Constructor is using one parameters: 1. Customer ID
     * @param int id
     * @return None
     */
	public ShowCustomer(int id) {
		this.id = id;
	}
	/**
	 * This method will Override {@link FlightBookingSystem} execute method. Using methods in class {@link FlightBookingSystem}, it will show specific Customer.
	 * It will check if the customer exist(Visible) or not.
	 * 
	 * @param FlightBookingSystem flightBookingSystem
	 * @return None
	 * 
	 * @see FlightBookingSystem
	 * @see Customer
	 * @throws FlightBookingSystemException
	 */
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		Customer cust = flightBookingSystem.getCustomerByID(id);
		if (cust.isVisible()==true) {
			System.out.println(cust.getDetailsLong());
		}else {
			throw new FlightBookingSystemException("No Customer With This ID");
		}
		
		
	}
	

}
