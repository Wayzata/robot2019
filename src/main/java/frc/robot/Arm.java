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

    TalonSRX testEncoder;

    private static double currWristPos = 0;
    private static double currShouldPos = 0;
    private static double desiredShouldPos;
    private static double desiredWristPos;
    private static String shoulderDirection = "yeet";
    
    double shoulderSpeed = 0.3;
    double wristSpeed = 0.3;
    int currentCount = 0;
    
    public static boolean shoulderMoveFlag = false;

    //one rev = 7 pulses
    //51.43 degrees to a pulse?

    //Gets position/pulse count

    public Arm(){
        rightShouldMotor = new TalonSRX(Variables.leftShouldMotor);
        leftShouldMotor = new TalonSRX(Variables.rightShouldMotor);
        wristMotor = new TalonSRX(Variables.wristMotor);
        
        testEncoder = new TalonSRX(5);
    }

    public void checkShoulder(){

        currShouldPos = leftShouldMotor.getSelectedSensorPosition();

        switch(shoulderDirection) {
            case "up":
                if(currShouldPos >= desiredShouldPos) {
                    rightShouldMotor.set(ControlMode.PercentOutput, 0);
                    leftShouldMotor.set(ControlMode.PercentOutput, 0);
                    shoulderMoveFlag = false;
                    desiredShouldPos = 0;
                    shoulderDirection = "yeet";
                }
                break;
            case "down":
                if(currShouldPos <= desiredShouldPos) {
                    rightShouldMotor.set(ControlMode.PercentOutput, 0);
                    leftShouldMotor.set(ControlMode.PercentOutput, 0);
                    shoulderMoveFlag = false;
                    desiredShouldPos = 0;
                    shoulderDirection = "yeet";
                }
                break;
            case "yeet":
                System.out.println("This should not be happening, check your switch statement liberal");
                break;
        }


    }

    public void checkWrist(){

    }

    public void startShoulder(double pos) {
        desiredShouldPos = pos;
        shoulderMoveFlag = true;

        if(pos < currShouldPos) {
            shoulderDirection = "down";
            leftShouldMotor.set(ControlMode.PercentOutput, -1 * Variables.shoulderSpeed);
            rightShouldMotor.set(ControlMode.PercentOutput, Variables.shoulderSpeed);
        }
        else if(pos > currShouldPos) {
            shoulderDirection = "up";
            leftShouldMotor.set(ControlMode.PercentOutput, Variables.shoulderSpeed);
            rightShouldMotor.set(ControlMode.PercentOutput, -1 * Variables.shoulderSpeed);
        }

        checkShoulder();
    }

    public void moveEncoder(double pos) {
        testEncoder.set(ControlMode.PercentOutput, 0.2);

        while(testEncoder.getSelectedSensorPosition() < pos) {
            System.out.println(testEncoder.getSelectedSensorPosition());
        }

        testEncoder.set(ControlMode.PercentOutput, 0);
    }

    public double getCurrShoulder() {
        return currShouldPos;
    }

    public double getCurrWrist() {
        return currWristPos;
    }
}