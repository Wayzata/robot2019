/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID;

public class DriveTrain {
    
    TalonSRX frontLeft = new TalonSRX(Variables.fLeftMotor);
    TalonSRX backLeft = new TalonSRX(Variables.bLeftMotor);

    TalonSRX frontRight = new TalonSRX(Variables.fRightMotor);
    TalonSRX backRight = new TalonSRX(Variables.bRightMotor);

    /* public void stop() {

        frontLeft.set(ControlMode.PercentOutput, 0);
        backLeft.set(ControlMode.PercentOutput, 0);
        frontRight.set(ControlMode.PercentOutput, 0);
        backRight.set(ControlMode.PercentOutput, 0);

    }*/


    public void drive(double leftSpeed, double rightSpeed) {

        frontLeft.set(ControlMode.PercentOutput, leftSpeed);
        backLeft.set(ControlMode.PercentOutput, leftSpeed);
        frontRight.set(ControlMode.PercentOutput, rightSpeed);
        backRight.set(ControlMode.PercentOutput, rightSpeed);
    }

    public void tankDrive(Joystick leftJoystick, Joystick rightJoystick){

        drive((-1 * leftJoystick.getY(GenericHID.Hand.kLeft)*Variables.driveLimiter), (rightJoystick.getY(GenericHID.Hand.kRight)*Variables.driveLimiter));
    }

}