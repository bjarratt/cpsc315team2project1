# Overview #

## **class AddFlight** ##

The **AddFlight** class allows for the creation of a new flight in the airline reservation system.

### Data Members ###

private static final long serialVersionUID

Interface _caller_ - Interface object that integrates all of the GUI classes.

final JLabel _flightNumLabel_ - All of these members are the text labels for input areas in the interface.

final JLabel _planeNumLabel_

final JLabel _numPassLabel_

final JLabel _depCityLabel_

final JLabel _arrCityLabel_

final JLabel _mealLabel_

final JLabel _timeLabel_


JTextField _flightNumField_ - These data members are the input fields for the items to be added.

JTextField _planeNumField_

JCheckBox _mealBox_

JTextField _numPassField_

JTextField _depCityField_

JTextField _arrCityField_

JTextField _timeField_


JButton _addButton_ - The button that will add a flight to the database.

JButton _backButton_ - The button that will return the user to the main GUI menu.

JPanel _panel_ - Panel for visual organization of the above buttons, labels and input fields.


final int _height_ - The dimensions of _panel_.

final int _width_

Dimension _dim_

String _meal_ - Sets the initial state of _meal_ to FALSE.

### Constructor Summary ###

**AddFlight**(Interface _win_){} - Pass the calling interface so that it can be popped back up whenever the window is (legitimately)closed.  Adds all data members to _panel_.

### Method Summary ###

void **setCaller**(Interface _window_) - Takes in an Interface object _window_ and sets _caller_ equal to it.

public void **actionPerformed**(ActionEvent _e_) - Adds the inputted items to the database it they are in the correct format.

public void **windowActivated**(WindowEvent _e_) - An empty override method.

public void **windowClosed**(WindowEvent _e_) - Sets the input fields back to default blanks if the window is closed.

public void **windowClosing**(WindowEvent _e_) - An empty override method.

public void **windowDeactivated**(WindowEvent _e_) - An empty override method.

public void **windowDeiconified**(WindowEvent _e_) - An empty override method.

public void **windowIconified**(WindowEvent _e_) - An empty override method.

public void **windowOpened**(WindowEvent _e_) - An empty override method.

public void **itemStateChanged**(ItemEvent _e_) - Handles the _meal_ check box and changes its value appropriately based on whether the box is checked or unchecked.