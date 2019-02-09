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
        if(leftJoy.getRawButton(4)) {
            Arm.startShoulder(-20);
        }
        if(leftJoy.getRawButton(3)) {
            Arm.startShoulder(20);
        }

        //Robot.climb.checkClimbButtons();

        ///***Arm State Checking ***///
        if(Arm.shoulderMoveFlag) {
            Arm.checkShoulder();
        }
        if(Arm.wristMoveFlag) {
            Arm.checkWrist();
        }

        //Buttons for Arm methods
        //Buttons for Intake methods
        //Buttons for Climbing methods
        //Any other buttons we need

    }
}