package auton;

import drive.SensorManager;

public class Moat extends DefenseFrame
{
	private int caseSelector;
	private boolean caseSelectorCheck = false;
	private double speed1 = 0.5;
	private double speed2 = 1;
	private double time;
	
	public void update(SensorManager sensors)
	{
		if(sensors.getGyroZAngle() <= 5 && !caseSelectorCheck)
			caseSelector = 1;
		
		else if(sensors.getGyroZAngle() > 5)
			caseSelector = 2;
		
		else if(sensors.getGyroZAngle() <= 5 && caseSelectorCheck && System.currentTimeMillis() - time >= 5000)
		{
			caseSelector = 3;
			time = System.currentTimeMillis();
		}
		
		switch(caseSelector)
		{
		case 1:
			controller.forward(speed1);
			caseSelectorCheck = true;
			break;
			
		case 2:
			controller.forward(speed2);
			break;
			
		case 3:
			System.out.println("Done!");
			break;
		}
	}
}
