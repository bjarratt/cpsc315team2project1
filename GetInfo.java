import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GetInfo extends JFrame implements ActionListener, WindowListener {

	private static final long serialVersionUID = 3L;
	Interface caller;
	
    // Buttons
    JButton passengersOnFlight	= new JButton("Get Passengers On Flight");
    JButton passengerInfo		= new JButton("Info on a passenger");
    JButton flightsForPassenger = new JButton("Flights for passenger");
    JButton passengerLimit		= new JButton("Max Passengers for a flight");
    JButton mealFlights			= new JButton("get Flights that provide a meal");
    JButton getPlaneItinerary	= new JButton("get itinerary for a plane");
    JButton backButton			= new JButton("< Back");
    JButton helpButton			= new JButton("Help");
    
    // Text boxes
    JTextArea textArea 		= new JTextArea("Enter query here");
    JTextField textField 	= new JTextField();
    JScrollPane scrollPane 	= new JScrollPane(textArea);
    
    // Organization panels
    JPanel topPanel			= new JPanel();
    JPanel topMiddlePanel	= new JPanel();
    JPanel northPanel		= new JPanel();
    JPanel fullPanel  		= new JPanel();

    final int width  = 600;
    final int height = 450;
    Dimension dim;

    
    /* Pass the calling interface so that it can be 
	 * popped back up whenever this window is (legitimately)
	 * closed. 
	 */
    public GetInfo(Interface win) {
    	caller = win;
        setTitle("Information Query");

        textField.setColumns(40);
        textArea.setText("Enter in any one piece of information that may exist about a flight or passenger.");
        
        
        topPanel.add(textField);
        topPanel.setAlignmentX(CENTER_ALIGNMENT);
        
        topMiddlePanel.setLayout(new GridLayout(4,2));
        topMiddlePanel.add(passengersOnFlight);
        topMiddlePanel.add(passengerInfo);
        topMiddlePanel.add(flightsForPassenger);
        topMiddlePanel.add(passengerLimit);
        topMiddlePanel.add(mealFlights);
        topMiddlePanel.add(getPlaneItinerary);
        topMiddlePanel.add(backButton);
        topMiddlePanel.add(helpButton);
        topMiddlePanel.setAlignmentX(CENTER_ALIGNMENT); 
        
        northPanel.setLayout(new BorderLayout());
        northPanel.add(topPanel, BorderLayout.NORTH);
        northPanel.add(topMiddlePanel, BorderLayout.SOUTH);
        northPanel.setAlignmentX(CENTER_ALIGNMENT);

        fullPanel.setLayout(new BorderLayout());
        fullPanel.add(northPanel, BorderLayout.NORTH);
        fullPanel.add(scrollPane, BorderLayout.CENTER);
        fullPanel.setAlignmentX(CENTER_ALIGNMENT);

        getContentPane().add(fullPanel, BorderLayout.CENTER);

        passengersOnFlight.addActionListener(this);
        flightsForPassenger.addActionListener(this);
        mealFlights.addActionListener(this);
        getPlaneItinerary.addActionListener(this);
        passengerInfo.addActionListener(this);
        passengerLimit.addActionListener(this);
        backButton.addActionListener(this);
        helpButton.addActionListener(this);

        dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        addWindowListener(this);
        setSize(width, height);
        setLocation((dim.width-width)/2,(dim.height-height)/2);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    void setCaller(Interface window) {
		caller = window;
	}

	public void actionPerformed(ActionEvent action) {
    	Table displayTable = new Table();
    	boolean needsHelp = false;
		if (action.getSource().equals(backButton)) {
			textArea.setText("Enter in any one piece of information that may exist about a flight or passenger.");
			caller.setVisible(true);
			dispose();
			displayTable=new Table();
		}
		else if (action.getSource().equals(helpButton)) {
			textArea.setText("Enter in any one piece of information that may exist about a flight or passenger.");
			needsHelp = true;
		}
        else if (action.getSource().equals(passengersOnFlight)) {
        	if(textField.getText().equals(""))
            	displayTable=TableOps.select("flight#,name,addr,passenger#,age" +
            						   "FROM TicketInfo,PassengerInfo");
        	else {
        		try {
        			int number=Integer.valueOf(textField.getText());
        			displayTable=TableOps.select("name,addr,passenger#,age" +
						   	   "FROM TicketInfo,PassengerInfo" +
						      "WHERE flight#=" + number);
        		}
        		catch(NumberFormatException e) {
        			displayTable=new Table();
        			System.err.println("INVALID QUERY");
        		}
        	}
        }
        else if (action.getSource().equals(passengerInfo)) {
        	if(textField.getText().equals(""))
            	displayTable=TableOps.select("*" +
 				   "FROM PassengerInfo");
        	else {
        		try {
        			int number=Integer.valueOf(textField.getText());
                	displayTable=TableOps.select("*" +
 						   "FROM PassengerInfo" +
 						  "WHERE passenger#=" + number);
        		}
        		catch(NumberFormatException e) {
        			String name=textField.getText();
					name="\"" + name.toUpperCase() + "\"";
                	displayTable=TableOps.select("*" +
  						   "FROM PassengerInfo" +
  						  "WHERE name=" + name);
        		}
        	}
        }
        else if (action.getSource().equals(flightsForPassenger)) {
        	if(textField.getText().trim().equals(""))
            	displayTable=TableOps.select("passenger#,flight#,plane#,startCity,stopCity,departure,class,seat,price " +
          			  				   "FROM FlightInfo,TicketInfo");
        	else {
        		try {
        			int number=Integer.valueOf(textField.getText());
                	displayTable=TableOps.select("flight#,plane#,startCity,stopCity,departure,class,seat,price " +
			  				   "FROM FlightInfo,TicketInfo" +
			  				  "WHERE passenger#=" + number);
        		}
        		catch(NumberFormatException e) {
        			String name=textField.getText();
					name=name.toUpperCase();
                	displayTable=TableOps.select("flight#,plane#,startCity,stopCity,departure,class,seat,price " +
			  				   "FROM FlightInfo,TicketInfo,PassengerInfo" +
			  				  "WHERE name=" + name);
        		}
        	}
        }
        else if (action.getSource().equals(passengerLimit)) {
        	if(textField.getText().equals(""))
            	displayTable=TableOps.select("flight#,maxPassengerCount" +
          			  				   "FROM FlightInfo");
           	else
            	displayTable=TableOps.select("maxPassengerCount" +
          			  				   "FROM FlightInfo" +
          			  				  "WHERE flight#=" + textField.getText());
        }
        else if (action.getSource().equals(mealFlights)) {
        	displayTable=TableOps.select("flight#, plane#, startCity, stopCity, departure" +
        						   "FROM FlightInfo" +
        			 			  "WHERE mealInclude=TRUE");
        }
        else if (action.getSource().equals(getPlaneItinerary)) {
        	if(textField.getText().equals(""))
            	displayTable=TableOps.select("plane#,flight#, startCity, stopCity, departure" +
            						   "FROM FlightInfo");
           	else
            	displayTable=TableOps.select("flight#, startCity, stopCity, departure" +
          			  				   "FROM FlightInfo" +
          			  				  "WHERE plane#=" + textField.getText());
        }

        if(!needsHelp)
        	textArea.setText(displayTable.toString());
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		textArea.setText("Enter in any one piece of information that may exist about a flight or passenger.");
		caller.setVisible(true);	
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}
}
