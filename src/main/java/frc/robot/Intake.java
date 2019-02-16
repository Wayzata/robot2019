package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

//Authors: Nathanael, Dillan

public class Intake {

    // This solenoid controls the piston used in ejecting the disk
    private Solenoid piston;

    // This controls the motors for the intake motors
    private TalonSRX wheelMotor;

    // This variable stores whether or not the intake piston is extended
    public boolean pistonExtended;

    // This is the switch that monitors if the top flap is up or down
    public DigitalInput limitSwitch;

    public String desiredPos;
    public boolean flapMoveFlag;

    // Initializes motors and talons
    public Intake() {
        piston = new Solenoid(Variables.intakeSolenoid);
        pistonExtended = false;
        flapMoveFlag = false;
        wheelMotor = new TalonSRX(Variables.intakeTalon);
        limitSwitch = new DigitalInput(Variables.intakeLimitSwitchPort);
    }

    // turns on the motors for intake
    public void activateIntakeMotors() {
        wheelMotor.set(ControlMode.PercentOutput, -1 * Variables.intakeMotorSpeed);
    }

    // stops the motors
    public void stopIntakeMotors() {
        wheelMotor.set(ControlMode.PercentOutput, 0);
    }

    // turns on the motors for output
    public void reverseIntakeMotors() {
        wheelMotor.set(ControlMode.PercentOutput, Variables.outputMotorSpeed);
    }

    // extends the ouput piston
    public void extendIntakePiston() {
        Pneumatics.extendSingleSolenoid(piston);
        pistonExtended = true;
    }

    // retracts the intake piston
    public void retractIntakePiston() {
        Pneumatics.retractSingleSolenoid(piston);
        pistonExtended = false;
    }

    public void startFlap(String pos) {
        desiredPos = pos;
        flapMoveFlag = true;

        checkFlap();
    }

    public void checkFlap() {

        if (!flapMoveFlag) {
            return;
        }

        switch (desiredPos) {
        case "up":
            if (limitSwitch.get()) {
                activateIntakeMotors();
            } else {
                stopIntakeMotors();
                flapMoveFlag = false;
            }
            break;
        case "down":
            if (!limitSwitch.get()) {
                activateIntakeMotors();
            } else {
                stopIntakeMotors();
                flapMoveFlag = false;
            }
            break;
        }
    }
}
