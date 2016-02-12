package controllers;

import drive.MotorManager;
import drive.SensorManager;
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
	
	public Servo pusher;//rebel
	
	private boolean intake;
	private boolean shootLow;
	private boolean tiltDown;
	private boolean tiltUp;
	
	private double lowValue;
	private double tiltValue;
	private double timer;
	private int servoDir;
	
	//instantiate objects with obviously fake ports
	//Done
	public void init()
	{
		maxAngle = new DigitalInput(2);//fake
		minAngle = new DigitalInput(3);//fake
		
		pusher = new Servo(0);//real
		pusher.setAngle(0);
		
		servoDir = 1;
	}
	
	//It requires dr for motor access, monitor for stick access and sensors for sensor access (Especially for auto-shoot)
	public void update(MotorManager dr, JoystickController monitor, SensorManager sensors)
	{
		manualTilt(dr, monitor);
		manualWheels(dr, monitor);
		
		/*s e r v o 
		  e 
		  r
		  v
		  o		  */
		
		switch(servoDir)
		{
			case 1:
				if(monitor.getServo())
				{
					servoDir = 2;
				}
				break;
				
			case 2:
				pusher.setAngle(180);
				servoDir = 3;
				timer = System.currentTimeMillis();
				break;
			
			case 3:
				if(pusher.getAngle() > 175.0 && System.currentTimeMillis() - timer > 500)
				{
					pusher.setAngle(0);
				}
				servoDir = 1;
				break;
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
	
}
