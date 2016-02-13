package drive;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class SensorManager
{
	//adding the gyroscope and accelerometer into single class
	
	BuiltInAccelerometer accel;
	
	AnalogGyro gyroXY;
	AnalogGyro gyroZ;
	
	private Encoder encoder;
	
	private AnalogInput stringPot;
	
	private AnalogInput sonicRange;//sonicRAGE
	
	private USBCamera lmao;
	
	public void init()
	{
		//accelerometer
		accel = new BuiltInAccelerometer();
		System.out.println("Test for toString of Accelerometer: " + accel.toString());
		System.out.println("Test for toString(NO toString) of Accelerometer: " + accel);
		System.out.println("Accelerometer X is now initiated\t" + accel.getX());
		System.out.println("Accelerometer Y is now initiated\t" + accel.getY());
		System.out.println("Accelerometer Z is now initiated\t" + accel.getZ());
		
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
		
		lmao = new USBCamera();
		
		lmao.startCapture();
	}
	
	public void update()
	{
		gyroXY.updateTable();
		gyroZ.updateTable();
		accel.updateTable();
		lmao.updateSettings();
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
		
	public double getAccelX()
	{
		return accel.getX();
	}
	
	public double getAccelY()
	{
		return accel.getY();
	}
	
	public double getAccelZ()
	{
		return accel.getZ();
	}
	
	public String getLmao()
	{
		while(true)
		{
			for(int i = 0; i <= 6; i++)
			{
				if(i == 0)
					return "A";
				if(i == 1)
					return "Y";
				if(i == 2)
					return "Y";
				if(i == 3)
					return "L";
				if(i == 4)
					return "M";
				if(i == 5)
					return "A";
				if(i == 6)
					return "O";
			}
		}
	}
}
