/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;

//Shoulder Ticks-Per-Degree: 

public class Arm {

    DriveTrain driveT = new DriveTrain();
    

    TalonSRX rightShouldMotor;
    TalonSRX leftShouldMotor;
    TalonSRX wristMotor;

    double currWristPos = 0;
    double currShouldPos = 0;
    
    double shoulderSpeed = 0.3;
    double wristSpeed = 0.3;
    int currentCount = 0;

    //one rev = 7 pulses
    //51.43 degrees to a pulse?

    //Gets position/pulse count

    public Arm(){
        rightShouldMotor = new TalonSRX(Variables.leftShouldMotor);
        leftShouldMotor = new TalonSRX(Variables.rightShouldMotor);
        wristMotor = new TalonSRX(Variables.wristMotor);
    }
    public void getCount(){

        System.out.println(51.43 * currentCount + " Degrees.");

    }


    public void moveShoulder(double newPos){

        while(newPos < currShouldPos){

            rightShouldMotor.set(ControlMode.PercentOutput, shoulderSpeed);
            leftShouldMotor.set(ControlMode.PercentOutput, -1* shoulderSpeed);

        }
        while(newPos > currShouldPos){

            rightShouldMotor.set(ControlMode.PercentOutput, -1* shoulderSpeed);
            leftShouldMotor.set(ControlMode.PercentOutput, shoulderSpeed);

        }


    }

    public void moveWrist(){

    }






}
