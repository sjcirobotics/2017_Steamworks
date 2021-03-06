
package org.usfirst.frc.team5590.robot;

import org.usfirst.frc.team5590.robot.commands.LeftGearAutoGroup;
import org.usfirst.frc.team5590.robot.commands.MidGearAutoGroup;
import org.usfirst.frc.team5590.robot.commands.StraightAutoGroup;
import org.usfirst.frc.team5590.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5590.robot.subsystems.GearHolder;
import org.usfirst.frc.team5590.robot.subsystems.GearPusher;
import org.usfirst.frc.team5590.robot.subsystems.RopeClimber;
import org.usfirst.frc.team5590.robot.subsystems.RopeGrabber;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	// All subsystems needed by the robot
    public static final Drivetrain drivetrain = new Drivetrain();
    public static final GearHolder gearHolder = new GearHolder();
    public static final GearPusher gearPusher = new GearPusher();
    public static final RopeClimber ropeClimber = new RopeClimber();
    public static final RopeGrabber ropeGrabber = new RopeGrabber();
    public static final Compressor compressor = new Compressor();
    public static OI oi;

    // CHANGE ME FOR AUTONOMOUS
    Command autonomousCommand = new StraightAutoGroup();
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		CameraServer.getInstance().startAutomaticCapture();
		CameraServer.getInstance().startAutomaticCapture();
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	Scheduler.getInstance().run();
    	//System.out.println(Robot.drivetrain.gyro.getAngle());
    	
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        drivetrain.stop();
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        drivetrain.joystickSpeed();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
