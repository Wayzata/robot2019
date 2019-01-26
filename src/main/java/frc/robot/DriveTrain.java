package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain {
    private TalonSRX frontLeftMotor;
    // private TalonSRX backLeftMotor;
    private TalonSRX frontRightMotor;
    // private TalonSRX backRightMotor;
    private final double speedMultiplier = .5;

    public DriveTrain() {
        frontLeftMotor = new TalonSRX(26);
        // backLeftMotor = new TalonSRX(3);
        frontRightMotor = new TalonSRX(25);
        // backRightMotor = new TalonSRX(4);
    }

    public double calcSpeed(double speed) {
        return speed;
    }

    public void leftMove(double speed) {
        /*
         * if(speed < .05){ speed = 0; }
         * 
         */

        frontLeftMotor.set(ControlMode.PercentOutput, 1 * calcSpeed(speed) * .4);
        // backLeftMotor.set(ControlMode.PercentOutput, 1*calcSpeed(speed));

    }

    public void rightMove(double speed) {
        /*
         * if(speed < .05){ speed = 0; }
         */

        frontRightMotor.set(ControlMode.PercentOutput, -1 * calcSpeed(speed) * .4);

    }

}