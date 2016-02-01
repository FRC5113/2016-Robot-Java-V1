package drive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.RobotDrive;

public class MotorManager 
{
	/*ayylmao
	 * dank memes
	 * Copy Pasta
	*/
	
	private CANTalon leftMotor;
	private CANTalon rightMotor; 
	
	RobotDrive roboDrive;
	
	public void init()
	{
		leftMotor = new CANTalon(420);//blaze it
		leftMotor.set(0);
		
		rightMotor = new CANTalon(1337);//leet
		rightMotor.set(0);
		
		roboDrive = new RobotDrive(leftMotor, rightMotor);
	}
	
	public void tankDrive(double leftValue, double rightValue)//HOW DO I GET MEMES?!??!?
	{	
		double leftPower = leftValue;
		double rightPower = rightValue;
		
		leftMotor.set(leftPower);
		rightMotor.set(rightPower);
	}
}