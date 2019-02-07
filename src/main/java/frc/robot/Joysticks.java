package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Joysticks {

    public static Joystick leftJoy, rightJoy;

    public Joysticks() {
        leftJoy = new Joystick(Variables.joyLeftPort);
        rightJoy = new Joystick(Variables.joyRightPort);
    }

    //NEED TO DO
    //BUTTON MAPPING TALK TO DRIIIIVEEEE TEEEAAAAMMMMM ~Drake
    public static void checkButtons() {

        //Buttons for Arm methods
        //Buttons for Intake methods
        //Buttons for Climbing methods
        //Any other buttons we need

    }
}