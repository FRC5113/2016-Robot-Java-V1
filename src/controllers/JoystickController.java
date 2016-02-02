package controllers;

//This is for you Kyle :)

import drive.MotorManager;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger.ButtonScheduler;

public class JoystickController extends DriveController
{
	
	Joystick leftStick;
	Joystick rightStick;

	private JoystickButton reverseControl;
	
	public void init() 
	{	//The numbers are the USB port that the joysticks are plugged into
		leftStick = new Joystick(0);//"Beat the devil out of it."
		rightStick = new Joystick(1);//Ayy Lmao
		
		reverseControl = new JoystickButton(rightStick, 4);
	}
	
	public void update(MotorManager dr) 
	{
		double leftYAxis = leftStick.getY();
		double rightYAxis = rightStick.getY();
		
		if(leftYAxis > 0.99)
			leftYAxis = 0.99;
		
		if(leftYAxis < -0.99)
			leftYAxis = -0.99;
		
		if(rightYAxis > 0.99)
			rightYAxis = 0.99;
		
		if(rightYAxis < -0.99)
			rightYAxis = -0.99;

		boolean reverse = reverseControl.get();
		
		if (reverse == true)
		{
			leftYAxis = -leftYAxis;
			rightYAxis = -rightYAxis;
		}
		
		dr.tankDrive(leftYAxis, rightYAxis);
	}
	
}