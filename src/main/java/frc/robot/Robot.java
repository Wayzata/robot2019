package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
  public boolean zeroFlag = true;

  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    driveTrain = new DriveTrain();
    intake = new Intake();
    pneumatics = new Pneumatics();
    arm = new Arm();
    climb = new Climbing();
    joysticks = new Joysticks();
    
    arm.resetStuff(0);
<<<<<<< HEAD

    //Arm.setToZero(); 
=======
>>>>>>> 5efb8d56f0f913b27733c999f4c7c32b8c7a753c
  }

  @Override
  public void robotPeriodic() {
    //arm.printPosition();
<<<<<<< HEAD
    //Arm.setCurrPos(Arm.leftShoulderMotor.getSelectedSensorPosition());
=======
    Arm.setCurrPos(Arm.leftShoulderMotor.getSelectedSensorPosition());
>>>>>>> 5efb8d56f0f913b27733c999f4c7c32b8c7a753c

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
    if(zeroFlag) {
      Arm.setToZero();
      zeroFlag = false;
    }
    
    joysticks.checkButtons();
    //Arm.startThing();
    //driveTrain.drive();
    //driveTrain.tankDrive(joysticks.leftJoy, joysticks.rightJoy);
  }
  @Override
  public void testPeriodic() {
  }
}
