/**
 * <h1>DeleteCustomer Class</h1>
 * This class is used to Delete a Customer using  Customer ID
 * It also implements {@link Command} Interface.
 * 
 * @author Alireza Seifi Shalamzari
 * 
 * @see Command
 * @see ShowCustomer
 * @see AddCustomer
 * @see ListCustomers
 */
package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class DeleteCustomer implements Command{

	int id;

	/**
     * Constructor is using one parameters: 1. Customer ID
     * @param int id
     * @return None
     */
	public DeleteCustomer(int id) {
		this.id = id;
	}
	/**
	 * This method will Override {@link FlightBookingSystem} execute method. Using methods in class {@link FlightBookingSystem}, it will Delete a Customer.
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
		flightBookingSystem.deleteCustomer(id);
		System.out.println("Customer Deleted!");
	}
}
