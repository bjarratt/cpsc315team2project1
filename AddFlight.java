import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class AddFlight extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 2L;
	
	Interface caller;
		
	// Text labels	
	final JLabel flightNumLabel	= new JLabel("Flight                 #");
	final JLabel numPassLabel	= new JLabel("# Passengers");
	final JLabel depCityLabel	= new JLabel("Departure City");
	final JLabel arrCityLabel	= new JLabel("Arrival City");
	final JLabel timeLabel		= new JLabel("Departure Time");
	
	// Text fields	
	JTextField flightNumField 	= new JTextField(20);
	JTextField numPassField		= new JTextField(20);
	JTextField depCityField		= new JTextField(20);
	JTextField arrCityField		= new JTextField(20);
	JTextField timeField		= new JTextField(20);
	
	// Buttons
	JButton addButton	= new JButton("Submit");
	JButton closeButton	= new JButton("Close");
	
	// Panel for visual organization
	JPanel panel = new JPanel(new SpringLayout());
	
	final int height = 200;
	final int width  = 300;
	Dimension dim;
	
	
	/* Pass the calling interface so that it can be 
	 * popped back up whenever this window is (legitimately)
	 * closed. 
	 */
	AddFlight(Interface win) {
		caller = win;
		
		setTitle("Add Flight");		
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		panel.add(flightNumLabel);
		panel.add(flightNumField);
		panel.add(numPassLabel);
		panel.add(numPassField);
		panel.add(depCityLabel);
		panel.add(depCityField);
		panel.add(arrCityLabel);
		panel.add(arrCityField);
		panel.add(timeLabel);
		panel.add(timeField);
		panel.add(addButton);
		panel.add(closeButton);
		
		SpringUtilities.makeCompactGrid(panel,
                6, 2,	//rows, cols
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
			numPassField.setText("");
			depCityField.setText("");
			arrCityField.setText("");
			timeField.setText("");
			caller.setVisible(true);
			dispose();
		}
		else if (e.getSource().equals(addButton)) {
			flightNumField.setText("");
			numPassField.setText("");
			depCityField.setText("");
			arrCityField.setText("");
			timeField.setText("");
		}
	}
}
