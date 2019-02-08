package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

//Authors: ... (Add here)

public class Joysticks {

    public static Joystick leftJoy, rightJoy;

    public Joysticks() {
        leftJoy = new Joystick(Variables.joyLeftPort);
        rightJoy = new Joystick(Variables.joyRightPort);
    }
    

    public static void checkButtons() {
        if(leftJoy.getRawButton(1)) {
            Arm.startShoulder(0);
        }
        if(leftJoy.getRawButton(2)) {
            Arm.startShoulder(90);
        }


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