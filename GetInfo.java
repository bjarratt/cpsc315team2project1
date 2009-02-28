import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GetInfo extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 3L;
	Interface caller;
	
    // Buttons
    JButton queryFlightsButton = new JButton("Query Flights");
    JButton queryPassengersButton = new JButton("Query Passengers");
    JButton closeButton	= new JButton("Close");
    
    // Text boxes
    JTextArea textArea = new JTextArea("Enter query here");
    JTextField textField = new JTextField();
    
    // Organization panels
    JPanel topPanel			= new JPanel();
    JPanel topMiddlePanel	= new JPanel();
    JPanel northPanel		= new JPanel();
    JPanel fullPanel  		= new JPanel();

    final int width  = 500;
    final int height = 400;
    Dimension dim;

    
    /* Pass the calling interface so that it can be 
	 * popped back up whenever this window is (legitimately)
	 * closed. 
	 */
    public GetInfo(Interface win) {
    	caller = win;
        setTitle("Information Query");

        textField.setColumns(40);
        textArea.setText("Enter in any one piece of information that may exist about a flight or passenger.");

        
        topPanel.add(textField);
        
        topMiddlePanel.setLayout(new FlowLayout());
        topMiddlePanel.add(queryFlightsButton);
        topMiddlePanel.add(queryPassengersButton);
        topMiddlePanel.add(closeButton);
        
        northPanel.setLayout(new BorderLayout());
        northPanel.add(topPanel, BorderLayout.NORTH);
        northPanel.add(topMiddlePanel, BorderLayout.SOUTH);

        fullPanel.setLayout(new BorderLayout());
        fullPanel.add(northPanel, BorderLayout.NORTH);
        fullPanel.add(textArea, BorderLayout.CENTER);

        getContentPane().add(fullPanel, BorderLayout.CENTER);

        queryFlightsButton.addActionListener(this);
        closeButton.addActionListener(this);
        textField.addKeyListener(this);

        dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        setSize(width, height);
        setLocation((dim.width-width)/2,(dim.height-height)/2);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    void setCaller(Interface window) {
		caller = window;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(closeButton)) {
			textArea.setText("Enter in any one piece of information that\n" +
    			"may exist about a flight or passenger.");
			caller.setVisible(true);
			dispose();
		}
	}

	public void keyPressed(KeyEvent e) {
		// Make it nice and easy for the user to press enter
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            queryFlightsButton.doClick();
        }
	}

	public void keyReleased(KeyEvent e) {
		// Nothing here
		
	}

	public void keyTyped(KeyEvent e) {
		// Nothing here		
	}
}
