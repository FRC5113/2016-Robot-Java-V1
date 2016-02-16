package auton;

import drive.SensorManager;

public class Moat extends DefenseFrame
{
	private int servoDir;
	private boolean selectorCheck = false;
	private final double speed1 = 0.5, speed2 = 1;
	private double time;
	
	public void update(SensorManager sensors)
	{
		if(sensors.getGyroZAngle() <= 5 && !selectorCheck)
			servoDir = 1;
		
		switch(servoDir)
		{
		case 1:
			controller.move(speed1);
			
			if(sensors.getGyroZAngle() > 5)
			{
				servoDir = 2;
				time = System.currentTimeMillis();
			}
			
			break;
			
		case 2:
			controller.move(speed2);
			
			if(sensors.getGyroZAngle() <= 5 && System.currentTimeMillis() - time >= 5000)
				servoDir = 3;
			
			break;
			
		case 3:
			controller.stop();
			System.out.println("Done!");
			break;
		}
	}
}
