import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GetInfo extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 3L;
	Interface caller;
	
    // Buttons
    JButton passengersOnFlight = new JButton("Get Passengers On Flight");
    JButton passengerInfo = new JButton("Info on a passenger");
    JButton flightsForPassenger = new JButton("Flights for passenger");
    JButton passengerLimit = new JButton("Max Passengers for a flight");
    JButton mealFlights = new JButton("get Flights that provide a meal");
    JButton getPlaneItinerary = new JButton("get itinerary for a plane");
    JButton backButton	= new JButton("< Back");
    
    // Text boxes
    JTextArea textArea = new JTextArea("Enter query here");
    JTextField textField = new JTextField();
    JScrollPane scrollPane = new JScrollPane(textArea);
    
    // Organization panels
    JPanel topPanel			= new JPanel();
    JPanel topMiddlePanel	= new JPanel();
    JPanel northPanel		= new JPanel();
    JPanel fullPanel  		= new JPanel();

    final int width  = 500;
    final int height = 400;
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
        
        
        topPanel.add(scrollPane);
        
        topMiddlePanel.setLayout(new SpringLayout());
        topMiddlePanel.add(passengersOnFlight);
        topMiddlePanel.add(passengerInfo);
        topMiddlePanel.add(flightsForPassenger);
        topMiddlePanel.add(passengerLimit);
        topMiddlePanel.add(mealFlights);
        topMiddlePanel.add(getPlaneItinerary);
        topMiddlePanel.add(backButton);
        topMiddlePanel.add(new JLabel(""));
        topMiddlePanel.setAlignmentX(CENTER_ALIGNMENT);

        SpringUtilities.makeCompactGrid(topMiddlePanel,
                4, 2,	//rows, cols
                6, 6,	//initX, initY
                6, 6);	//xPad, yPad
        
        northPanel.setLayout(new BorderLayout());
        northPanel.add(topPanel, BorderLayout.NORTH);
        northPanel.add(topMiddlePanel, BorderLayout.SOUTH);

        fullPanel.setLayout(new BorderLayout());
        fullPanel.add(northPanel, BorderLayout.NORTH);
        fullPanel.add(textArea, BorderLayout.CENTER);

        getContentPane().add(fullPanel, BorderLayout.CENTER);

        passengersOnFlight.addActionListener(this);
        flightsForPassenger.addActionListener(this);
        mealFlights.addActionListener(this);
        getPlaneItinerary.addActionListener(this);
        passengerInfo.addActionListener(this);
        passengerLimit.addActionListener(this);
        backButton.addActionListener(this);
        textField.addKeyListener(this);

        dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        setSize(width, height);
        setLocation((dim.width-width)/2,(dim.height-height)/2);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    void setCaller(Interface window) {
		caller = window;
	}

	public void actionPerformed(ActionEvent action) {
    	Table displayTable;
		if (action.getSource().equals(backButton)) {
			textArea.setText("Enter in any one piece of information that\n" +
    			"may exist about a flight or passenger.");
			caller.setVisible(true);
			dispose();
			displayTable=new Table();
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
        	if(textField.getText().equals(""))
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
					name="\"" + name.toUpperCase() + "\"";
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
        else
        	displayTable=new Table();
        textArea.setText(displayTable.toString());
	}

	public void keyPressed(KeyEvent e) {
		// Make it nice and easy for the user to press enter
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            passengersOnFlight.doClick();
        }
	}

	public void keyReleased(KeyEvent e) {
		// Nothing here
		
	}

	public void keyTyped(KeyEvent e) {
		// Nothing here		
	}
}
