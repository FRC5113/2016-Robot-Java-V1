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
	Joystick monitorStick;

	private JoystickButton reverseControl; 
	private JoystickButton servo;
	private JoystickButton tiltDown;
	private JoystickButton tiltUp;
	private JoystickButton intake;
	private JoystickButton shootLow;
	private JoystickButton activateAutoShoot;
	private JoystickButton hookLift;
	private JoystickButton emergencyStop;
	
	private int leftHandedness = 0;
	
	public void init() 
	{	//The numbers are the USB port that the joysticks are plugged into
		leftStick = new Joystick(0);//"Beat the devil out of it."
		rightStick = new Joystick(1);//Ayy Lmao
		monitorStick = new Joystick(2);//
		
		reverseControl = new JoystickButton(rightStick, 4);
		
		servo = new JoystickButton(monitorStick, 1);
		tiltDown = new JoystickButton(monitorStick, 2);
		tiltUp = new JoystickButton(monitorStick, 3);
		intake = new JoystickButton(monitorStick, 4);
		shootLow = new JoystickButton(monitorStick, 5);
		
		if (leftHandedness == 1)
		{
		  activateAutoShoot = new JoystickButton(monitorStick, 10);
		  hookLift = new JoystickButton(monitorStick, 7);
		  emergencyStop = new JoystickButton(monitorStick, 6);
		}
		  else
		    {
			activateAutoShoot = new JoystickButton(monitorStick, 7);
		    hookLift = new JoystickButton(monitorStick, 10);
		    emergencyStop = new JoystickButton(monitorStick, 11);
		    }
		
	}
	
	public boolean getServo()
	{
		return servo.get();
	}
	
	public boolean getTiltDown()
	{
		return tiltDown.get(); 	
	}
	
	public boolean getTiltUp()
	{
		return tiltUp.get();
	}
	
	public boolean getIntake()
	{
		return intake.get();
	}
	
	public boolean getShootLow()
	{
		return shootLow.get();
	}
	
	public boolean getActivateAutoShoot()
	{
		return activateAutoShoot.get();
	}
	
	public boolean getHookLift()
	{
		return hookLift.get(); 
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