package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

//Authors: ... (Add here)

public class Joysticks {

    Joystick leftJoy, rightJoy;

    public Joysticks() {
        leftJoy = new Joystick(Variables.joyLeftPort);
        rightJoy = new Joystick(Variables.joyRightPort);
    }

    public void checkButtons() {
        if (leftJoy.getRawButton(4)) {
            System.out.println("Button 4 - Move up");
            Arm.startShoulder(-45);
        } else if (leftJoy.getRawButton(3)) {
            System.out.println("Button 3 - Move down");
            Arm.startShoulder(45);
        } else if (leftJoy.getRawButton(5)) {
            Arm.startShoulder(0);
        } else if (leftJoy.getRawButton(11)) {
            Arm.setShouldMoveFlag(false);
            //Robot.arm.resetStuff(0);
            Robot.arm.stopShoulder();

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