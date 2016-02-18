package drive;

import controllers.JoystickController;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class SensorManager
{
	//adding the gyroscope and accelerometer into single class
	
	BuiltInAccelerometer accel;
	
	AnalogGyro gyroXY;
	AnalogGyro gyroZ;
	
	private Encoder encoder;
	
	private AnalogInput stringPot;
	
	private AnalogInput sonicRange;//sonicRAGE
	
	private int encoderAngle;
	
	private double gyroXYAngle;
	private double gyroZAngle;
	
	
	public void init()
	{
		//accelerometer
		accel = new BuiltInAccelerometer();
		System.out.println("Test for toString of Accelerometer: " + accel.toString());
		System.out.println("Test for toString(NO toString) of Accelerometer: " + accel);
		
		//gyroscope
		gyroXY = new AnalogGyro(0);
		gyroXY.initGyro();
		System.out.println("Gyro XY is now initiated\t" + gyroXY.getAngle());
		
		gyroZ = new AnalogGyro(1);
		gyroZ.initGyro();
		System.out.println("Gyro Z is now initiated\t" + gyroZ.getAngle());
		
		encoder = new Encoder(0, 1);
		
		stringPot = new AnalogInput(3);
		
		sonicRange = new AnalogInput(2);//sonicRage

	}
	
	public void update()
	{
		gyroXY.updateTable();
		gyroZ.updateTable();
		accel.updateTable();
	}
	
	public int getEncoderValues()
	{
		//Not sure which one we should use.
		//Returns raw value from the encoder.
		return encoder.getRaw();
		//Returns the current count from the encoder.
		//return encoder.get();
	}
	
	public double getEncoderDistance()
	{
		return encoder.getDistance();
	}
	
	public int getEncoderCount()
	{
		return encoder.get();
	}
	
	public double getEncoderRate()
	{
		return encoder.getRate();
	}
	
	public void getDistancePerPulse(double DistancePerPulse)
	{
		encoder.setDistancePerPulse(0.5);
	}
	
	public double getEncoderAngularSpeed()
	{
		double PulsePerRotation = 720;
		double Rate = encoder.getRate();
		double DistancePerPulse = 0.5;
		double AngularSpeed = PulsePerRotation * Rate * DistancePerPulse;
		return AngularSpeed;
	}
	
	public void resetEncoder()
	{
		encoder.reset();
	}
	
	public double getEncoderAngle()
	{
		double raw = encoder.get();
		raw = raw / 250;
		
		return raw;
	}
	
	public double getStringPot()
	{
		return stringPot.getValue();
	}
	
	public double getSonicRangeInches()
	{
		double voltage = sonicRange.getVoltage();
		double range = ((voltage * 1024) / 5) / 2.54;
		
		return range;
	}
	
	public double getGyroXYAngle()
	 
	{
		return gyroXY.getAngle();
	}
	
	 public double getGyroZAngle()
	 {
		 return gyroZ.getAngle();
	 }
	 
	 public void resetGyroAngles(JoystickController joystick)
	 {
		 if(joystick.getGyroReset())
		 {
		   gyroXY.reset();
		   gyroZ.reset();
		 }
			 
	 }
}
		

