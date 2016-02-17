package drive;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;

public class MotorManager 
{
	/* ayylmao
	 * dank memes
	 * Copy Pasta
	 */
    
    
	private CANTalon leftMotor1;
	private CANTalon leftMotor2;
	private CANTalon rightMotor1;
	private CANTalon rightMotor2;
	private CANTalon leftTake;
	private CANTalon rightTake;
	
	//We are assuming this is a CAN
	private CANTalon tiltMotorShoot;
	
	//^Lol, same xD
	private CANTalon tiltMotorArm;
	
	//^ditto
	private CANTalon tiltMotorJoint;
	
	//The hook
	private CANTalon captainHook;
	
	RobotDrive roboDrive;
	
	public void init()
	{
		leftMotor1 = new CANTalon(0);//blaze it
		leftMotor1.set(0);
		
		leftMotor2 = new CANTalon(1);//blaze it
		leftMotor2.set(0);
		
		rightMotor1 = new CANTalon(2);//leet
		rightMotor1.set(0);
		
		rightMotor2 = new CANTalon(3);//leet
		rightMotor2.set(0);
		
		tiltMotorShoot = new CANTalon(4);//us
		tiltMotorShoot.set(0);
		
		tiltMotorArm = new CANTalon(5);//Bob Ross
		tiltMotorArm.set(0);
		
		tiltMotorJoint = new CANTalon(6);//not 9/11
		tiltMotorJoint.set(0);
		
		leftTake = new CANTalon(7);//not quite 9000
		leftTake.set(0);
		
		rightTake = new CANTalon(8);//over 9000
		rightTake.set(0);
		
		captainHook = new CANTalon(9);//What is a pirate's favorite letter?
		captainHook.set(0);
		
		roboDrive = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
	}
	
	public void tankDrive(double leftValue, double rightValue)//HOW DO I GET MEMES?!??!?
	{	
		double leftPower = leftValue;
		double rightPower = rightValue;
		
		leftMotor1.set(leftPower);
		leftMotor2.set(leftPower);
		rightMotor1.set(rightPower);
		rightMotor2.set(rightPower);
	}
	
	public void tiltShoot(double tiltValue)
	{
		tiltMotorShoot.set(tiltValue);
	}
	
	public void tiltArm(double tiltValue)
	{
		tiltMotorArm.set(tiltValue);
		
	}
	
	public void tiltJoint(double tiltValue)
	{
		tiltMotorJoint.set(tiltValue);
	}
	
	public void spinShooterWheels(double leftWheel, double rightWheel)
	{
		leftTake.set(leftWheel);
		rightTake.set(rightWheel);
	}
	
	public void moveHook(double speed)
	{
		captainHook.set(speed);
	}
	
	
}