/* This class will hold all of the items for the interface */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Interface extends JFrame implements ActionListener {
        
        // Global variables, window elements, and defaults
        
        private static final long serialVersionUID = 1L;
        
        JButton addPassButton   = new JButton("Add Passengers");
        JButton addFlightButton = new JButton("Add Flights");
        JButton addTicketButton = new JButton("Add Tickets");
        JButton getInfoButton   = new JButton("Get Info");
        JButton quitButton      = new JButton("Save & Quit");
        JButton quitNoSaveButton= new JButton("Quit w/o Save");

        
        AddPassenger passWindow;
        AddFlight flightWindow;
        AddTicket ticketWindow;
        GetInfo infoWindow;
        
        final int width  = 375;
        final int height = 100;
        Dimension dim;
        
        JPanel panel = new JPanel();
        
        public Interface() {
                passWindow		= new AddPassenger(this);
                flightWindow 	= new AddFlight(this);
                ticketWindow	= new AddTicket(this);
                infoWindow      = new GetInfo(this);
                
                passWindow.setCaller(this);
                passWindow.dispose();
                flightWindow.setCaller(this);
                flightWindow.dispose();
                ticketWindow.setCaller(this);
                ticketWindow.dispose();
                infoWindow.setCaller(this);
                infoWindow.dispose();
                
                setTitle("Airline Manager");
                
                panel.setLayout(new FlowLayout());
                panel.add(addPassButton);
                panel.add(addFlightButton);
                panel.add(addTicketButton);
                panel.add(getInfoButton);
                panel.add(quitButton);
                panel.add(quitNoSaveButton);
                getContentPane().add(panel);
                
                addPassButton.addActionListener(this);
                addFlightButton.addActionListener(this);
                getInfoButton.addActionListener(this);
                addTicketButton.addActionListener(this);
                quitButton.addActionListener(this);
                quitNoSaveButton.addActionListener(this);
                
                dim = Toolkit.getDefaultToolkit().getScreenSize();
                
                setLocation((dim.width-width)/2,(dim.height-height)/2);
                setSize(width,height);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        
        
        public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(addPassButton)) {
                        this.dispose();
                        passWindow.setVisible(true);
                }
                else if(e.getSource().equals(addFlightButton)) {
                        this.dispose();
                        flightWindow.setVisible(true);
                }
                else if(e.getSource().equals(addTicketButton)) {
            ticketWindow.populateBoxes();
                        this.dispose();
                        ticketWindow.setVisible(true);
                }
                else if (e.getSource().equals(getInfoButton)) {
                        this.dispose();
                        infoWindow.setVisible(true);
                }
                else if (e.getSource().equals(quitButton)) {
                        loadDb.saveDatabase("restoreDb.txt");
                        dispose();
                }
                else if (e.getSource().equals(quitNoSaveButton))
                        dispose();
    }
}