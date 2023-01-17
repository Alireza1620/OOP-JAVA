package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Customer {

	private int id;
	private String name;
	private String phone;
	private String email;
	private final Map<Flight,LocalDate> bookings = new HashMap<Flight, LocalDate>();
	private boolean visible;

	// TODO: implement constructor here
	public Customer(int id, String name, String phone, String email, boolean visible) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.visible = visible;
	}

	// TODO: implementation of Getter and Setter methods
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map<Flight, LocalDate> getBookings() {
		return bookings;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public String getDetailsShort() {
		return "Customer #" + id + " - " + name + ", with phone number " + phone;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public String getDetailsLong() throws FlightBookingSystemException {
		String lon="Customer #" + id + "\n" + "Name: " + name + "\n" + "Phone: " + phone + "\n"
				+ "--------------------------\n" + "Bookings:\n";
		
		for(Flight flight : bookings.keySet()) {
			String shortdet = flight.getDetailsShort();
			lon += "* Booking date:" + bookings.get(flight) + " For " + shortdet + "\n";
		}
		lon += bookings.size() + " booking(s)";
		return lon;

	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void addBooking(Booking booking) throws FlightBookingSystemException {
		// TODO: implementation here
		for (Flight existing : bookings.keySet()) {
			if (existing.equals(booking.getFlight())) {
				throw new FlightBookingSystemException("This flight is already booked.");
			}
		}
		Flight flight = booking.getFlight();
		bookings.put(flight, booking.getBookingDate());
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	public void deleteBooking(Flight flight) throws FlightBookingSystemException{
		if(!bookings.containsKey(flight)) {
			throw new FlightBookingSystemException("Customer has no booking for this flight.");
		}
		bookings.remove(flight);
	}
	

}
