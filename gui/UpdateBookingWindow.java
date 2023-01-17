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

import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.EditBooking;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

public class UpdateBookingWindow extends JFrame implements ActionListener{
	private MainWindow mw;

    private JTextField customerText = new JTextField();
    private JTextField flightText = new JTextField();
    private JTextField idText = new JTextField();


    private JButton conBtn = new JButton("Continue");
    private JButton cancelBtn = new JButton("Cancel");

    public UpdateBookingWindow(MainWindow mw) {
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

        setTitle("Update Booking");

        setSize(500, 200);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 2));
        
        topPanel.add(new JLabel("Original Customer ID : "));
        topPanel.add(customerText);
        topPanel.add(new JLabel("Original Flight ID : "));
        topPanel.add(flightText);
        topPanel.add(new JLabel("New Flight ID : "));
        topPanel.add(idText);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(conBtn);
        bottomPanel.add(cancelBtn);

        conBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == conBtn) {
            addBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void addBook() {
        try {
            String customer = customerText.getText();
            String flight = flightText.getText();
            String sid = idText.getText();
            
            int cust = Integer.parseInt(customer);
            int fli = Integer.parseInt(flight);
            int id = Integer.parseInt(sid);
            	 // create and execute the Command
                Command delBooking = new EditBooking( cust, fli, id);
                delBooking.execute(mw.getFlightBookingSystem());
                // refresh the view with the list of Customers
                JOptionPane.showMessageDialog(this, "Booking Updated.","Information", JOptionPane.INFORMATION_MESSAGE);
                mw.displayCustomers();
                // hide (close) the UpdateBookingWindow
                this.setVisible(false);
            
           
        }  catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }catch(IllegalArgumentException e) {
        	JOptionPane.showMessageDialog(this, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
