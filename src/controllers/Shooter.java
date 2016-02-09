package controllers;

import drive.MotorManager;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;

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
	
	private AnalogInput sonicRange;//sonicRAGE
	
	private double lowValue;
	
	public Servo pusher;//rebel
	
	private boolean intake;
	private boolean shootLow;
	private boolean tiltDown;
	private boolean tiltUp;
	
	private double tiltValue;
	
	//instantiate objects with obviously fake ports
	//Done
	public void init()
	{
		maxAngle = new DigitalInput(2);//fake
		minAngle = new DigitalInput(3);//fake
		
		pusher = new Servo(0);//real
		
		sonicRange = new AnalogInput(1);//sonicRage
	}
	
	//Make sure to call this in Robot
	//Honestly, I was wrong I am sorry. You actually want to just call
	//the method and let the method do the checking
	//Do manual wheels like manual tilt
	public void update(MotorManager dr, JoystickController monitor)
	{
		manualTilt(dr, monitor);
		manualWheels(dr, monitor);
		
		if(monitor.getServo())
		{
			pusher.setAngle(pusher.getAngle() + 2);
		}
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
		
		if(monitor.getTiltDownShoot() > 0.05)
		{
			tiltValue = -monitor.getTiltDownShoot();
		}
		else if(monitor.getTiltUpShoot() > 0.05)
		{
			tiltValue = monitor.getTiltUpShoot();
		}
		else
		{
			tiltValue = 0;
		}
		
		dr.tiltShoot(tiltValue);
	}
	
	public double getSonicRangeInches()
	{
		double voltage = sonicRange.getVoltage();
		double range = ((voltage * 1024) / 5) / 2.54;
		
		return range;
	}
}
