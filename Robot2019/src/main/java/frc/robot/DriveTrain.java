package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

public class DriveTrain {

    TalonSRX leftMotor, rightMotor;
    Joystick leftJoy, rightJoy;

    public DriveTrain(Joystick l, Joystick r) {
        leftJoy = l;
        rightJoy = r;
        leftMotor = new TalonSRX(5);
        rightMotor = new TalonSRX(6);
    }

    public void drive() {
        leftMotor.set(ControlMode.PercentOutput, -1 * leftJoy.getY());
        rightMotor.set(ControlMode.PercentOutput, rightJoy.getY());
    }
}