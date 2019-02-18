package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
import java.lang.Runtime;
//Authors: Technical Team

/*
To Do:
- Perfect Arms
- Test Wrist
- Test Intake
- Test Climbing
- Vision                                                                                                                                                     :(
- Practice Driving
*/

public class Robot extends TimedRobot {
  // Sets up the SmartDashboard
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  // Objects for the different components of the robot
  private static Vision vision;
  public static DriveTrain driveTrain;
  public static Pneumatics pneumatics;
  public static Arm arm;
  public static Climbing climb;
  public static Joysticks joysticks;
  public static Intake intake;
  public XboxController xbox= new XboxController(2);
  @Override
  public void robotInit() {
    // Sets up the autonomous options on the SmartDashboard
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  
    // Initializes robot components
    driveTrain = new DriveTrain();
    intake = new Intake();
    pneumatics = new Pneumatics();
    arm = new Arm();
    climb = new Climbing();
    joysticks = new Joysticks();
    vision = new Vision();
    
  }

  @Override
  public void robotPeriodic() {
    // Test print-outs
    arm.printShoulderPosition();
    // System.out.println("Limit!: " + Arm.shoulderLimitSwitch.get());
    // climb.frontPistonUltra.ping();
    // climb.backPistonUltra.ping();
    // climb.frontUltrasonic.ping();
    // System.out.println("Back Ultra: " + climb.backPistonUltra.getRangeInches());
    // System.out.println("Mid Ultra: " + climb.frontPistonUltra.getRangeInches());
    // System.out.println("Front Ultra: " + climb.frontUltrasonic.getRangeInches());

  }

  @Override
  public void autonomousInit() {
    // Sets the selected autonomous to what is selected on the SmartDashboard
    m_autoSelected = m_chooser.getSelected();
  }

  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
    case kCustomAuto:
      // Put custom auto code here
      break;
    case kDefaultAuto:
    default:
      // Put default auto code here
      break;
    }
  }

  @Override
  public void teleopPeriodic() {
   // if (xbox.getAButtonPressed()){
      //climb.ClimbingDown();
   // }
    // Sets the current position of the shoulder and wrist to their respective
    // encoder values
    Arm.updateCurrPos();

    // Updates the motor values for the shoulder and wrist
    Arm.checkShoulder();
    Arm.checkWrist();
    intake.checkFlap();
    Arm.checkWristReset();

    // Checks the buttons and performs the appropriate actions
    joysticks.checkButtons();

    // Updates the drivetrain motor values based on where the joysticks are
    driveTrain.tankDrive(joysticks.leftJoy, joysticks.rightJoy);
  }

  @Override
  public void testPeriodic() {
    

  }
}
