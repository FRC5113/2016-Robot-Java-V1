package controllers;

import drive.MotorManager;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class Arm
{
	private CANTalon baseMotor;
	private CANTalon joinMotor;
	private CANTalon winchMotor;
	
	private DigitalInput minAngleBase;
	private DigitalInput maxAngleBase;
	
	private boolean tiltUpArm;
	private boolean tiltDownArm;
	private boolean tiltUpJoint;
	private boolean tiltDownJoint;
	
	private double tiltValueBase;
	private double tiltValueJoint;
	
	public void tiltBase(MotorManager dr, JoystickController monitor)
	{
		if(monitor.getTiltDownArm())
		{
			tiltValueBase = -.5;
		}
		else if(monitor.getTiltUpArm())
		{
			tiltValueBase = .5;
		}
		else
		{
			tiltValueBase = 0;
		}
		
		dr.tiltArm(tiltValueBase);
	}
	
	public void tiltJoint(MotorManager dr, JoystickController monitor)
	{
		if(monitor.getTiltDownJoint())
		{
			tiltValueJoint = -.5;
		}
		else if(monitor.getTiltDownJoint())
		{
			tiltValueJoint = .5;
		}
		else
		{
			tiltValueJoint = 0;
		}
		
		dr.tiltJoint(tiltValueJoint);
		}
	
	public void moveHook()
	{
		
	}
	
	public void reset()
	{
		
	}
}
