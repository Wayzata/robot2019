package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Alignment {

    // These are the constants used as input in the align method
    public static final int RIGHT = 1;
    public static final int LEFT = -1;

    // This is the distance (in pixels) from the center of the screen that the midpoint of the two vision targets must be in order to be "aligned"
    public static final double margin = 20;
 
    // This is the percent output of the motor when the robot is aligning itself
    public static final double motorStrength = .5;

    // This method causes the robot to turn in a given direction until it is aligned
    public void align(int direction, Vision vision, TalonSRX leftMotor, TalonSRX rightMotor){
        // This condition determines whether or not the robot is aligned
        if(Math.abs(vision.getMidpoint().x - (Vision.IMG_WIDTH / 2)) > margin){
            // Turns the robot
            leftMotor.set(ControlMode.PercentOutput, direction * motorStrength);
            rightMotor.set(ControlMode.PercentOutput, -1 * direction * motorStrength);
        }
        else{
            // Stops the robot
            leftMotor.set(ControlMode.PercentOutput, 0);
            rightMotor.set(ControlMode.PercentOutput, 0);
        }
    }


}