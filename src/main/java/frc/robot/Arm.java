package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

///***Shoulder Stuff ***///
//one motor rev = 7 pulses; gearbox is 188:1 ratio; external gearing is 3.75:1
//6.25 pulses per degree; Or maybe 23.25; OOORRRRRRR maybe but probably not 13.71

///***Wrist Stuff ***///
//one motor rev = 7 pulses; gearbox is 188:1 ratio; external gearing is 2.25:1
//X pulses per degree; Or maybe 2.63

//Authors: Cameron, Drake, Divyam, (Add here)

public class Arm {

    DriveTrain driveT = new DriveTrain();

    static TalonSRX leftShoulderMotor;
    static TalonSRX wristMotor;

    static TalonSRX testEncoder;

    private static DigitalInput shoulderLimitSwitch;

    private static double currWristPos = 0;
    public static double currShoulderPos = 0;
    private static boolean shoulderMoveFlag = false;
    public static double desiredShoulderPos;
    public static double desiredWristPos;
    private static String shoulderDirection = "yeet";
    private static String wristDirection = "yeeted";

    double shoulderSpeed = 0.3;
    double wristSpeed = 0.3;
    int currentCount = 0;

    public static boolean wristMoveFlag = false;

    public Arm() {
        wristMotor = new TalonSRX(Variables.wristMotor);
        leftShoulderMotor = new TalonSRX(Variables.leftShoulderMotor);

        testEncoder = new TalonSRX(5);

        shoulderLimitSwitch = new DigitalInput(Variables.shoulderLimitSwitchPort);
    }

    /*
     * @brief Return whether the joint should move
     * 
     * @param[in] dir Direction of movement
     * 
     * @param[in] curPos Current position
     * 
     * @param[in] desiredPos Target position
     * 
     * @return True if more movement is needed, false otherwise
     */
    public static boolean shouldMove(String dir, double curPos, double desiredPos) {
        if (dir.equals("up")) {
            return (curPos > desiredPos);
        } else if (dir.equals("down")) {
            return (curPos < desiredPos);
        } else {
            return false;
        }
    }

    // Press button 4, should move up
    // Robot arm moves up
    // keep moving down
    // curr: -5069.0
    // des: -232.5

    public static void checkShoulder() {

        if (!shoulderMoveFlag) {
            return;
        }

        if (!shoulderLimitSwitch.get()) {
            currShoulderPos = 0;
            desiredShoulderPos = currShoulderPos;
        }

        switch (shoulderDirection) {
        case "up":
            if (shouldMove(shoulderDirection, currShoulderPos, desiredShoulderPos)) {
                shoulderMoveFlag = true;
                leftShoulderMotor.set(ControlMode.PercentOutput, -1 * Variables.shoulderSpeed);
                // System.out.println("keep moving up");
            } else {
                leftShoulderMotor.set(ControlMode.PercentOutput, 0);
                shoulderMoveFlag = false;
                // desiredShoulderPos = 0;
                shoulderDirection = "yeet";
                System.out.println("ARRIVED, STOP MOVING UP");
            }

            break;
        case "down":
            if (shouldMove(shoulderDirection, currShoulderPos, desiredShoulderPos)) {
                leftShoulderMotor.set(ControlMode.PercentOutput, Variables.shoulderSpeed);
                shoulderMoveFlag = true;
                // System.out.println("keep moving down");
            } else {
                leftShoulderMotor.set(ControlMode.PercentOutput, 0);
                shoulderMoveFlag = false;
                // desiredShoulderPos = 0;
                shoulderDirection = "yeet";
                System.out.println("ARRIVED, STOP DOWN");
            }
            break;
        default:
            System.out.println("Not Moving; This should not happen");
            break;
        }

    }

