package auton;

import drive.SensorManager;

public class Moat extends DefenseFrame
{
	private int caseSelector;
	private double speed1 = 0.5;
	private double speed2 = 1;
	private double time;
	private double theta;
	
	public void update(SensorManager sensors)
	{
		//if(sensors.)
		
		switch(caseSelector)
		{
		case 1:
			controller.forward(speed1);
			break;
		}
	}
}
