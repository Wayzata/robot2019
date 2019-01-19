package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain {
    private TalonSRX frontLeftMotor;
    private TalonSRX backLeftMotor;
    private TalonSRX frontRightMotor;
    private TalonSRX backRightMotor;
    private final double speedMultiplier = .5;

    public DriveTrain() {
        frontLeftMotor = new TalonSRX(6);
        backLeftMotor = new TalonSRX(3);
        frontRightMotor = new TalonSRX(5);
        backRightMotor = new TalonSRX(4);
    }

    public double calcSpeed(double speed){
        return (Math.pow(speed, 2) * speedMultiplier);
    }

    public void leftMove(double speed) {
        /*
         * if(speed < .05){ speed = 0; }
         * 
         */
        if (speed < 0) {
            frontLeftMotor.set(ControlMode.PercentOutput, 1*calcSpeed(speed));
            backLeftMotor.set(ControlMode.PercentOutput, 1*calcSpeed(speed));
        } else {
            frontLeftMotor.set(ControlMode.PercentOutput, -1*calcSpeed(speed));
            backLeftMotor.set(ControlMode.PercentOutput, -1*calcSpeed(speed));
        }
    }

    public void rightMove(double speed) {
        /*
         * if(speed < .05){ speed = 0; }
         */
        if (speed < 0) {
            frontRightMotor.set(ControlMode.PercentOutput, -1*calcSpeed(speed));
            backRightMotor.set(ControlMode.PercentOutput, -1*calcSpeed(speed));
        } else {
            frontRightMotor.set(ControlMode.PercentOutput, 1*calcSpeed(speed));
            backRightMotor.set(ControlMode.PercentOutput, 1*calcSpeed(speed));
        }
    }

}