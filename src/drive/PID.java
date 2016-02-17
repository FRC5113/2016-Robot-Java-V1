package drive;

public class PID
{
	
	double PIDSpeed;
	double PIDTime;
	double PIDError;
    double PIDI;
    double PIDintegral;
    double PIDKi;
    double PIDKd;
    double PIDKp;
    double PIDErrorPrev;
    
    double Scurr;
    double Tcurr;
    double Ecurr;

    
    double dt;
    double output;
    double PIDCurrent;
    double delta;
    double Derivative;
    double SpeedCurrent;

    
    public void init()
    {
		PIDTime = System.currentTimeMillis ()/1000;
		PIDError = 0;
		PIDKi = 1;
		dt = 0;
	    output = 0;
	    PIDCurrent = 0;
	    delta = 0;
	    Derivative = 0;
	    SpeedCurrent = 0;
	    Scurr = 0;
	    Ecurr = 0;
	    Tcurr = 0;
	    PIDKi = 0;
	    PIDKd = 0;
	    PIDKp = 0;
	    PIDErrorPrev = 0;
	    PIDI = 0;
	    PIDintegral = 0;
	    PIDSpeed = 0;
    }
    
	public double UsePID(SensorManager sensors, double desiredSpeed)
	{
		Scurr = sensors.getEncoderRate();
		Tcurr= System.currentTimeMillis();
		Ecurr = desiredSpeed - PIDSpeed;
		dt = Tcurr - PIDTime;
		PIDintegral = PIDintegral + (Ecurr * (dt/1000));
		Derivative = ((Ecurr- PIDError) / dt);
		output = (PIDKp * Ecurr) + (PIDKi * PIDintegral) + (PIDKd * Derivative);
		
		PIDError = Ecurr;
		PIDTime = Tcurr;
		PIDSpeed = Scurr;
		
		delta = (output - PIDSpeed)/output;
		
		if (delta > 0.01)
			delta = 0.01;
		if (delta < -0.01)
			delta = -0.01;
		
		
		PIDI = PIDI * (1 + delta);
		
		if(PIDI > .99)
			PIDI = .99;
		
		return PIDI;
	}
}
