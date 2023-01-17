package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class BookingDataManager implements DataManager {

	public final String RESOURCE = "./resources/data/bookings.txt";

	@Override
	public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
		// TODO: implementation here
		try (Scanner sc = new Scanner(new File(RESOURCE))) {
			int line_idx = 1;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] properties = line.split(SEPARATOR, -1);
				try {
					Customer cust = fbs.getCustomerByID(Integer.parseInt(properties[0]));
					Flight fli = fbs.getFlightByID(Integer.parseInt(properties[1]));

					Booking book = new Booking(cust, fli, fbs.getSystemDate());
					cust.addBooking(book);
					fli.addPassenger(cust);
				} catch (NumberFormatException ex) {
					throw new FlightBookingSystemException(
							"Unable to parse book id " + properties[0] + " on line " + line_idx + "\nError: " + ex);
				}
				line_idx++;
			}
		}
	}

	@Override
	public void storeData(FlightBookingSystem fbs) throws IOException {
		// TODO: implementation here
		try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
			for (Customer cust : fbs.getCustomers()) {
				if (!(cust.getBookings().isEmpty())) {
					for (Map.Entry<Flight, LocalDate> bokk : cust.getBookings().entrySet()) {
							out.print(cust.getId()+ SEPARATOR);
							out.print(bokk.getKey().getId()+ SEPARATOR);
							out.print(bokk.getValue()+ SEPARATOR);
							out.println();
						
					}

				}
			}
		}
	}

}
