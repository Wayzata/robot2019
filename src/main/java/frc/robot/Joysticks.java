package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

//Authors: ... (Add here)

public class Joysticks {

    Joystick leftJoy, rightJoy;

    // Ground cargo pickup
    public static final double cargoFloor = 0;
    // Low hatch player-station/ship/rocket
    public static final double lowHatch = -500;
    // Low cargo rocket
    public static final double lowCargoRocket = -600;
    // middle hatch placement
    public static final double midHatchRocket = -2264;
    // middle cargo placement
    public static final double midCargoRocket = -2500;
    // ship cargo
    public static final double cargoShip = -2500;

    // wrist position for panels
    public static final double hatchWrist = 7;
    // wrist position for cargo
    public static final double cargoWrist = 8;

    public Joysticks() {
        leftJoy = new Joystick(Variables.joyLeftPort);
        rightJoy = new Joystick(Variables.joyRightPort);
    }

    public void checkButtons() {
        if (leftJoy.getRawButton(3)) {
            Arm.setToZero();
        } else if (leftJoy.getRawButton(4)) {
            Arm.setShouldMoveFlag(false);
            // Robot.arm.resetStuff(0);
            Robot.arm.stopShoulder();
        } else if (leftJoy.getRawButton(7)) {
            Arm.startShoulder(cargoFloor);
        } else if (leftJoy.getRawButton(8)) {
            Arm.startShoulder(lowHatch);
        } else if (leftJoy.getRawButton(9)) {
            Arm.startShoulder(lowCargoRocket);
        } else if (leftJoy.getRawButton(10)) {
            Arm.startShoulder(midHatchRocket);
        } else if (leftJoy.getRawButton(11)) {
            Arm.startShoulder(midCargoRocket);
        } else if (leftJoy.getRawButton(12)) {
            Arm.startShoulder(cargoShip);
        }

        if (leftJoy.getRawButton(5)) {
            Robot.intake.activateIntakeMotors();
        } else if (leftJoy.getRawButton(6)) {
            Robot.intake.stopIntakeMotors();
        }

        // Robot.climb.checkClimbButtons();

        /// ***Arm State Checking ***///
        Arm.checkShoulder();

        if (Arm.wristMoveFlag) {
            Arm.checkWrist();
        }

        // Buttons for Arm methods
        // Buttons for Intake methods
        // Buttons for Climbing methods
        // Any other buttons we need

    }
}