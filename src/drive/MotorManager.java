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
	
	private CANTalon leftMotor;
	private CANTalon rightMotor;
	private CANTalon leftTake;
	private CANTalon rightTake;
	
	//We are assuming this is a CAN
	private CANTalon tiltMotorShoot;
	
	//^Lol, same xD
	private CANTalon tiltMotorArm;
	
	//^ditto
	private CANTalon tiltMotorJoint;
	
	private Encoder encoder;
	
	private AnalogInput stringPot;
	
	RobotDrive roboDrive;
	
	public void init()
	{
		leftMotor = new CANTalon(0);//blaze it
		leftMotor.set(0);
		
		rightMotor = new CANTalon(1);//leet
		rightMotor.set(0);
		
		tiltMotorShoot = new CANTalon(2);//us
		tiltMotorShoot.set(0);
		
		tiltMotorArm = new CANTalon(3);//Bob Ross
		tiltMotorArm.set(0);
		
		tiltMotorJoint = new CANTalon(4);//not 9/11
		tiltMotorJoint.set(0);
		
		leftTake = new CANTalon(5);//not quite 9000
		leftTake.set(0);
		
		rightTake = new CANTalon(6);//over 9000
		rightTake.set(0);
		
		encoder = new Encoder(0, 1);
		
		stringPot = new AnalogInput(0);
		
		roboDrive = new RobotDrive(leftMotor, rightMotor);
	}
	
	public void tankDrive(double leftValue, double rightValue)//HOW DO I GET MEMES?!??!?
	{	
		double leftPower = leftValue;
		double rightPower = rightValue;
		
		leftMotor.set(leftPower);
		rightMotor.set(rightPower);
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
	
	public int getEncoderValues()
	{
		//Not sure which one we should use.
		//Returns raw value from the encoder.
		//return encoder.getRaw();
		//Returns the current count from the encoder.
		return encoder.get();
	}
	
	public double getStringPot()
	{
		return stringPot.getValue();
	}
	
	public void spinShooterWheels(double leftWheel, double rightWheel)
	{
		leftTake.set(leftWheel);
		rightTake.set(rightWheel);
	}
}