    public static void startShoulder(double pos) {
        desiredShoulderPos = pos;
        // desiredShoulderPos = shoulderDegreesToTicks(pos);
        // desiredShoulderPos = pos;
        System.out.println("current pos: " + currShoulderPos);
        System.out.println("SET DES TO: " + desiredShoulderPos);

        if (shouldMove("up", currShoulderPos, desiredShoulderPos)) {
            shoulderDirection = "up";
            System.out.println("MOVE UP");
        } else if (shouldMove("down", currShoulderPos, desiredShoulderPos)) {

            shoulderDirection = "down";
            System.out.println("MOVE DOWN");
        }
        shoulderMoveFlag = true;
        checkShoulder();
    }

    public static void startWrist(double pos) {
        desiredShoulderPos = pos;
        // desiredShoulderPos = shoulderDegreesToTicks(pos);
        // desiredShoulderPos = pos;
        System.out.println("current pos: " + currWristPos);
        System.out.println("SET DES TO: " + desiredWristPos);

        if (shouldMove("up", currWristPos, desiredWristPos)) {
            wristDirection = "up";
            System.out.println("MOVE UP");
        } else if (shouldMove("down", currWristPos, desiredWristPos)) {

            wristDirection = "down";
            System.out.println("MOVE DOWN");
        }
        wristMoveFlag = true;
        checkShoulder();
    }

    public static void checkWrist() {

        if (!wristMoveFlag) {
            return;
        }

        switch (wristDirection) {
        case "up":
            if (shouldMove(wristDirection, currWristPos, desiredWristPos)) {
                wristMoveFlag = true;
                wristMotor.set(ControlMode.PercentOutput, -1 * Variables.wristSpeed);
                // System.out.println("keep moving up");
            } else {
                wristMotor.set(ControlMode.PercentOutput, 0);
                wristMoveFlag = false;
                // desiredShoulderPos = 0;
                wristDirection = "yeeted";
                System.out.println("ARRIVED, STOP MOVING UP");
            }

            break;
        case "down":
            if (shouldMove(wristDirection, currWristPos, desiredWristPos)) {
                wristMotor.set(ControlMode.PercentOutput, Variables.wristSpeed);
                wristMoveFlag = true;
                // System.out.println("keep moving down");
            } else {
                wristMotor.set(ControlMode.PercentOutput, 0);
                wristMoveFlag = false;
                // desiredShoulderPos = 0;
                wristDirection = "yeeted";
                System.out.println("ARRIVED, STOP DOWN");
            }
            break;
        default:
            System.out.println("Not Moving; This should not happen");
            break;
        }
    }

    public static void setToZero() {
        startShoulder(100000);
        /*
         * long time = System.currentTimeMillis(); while(shoulderLimitSwitch.get() &&
         * ((System.currentTimeMillis() - time) < 5000)) {
         * //System.out.println(shoulderLimitSwitch.get());
         * leftShoulderMotor.set(ControlMode.PercentOutput, Variables.shoulderSpeed); }
         */
    }

    public static void moveEncoder(double pos) {
        testEncoder.set(ControlMode.PercentOutput, 0.2);

        while (testEncoder.getSelectedSensorPosition() < pos) {
            System.out.println(testEncoder.getSelectedSensorPosition());
        }

        testEncoder.set(ControlMode.PercentOutput, 0);
    }

    public double getCurrShoulder() {
        return currShoulderPos;
    }

    public double getCurrWrist() {
        return currWristPos;
    }

    public static double shoulderDegreesToTicks(double deg) {
        return deg * 46.5;
    }

    public static double wristDegreesToTicks(double deg) {
        return deg * 2.63;
    }

    /*
     * public static void startThing() {
     * leftShoulderMotor.set(ControlMode.PercentOutput, 0.2); }
     */

    public void resetStuff(int pos) {
        leftShoulderMotor.setSelectedSensorPosition(pos);
    }

    public void stopShoulder() {
        leftShoulderMotor.set(ControlMode.PercentOutput, 0);
        // shoulderMoveFlag = false;
        System.out.println("stopping");
    }

    public void printPosition() {
        System.out.println("Current Position: " + leftShoulderMotor.getSelectedSensorPosition());
    }

    public static void setCurrPos(int i) {
        currShoulderPos = i;
    }

    public static void setShouldMoveFlag(boolean f) {
        shoulderMoveFlag = f;
    }
}