import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;


public class AddTicket extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 2L;
	
	Interface caller;
		
	// Text labels	
	final JLabel flightNumLabel	= new JLabel("Flight                      #");
	final JLabel passengerLabel	= new JLabel("Passenger Flyer #");
    final JLabel passengerText	= new JLabel("");
    final JLabel nameLabel      = new JLabel("Name");
    final JLabel classLabel     = new JLabel("Class");
    final JLabel seatLabel      = new JLabel("Seat");
	final JLabel costLabel		= new JLabel("Cost                       $");
		
	// Text fields
    JTextField classField   = new JTextField(20);
    JTextField seatField    = new JTextField(20);
	JTextField costField    = new JTextField(20);

    // Combo boxes
    JComboBox flightNumBox  = new JComboBox();
    JComboBox nameBox  = new JComboBox();
		
	// Buttons
	JButton addButton	= new JButton("Submit");
	JButton backButton	= new JButton("< Back");
	
	// Panel for visual organization
	JPanel panel = new JPanel(new SpringLayout());

    boolean populated = false;
	final int height = 260;
	final int width  = 300;
	Dimension dim;
	
	
	/* Pass the calling interface so that it can be 
	 * popped back up whenever this window is (legitimately)
	 * closed. 
	 */
	AddTicket(Interface win) {
		caller = win;
		
		setTitle("Add Ticket");
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		panel.add(flightNumLabel);
		panel.add(flightNumBox);
        panel.add(nameLabel);
		panel.add(nameBox);
        panel.add(passengerLabel);
        panel.add(passengerText);
        panel.add(classLabel);
        panel.add(classField);
        panel.add(seatLabel);
        panel.add(seatField);
		panel.add(costLabel);
		panel.add(costField);
		panel.add(addButton);
		panel.add(backButton);
		
		SpringUtilities.makeCompactGrid(panel,
                7, 2,	//rows, cols
                6, 6,	//initX, initY
                6, 6);	//xPad, yPad

		panel.setOpaque(true);
		setContentPane(panel);
        passengerText.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		
		addButton.addActionListener(this);
		backButton.addActionListener(this);
        nameBox.addActionListener(this);
		
		setSize(width,height);
		setLocation((dim.width-width)/2,(dim.height-height)/2);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	void setCaller(Interface window) {
		caller = window;
	}

    void populateBoxes() {
        nameBox.removeAllItems();
        flightNumBox.removeAllItems();

        Table passTable = TableOps.select("name FROM PassengerInfo");
        for(int i=0; i<passTable.getRowCount(); i++)
            nameBox.addItem(passTable.getValueAt(i,0));

        Table flyerIDs = TableOps.select("passenger# FROM PassengerInfo");
        if(flyerIDs.getRowCount()>0)
            passengerText.setText(flyerIDs.getValueAt(0,0).toString());

        Table flightTable = TableOps.select("flight# FROM FlightInfo");
        for(int i=0; i<flightTable.getRowCount(); i++)
            flightNumBox.addItem(flightTable.getValueAt(i,0));

        Table newID = TableOps.select("passenger# FROM PassengerInfo WHERE name=" + nameBox.getSelectedItem().toString());
        passengerText.setText(newID.getValueAt(0, 0).toString());

        populated = true;
    }

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(backButton)) {
            passengerText.setText("");
            classField.setText("");
            seatField.setText("");
			costField.setText("");
			caller.setVisible(true);
            populated = false;
			dispose();
		}
		else if (e.getSource().equals(addButton)) {
            if (classField.getText().equals("") || seatField.getText().equals("") || costField.getText().equals(""))
                return;
            else {
                TableOps.insertInto("TicketInfo VALUES (" + flightNumBox.getSelectedItem() + "," + passengerText.getText()
                        + "," + classField.getText() + "," + seatField.getText() + "," + costField.getText() + ")");
                classField.setText("");
                seatField.setText("");
                costField.setText("");
                
                Table tickets = TableOps.select("* FROM TicketInfo");
                UserTables.printEntireTable(tickets);
            }
		}
        else if (e.getSource().equals(nameBox) && populated) {
            String name = nameBox.getSelectedItem().toString();
            Table newID = TableOps.select("passenger# FROM PassengerInfo WHERE name=" + name);
            passengerText.setText(newID.getValueAt(0, 0).toString());
        }
	}
}
