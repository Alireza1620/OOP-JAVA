package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.commands.DeleteCustomer;
import bcu.cmp5332.bookingsystem.commands.DeleteFlight;
import bcu.cmp5332.bookingsystem.gui.DeleteCustomerWindow;
import bcu.cmp5332.bookingsystem.gui.DeleteFlightWindow;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.util.*;

public class FlightBookingSystem {

	private final LocalDate systemDate = LocalDate.parse("2020-11-11");

	private final Map<Integer, Customer> customers = new TreeMap<>();
	private final Map<Integer, Flight> flights = new TreeMap<>();

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public LocalDate getSystemDate() {
		return systemDate;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public List<Flight> getFlights() {
		List<Flight> out = new ArrayList<>(flights.values());
		return Collections.unmodifiableList(out);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public List<Customer> getCustomers() {
		List<Customer> out = new ArrayList<>(customers.values());
		return Collections.unmodifiableList(out);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public Flight getFlightByID(int id) throws FlightBookingSystemException {
		if (!flights.containsKey(id)) {
			throw new FlightBookingSystemException("There is no flight with that ID.");
		}
		return flights.get(id);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public Customer getCustomerByID(int id) throws FlightBookingSystemException {
		// TODO: implementation here
		if (!customers.containsKey(id)) {
			throw new FlightBookingSystemException("There is no customer with that ID.");
		}
		return customers.get(id);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public void addFlight(Flight flight) throws FlightBookingSystemException {
		if (flights.containsKey(flight.getId())) {
			throw new IllegalArgumentException("Duplicate flight ID.");
		}
		for (Flight existing : flights.values()) {
			if (existing.getFlightNumber().equals(flight.getFlightNumber())
					&& existing.getDepartureDate().isEqual(flight.getDepartureDate())) {
				throw new FlightBookingSystemException(
						"There is a flight with same " + "number and departure date in the system");
			}
		}
			flights.put(flight.getId(), flight);
		
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public void addCustomer(Customer customer) throws FlightBookingSystemException {
		// TODO: implementation here
		if (customers.containsKey(customer.getId())) {
			throw new IllegalArgumentException("Duplicate Customer ID.");
		}
		for (Customer existing : customers.values()) {
			if (existing.getName().equals(customer.getName()) && existing.getPhone().equals(customer.getPhone())) {
				throw new FlightBookingSystemException(
						"There is a customer with same " + "name and phone nubmer in the system");
			}
		}
			customers.put(customer.getId(), customer);
		

	}

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * This method is used to delete a Customer using {@link DeleteCustomer} and {@link DeleteCustomerWindow}.
	 * Instead of completely deleting the Customer, It will hide it from system.
	 * If the customer is already hidden from system, it will throw FlightBookingSystemException.
	 * 
	 * @param id
	 * @return None
	 * 
	 * @throws FlightBookingSystemException
	 * 
	 * @see DeleteCustomer
	 * @see DeleteCustomerWindow
	 */
	public void deleteCustomer(int id) throws FlightBookingSystemException {
		if (!customers.containsKey(id)) {
			throw new IllegalArgumentException("Customer ID is not valid");
		}
		Customer cust = getCustomerByID(id);
		if(cust.isVisible()==true) {
			cust.setVisible(false);
		}else {
			throw new FlightBookingSystemException("No Customer with this ID");
		}
		
	}

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * This method is used to delete a Flight using {@link DeleteFlight} and {@link DeleteFlightWindow}.
	 * Instead of completely deleting the Flight, It will hide it from system.
	 * If the flight is already hidden from system, it will throw FlightBookingSystemException.
	 * 
	 * @param id
	 * @return None
	 * 
	 * @throws FlightBookingSystemException
	 * 
	 * @see DeleteFlight
	 * @see DeleteFlightWindow
	 */
	public void deleteFlight(int id) throws FlightBookingSystemException {
		if (!flights.containsKey(id)) {
			throw new IllegalArgumentException("Flight ID is not valid");
		}
		Flight fli = getFlightByID(id);
		if(fli.isVisible()==true) {
			fli.setVisible(false);
		}else {
			throw new FlightBookingSystemException("No Flight with this ID");
		}
	}

}
