package drive;

public class PID
{
	
	double PIDPosition;
	double PIDCurrentSpeed;
	double PIDCurrentTime;
	double PIDTime;
	double PIDError;
    double PIDErrorPrev;
    double deviate;
    double dt;
    double output;
    double integral;
    double Kp;
    double PIDCurrentError;
    double Ki;
    double Kd;
    double Speed;
    double Current;
    double Tcurr;
    double PIDCurrent;
    double delta;
    double Dirivative;
    double SpeedCurrent;
    double Iprev;
    double IntPrev;
    double Sp;
    double Tp;
    double I;
    
    
	public double UsePID(SensorManager sensors, double desiredSpeed)
	{
		PIDCurrentSpeed = sensors.getEncoderRate();
		PIDCurrentTime = System.currentTimeMillis();
		PIDCurrentError = desiredSpeed - PIDCurrentSpeed;
		integral = IntPrev + (PIDCurrentError * ((PIDCurrentTime - PIDTime) /1000));
		deviate = ((PIDCurrentError - PIDErrorPrev) / dt);
		output = (Kp * PIDCurrentError) + (Ki * integral) + (Kd * Dirivative);
		Sp = Speed - Current;
		Tp = Tcurr;
		PIDErrorPrev = PIDCurrentError;
		delta = output - SpeedCurrent/output;
		
		if (delta > 0.1)
			delta = 0.1;
		if (delta < -0.1)
			delta = -0.1;
		
		I = Iprev * (1 + delta);
		Iprev = I;
		
		return I;
	}
}
