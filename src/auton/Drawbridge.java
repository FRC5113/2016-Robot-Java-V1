package auton;

import drive.SensorManager;

public class Drawbridge extends DefenseFrame
{	
	//WE DON'T KNOW ENOUGH ABOUT ARM YET SO I WILL DO THIS LATER HOW CAN I MAKE THIS COMMENT LONGER AND BEN IS A PLEB WHICH WAS SAID BY KYLE WHO IS AN UNRELIABLE SOURCE
	
	//NONE OF THESE VALUES ARE REAL VALUES
	private final double speed1 = 0.33;
	private final double speed2 = 0.66;
	private final double speed3 = 1;
	private final double distance = 200;
	private final double angle1 = 180 * 1/3;
	private final double angle2 = 180 * 2/3;
	private final double angle3 = 180;
	private final double gyroAngle = 5;
	private final double upperMotorAngle = 50;
	private final double lowerMotorAngle = 25;
	int servoDir = 1;
	
	@Override
	public void update(SensorManager sensors)
	{
		switch(servoDir)
		{
		case 1:
			if(sensors.getSonicRangeInches() < distance)
				controller.move(speed1);
			else
			{
				controller.stop();
				servoDir = 2;
			}
			
			break;
		case 2:
			controller.setArm(angle1);
			servoDir = 3;
			break;
		case 3:
			controller.move(-speed2);
			servoDir = 4;
			break;
		case 4:
			controller.setArm(angle2);
			controller.setShooter(angle2);
			
		}
	}
}
