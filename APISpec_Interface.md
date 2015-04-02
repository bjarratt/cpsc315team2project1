# Overview #

## **class Interface** ##

The **Interface** class holds all items to be included in the GUI, and allows for easy placement of query sub-interfaces.

### Data Members ###

private static final long serialVersionUID

JButton _addPassButton_ - The following members are the buttons to be included in the main GUI interface.

JButton _addFlightButton_

JButton _addTicketButton_

JButton _getInfoButton_

JButton _quitButton_

JButton _quitNoSaveButton_

**AddPassenger** _passWindow_ - Objects of the various add info/return info types; each will pop out into its own new window when its action button is clicked.

**AddFlight** _flightWindow_

**AddTicket** _ticketWindow_

**GetInfo** _infoWindow_

final int _width_ - Sets the default dimensions of the **Interface**.

final int _height_

Dimension _dim_

JPanel _panel_ - All buttons, labels, etc. are attached to _panel_ for display.

### Constructor Summary ###
**Interface**(){} - Adds and initializes the pop-out windows, sets the title of the GUI window, adds all the buttons and their appropriate labels and actionListeners, and sets the default location of the **Interface** window.

### Method Summary ###

public void **actionPerformed**(ActionEvent _e_) - Sets the various pop-out windows visible if their corresponding button is clicked, or disposes of or saves the database.