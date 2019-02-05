package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Joysticks {

    public static Joystick leftJoy, rightJoy;

    public Joysticks() {
        leftJoy = new Joystick(Variables.joyLeftPort);
        rightJoy = new Joystick(Variables.joyRightPort);
    }

    public static void checkButtons() {

    }
}