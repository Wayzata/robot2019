package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private Vision vision;
  //private DriveTrain driveTrain;

  private Joystick leftJoystick;
  private Joystick rightJoystick;

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    vision = new Vision();
    //driveTrain = new DriveTrain();

    leftJoystick = new Joystick(0);
    rightJoystick = new Joystick(1);

  }

  @Override
  public void robotPeriodic() {
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
    //driveTrain.leftMove(leftJoystick.getY());
    //driveTrain.rightMove(rightJoystick.getY());

    try{
      SmartDashboard.putNumber("Separation Distance", vision.getSeparationDistance());
      SmartDashboard.putNumber("Center X", vision.getMidpoint().x);
      SmartDashboard.putNumber("Center Y", vision.getMidpoint().y);
      SmartDashboard.putBoolean("Is Valid", vision.isValid());
    }catch(Exception e){
      e.printStackTrace();
    }

    System.out.println("Seperation Distance: " + vision.getSeparationDistance());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
