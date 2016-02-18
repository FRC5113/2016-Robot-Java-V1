package controllers;

import drive.MotorManager;
import drive.PID;
import drive.SensorManager;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;

public class Shooter
{
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
	
	private boolean autoShootToggle;
	private double debounce;
	
	private double distance;
	private double index;
	private double angle;
	private double velocity;
	private AimParameters whereToShoot;
	private PID pid;
	
	//instantiate objects with obviously fake ports
	//Done
	public void init()
	{
		maxAngle = new DigitalInput(2);//fake
		minAngle = new DigitalInput(3);//fake
		
		pusher = new Servo(0);//real
		pusher.setAngle(0);
		
		servoDir = 1;
		
    	autoShootToggle = false; 
    	debounce = -5000;
	}
	
	//It requires dr for motor access, monitor for stick access and sensors for sensor access (Especially for auto-shoot)
	public void update(MotorManager dr, JoystickController monitor, SensorManager sensors, ShooterSubSystem SSS)
	{
		manualTilt(dr, monitor);
		manualWheels(dr, monitor);
		
		//Calling autoshoot
        // if "A" is pressed & it has been at least 5 seconds since last time "A" has been pressed
        if(monitor.getActivateAutoShoot() && System.currentTimeMillis() - debounce > 5000) 
        {
        	debounce = System.currentTimeMillis();
        	autoShootToggle = !autoShootToggle;
        }
        
        if(autoShootToggle)
        	autoShoot(SSS, sensors, dr);
		
		/*s e r v o 
		  e 
		  r
		  v
		  o		  */
		
		switch(servoDir)
		{
			case 1:
				
				if(monitor.getServo())//RIP rumble 2016
				{
					pusher.setAngle(180);
					servoDir = 2;
					timer = System.currentTimeMillis();
				}
			
				break;
				
			case 2:
				if(pusher.getAngle() > 175.0 && System.currentTimeMillis() - timer > 750)
				{
					pusher.setAngle(0);
					servoDir = 1;
				}
				
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
	
	public void autoShoot(ShooterSubSystem SSS, SensorManager sensors, MotorManager dr)//Now is the time to do this. 
	{
		distance = sensors.getSonicRangeInches();
		whereToShoot = SSS.getAimParmFromArray(distance);
		
		angle = whereToShoot.getCarriageTiltAngle();
		velocity = whereToShoot.getWheelRotationVelocity();
		
		
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
