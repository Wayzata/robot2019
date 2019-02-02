/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.AnalogInput;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String SecondPlatform = "SECOND";
  private static final String FirstPlatform = "FIRST";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  ///*** VISION STUFF ***///
  private static final int IMG_WIDTH = 400;
	private static final int IMG_HEIGHT = 400;
  UsbCamera frontCamera;
  UsbCamera backCamera;
  

  // ULTRASONIC STUFF //
  Joystick leftJoy, rightJoy;
  public static DriveTrain driveTrain;
  public static Pneumatics pneumatics;
  Climbing climbing;
  DoubleSolenoid armsSolenoid;
  DoubleSolenoid shooterSolenoid;
  AnalogInput frontUltrasonic;
  AnalogInput midUltrasonic;
  AnalogInput backUltrasonic;

  // Compressor
  Compressor compressor;
  //gamepad
  XboxController gamepad;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto - Second Platform", SecondPlatform);
    m_chooser.addOption("Failsafe - First Platform", FirstPlatform);
    SmartDashboard.putData("Auto choices", m_chooser);

    leftJoy = new Joystick(0);
    rightJoy = new Joystick(1);
    driveTrain = new DriveTrain(leftJoy, rightJoy);

    //climbing stuff and pneumatics
    climbing = new Climbing();
    compressor = new Compressor(1);
    pneumatics= new Pneumatics();
    compressor.setClosedLoopControl(true);
    armsSolenoid = new DoubleSolenoid(1, 2, 3);
    shooterSolenoid = new DoubleSolenoid(1, 0, 1);
    frontUltrasonic = new AnalogInput(1);
    

    //vision stuff
    frontCamera = CameraServer.getInstance().startAutomaticCapture();
    frontCamera.setResolution(IMG_WIDTH, IMG_HEIGHT);
    backCamera = CameraServer.getInstance().startAutomaticCapture();
    backCamera.setResolution(IMG_WIDTH, IMG_HEIGHT);
    //buttons
    gamepad= new XboxController(1);
     
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  

  }
  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case FirstPlatform:
          driveTrain.drive();
        break;
      case SecondPlatform:
          if (!climbing.climbingDownCompleted){
            climbing.ClimbingDown();
          }
          
          driveTrain.drive();
        break;
      default:
          driveTrain.drive();
        break;
    }
  }


  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic(){  
    driveTrain.drive();
    if (gamepad.getAButtonPressed() && !climbing.climbingUpSuccess){
      climbing.ClimbingUp();
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
