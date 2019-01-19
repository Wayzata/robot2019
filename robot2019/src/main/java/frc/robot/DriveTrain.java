package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

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

    public void leftMove(double speed) {
        /*
         * if(speed < .05){ speed = 0; }
         * 
         */
        if (speed < 0) {
            frontLeftMotor.set(ControlMode.PercentOutput, -1*(Math.pow(speed, 2) * speedMultiplier));
            backLeftMotor.set(ControlMode.PercentOutput, -1*(Math.pow(speed, 2) * speedMultiplier));
        } else {
            frontLeftMotor.set(ControlMode.PercentOutput, 1*(Math.pow(speed, 2) * speedMultiplier));
            backLeftMotor.set(ControlMode.PercentOutput, 1*(Math.pow(speed, 2) * speedMultiplier));
        }
    }

    public void rightMove(double speed) {
        /*
         * if(speed < .05){ speed = 0; }
         */
        if (speed < 0) {
            frontRightMotor.set(ControlMode.PercentOutput, -1*(Math.pow(speed, 2) * speedMultiplier));
            backRightMotor.set(ControlMode.PercentOutput, -1*(Math.pow(speed, 2) * speedMultiplier));
        } else {
            frontRightMotor.set(ControlMode.PercentOutput, 1*(Math.pow(speed, 2) * speedMultiplier));
            backRightMotor.set(ControlMode.PercentOutput, 1*(Math.pow(speed, 2) * speedMultiplier));
        }
    }

}