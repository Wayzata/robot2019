package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXPIDSetConfigUtil;
import edu.wpi.first.wpilibj.DigitalInput;

///***Shoulder Stuff ***///
//one motor rev = 7 pulses; gearbox is 188:1 ratio; external gearing is 3.75:1
//6.25 pulses per degree; Or maybe 23.25; OOORRRRRRR maybe but probably not 13.71

///***Wrist Stuff ***///
//one motor rev = 7 pulses; gearbox is 188:1 ratio; external gearing is 2.25:1
//X pulses per degree; Or maybe 2.63

//Authors: Cameron, Drake, Divyam, Rafael, (Add here)

public class Arm {

    // These talons control the rotation of the shoulder and the wrist respectively
    static TalonSRX shoulderMotor;
    static TalonSRX wristMotor;

    // This is the limit switch used to reset the shoulder encoder
    public static DigitalInput shoulderLimitSwitch;

    // These store the current position (in ticks) relative to a starting position
    // for the shoulder and wrist
    public static double currShoulderPos = 0;
    public static double currWristPos = 0;

    // These determine whether or not the shoulder or wrist are currently attempting
    // to move
    public static boolean shoulderMoveFlag = false;
    public static boolean wristMoveFlag = false;
    public static boolean wristResetFlag = false;

    // These variables store the target position (in ticks) of the shoulder and
    // wrist
    public static double desiredShoulderPos;
    public static double desiredWristPos;

    // These variables indicate the current direction of the shoulder and wrist
    public static String shoulderDirection = "yeet";
    public static String wristDirection = "yeeted";

    public static long resetTime;

