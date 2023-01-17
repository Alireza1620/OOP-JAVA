package bcu.cmp5332.bookingsystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.DeleteFlight;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

public class DeleteFlightWindow extends JFrame implements ActionListener {
	private MainWindow mw;
	
    private JTextField flightIdText = new JTextField();

    private JButton delBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");

    public DeleteFlightWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
	 /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Delete Flight");

        setSize(500, 150);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.add(new JLabel("Flight ID : "));
        topPanel.add(flightIdText);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(delBtn);
        bottomPanel.add(cancelBtn);

        delBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delBtn) {
            addBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void addBook() {
        try {
            String flightId = flightIdText.getText();
            int id = 0;
            try {
            	id = Integer.parseInt(flightId);
            }catch(NumberFormatException e) {
            	JOptionPane.showMessageDialog(this, "Make Sure Values Entered Correctly","Error", JOptionPane.ERROR_MESSAGE);
            }
            // create and execute the AddFlight Command
            Command delFlight = new DeleteFlight(id);
            delFlight.execute(mw.getFlightBookingSystem());
            
            JOptionPane.showMessageDialog(this, "Flight Deleted!","Deleted", JOptionPane.INFORMATION_MESSAGE);
            // refresh the view with the list of flights
            mw.displayFlights();
            // hide (close) the DeleteFlightWindow
            this.setVisible(false);
        }  catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }catch(IllegalArgumentException e) {
        	JOptionPane.showMessageDialog(this, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

