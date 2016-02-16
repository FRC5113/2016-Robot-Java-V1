package auton;

import drive.SensorManager;

public class ChivalDeFrise extends DefenseFrame
{
	//NONE OF THESE VALUES ARE CORRECT[REMEMBER YOUR SEMICOLONS];
	private final double speed1 = 1, speed2 = 0.75;
	private final double gyroAngle = 5;
	private final double shooterAngle = 0;
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
				controller.stop();
		}
	}
}
