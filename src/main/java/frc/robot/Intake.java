package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

//Authors: Nathaniel, Dillan

public class Intake {

    public static final int intakeTalon = 25;
    public static final int intakeSolenoid = 1;
    public static final double intakeMotorSpeed = .5;
    public static final double outputMotorSpeed = .5;

    // This solenoid controls the piston used in ejecting the disk
    private Solenoid piston;

    // This controls the motors for the intake motors
    private TalonSRX wheelMotor;

    // Initializes motors and talons
    public Intake(){
        piston = new Solenoid(1);

        wheelMotor = new TalonSRX(25);
    }

    // turns on the motors for intake
    public void activateIntakeMotors(){
        wheelMotor.set(ControlMode.PercentOutput, intakeMotorSpeed);
    }

    // stops the motors
    public void stopIntakeMotors(){
        wheelMotor.set(ControlMode.PercentOutput, 0);
    }

    // turns on the motors for output
    public void reverseIntakeMotors(){
        wheelMotor.set(ControlMode.PercentOutput, outputMotorSpeed);
    }

    // extends the ouput piston
    public void extendOutputPiston(){ //Han shot first
        piston.set(true);
    }

    // retracts the intake piston
    public void retractIntakePiston(){
        piston.set(false);
    }
}
