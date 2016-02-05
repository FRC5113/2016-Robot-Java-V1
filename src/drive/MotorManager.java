package drive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;

public class MotorManager 
{
	/*ayylmao
	 * dank memes
	 * Copy Pasta
	*/
	
	private CANTalon leftMotor;
	private CANTalon rightMotor; 
	
	//We are assuming this is a CAN
	private CANTalon tiltMotor;
	
	private Encoder encoder;
	
	RobotDrive roboDrive;
	
	public void init()
	{
		leftMotor = new CANTalon(420);//blaze it
		leftMotor.set(0);
		
		rightMotor = new CANTalon(1337);//leet
		rightMotor.set(0);
		
		tiltMotor = new CANTalon(5113);
		tiltMotor.set(0);
		
		roboDrive = new RobotDrive(leftMotor, rightMotor);
	}
	
	public void tankDrive(double leftValue, double rightValue)//HOW DO I GET MEMES?!??!?
	{	
		double leftPower = leftValue;
		double rightPower = rightValue;
		
		leftMotor.set(leftPower);
		rightMotor.set(rightPower);
	}
	
	//Add in Limit Switches when created
	public void tilt(double tiltValue)
	{
		tiltMotor.set(tiltValue);
	}
	
	public int getEncoderValues(Encoder e)
	{
		encoder = e;
		//Not sure which one we should use.
		//Returns raw value from the encoder.
		//return encoder.getRaw();
		//Returns the current count from the encoder.
		return encoder.get();
	}
}