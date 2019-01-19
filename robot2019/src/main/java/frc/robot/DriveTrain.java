/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class DriveTrain {

    Variables var = new Variables();

    public void stop() {

        var.leftMotor1 = 0;
        var.leftMotor2 = 0;
        var.rightMotor1 = 0;
        var.rightMotor2 = 0;

    }

    public void drive(double leftSpeed, double rightSpeed){
        var.leftMotor1 = leftSpeed;
        var.leftMotor2 = leftSpeed;
        var.rightMotor1 = rightSpeed;
        var.rightMotor2 = rightSpeed;
    }



}
