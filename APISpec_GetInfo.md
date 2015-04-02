# Overview #

## **class GetInfo** ##

The **GetInfo** class runs all the database queries enumerated in our databaseAPI, handles any incorrect input (whether file or from keyboard) and displays the queried info in the GUI.

### Data Members ###

private static final long serialVersionUID

Interface _caller_ - Interface object that integrates all of the GUI classes.

JButton _passengersOnFlight_ - The following members are buttons displayed in the main GUI menu.

JButton _passengerInfo_

JButton _flightsForPassenger_

JButton _passengerLimit_

JButton _mealFlights_

JButton _getPlaneItinerary_

JButton _backButton_ - The button that will return the user to the main GUI menu.

JButton _helpButton_ - Panel for visual organization of the above buttons, labels and input fields.

JTextArea _textArea_ - The space into which the query is entered.

JTextField _textField_ - The space into which the output of the query is displayed.

JScrollPane _scrollPane_ - Resizes the _textField_ dynamically based on its size.

JPanel _topPanel_ - The following are the organization panels for the GUI.

JPanel _topMiddlePanel_

JPanel _northPanel_

JPanel _fullPanel_

final int _height_ - The dimensions of _panel_.

final int _width_

Dimension _dim_

### Constructor Summary ###

**GetInfo**(Interface _win_){} - Pass the calling interface so that it can be popped back up whenever the window is (legitimately) closed.  Adds all data members to _panel_.

### Method Summary ###

void **setCaller**(Interface _window_) - Takes in an Interface object _window_ and sets _caller_ equal to it.

public void **actionPerformed**(ActionEvent _e_) - Reads in the query, calls the required methods from the database classes, and displays the output of the query in _textArea_.

public void **windowActivated**(WindowEvent _e_) - An empty override method.

public void **windowClosed**(WindowEvent _e_) - Sets the _textArea_ back to a default blank if the window is closed.

public void **windowClosing**(WindowEvent _e_) - An empty override method.

public void **windowDeactivated**(WindowEvent _e_) - An empty override method.

public void **windowDeiconified**(WindowEvent _e_) - An empty override method.

public void **windowIconified**(WindowEvent _e_) - An empty override method.

public void **windowOpened**(WindowEvent _e_) - An empty override method.