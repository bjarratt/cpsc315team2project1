import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class AddPassenger extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 2L;
	
	Interface caller;
		
	// Text labels	
	final JLabel nameLabel		= new JLabel("Name");
	final JLabel addressLabel	= new JLabel("Address");
	final JLabel flyerLabel		= new JLabel("Frequent Flyer #");
	final JLabel ageLabel		= new JLabel("Age");
	
	// Text fields	
	JTextField nameField 	= new JTextField(20);
	JTextField addressField	= new JTextField(20);
	JTextField flyerField	= new JTextField(20);
	JTextField ageField		= new JTextField(20);
	
	// Buttons
	JButton addButton	= new JButton("Submit");
	JButton closeButton	= new JButton("Close");
	
	// Panel for visual organization
	JPanel panel = new JPanel(new SpringLayout());
	
	final int width  = 300;
	final int height = 175;
	
	Dimension dim;
	
	
	/* Pass the calling interface so that it can be 
	 * popped back up whenever this window is (legitimately)
	 * closed. 
	 */
	AddPassenger(Interface win) {
		caller = win;
		
		setTitle("Add Passenger");		
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(addressLabel);
		panel.add(addressField);
		panel.add(flyerLabel);
		panel.add(flyerField);
		panel.add(ageLabel);
		panel.add(ageField);
		panel.add(addButton);
		panel.add(closeButton);
		
		SpringUtilities.makeCompactGrid(panel,
                5, 2,	//rows, cols
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(closeButton)) {
			nameField.setText("");
			addressField.setText("");
			flyerField.setText("");
			ageField.setText("");
			caller.setVisible(true);
			dispose();
		}
		else if (e.getSource().equals(addButton)) {
			nameField.setText("");
			addressField.setText("");
			flyerField.setText("");
			ageField.setText("");
		}
	}
}