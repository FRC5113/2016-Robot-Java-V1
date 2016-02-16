package auton;

import drive.SensorManager;

public class Ramparts extends DefenseFrame
{
	//NONE OF THESE VALUES ARE KNOWN YET
	
	double gyroAngle = 80;
	double time = 5000;
	double timer;
	double speed1 = 1, speed2 = 0.5;
	int servoDir = 1;
	
	@Override
	public void update(SensorManager sensors)
	{
		switch(servoDir)
		{
		case 1:
			if(sensors.getGyroZAngle() < gyroAngle)
				controller.move(speed1);
			else
			{
				controller.move(speed2);
				timer = System.currentTimeMillis();
				servoDir = 2;
			}
			
			break;
		case 2:
			if(sensors.getGyroZAngle() < 5 && System.currentTimeMillis() - timer >= time)
				controller.stop();
		}
	}
}
