import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class AddTicket extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 2L;
	
	Interface caller;
		
	// Text labels	
	final JLabel flightNumLabel	= new JLabel("Flight                      #");
	final JLabel passengerLabel	= new JLabel("Passenger Flyer #");
	final JLabel costLabel		= new JLabel("Cost                       $");
		
	// Text fields	
	JTextField flightNumField 	= new JTextField(20);
	JTextField passengerField	= new JTextField(20);
	JTextField costField		= new JTextField(20);
		
	// Buttons
	JButton addButton	= new JButton("Submit");
	JButton closeButton	= new JButton("Close");
	
	// Panel for visual organization
	JPanel panel = new JPanel(new SpringLayout());
	
	final int height = 150;
	final int width  = 300;
	Dimension dim;
	
	
	/* Pass the calling interface so that it can be 
	 * popped back up whenever this window is (legitimately)
	 * closed. 
	 */
	AddTicket(Interface win) {
		caller = win;
		
		setTitle("Add Flight");		
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		panel.add(flightNumLabel);
		panel.add(flightNumField);
		panel.add(passengerLabel);
		panel.add(passengerField);
		panel.add(costLabel);
		panel.add(costField);
		panel.add(addButton);
		panel.add(closeButton);
		
		SpringUtilities.makeCompactGrid(panel,
                4, 2,	//rows, cols
                6, 6,	//initX, initY
                6, 6);	//xPad, yPad

		panel.setOpaque(true);
		setContentPane(panel);
		
		addButton.addActionListener(this);
		closeButton.addActionListener(this);
		
		setSize(width,height);
		setLocation((dim.width-width)/2,(dim.height-height)/2);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	void setCaller(Interface window) {
		caller = window;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(closeButton)) {
			flightNumField.setText("");
			passengerField.setText("");
			costField.setText("");
			caller.setVisible(true);
			dispose();
		}
		else if (e.getSource().equals(addButton)) {
			flightNumField.setText("");
			passengerField.setText("");
			costField.setText("");
		}
	}
}
