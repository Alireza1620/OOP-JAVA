package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Flight {

	private int id;
	private String flightNumber;
	private String origin;
	private String destination;
	private LocalDate departureDate;
	private int numSeat;
	private int price;
	private boolean visible;

	private final Set<Customer> passengers;

	public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate, int numSeat,
			int price, boolean visible) {
		this.id = id;
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.departureDate = departureDate;
		this.price = price;
		this.numSeat = numSeat;
		this.visible = visible;

		passengers = new HashSet<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public List<Customer> getPassengers() {
		return new ArrayList<>(passengers);
	}

	public int getNumSeat() {
		return numSeat;
	}

	public void setNumSeat(int numSeat) {
		this.numSeat = numSeat;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public String getDetailsShort() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " + destination + " on "
				+ departureDate.format(dtf);
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public String getDetailsLong() {
		String lon = "Flight #" + id + "\n" + "Flight No: " + flightNumber + "\n" + "Origine: " + origin + "\n"
				+ "Destination: " + destination + "\n" + "Departure Date: " + departureDate + "\n" + "Number Of Seats: "
				+ numSeat + "\n" + "Ticket Price: " + price + "\n" + "--------------------------\n" + "Passangers:\n";

		for (Customer passenger : passengers) {
			lon += passenger.getDetailsShort() + "\n";
		}
		lon += passengers.size() + " Passanger(s)";
		return lon;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public void addPassenger(Customer passenger) throws FlightBookingSystemException {
		for (Customer existing : passengers) {
			if (passenger.getId() == existing.getId()) {
				throw new FlightBookingSystemException("This passenger is aready in flight.");
			}
		}
		numSeat--;
		passengers.add(passenger);

	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public void deletePassenger(Customer passenger) throws FlightBookingSystemException {
		if (!passengers.contains(passenger)) {
			throw new FlightBookingSystemException("Customer has no booking for this flight.");
		}
		numSeat++;
		passengers.remove(passenger);

	}

}
