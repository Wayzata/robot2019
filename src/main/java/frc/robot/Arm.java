package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;

///***Shoulder Stuff ***///
//one motor rev = 7 pulses; gearbox is 188:1 ratio; external gearing is 3.75:1
//6.25 pulses per degree; Or maybe 23.25; OOORRRRRRR maybe but probably not 13.71

///***Wrist Stuff ***///
//one motor rev = 7 pulses; gearbox is 188:1 ratio; external gearing is 2.25:1
//X pulses per degree; Or maybe 2.63

//Authors: Cameron, Drake, Dihviam, (Add here)

public class Arm {

    DriveTrain driveT = new DriveTrain();

    static TalonSRX leftShouldMotor;
    static TalonSRX wristMotor;

    static TalonSRX testEncoder;

    private static double currWristPos = 0;
    private static double currShouldPos = 0;
    private static double desiredShouldPos;
    private static double desiredWristPos;
    private static String shoulderDirection = "yeet";
    private static String wristDirection = "yeeted";
    
    double shoulderSpeed = 0.3;
    double wristSpeed = 0.3;
    int currentCount = 0;
    
    public static boolean shoulderMoveFlag = false;
    public static boolean wristMoveFlag = false;

    public Arm(){
        wristMotor = new TalonSRX(Variables.wristMotor);
        leftShouldMotor = new TalonSRX(Variables.leftShouldMotor);
        
        testEncoder = new TalonSRX(5);
    }

    public static void checkShoulder(){

        currShouldPos = leftShouldMotor.getSelectedSensorPosition();

        switch(shoulderDirection) {
            case "up":
                if(currShouldPos >= desiredShouldPos) {
                    leftShouldMotor.set(ControlMode.PercentOutput, 0);
                    shoulderMoveFlag = false;
                    desiredShouldPos = 0;
                    shoulderDirection = "yeet";
                }
                break;
            case "down":
                if(currShouldPos <= desiredShouldPos) {
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

    public static void checkWrist(){

        currShouldPos = wristMotor.getSelectedSensorPosition();

        switch(wristDirection) {
            case "up":
                if(currWristPos >= desiredWristPos) {
                    wristMotor.set(ControlMode.PercentOutput, 0);
                    
                    wristMoveFlag = false;
                    desiredWristPos = 0;
                    wristDirection = "yeeted";
                }
                break;
            case "down":
                if(currShouldPos <= desiredShouldPos) {
                    wristMotor.set(ControlMode.PercentOutput, 0);
                    wristMoveFlag = false;
                    desiredWristPos = 0;
                    wristDirection = "yeeted";
                }
                break;
            case "yeeted":
                System.out.println("This should not be happening, check your switch statement liberal");
                break;
        }


    }

    public static void startShoulder(double pos) {
        desiredShouldPos = shoulderDegreesToTicks(pos);
        shoulderMoveFlag = true;

        if(pos < currShouldPos) {
            shoulderDirection = "down";
            leftShouldMotor.set(ControlMode.PercentOutput, -1 * Variables.shoulderSpeed);
        }
        else if(pos > currShouldPos) {
            shoulderDirection = "up";
            leftShouldMotor.set(ControlMode.PercentOutput, Variables.shoulderSpeed);
        }

        checkShoulder();
    }


    public static void startWrist(double pos) {
        desiredWristPos = wristDegreesToTicks(pos);
        shoulderMoveFlag = true;

        if(pos < currWristPos) {
            wristDirection = "down";
            wristMotor.set(ControlMode.PercentOutput, -1 * Variables.shoulderSpeed);
        }
        else if(pos > currShouldPos) {
            wristDirection = "up";
            wristMotor.set(ControlMode.PercentOutput, Variables.shoulderSpeed);

        }

        checkWrist();
    }

    public static void moveEncoder(double pos) {
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

    public static double shoulderDegreesToTicks(double deg) {
        return deg * 23.25;
    }

    public static double wristDegreesToTicks(double deg) {
        return deg * 2.63;
    }

    public static void startThing() {
        leftShouldMotor.set(ControlMode.PercentOutput, 0.2);
    }
}