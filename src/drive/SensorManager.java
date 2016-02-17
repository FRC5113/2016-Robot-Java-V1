package drive;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

public class SensorManager
{
	//adding the gyroscope and accelerometer into single class

	//	ADXL345_I2C accel;
	
	/* 1 Geared rev = 360 degrees
	 * 343 Motor revs = 1 geared rev
	 * 1 encoder rev = 1 motor rev
	 * 1 encoder rev = 7 pulses
	 */
	
	AnalogGyro gyroXY;
	AnalogGyro gyroZ;

	private Encoder encoder;
	
	private int ENCODER_PULSES_PER_REV = 7;
	private int MOTOR_TO_GEAR_RATIO = 343;
	private int DEGREES_PER_GEAR_REV = 360;
	private  int MOTOR_REVS_PER_ENCODER_REV = 1;
	public static  double DEGREES_PER_ENCODER_PULSE;
	
	private int encoderAngle;
	
	private AnalogInput stringPot;
	private AnalogInput sonicRange;//sonicRAGE

	public static void main(String[] agrs)
	{
		SensorManager sensor = new SensorManager();
		System.out.println(DEGREES_PER_ENCODER_PULSE);
	}
	
	public SensorManager()
	{
		ENCODER_PULSES_PER_REV = 7;
		MOTOR_TO_GEAR_RATIO = 343;
		DEGREES_PER_GEAR_REV = 360;
		MOTOR_REVS_PER_ENCODER_REV = 1;
		
		DEGREES_PER_ENCODER_PULSE = (MOTOR_REVS_PER_ENCODER_REV/ENCODER_PULSES_PER_REV) * 
				(MOTOR_REVS_PER_ENCODER_REV/MOTOR_TO_GEAR_RATIO) *
				(DEGREES_PER_GEAR_REV/MOTOR_REVS_PER_ENCODER_REV);
		
		System.out.println("Hello World!");
		System.out.println(DEGREES_PER_ENCODER_PULSE);
	}
	
	public void init()
	{
		//accelerometer
		/*accel = new ADXL345_I2C(null, null, 0);
		System.out.println("Test for toString of Accelerometer: " + accel.toString());
		System.out.println("Test for toString(NO toString) of Accelerometer: " + accel);
		 */
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

	public void resetEncoder()
	{
		encoder.reset();
	}

	public int getEncoderAngle()
	{
		encoder.setDistancePerPulse(.5);

		return encoderAngle;
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




