package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;


//Authors: Jack, Preeti, Rafael, Divyam, (Add here)

public class Joysticks {

    // These are the two joysticks used for driving and other controls
    Joystick leftJoy, rightJoy;
    XboxController xbox;

    

    public Joysticks() {
        // Initializes the Joysticks
        leftJoy = new Joystick(Variables.joyLeftPort);
        rightJoy = new Joystick(Variables.joyRightPort);
        xbox= new XboxController(Variables.xboxPort);
    }

    public void checkButtons() {
        // This if-block moves the arm to the given position.
        System.out.println("checkButtons()");
        if (xbox.getBackButtonPressed()) {
            System.out.println("backButton");
            Arm.startShoulder(Variables.cargoFloor);
            Arm.startWrist(Variables.cargoWrist);
        } else if (xbox.getXButtonPressed()) {
            Arm.startShoulder(Variables.hatchLow);
            Arm.startWrist(Variables.hatchWrist);
        } else if (xbox.getAButton()) {
            Arm.startShoulder(Variables.cargoLowRocket);
            Arm.startWrist(Variables.cargoWrist);
        } else if (xbox.getYButton()) {
            Arm.startShoulder(Variables.hatchMidRocket);
            Arm.startWrist(Variables.hatchWrist);
        } else if (xbox.getBButton()) {
            Arm.startShoulder(Variables.cargoMidRocket);
            Arm.startWrist(Variables.cargoWrist);
        } else if (xbox.getStartButton()) {
            Arm.startShoulder(Variables.cargoShip);
            Arm.startWrist(Variables.cargoWrist);
        }

        // if (rightJoy.getRawButton(7)) {
        //     Arm.startWrist(Variables.hatchWrist);
        // } else if (rightJoy.getRawButton(8)) {
        //     Arm.startWrist(Variables.cargoWrist);
        // } else if (rightJoy.getRawButton(12)){
        //     Arm.startWristReset();
        // }

        if (rightJoy.getRawButton(9)) {
            Robot.intake.startFlap("up");
        } else if (rightJoy.getRawButton(10)) {
            Robot.intake.startFlap("down");
        }

        if (rightJoy.getRawButton(5)) {
            // This moves the wrist back and sets the encoder value to zero
            Arm.startWristReset();
            Arm.startShoulder(1000000);
        }

        // This if-block extends and retracts the climbing pistons
        // if (leftJoy.getTrigger()) {
        //     Robot.climb.testFullClimb("UP");
        // } else if (rightJoy.getTrigger()) {
        //     Robot.climb.testFullClimb("DOWN");
        // }

        // This if-block extends and retracts the pivot piston
        // if (leftJoy.getRawButton(2)) {
        //     Robot.climb.testPivotPiston("OUT");
        // } else if (rightJoy.getRawButton(2)) {
        //     Robot.climb.testPivotPiston("IN");
        // }

        // if (leftJoy.getRawButton(3)) {
        //     Robot.climb.testLongClimb();
        // }

        // This if-block controls the intake motors
        // These motors move when the corresponding buttons are held down
        if (leftJoy.getTrigger()) {
            Robot.intake.activateIntakeMotors();
        } else if (rightJoy.getTrigger()) {
            Robot.intake.reverseIntakeMotors();
        } else {
            Robot.intake.stopIntakeMotors();
        }

        // This if-block extends and retracts the intake piston
        if (rightJoy.getRawButton(3)) {
            Robot.intake.extendIntakePiston();
        } else if (rightJoy.getRawButton(2)) {
            Robot.intake.retractIntakePiston();
        }

        //Climbing

        if (xbox.getBumper(Hand.kLeft)){
            Pneumatics.compressor.stop();
            Robot.pneumatics.extendSingleSolenoid(Robot.climb.pivotPiston);
        } else if (xbox.getBumper(Hand.kRight)){
            Pneumatics.compressor.stop();
            Robot.pneumatics.retractSingleSolenoid(Robot.climb.pivotPiston);
        }

        if (leftJoy.getRawButton(7)){
            Pneumatics.compressor.stop();
            Robot.pneumatics.extendSingleSolenoid(Robot.climb.backShort);
        } else if (leftJoy.getRawButton(8)){
            Pneumatics.compressor.stop();
            Robot.pneumatics.extendSingleSolenoid(Robot.climb.frontShort);
        } else if (leftJoy.getRawButton(9)){
            Pneumatics.compressor.stop();
            Robot.pneumatics.extendSingleSolenoid(Robot.climb.backLong);
        } else if (leftJoy.getRawButton(10)){
            Pneumatics.compressor.stop();
            Robot.pneumatics.extendSingleSolenoid(Robot.climb.frontLong);
        }

        if (rightJoy.getRawButton(7)){
            Pneumatics.compressor.start();
            Robot.pneumatics.retractSingleSolenoid(Robot.climb.backShort);
        } else if (rightJoy.getRawButton(8)){
            Pneumatics.compressor.start();
            Robot.pneumatics.retractSingleSolenoid(Robot.climb.frontShort);
        } else if (rightJoy.getRawButton(9)){
            Robot.pneumatics.retractSingleSolenoid(Robot.climb.backLong);
        } else if (rightJoy.getRawButton(10)){
            Robot.pneumatics.retractSingleSolenoid(Robot.climb.frontLong);
        }

    }
}