package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

//Authors: Jack, Preeti, Rafael, Divyam, (Add here)

public class Joysticks {

    // These are the two joysticks used for driving and other controls
    Joystick leftJoy, rightJoy;

    public Joysticks() {
        // Initializes the Joysticks
        leftJoy = new Joystick(Variables.joyLeftPort);
        rightJoy = new Joystick(Variables.joyRightPort);
    }

    public void checkButtons() {
        // This if-block moves the arm to the given position.
        if (leftJoy.getRawButton(4)) {
            // Stops the shoulder
            Robot.arm.stopShoulder();
        } else if (leftJoy.getRawButton(7)) {
            Arm.startShoulder(Variables.cargoFloor);
            Arm.startWrist(Variables.hatchWrist);
        } else if (leftJoy.getRawButton(8)) {
            Arm.startShoulder(Variables.hatchLow);
            Arm.startWrist(Variables.hatchWrist);
        } else if (leftJoy.getRawButton(9)) {
            Arm.startShoulder(Variables.cargoLowRocket);
            Arm.startShoulder(Variables.cargoWrist);
        } else if (leftJoy.getRawButton(10)) {
            Arm.startShoulder(Variables.hatchMidRocket);
            Arm.startShoulder(Variables.hatchWrist);
        } else if (leftJoy.getRawButton(11)) {
            Arm.startShoulder(Variables.cargoMidRocket);
            Arm.startShoulder(Variables.cargoWrist);
        } else if (leftJoy.getRawButton(12)) {
            Arm.startShoulder(Variables.cargoShip);
            Arm.startShoulder(Variables.cargoWrist);
        }

        if (rightJoy.getRawButton(7)) {
            Arm.startWrist(Variables.hatchWrist);
        } else if (rightJoy.getRawButton(8)) {
            Arm.startWrist(Variables.cargoWrist);
        }

        if (rightJoy.getRawButton(9)) {
            Robot.intake.startFlap("up");
        } else if (rightJoy.getRawButton(10)) {
            Robot.intake.startFlap("down");
        }

        if (rightJoy.getRawButton(5)) {
            // This moves the wrist back and sets the encoder value to zero
            Arm.startWristReset();
        }

        // This if-block extends and retracts the climbing pistons
        if (leftJoy.getTrigger()) {
            Robot.climb.testFullClimb("UP");
        } else if (rightJoy.getTrigger()) {
            Robot.climb.testFullClimb("DOWN");
        }

        // This if-block extends and retracts the pivot piston
        if (leftJoy.getRawButton(2)) {
            Robot.climb.testPivotPiston("OUT");
        } else if (rightJoy.getRawButton(2)) {
            Robot.climb.testPivotPiston("IN");
        }

        if (leftJoy.getRawButton(3)) {
            Robot.climb.testLongClimb();
        }

        // This if-block controls the intake motors
        // These motors move when the corresponding buttons are held down
        if (leftJoy.getRawButton(5)) {
            Robot.intake.activateIntakeMotors();
        } else if (leftJoy.getRawButton(6)) {
            Robot.intake.reverseIntakeMotors();
        } else {
            Robot.intake.stopIntakeMotors();
        }

        // This if-block extends and retracts the intake piston
        if (rightJoy.getRawButton(3)) {
            Robot.intake.extendIntakePiston();
        } else if (rightJoy.getRawButton(4)) {
            Robot.intake.retractIntakePiston();
        }

    }
}