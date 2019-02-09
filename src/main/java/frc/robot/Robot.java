package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Authors: Technical Team

public class Robot extends TimedRobot {
  //comment
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private static Vision vision;
  public static DriveTrain driveTrain;
  public static Pneumatics pneumatics;
  public static Arm arm;
  public static Climbing climb;
  public static Joysticks joysticks;
  public static Intake intake;

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    driveTrain = new DriveTrain();
    intake = new Intake();
    //pneumatics = new Pneumatics();
    arm = new Arm(); 
    //climb = new Climbing();
    joysticks = new Joysticks();
    
    arm.resetStuff(0);

  }

  @Override
  public void robotPeriodic() {
    //arm.printPosition();
    Arm.setCurrPos(Arm.leftShoulderMotor.getSelectedSensorPosition());

  }

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
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
    
   joysticks.checkButtons();
   //Arm.startThing();
   //driveTrain.drive();
   //driveTrain.tankDrive(joysticks.leftJoy, joysticks.rightJoy);
  }
  @Override
  public void testPeriodic() {
  }
}
