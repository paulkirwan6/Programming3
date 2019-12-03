/*
 * Paul Kirwan
 * 17321313
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LightsController extends JFrame {
	
	private boolean on = false;
	private JRadioButton manualButton, timedButton;
	private JButton statusButton;
	private JSlider slider;
	private JTextField statusTextField;
	private JToggleButton tButton;
	private JPanel north, lights;
	private JPanel one, two, three, four;
	
	//Set default display messages
	private String buttonMessage = "Manual";
	private String sliderMessage = "LightLevel: 1";
	private String onOrOff = "off";

	// set up GUI
	public LightsController()
	{
		super("Lights Controller");

		//Create On/Off toggleButton
		tButton = new JToggleButton("off");
		ToggleButtonHandler tbh = new ToggleButtonHandler();
		tButton.addActionListener(tbh);
		
		//create radio buttons for manual/timed selection as only one should be selected at a time
		manualButton = new JRadioButton("Manual", true);
		timedButton = new JRadioButton( "Timed");
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(manualButton);
		bgroup.add(timedButton);
		
		//instance of button handler for the radio button event handling
		RadioButtonHandler rbHandler = new RadioButtonHandler();
		manualButton.addActionListener(rbHandler);
		timedButton.addActionListener(rbHandler);

		//Status button with event handling
		statusButton = new JButton("Current Status:");
		ButtonHandler bHandler = new ButtonHandler();
		statusButton.addActionListener(bHandler);

		//Create slider with 4 options and event handling
		slider = new JSlider(1, 4);
		slider.setValue(1);
		SliderHandler sHandler = new SliderHandler();
		slider.addChangeListener(sHandler);
		
		//Create textField to display current status message
		statusTextField = new JTextField();
		statusTextField.setEditable(false);
		setMessage("", "", "");
		
		//Display lights as JPanels
		one = new JPanel();
		two = new JPanel();
		three = new JPanel();
		four = new JPanel();
		
		//Configure light dimensions
		one.setPreferredSize(new Dimension(90,50));
		two.setPreferredSize(new Dimension(90,50));
		three.setPreferredSize(new Dimension(90,50));
		four.setPreferredSize(new Dimension(90,50));
		
		//Display lights as on/off based on toggleButton and slider values
		displayLights();
		
		//Create the GUI layout
		north = new JPanel();
		north.add(timedButton, BorderLayout.EAST);
		north.add(manualButton, BorderLayout.WEST);
		
		//Add lights to a new JPanel
		lights = new JPanel();
		lights.setLayout(new FlowLayout());
		lights.add(one);
		lights.add(two);
		lights.add(three);
		lights.add(four);
		
		//Configure the container
		Container container = getContentPane();
		container.setLayout(new GridLayout(6, 1));
		container.add(north);
		container.add(tButton);
		container.add(lights);
		container.add(slider);
		container.add(statusButton);
		container.add(statusTextField);
		
		setSize(400, 200);
		setVisible(true);
		setResizable(false);
	}
	
	//Display Lights as coloured JLabels based on the values from toggleButton and slider
	public void displayLights() {
		
		//Get light value from slider
		int value = slider.getValue();
		
		//Light level 1/4
		if (on & value == 1) {
			one.setBackground(Color.yellow);
			two.setBackground(Color.black);
			three.setBackground(Color.black);
			four.setBackground(Color.black);
			
		}
		//Light level 2/4
		else if (on & value == 2) {
			one.setBackground(Color.yellow);
			two.setBackground(Color.yellow);
			three.setBackground(Color.black);
			four.setBackground(Color.black);
		}
		//Light level 3/4
		else if (on & value == 3) {
			one.setBackground(Color.yellow);
			two.setBackground(Color.yellow);
			three.setBackground(Color.yellow);
			four.setBackground(Color.black);
		}
		//Light level 4/4
		else if (on & value == 4) {
			one.setBackground(Color.yellow);
			two.setBackground(Color.yellow);
			three.setBackground(Color.yellow);
			four.setBackground(Color.yellow);
		}
		else { //Lights off
			one.setBackground(Color.black);
			two.setBackground(Color.black);
			three.setBackground(Color.black);
			four.setBackground(Color.black);
		}
	}
	
	//Display the status of the LightsController in the TextField
	public void setMessage(String onOrOff, String buttonMessage, String sliderMessage) {
		
		statusTextField.setText(onOrOff + '\t'+ '\t' + buttonMessage + '\t' + sliderMessage);
	}

	// execute application
	public static void main( String args[] ) {
		
		LightsController application = new LightsController();
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
	}
	
	//inner class for on/off toggle button event handling
	private class ToggleButtonHandler implements ActionListener {

		@Override
		public void actionPerformed( ActionEvent event ) {
			
			//Update lights based on on/off button status
			on = ((AbstractButton) event.getSource()).isSelected();
			onOrOff = (on? "on":"off");
			displayLights();
			
			//Update message based on on/off
			tButton.setText(onOrOff);
			setMessage("","","");	//Reset status textField
			System.out.println("Lights toggled " + onOrOff);
		}
	}

	//inner class for manual/timed button event handling
	private class RadioButtonHandler implements ActionListener {

		//update buttonMessage with 'timed' or 'manual'
		@Override
		public void actionPerformed( ActionEvent event ) {
			buttonMessage = event.getActionCommand();
			setMessage("","","");	//Reset status textField
			System.out.println(buttonMessage + " pressed.");
		}
	}

	//inner class for current status button event handling 
	private class ButtonHandler implements ActionListener {

		//update the current status text field
		@Override
		public void actionPerformed( ActionEvent event ) {
			setMessage("Lights are " + onOrOff, buttonMessage, sliderMessage);
			System.out.println("Current Status Pressed.");
		}
	}

	//inner class for slider event handling
	private class SliderHandler implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent event) {
			
			//Update the lights display as slider changes
			int value = slider.getValue();
			displayLights();
			
			//Update  messages based on slider
			sliderMessage = "Light level: " + value;
			setMessage("", "", "");	//Reset status textField
			System.out.println("Slider changed to value: " + value);
		}
	}
} // end class LightsController