# Overview #

## **class AddTicket** ##

The **AddTicket** class allows for the creation of a new ticket in the airline reservation system.

### Data Members ###

private static final long serialVersionUID

Interface _caller_ - Interface object that integrates all of the GUI classes.

final JLabel _flightNumLabel_ - All of these members are the text labels for input areas in the interface, in this case flight number, passenger flyer #, passenger name, ticket class, seat number, and ticket cost.

final JLabel _passengerText_

final JLabel _nameLabel_

final JLabel _classLabel_

final JLabel _seatLabel_

final JLabel _costLabel_

JTextField _classField_ - These data members are the input fields for the items to be added to the database.

JTextField _seatField_

JTextField _costField_

JComboBox _flightNumBox_ - A drop-down selection menu that allows the user to select the flight number for the ticket.

JComboBox _nameBox_ - A drop-down selection menu that allows the user to select the passenger name for the ticket.

JButton _addButton_ - The button that will add a ticket to the database.

JButton _backButton_ - The button that will return the user to the main GUI menu.

JPanel _panel_ - Panel for visual organization of the above buttons, labels and input fields.

boolean _populated_ - A variable that determines if the input boxes contain data.

final int _height_ - These three members define the dimensions of _panel_.

final int _width_

Dimension _dim_


### Constructor Summary ###

**AddTicket**(Interface _win_){} - Pass the calling interface so that it can be popped back up whenever the window is (legitimately) closed.  Adds all data members to _panel_.

### Method Summary ###

void **setCaller**(Interface _window_) - Takes in an Interface object _window_ and sets _caller_ equal to it.

void **populateBoxes**() - Gets the passenger names and flight numbers from the database with which to populate the drop-down menus _flightNumBox_ and _nameBox_.

public void **actionPerformed**(ActionEvent _e_) - Adds the inputted items to the database it they are in the correct format.

public void **windowActivated**(WindowEvent _e_) - An empty override method.

public void **windowClosed**(WindowEvent _e_) - Sets the input fields to a default blank state if the window is closed.

public void **windowClosing**(WindowEvent _e_) - An empty override method.

public void **windowDeactivated**(WindowEvent _e_) - An empty override method.

public void **windowDeiconified**(WindowEvent _e_) - An empty override method.

public void **windowIconified**(WindowEvent _e_) - An empty override method.

public void **windowOpened**(WindowEvent _e_) - An empty override method.