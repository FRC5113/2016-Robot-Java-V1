package drive;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorManager
{
	//adding the gyroscope and accelerometer into single class
	
	BuiltInAccelerometer accel;
	
	AnalogGyro gyroXY;
	AnalogGyro gyroZ;
	
	private Encoder encoder;
	
	private AnalogInput stringPot;
	
	private AnalogInput sonicRange;//sonicRAGE
	
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

		SmartDashboard.putString("AyyLmao", "Ayy Lmao");
	}
	
	public void update()
	{
		gyroXY.updateTable();
		gyroZ.updateTable();
		accel.updateTable();
		
		SmartDashboard.getString("AyyLmao", "Ayy Lmao");
	}
	
	public int getEncoderValues()
	{
		//Not sure which one we should use.
		//Returns raw value from the encoder.
		return encoder.getRaw();
		//Returns the current count from the encoder.
		//return encoder.get();
	}
	
	public void resetEncoder()
	{
		encoder.reset();
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
		
}