    public Arm() {
        // Initializes variables
        wristMotor = new TalonSRX(Variables.wristMotor);
        shoulderMotor = new TalonSRX(Variables.leftShoulderMotor);

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

    // Sets the encoder value and the current position of the shoulder motor to zero
    public static void setShoulderToZero() {
        shoulderMotor.setSelectedSensorPosition(0);
        currShoulderPos = 0;
    }

    // Moves the wrist backwards and then sets the encoder value and current
    // position of the wrist motor to zero
    public static void moveWristToZero() {
        // Moves the wrist backwards
        wristMotor.set(ControlMode.PercentOutput, -1 * Variables.wristResetSpeed);

        try {
            // Waits as the wrist moves backwards
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stops the wrist
        wristMotor.set(ControlMode.PercentOutput, 0);

        // Sets the encoder value and current position of the wrist to zero
        wristMotor.setSelectedSensorPosition(0);
        currWristPos = 0;
    }

    // Starts moving the wrist backwards
    public static void startWristReset() {
        wristResetFlag = true;
        wristMotor.set(ControlMode.PercentOutput, -1 * Variables.wristResetSpeed);
        resetTime = System.currentTimeMillis();

        checkWristReset();
    }

    // Checks how long the wrist has been resetting and stops it after a certain
    // amount of time
    public static void checkWristReset() {

        if (!wristResetFlag) {
            return;
        }

        if (System.currentTimeMillis() - resetTime >= Variables.resetTimer) {
            wristMotor.set(ControlMode.PercentOutput, 0);
            currWristPos = 0;
            wristMotor.setSelectedSensorPosition(0);
            wristResetFlag = false;
        }
    }

    // Sets the current position of the shoulder and wrist to their respective
    // encoder value
    public static void updateCurrPos() {
        currShoulderPos = shoulderMotor.getSelectedSensorPosition();
        currWristPos = wristMotor.getSelectedSensorPosition();
    }

    // Checks and stops the shoulder motor if the shoulder has reached its desired
    // position
    public static void checkShoulder() {

        // Ends the method if the flag is not active
        if (!shoulderMoveFlag) {
            return;
        }

        System.out.println("MOVING THE ARM");

        // If the arm is moving down and hits the switch, then it stops the arm and sets
        // it to zero
        if (!shoulderLimitSwitch.get() && shoulderDirection.equalsIgnoreCase("down")) {
            currShoulderPos = 0;
            desiredShoulderPos = 0;
            shoulderMoveFlag = false;
            System.out.println("LIMIT SWITCH IS DOWN");
        }

        // Checks the current position of the shoulder and stops it if it has reached
        // its desired position
        switch (shoulderDirection) {
        case "up":
            if (shouldMove(shoulderDirection, currShoulderPos, desiredShoulderPos)) {
                shoulderMoveFlag = true;
                shoulderMotor.set(ControlMode.PercentOutput, -1 * Variables.shoulderSpeed);
            } else {
                shoulderMotor.set(ControlMode.PercentOutput, 0);
                shoulderMoveFlag = false;
                shoulderDirection = "yeet";
            }

            break;
        case "down":
            if (shouldMove(shoulderDirection, currShoulderPos, desiredShoulderPos)) {
                shoulderMotor.set(ControlMode.PercentOutput, Variables.shoulderSpeed);
                shoulderMoveFlag = true;
            } else {
                shoulderMotor.set(ControlMode.PercentOutput, 0);
                shoulderMoveFlag = false;
                shoulderDirection = "yeet";
            }
            break;
        }

    }

    // Sets a desired position for the shoulder and starts the shoulder motor
    public static void startShoulder(double pos) {
        // Sets the desired position to a given position
        desiredShoulderPos = pos;

        // Determines if the shoulder should move up or down
        if (shouldMove("up", currShoulderPos, desiredShoulderPos)) {
            shoulderDirection = "up";
        } else if (shouldMove("down", currShoulderPos, desiredShoulderPos)) {
            shoulderDirection = "down";
        }

        // Starts the shoulder motor
        shoulderMoveFlag = true;
        checkShoulder();
    }

    // Sets a desired position for the wrist and starts the wrist motor
    public static void startWrist(double pos) {
        // Sets the desired wrist position to the given position
        desiredWristPos = pos;

        // Determines whether or not the wrist should move up or down
        if (shouldMove("up", currWristPos, desiredWristPos)) {
            wristDirection = "up";
        } else if (shouldMove("down", currWristPos, desiredWristPos)) {
            wristDirection = "down";
        }

        // Starts the wrist motor
        wristMoveFlag = true;
        checkShoulder();
    }

    // Checks the current position of the wrist and stops it if it has reached its
    // desired position
    public static void checkWrist() {
        // Ends the method if the flag is not active
        if (!wristMoveFlag) {
            return;
        }

        // Checks the current position of the wrist and stops it if it has reached its
        // desired position
        switch (wristDirection) {
        case "up":
            if (shouldMove(wristDirection, currWristPos, desiredWristPos)) {
                wristMoveFlag = true;
                wristMotor.set(ControlMode.PercentOutput, -1 * Variables.wristSpeed);
            } else {
                wristMotor.set(ControlMode.PercentOutput, 0);
                wristMoveFlag = false;
                wristDirection = "yeeted";
            }

            break;
        case "down":
            if (shouldMove(wristDirection, currWristPos, desiredWristPos)) {
                wristMotor.set(ControlMode.PercentOutput, Variables.wristSpeed);
                wristMoveFlag = true;
            } else {
                wristMotor.set(ControlMode.PercentOutput, 0);
                wristMoveFlag = false;
                wristDirection = "yeeted";
            }
            break;
        }
    }

    public static void checkNewWrist(){

        if(!wristMoveFlag){
            return;
        }

        if(currWristPos - desiredWristPos < -1 * Variables.wristError){
            wristMotor.set(ControlMode.PercentOutput, Variables.wristSpeed);
        } else if (currWristPos - desiredWristPos > Variables.wristError){
            wristMotor.set(ControlMode.PercentOutput, -1 * Variables.wristSpeed);
        } else{
            wristMotor.set(ControlMode.PercentOutput, 0);
        }
    }

    public static void checkNewShoulder(){
        
        if(!shoulderMoveFlag){
            return;
        }

        if(currShoulderPos - desiredShoulderPos < -1 * Variables.shoulderError){
            shoulderMotor.set(ControlMode.PercentOutput, Variables.shoulderSpeed);
        } else if (currShoulderPos - desiredShoulderPos > Variables.shoulderError){
            shoulderMotor.set(ControlMode.PercentOutput, Variables.shoulderSpeed);
        } else {
            shoulderMotor.set(ControlMode.PercentOutput, 0);
        }
    }

    // Returns the current position (in ticks) of the shoulder
    public double getCurrShoulder() {
        return currShoulderPos;
    }

    // Returns the current position (in ticks) of the wrist
    public double getCurrWrist() {
        return currWristPos;
    }

    // Deprecated
    public static double shoulderDegreesToTicks(double deg) {
        return deg * 46.5;
    }

    // Deprecated
    public static double wristDegreesToTicks(double deg) {
        return deg * 2.63;
    }

    // Sets the value of the shoulder encoder to a given value
    public void setShoulderEncoder(int pos) {
        shoulderMotor.setSelectedSensorPosition(pos);
    }

    // Stops the shoulder motor and sets its flag to false
    public void stopShoulder() {
        shoulderMotor.set(ControlMode.PercentOutput, 0);
        shoulderMoveFlag = false;
    }

    // Prints the value of the shoulder encoder
    public void printShoulderPosition() {
        System.out.println("Current Position: " + shoulderMotor.getSelectedSensorPosition());
    }

    // Prints the value of the wrist encoder
    public void printWristPosition() {
        System.out.println("Current Position: " + wristMotor.getSelectedSensorPosition());
    }
}