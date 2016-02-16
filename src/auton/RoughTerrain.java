package auton;

import drive.SensorManager;

public class RoughTerrain extends DefenseFrame
{
	private int servoDir = 1;
	private double speed = 0.5;
	private double time;
	
	public void update(SensorManager sensors)
	{
		switch(servoDir)
		{
		case 1:
			controller.move(speed);
			time = System.currentTimeMillis();
			
			if(sensors.getGyroZAngle() > 5)
				servoDir = 2;
			
			break;
		
		case 2:
			controller.move(speed);
			
			if(sensors.getGyroZAngle() <= 5 && System.currentTimeMillis() - time > 5000)
				servoDir = 3;
			
			break;
			
		case 3:
			controller.stop();
			System.out.println("Done!");
			
			break;
				
		}
	}
}
