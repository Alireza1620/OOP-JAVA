package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

public class MainWindow extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu adminMenu;
	private JMenu flightsMenu;
	private JMenu bookingsMenu;
	private JMenu customersMenu;

	private JMenuItem adminExit;

	private JMenuItem flightsView;
	private JMenuItem flightsAdd;
	private JMenuItem flightShow;
	private JMenuItem flightsDel;

	private JMenuItem bookingsIssue;
	private JMenuItem bookingsUpdate;
	private JMenuItem bookingsCancel;

	private JMenuItem custView;
	private JMenuItem custAdd;
	private JMenuItem custShow;
	private JMenuItem custDel;

	private FlightBookingSystem fbs;

	public MainWindow(FlightBookingSystem fbs) {

		initialize();
		this.fbs = fbs;
	}

	public FlightBookingSystem getFlightBookingSystem() {
		return fbs;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {

		}

		setTitle("Flight Booking Management System");

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// adding adminMenu menu and menu items
		adminMenu = new JMenu("Admin");
		menuBar.add(adminMenu);

		adminExit = new JMenuItem("Exit");
		adminMenu.add(adminExit);
		adminExit.addActionListener(this);

		// adding Flights menu and menu items
		flightsMenu = new JMenu("Flights");
		menuBar.add(flightsMenu);

		flightsView = new JMenuItem("View");
		flightsAdd = new JMenuItem("Add");
		flightShow = new JMenuItem("Show Flight Details");
		flightsDel = new JMenuItem("Delete");
		flightsMenu.add(flightsView);
		flightsMenu.add(flightsAdd);
		flightsMenu.add(flightShow);
		flightsMenu.add(flightsDel);
		// adding action listener for Flights menu items
		for (int i = 0; i < flightsMenu.getItemCount(); i++) {
			flightsMenu.getItem(i).addActionListener(this);
		}

		// adding Customers menu and menu items
		customersMenu = new JMenu("Customers");
		menuBar.add(customersMenu);

		custView = new JMenuItem("View");
		custAdd = new JMenuItem("Add");
		custShow = new JMenuItem("Show Customer Details");
		custDel = new JMenuItem("Delete");

		customersMenu.add(custView);
		customersMenu.add(custAdd);
		customersMenu.add(custShow);
		customersMenu.add(custDel);
		// adding action listener for Customers menu items
		custView.addActionListener(this);
		custAdd.addActionListener(this);
		custShow.addActionListener(this);
		custDel.addActionListener(this);

		// adding Bookings menu and menu items
		bookingsMenu = new JMenu("Bookings");
		menuBar.add(bookingsMenu);

		bookingsIssue = new JMenuItem("Issue");
		bookingsUpdate = new JMenuItem("Update");
		bookingsCancel = new JMenuItem("Cancel");
		bookingsMenu.add(bookingsIssue);
		bookingsMenu.add(bookingsUpdate);
		bookingsMenu.add(bookingsCancel);
		// adding action listener for Bookings menu items
		for (int i = 0; i < bookingsMenu.getItemCount(); i++) {
			bookingsMenu.getItem(i).addActionListener(this);
		}

		setSize(1100, 500);

		setVisible(true);
		setAutoRequestFocus(true);
		toFront();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		/*
		 * Uncomment the following line to not terminate the console app when the window
		 * is closed
		 */
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	/* Uncomment the following code to run the GUI version directly from the IDE */
//    public static void main(String[] args) throws IOException, FlightBookingSystemException {
//        FlightBookingSystem fbs = FlightBookingSystemData.load();
//        new MainWindow(fbs);			
//    }

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == adminExit) {
			try {
				FlightBookingSystemData.store(fbs);
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
			}
			System.exit(0);
		} else if (ae.getSource() == flightsView) {
			displayFlights();

		} else if (ae.getSource() == flightsAdd) {
			new AddFlightWindow(this);

		} else if (ae.getSource() == flightShow) {
			showFlights();

		} else if (ae.getSource() == flightsDel) {
			new DeleteFlightWindow(this);

		} else if (ae.getSource() == bookingsIssue) {
			new AddBookingWindow(this);

		} else if (ae.getSource() == bookingsUpdate) {
			new UpdateBookingWindow(this);

		} else if (ae.getSource() == bookingsCancel) {
			new CancelBookingWindow(this);

		} else if (ae.getSource() == custView) {
			displayCustomers();

		} else if (ae.getSource() == custShow) {
			showCstomers();

		} else if (ae.getSource() == custAdd) {
			new AddCustomerWindow(this);

		} else if (ae.getSource() == custDel) {
			new DeleteCustomerWindow(this);
		}
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void displayFlights() {
		List<Flight> flightsList = fbs.getFlights();
		// headers for the table
		String[] columns = new String[] { "Flight ID", "Flight No", "Origin", "Destination", "Departure Date",
				"Number of Seats", "Ticket Price", "Number Of Passengers" };

		Object[][] data = new Object[flightsList.size()][8];
		for (int i = 0; i < flightsList.size(); i++) {
			Flight flight = flightsList.get(i);
			if (flight.isVisible() == true) {
				data[i][0] = flight.getId();
				data[i][1] = flight.getFlightNumber();
				data[i][2] = flight.getOrigin();
				data[i][3] = flight.getDestination();
				data[i][4] = flight.getDepartureDate();
				data[i][5] = flight.getNumSeat();
				data[i][6] = flight.getPrice();
				data[i][7] = flight.getPassengers().size();
			}
		}
		JTable table = new JTable(data, columns);
		this.getContentPane().removeAll();
		this.getContentPane().add(new JScrollPane(table));
		this.revalidate();
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void displayCustomers() {
		List<Customer> customersList = fbs.getCustomers();
		// headers for the table
		String[] columns = new String[] { "Customer ID", "Customer Name", "Phone Number", "Email",
				"Number Of Bookings" };

		Object[][] data = new Object[customersList.size()][5];
		for (int i = 0; i < customersList.size(); i++) {
			Customer cust = customersList.get(i);
			if (cust.isVisible() == true) {
				data[i][0] = cust.getId();
				data[i][1] = cust.getName();
				data[i][2] = cust.getPhone();
				data[i][3] = cust.getEmail();
				data[i][4] = cust.getBookings().size();
			}
		}

		JTable table = new JTable(data, columns);
		this.getContentPane().removeAll();
		this.getContentPane().add(new JScrollPane(table));
		this.revalidate();
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void showFlights() {
		List<Flight> flightsList = fbs.getFlights();
		// headers for the table
		String[] columns = new String[] { "Flight ID", "Flight Number", "Passangers" };

		Object[][] data = new Object[flightsList.size()][3];
		for (int i = 0; i < flightsList.size(); i++) {
			Flight flight = flightsList.get(i);
			if (flight.isVisible() == true) {
				List<Customer> passengers = flight.getPassengers();

				data[i][0] = flight.getId();
				data[i][1] = flight.getFlightNumber();
				String lon = "";
				for (Customer passenger : passengers) {
					lon += passenger.getDetailsShort() + "     ";
				}
				data[i][2] = lon;

			}
		}

		JTable table = new JTable(data, columns);
		this.getContentPane().removeAll();
		this.getContentPane().add(new JScrollPane(table));
		this.revalidate();
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void showCstomers() {
		List<Customer> CustomerList = fbs.getCustomers();
		// headers for the table
		String[] columns = new String[] { "Customer ID", "Customer Email", "Bookings" };

		Object[][] data = new Object[CustomerList.size()][3];
		for (int i = 0; i < CustomerList.size(); i++) {
			Customer customer = CustomerList.get(i);
			if (customer.isVisible() == true) {
				data[i][0] = customer.getId();
				data[i][1] = customer.getEmail();
				String lon = "";
				for (Map.Entry<Flight, LocalDate> bookings : customer.getBookings().entrySet()) {
					lon += "Flight ID: #" + bookings.getKey().getId() + " Flight No: "
							+ bookings.getKey().getFlightNumber() + " Booking Date: " + bookings.getValue() + "     ";
				}
				data[i][2] = lon;

			}
		}
		JTable table = new JTable(data, columns);
		this.getContentPane().removeAll();
		this.getContentPane().add(new JScrollPane(table));
		this.revalidate();
	}
}
