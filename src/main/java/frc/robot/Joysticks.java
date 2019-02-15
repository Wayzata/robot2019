package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DriverStation;

//Authors: ... (Add here)

public class Joysticks {

    Joystick leftJoy, rightJoy;

    // Ground cargo pickup
    public static final double cargoFloor = 10000;
    // Low hatch player-station/ship/rocket
    public static final double lowHatch = -500;
    // Low cargo rocket
    public static final double lowCargoRocket = -600;
    // middle hatch placement
    public static final double midHatchRocket = -2264;
    // middle cargo placement
    public static final double midCargoRocket = -3750;
    // ship cargo
    public static final double cargoShip = -2500;

    // wrist hatch
    public static final double wristHatch = 500;
    // wrist cargo
    public static final double wristCargo = 1000;

    // wrist position for panels
    public static final double hatchWrist = 7;
    // wrist position for cargo
    public static final double cargoWrist = 8;

    public Joysticks() {
        leftJoy = new Joystick(Variables.joyLeftPort);
        rightJoy = new Joystick(Variables.joyRightPort);
    }

    public void checkButtons() {
        if (leftJoy.getRawButton(3)) {
           // Arm.setToZero();
        } else if (leftJoy.getRawButton(4)) {
            Arm.setShouldMoveFlag(false);
            // Robot.arm.resetStuff(0);
            Robot.arm.stopShoulder();
        } else if (leftJoy.getRawButton(7)) {
            Arm.startShoulder(cargoFloor);
            Arm.startWrist(wristHatch);
        } else if (leftJoy.getRawButton(8)) {
            Arm.startShoulder(lowHatch);
            Arm.startWrist(wristHatch);
        } else if (leftJoy.getRawButton(9)) {
            Arm.startShoulder(lowCargoRocket);
            Arm.startShoulder(wristCargo);
        } else if (leftJoy.getRawButton(10)) {
            Arm.startShoulder(midHatchRocket);
            Arm.startShoulder(wristHatch);
        } else if (leftJoy.getRawButton(11)) {
            Arm.startShoulder(midCargoRocket);
            Arm.startShoulder(wristCargo);
        } else if (leftJoy.getRawButton(12)) {
            Arm.startShoulder(cargoShip);
            Arm.startShoulder(wristCargo);
        } else if (rightJoy.getRawButton(5)){
            Arm.moveWristToZero();
        }

        if(leftJoy.getTrigger()){
            Robot.climb.testFullClimb("UP");
        }else if(rightJoy.getTrigger()){
            Robot.climb.testFullClimb("DOWN");
        }

        if (leftJoy.getRawButton(2)){
            Robot.climb.testPivotPiston("OUT");
        } else if (rightJoy.getRawButton(2)){
            Robot.climb.testPivotPiston("IN");
        }

        if (leftJoy.getRawButton(3)){
            Robot.climb.testLongClimb();
        }

        if (leftJoy.getRawButton(5)) {
            Robot.intake.activateIntakeMotors();
        } else if (leftJoy.getRawButton(6)) {
            Robot.intake.reverseIntakeMotors();
        } else{
            Robot.intake.stopIntakeMotors();
        }

        if (rightJoy.getRawButton(3)){
            Robot.intake.extendIntakePiston();
        } else if (rightJoy.getRawButton(4)){
            Robot.intake.retractIntakePiston();
        }

        //if((DriverStation.getInstance().getBatteryVoltage()) < 11.5){
        //    System.out.println("My battery is low and it is getting dark");
        //}



        // Robot.climb.checkClimbButtons();

        /// ***Arm State Checking ***///
        Arm.checkShoulder();

        if (Arm.wristMoveFlag) {
            Arm.checkWrist();
        }

        // Buttons for Arm methods
        // Buttons for Intake methods
        // Buttons for Climbing methods
        // Any other buttons we need

    }
}