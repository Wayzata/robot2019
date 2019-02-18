package frc.robot;

//Authors: Technical Team

public class Variables {

    // Align direction buttons
    public static final int leftButton = 1;
    public static final int rightButton = 2;
    public static final int alignButton = 3;

    // DriveTrain Motors
    public static final double driveLimiter = 0.4;
    public static final double singleMotorAdj1 = 0;
    public static final int fLeftMotor = 1;
    public static final int bLeftMotor = 2;
    public static final int fRightMotor = 3;
    public static final int bRightMotor = 4;

    // Joysticks
    public static final int joyLeftPort = 0;
    public static final int joyRightPort = 1;

    // Shoulder Motors
    public static final int leftShoulderMotor = 5;
    public static final int shoulderLimitSwitchPort = 9;
    // Margin of error for the shoulder
    public static final double shoulderError = 500;

    // -----------------Shoulder Positions-----------------------------------
    // Shoulder position for picking cargo up off of the floor
    public static final double cargoFloor = 10000;
    // Shoulder position for placing hatches on the low goal on CargoShip and
    // RocketShip
    // This position is also used for picking up hatches from PlayerStation
    public static final double hatchLow = -658;
    // Shoulder position for placing cargo on the low goal on CargoShip
    public static final double cargoLowRocket = -1815;
    // Shoulder position for placing hatches on the middle goal on RocketShip
    public static final double hatchMidRocket = -3405;
    // Shoulder position for placing cargo on the middle goal of RocketShip
    public static final double cargoMidRocket = -4460;
    // Shoulder position for placing cargo on CargoShip
    public static final double cargoShip = -2264;

    // Wrist Motors
    public static final int wristMotor = 7;
    // Margin of error on the wrist
    public static final double wristError = 300;

    // -----------------Wrist Positions-----------------------------------
    // Wrist position for picking up hatches from PlayerStation and placing hatches
    // on CargoShip and RocketShip
    public static final double hatchWrist = 500;
    // Wrist position for picking up cargo from PlayerStation and placing cargo on
    // CargoShip and RocketShip
    public static final double cargoWrist = 790;

    // Amount of time that we wrist will move backwards when resetting its position
    // to zero
    public static final long resetTimer = 500;

    // Motor Speeds
    public static final double shoulderSpeed = 0.8;
    public static final double wristSpeed = 0.5;
    public static final double wristResetSpeed = 0.5;

    // Buttons
    public static final int shoulderUp = 0;
    public static final int shoulderDown = 0;
    public static final int wristUp = 0;
    public static final int wristDown = 0;
    public static final int climbUp = 0;
    public static final int climbDown = 0;

    // Intake
    public static final int intakeTalon = 6;
    public static final int intakeSolenoid = 3;
    public static final double intakeMotorSpeed = 1;
    public static final double outputMotorSpeed = 1;
    public static final int intakeLimitSwitchPort = 8;

    // Climbing
    public static final int frontLongPort = 2;
    public static final int frontShortPort = 1;
    public static final int backLongPort = 5;
    public static final int backShortPort = 4;
    public static final int pivotPort = 6;

    // components

    public static final int pcm = 0;
    public static final int pdp = 1;

    // Lights
    // int centerLight = 4;
    // int frontLight = 5;

    // Other crap

}
