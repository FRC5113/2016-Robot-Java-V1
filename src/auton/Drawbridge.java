package auton;

import drive.SensorManager;
																				// lol have fun with this
public class Drawbridge extends DefenseFrame									// it's a boi
{
	private int caseSelector;													//      |
	private boolean selectorCheck = false;										//      |
	private double speed1 = 0.5;												// 		|
	private double speed2 = 1.0;												//		V
	private double speed3 = 0.5;
	private double degrees = 20.0;
	private double time;
	
	@Override
	public void update(SensorManager sensors)
	{
		if(sensors.getGyroZAngle() <= 5 && !selectorCheck)
			caseSelector = 1;
		
		switch(caseSelector)
		{
		case 1: //Approach the defense          ______
			controller.forward(speed1);    //   |     |
			//									V     | 
			if(sensors.getSonicRangeInches() == 6) //How far away from the drawbridge to stop
			{
				caseSelector = 2;
				//time = System.currentTimeMillis();
			} 
			
			break;
			
		case 2: // Manage arm movement and prepare to cross - steps 3-7 on sheet
				//Lower arm
				//Move backward
				//Lower arm further
				//Lower shooter
				//Raise arm
				//Move forward
				if (sensors.getGyroZAngle() > 5)
				{
					caseSelector = 3;
				}
			
				
			
			break;
			
		case 3: 
			controller.shootswing(-speed3);
			if (sensors.getGyroZAngle() < -5)
			{
				caseSelector = 4;
			}
			break;
		
		case 4:
			controller.forward(speed1);
			if (sensors.getGyroZAngle() == 0)
			{
				caseSelector = 5;
			}
			break;
			
		case 5: // Stop
			
			controller.stop();
			System.out.println("Done!");
			break;
		}
	}
}
