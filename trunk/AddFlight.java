import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class AddFlight extends JFrame implements ActionListener, WindowListener {
	
	private static final long serialVersionUID = 2L;
	
	Interface caller;
		
	// Text labels	
	final JLabel flightNumLabel	= new JLabel("Flight                 #");
    final JLabel planeNumLabel	= new JLabel("Plane                #");
	final JLabel numPassLabel	= new JLabel("# Passengers");
    final JLabel depCityLabel	= new JLabel("Departure City");
	final JLabel arrCityLabel	= new JLabel("Arrival City");
    final JLabel mealLabel  	= new JLabel("Meal? (yes/no)");
	final JLabel timeLabel		= new JLabel("Departure Time");
	
	// Text fields	
	JTextField flightNumField 	= new JTextField(20);
    JTextField planeNumField 	= new JTextField(20);
    JTextField mealField        = new JTextField(20);
	JTextField numPassField		= new JTextField(20);
	JTextField depCityField		= new JTextField(20);
	JTextField arrCityField		= new JTextField(20);
	JTextField timeField		= new JTextField(20);
	
	// Buttons
	JButton addButton	= new JButton("Submit");
	JButton backButton	= new JButton("< Back");
	
	// Panel for visual organization
	JPanel panel = new JPanel(new SpringLayout());
	
	final int height = 275;
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
        panel.add(planeNumLabel);
		panel.add(planeNumField);
		panel.add(numPassLabel);
		panel.add(numPassField);
		panel.add(depCityLabel);
		panel.add(depCityField);
		panel.add(arrCityLabel);
		panel.add(arrCityField);
        panel.add(mealLabel);
        panel.add(mealField);
		panel.add(timeLabel);
		panel.add(timeField);
		panel.add(addButton);
		panel.add(backButton);
		
		SpringUtilities.makeCompactGrid(panel,
                8, 2,	//rows, cols
                6, 6,	//initX, initY
                6, 6);	//xPad, yPad

		panel.setOpaque(true);
		setContentPane(panel);
		
		addButton.addActionListener(this);
		backButton.addActionListener(this);
		
		addWindowListener(this);
		setSize(width,height);
		setLocation((dim.width-width)/2,(dim.height-height)/2);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	void setCaller(Interface window) {
		caller = window;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(backButton)) {
			flightNumField.setText("");
			numPassField.setText("");
			depCityField.setText("");
			arrCityField.setText("");
			timeField.setText("");
			caller.setVisible(true);
			dispose();
		}
		else if (e.getSource().equals(addButton)) {
            if (flightNumField.getText().equals("") || numPassField.getText().equals("") || depCityField.getText().equals("")
                    || arrCityField.getText().equals("") || timeField.getText().equals(""))
                return;
            else {
                TableOps.insertInto("FlightInfo VALUES (" + flightNumField.getText() + "," + planeNumField.getText()
                        + "," + numPassField.getText() + ","  +  depCityField.getText() + "," + arrCityField.getText()
                        + "," + mealField.getText() + "," + timeField.getText() + ")");

                flightNumField.setText("");
                planeNumField.setText("");
                mealField.setText("");
                numPassField.setText("");
                depCityField.setText("");
                arrCityField.setText("");
                timeField.setText("");
                
                JOptionPane.showMessageDialog(null, "Flight added successfully.");
            }
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		flightNumField.setText("");
		numPassField.setText("");
		depCityField.setText("");
		arrCityField.setText("");
		timeField.setText("");
		caller.setVisible(true);		
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}
}
