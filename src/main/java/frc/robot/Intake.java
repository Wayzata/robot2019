package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

//Authors: Nathanael, Dillan

public class Intake {


    // This solenoid controls the piston used in ejecting the disk
    private Solenoid piston;

    // This controls the motors for the intake motors
    private TalonSRX wheelMotor;

    public boolean pistonExtended;
    // Initializes motors and talons
    public Intake(){
        piston = new Solenoid(Variables.intakeSolenoid);
        pistonExtended = false;
        wheelMotor = new TalonSRX(Variables.intakeTalon);
    }

    // turns on the motors for intake
    public void activateIntakeMotors(){
        wheelMotor.set(ControlMode.PercentOutput, -1 * Variables.intakeMotorSpeed);
    }

    // stops the motors
    public void stopIntakeMotors(){
        wheelMotor.set(ControlMode.PercentOutput, 0);
    }

    // turns on the motors for output
    public void reverseIntakeMotors(){
        wheelMotor.set(ControlMode.PercentOutput, Variables.outputMotorSpeed);
    }

    // extends the ouput piston
    public void extendIntakePiston(){ //Han shot firstx
        Pneumatics.extendSingleSolenoid(piston);
        pistonExtended = true;
    }

    // retracts the intake piston
    public void retractIntakePiston(){
        Pneumatics.extendSingleSolenoid(piston);
        pistonExtended = false;
    }
}
