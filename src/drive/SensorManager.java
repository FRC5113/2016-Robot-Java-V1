package drive;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

public class SensorManager
{
	//adding the gyroscope and accelerometer into single class
	
	BuiltInAccelerometer accel;
	//AnalogGyro gyro;
	
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
		//gyro = new AnalogGyro(1);
		//gyro.initGyro();
		//System.out.println("Gyro is now initiated\t" + gyro.getAngle());
		
		
		encoder = new Encoder(0, 1);
		
		stringPot = new AnalogInput(0);
		
		
		sonicRange = new AnalogInput(1);//sonicRage

	}
	
	public void update()
	{
		//gyro.updateTable();
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
		
	
}
