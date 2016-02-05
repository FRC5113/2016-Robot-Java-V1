package controllers;

import drive.MotorManager;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import controllers.JoystickController;

public class Shooter
{
	//Didn't know what to call these variables, change them to whatever they are supposed to be.
	private Encoder leftWheel;
	private Encoder rightWheel;
	private Encoder angle;
	
	//Not sure if "DigitalInput" is the correct object
	//it is -Reed
	private DigitalInput maxAngle;
	private DigitalInput minAngle;
	
	private double lowValue;
	
	private Servo servo1;
	
	private CANTalon shooterTilt;
	
	private boolean intake;
	private boolean shootLow;
	private boolean tiltDown;
	private boolean tiltUp;
	
	private double tiltValue;
	
	//instantiate objects with obviously fake ports
	//Done
	public void init()
	{
		maxAngle = new DigitalInput(543212345);//fake
		minAngle = new DigitalInput(432523);//fake
		
		servo1 = new Servo(4234234);//fake
		
		shooterTilt = new CANTalon(43242);//fake
	}
	
	
	
	//Make sure to call this in Robot
	//Honestly, I was wrong I am sorry. You actually want to just call
	//the method and let the method do the checking
	//Do manual wheels like manual tilt
	public void update(MotorManager dr, JoystickController monitor)
	{
		manualTilt(dr, monitor);
		manualWheels(dr, monitor);
	}
	
	//Get value of buttons for the intake and the shoot low
	//then set an appropriate value for the respective motor(s)
	public void manualWheels(MotorManager dr, JoystickController monitor)
	{
		if(monitor.getIntake())
		{
			dr.spinShooterWheels(-0.5, 0.5);
		}
		else if(monitor.getShootLow())
		{
			dr.spinShooterWheels(0.5,  -0.5);
		}
		else
			dr.spinShooterWheels(0, 0);
	}
	
	public void autoShoot()//Don't worry 'bout this just yet.
	{
		
	}
	
	//We assume that to tilt down we have a negative value and positive to tilt up
	//We will also experimentally figure out what value to set the motor speed to, we will use .5 for now
	public void manualTilt(MotorManager dr, JoystickController monitor)
	{
		
		if(monitor.getTiltDown())
		{
			tiltValue = -.5;
		}
		else if(monitor.getTiltUp())
		{
			tiltValue = .5;
		}
		else
		{
			tiltValue = 0;
		}
		
		dr.tilt(tiltValue);
	}
}
