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

public class Arm {

    DriveTrain driveT = new DriveTrain();
    

    TalonSRX rightShouldMotor;
    TalonSRX leftShouldMotor;
    TalonSRX wristMotor;

    //Encoders
    Encoder Encoder1 = new Encoder(0, 1, true);

    double currentPos = 0;

    double wristPos = 0;
    double shouldPos = 0;
    
    double shouldSpeed = 10;
    int currentCount = 0;

    //one rev = 7 pulses
    //51.43 degrees to a pulse?

    //Gets position/pulse count

    public Arm(){
        rightShouldMotor = new TalonSRX(Variables.leftShouldMotor);
        leftShouldMotor = new TalonSRX(Variables.rightShouldMotor);
        wristMotor = new TalonSRX(Variables.wristMotor);
        currentPos = 0;
    }
    public void getCount(){

        
        currentPos = Encoder1.getDistance();
        currentCount = Encoder1.get();

        System.out.println(currentCount);
        System.out.println(currentPos);
        System.out.println(51.43 * currentCount + " Degrees.");

    }


    public void shoulder(double newPos){

        while(newPos < shouldPos){

            rightShouldMotor.set(ControlMode.PercentOutput, shouldSpeed);
            leftShouldMotor.set(ControlMode.PercentOutput, -1* shouldSpeed);

        }
        while(newPos > shouldPos){

            rightShouldMotor.set(ControlMode.PercentOutput, -1* shouldSpeed);
            leftShouldMotor.set(ControlMode.PercentOutput, shouldSpeed);

        }


    }

    public void wrist(){

    }






}
