# Overview #

## **class AddPassenger** ##

The **AddPassenger** class allows for the creation of a new passenger in the airline reservation system.

### Data Members ###

private static final long serialVersionUID

Interface _caller_ - Interface object that integrates all of the GUI classes.

final JLabel _nameLabel_ - All of these members are the text labels for input areas in the interface, in this case passenger name, address, flyer #, and age.

final JLabel _addressLabel_

final JLabel _flyerLabel_

final JLabel _ageLabel_

JTextField _nameField_ - These data members are the input fields for the items to be added to the database.

JTextField _addressField_

JCheckBox _flyerBox_

JButton _addButton_ - The button that will add a passenger to the database.

JButton _backButton_ - The button that will return the user to the main GUI menu.

JPanel _panel_ - Panel for visual organization of the above buttons, labels and input fields.


final int _height_ - These three members define the dimensions of _panel_.

final int _width_

Dimension _dim_


### Constructor Summary ###

**AddPassenger**(Interface _win_){} - Pass the calling interface so that it can be popped back up whenever the window is (legitimately) closed.  Adds all data members to _panel_.

### Method Summary ###

void **setCaller**(Interface _window_) - Takes in an Interface object _window_ and sets _caller_ equal to it.

public void **actionPerformed**(ActionEvent _e_) - Adds the inputted items to the database it they are in the correct format.

public void **windowActivated**(WindowEvent _e_) - An empty override method.

public void **windowClosed**(WindowEvent _e_) - Makes the _caller_ Interface object visible if the window is closed.

public void **windowClosing**(WindowEvent _e_) - An empty override method.

public void **windowDeactivated**(WindowEvent _e_) - An empty override method.

public void **windowDeiconified**(WindowEvent _e_) - An empty override method.

public void **windowIconified**(WindowEvent _e_) - An empty override method.

public void **windowOpened**(WindowEvent _e_) - An empty override method.