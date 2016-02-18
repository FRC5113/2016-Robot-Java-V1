package controllers;

//This is for you Kyle :)

import drive.MotorManager;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class JoystickController extends DriveController
{
	Joystick leftStick;
	Joystick rightStick;
	Joystick xboxController;
	
	private JoystickButton reverseControl; 
	private JoystickButton servo;
	private JoystickButton tiltDownShoot;
	private JoystickButton tiltUpShoot;
	private JoystickButton intake;
	private JoystickButton shootLow;
	private JoystickButton activateAutoShoot;
	private JoystickButton hookLift;
	private JoystickButton hookDrop;
	private JoystickButton emergencyStop;
	private JoystickButton emergencyStop2;
	private JoystickButton tiltArm;
	//private JoystickButton tiltDownArm;
	private JoystickButton tiltJoint;
	//private JoystickButton tiltDownJoint;
	
	//private int leftHandedness = 0;
	
	private final int xboxA = 1;
	private final int xboxB = 2;
	private final int xboxX = 3;
	private final int xboxY = 4;
	private final int xboxLB = 5;
	private final int xboxRB = 6;
	private final int xboxBACK = 7;
	private final int xboxSTART = 8;
	private final int xboxLS = 9;
	private final int xboxRS = 10;
	
	public void init() 
	{	//The numbers are the USB port that the joysticks are plugged into
		leftStick = new Joystick(0);//"Beat the devil out of it."
		rightStick = new Joystick(1);//Ayy Lmao
		xboxController = new Joystick(2);//RIP DEVIL
		

//		tiltDownShoot = new JoystickButton(xboxController, 2);
//		tiltUpShoot = new JoystickButton(xboxController, 3);
		
		reverseControl = new JoystickButton(rightStick, xboxY);
		
		
		servo = new JoystickButton(xboxController, xboxY);
		intake = new JoystickButton(xboxController, xboxX);
		shootLow = new JoystickButton(xboxController, xboxB);
		activateAutoShoot = new JoystickButton(xboxController, xboxA);
		hookLift = new JoystickButton(xboxController, xboxLB);
		hookDrop = new JoystickButton (xboxController, xboxRB);
		emergencyStop = new JoystickButton(xboxController, xboxBACK);
		emergencyStop2 = new JoystickButton(xboxController, xboxSTART);
		
		//tiltUpJoint = new JoystickButton(xboxController, 8);
		//tiltDownJoint = new JoystickButton(xboxController, 9);
		
		//tiltUpJoint = new JoystickButton(xboxController, 8);
		//tiltDownJoint = new JoystickButton(xboxController, 9);
		
		//if (leftHandedness == 1)
		//{

	//	}
		//else
		//{
		//	activateAutoShoot = new JoystickButton(xboxController, 7);
//		    hookLift = new JoystickButton(xboxController, 10);
//		    emergencyStop = new JoystickButton(xboxController, 11);
	//	}
		
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
		
		if (reverse)
		{
			leftYAxis = -leftYAxis;
			rightYAxis = -rightYAxis;
		}
		if(emergencyStop.get() && emergencyStop2.get())
		{
			dr.tankDrive(0, 0);
		}
		else
		{
			dr.tankDrive(leftYAxis, rightYAxis);
		}
	}
	
	public boolean getServo()
	{
		return servo.get();
	}
	
	public double getTiltDownShoot()
	{
		return xboxController.getRawAxis(3); 	
	}
	
	public double getTiltUpShoot()
	{
		return xboxController.getRawAxis(2);
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
	
	public boolean getHookDrop()
	{
		return hookDrop.get();
	}
	
	public double getTiltArm()
	{
		return xboxController.getRawAxis(1);
	}

	public double getTiltJoint()
	{
		return xboxController.getRawAxis(5);
	}
}