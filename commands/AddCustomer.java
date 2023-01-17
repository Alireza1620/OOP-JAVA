/**
 * <h1>AddCustomer Class</h1>
 * This class is used to add a Customer using  Customer ID, Name, Phone Number and Email for a Customer.
 * It also implements {@link Command} Interface.
 * 
 * @author Alireza Seifi Shalamzari
 * 
 * @see Command
 * @see ShowCustomer
 * @see DeleteCustomer
 * @see ListCustomers
 */
package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class AddCustomer implements Command {

	private final String name;
	private final String phone;
	private final String email;

	/**
     * Constructor is using three parameters: 1. Customer Name 2. Customer Phone Number 3. Customer Email
     * @param String name
     * @param String phone
     * @param String email
     * @return None
     */
	public AddCustomer(String name, String phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	/**
	 * This method will Override {@link FlightBookingSystem} execute method. Using methods in class {@link FlightBookingSystem} and {@link Customer}, it will add a new Customer.
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
		// TODO: implementation here
		int maxId = 0;
		if (flightBookingSystem.getCustomers().size() > 0) {
			int lastIndex = flightBookingSystem.getCustomers().size() - 1;
			maxId = flightBookingSystem.getCustomers().get(lastIndex).getId();
		}

		Customer customer = new Customer(++maxId, name,phone, email, true);
		flightBookingSystem.addCustomer(customer);
		System.out.println("Customer #" + customer.getId() + " added.");
	}

}
