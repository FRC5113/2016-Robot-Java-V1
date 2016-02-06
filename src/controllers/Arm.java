package controllers;

import drive.MotorManager;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class Arm
{
	
	private DigitalInput minAngleBase;
	private DigitalInput maxAngleBase;
	
	private double tiltValueBase;
	private double tiltValueJoint;
	
	
	public void init() 
	{
		minAngleBase = new DigitalInput(12345);
		maxAngleBase = new DigitalInput(54321);
		
	}

	
	public void update(MotorManager dr, JoystickController monitor) 
	{
		tiltBase(dr, monitor);
		tiltJoint(dr, monitor);
		
	}
	
	public void tiltBase(MotorManager dr, JoystickController monitor)
	{
		tiltValueBase = monitor.getTiltArm();
		
		dr.tiltArm(tiltValueBase);
	}
	
	public void tiltJoint(MotorManager dr, JoystickController monitor)
	{
		tiltValueJoint = monitor.getTiltJoint();
		
		dr.tiltJoint(tiltValueJoint);
	}
	
	public void moveHook()
	{
		
	}
	
	public void reset()
	{
		
	}

}
