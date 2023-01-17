/**
 * <h1>ListCustomers Class</h1>
 * This class is used to List all Customers.
 * It also implements {@link Command} Interface.
 * 
 * @author Alireza Seifi Shalamzari
 * 
 * @see Command
 * @see ShowCustomer
 * @see DeleteCustomer
 * @see AddCustomer
 */
package bcu.cmp5332.bookingsystem.commands;

import java.util.ArrayList;
import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ListCustomers implements Command{
	/**
	 * This method will Override {@link FlightBookingSystem} execute method. Using methods in class {@link FlightBookingSystem}, it will show Customers.
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
        List<Customer> customers = flightBookingSystem.getCustomers();
        List<Customer> visible = new ArrayList<>();
        for (Customer customer : customers) {
        	if(customer.isVisible()==true) {
        		System.out.println(customer.getDetailsShort());
        		visible.add(customer);
        	}
            
        }
        System.out.println(visible.size() + " customer(s)");
    }
}
