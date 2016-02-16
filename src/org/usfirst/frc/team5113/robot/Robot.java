
package org.usfirst.frc.team5113.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import controllers.Arm;
import controllers.JoystickController;
import controllers.Shooter;
import drive.MotorManager;
import drive.SensorManager;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
//Auto-added, not sure if we actually need them... but whatever
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

//This is the main part of the code. It is where the robot looks first
public class Robot extends IterativeRobot 
{
	//This was auto-generated. Not sure if we need it
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    
    Image frame;
    int session;
    
	private MotorManager motorManagers;// this gives us access to the Drive class
	private SensorManager sensors;
	private JoystickController controller;
	private Shooter shoot;
	private Arm arm;
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() 
    {       
        controller = new JoystickController();
        controller.init();
        motorManagers = new MotorManager();
        motorManagers.init();//idk where i should put this but here: TEHURN.COM
        shoot = new Shooter();
        shoot.init();
        arm = new Arm();
        arm.init();
        sensors = new SensorManager();
        sensors.init();
        
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session); //I totally wrote this super EZM8 line of code by myself.
        
        
        //CameraServer server = CameraServer.getInstance();
        //server.setQuality(50);
        //server.startAutomaticCapture("cam0");
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() 
    {
    	sensors.resetEncoder();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {
    	
    }

    public void teleopInit()
    {
    	
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
    	//The order of the update methods is important. Besides making a nice slope of periods, the motors and sensors have to update first
        controller.update(motorManagers);
        sensors.update();
        shoot.update(motorManagers, controller, sensors);
        arm.update(motorManagers, controller);
        
		System.out.println("Encoder Raw: " + sensors.getEncoderValues());
		System.out.println("Encoder Count: " + sensors.getEncoderCount());
		System.out.println("Encoder Rate of Rotation: " + sensors.getEncoderRate());
		System.out.println("Encoder Distance: " + sensors.getEncoderDistance());
		System.out.println("Degrees per Second: " + sensors.getEncoderAngularSpeed());
		
		//System.out.println("StringPot: " + sensors.getStringPot());
		System.out.println("Ultrasonic Range Finder (Inches): " + sensors.getSonicRangeInches());
		//System.out.println("Servo: " + shoot.pusher.getAngle());
		
		//System.out.println("Gyro XY: " + sensors.getGyroXYAngle());
		//System.out.println("Gyro Z: " + sensors.getGyroZAngle());
		
		NIVision.IMAQdxStartAcquisition(session);
		NIVision.Rect rect = new NIVision.Rect(10,10,100,100);
		
		NIVision.IMAQdxGrab(session, frame, 1);
		NIVision.imaqDrawShapeOnImage(frame, frame, rect, DrawMode.DRAW_VALUE,  ShapeMode.SHAPE_OVAL, 0.0f);
		
		CameraServer.getInstance().setImage(frame);
		
		Timer.delay(0.005);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() 
    {
    
    }
    
}
