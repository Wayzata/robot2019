package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Solenoid;

import java.lang.Thread;

//Authors: Preeti, Jack, ... (Add here)

public class Climbing {

    // Sensor
    // private AnalogInput ultraSonic;

    // Testing Pistons

    // UltraSonic Range Calculation Values
    final double slope = .0090645714;
    final double yint = .0062214286;
    final double ultraMin = 8.0, ultraMax = 9.0;

    // Ultrasonic Automation Ranges - Different because possible off balance coming
    // off
    final double frontDeployDistance = 4.0, backDeployDistance = 4.0;
    final double climbingUpRetractDistance = 2.0;

    // Ultrasonic Sensors
    Ultrasonic frontUltrasonic;
    Ultrasonic frontPistonUltra;
    Ultrasonic backPistonUltra;

    // Climbing pistons
    Solenoid frontLong, frontShort, backLong, backShort, pivotPiston;

    // Booleans
    boolean frontExtended = false;
    boolean backExtended = false;
    boolean stageOneCompleted = false;
    boolean climbingDownCompleted = false;
    boolean climbingUpSuccess = false;
    boolean initialPistonDeploy = false;
    boolean pivoted = false;
    boolean frontRetracted = false;
    // time based end climb
    long autoStartTime;
    long timeInAuto;

    public Climbing() {
        frontUltrasonic = new Ultrasonic(0, 1);
        frontPistonUltra = new Ultrasonic(2, 3);
        backPistonUltra = new Ultrasonic(4, 5);
        frontLong = new Solenoid(Variables.pcm, Variables.frontLongPort);
        frontShort = new Solenoid(Variables.pcm, Variables.frontShortPort);
        backLong = new Solenoid(Variables.pcm, Variables.backLongPort);
        backShort = new Solenoid(Variables.pcm, Variables.backShortPort);
        pivotPiston = new Solenoid(Variables.pcm, Variables.pivotPort);
    }

    // Actual code that controls pistons - Can change
    public boolean CheckUltraRange(AnalogInput ultrasonic) {
        double range = (double) Math.round(((ultrasonic.getAverageVoltage() - yint) / slope) * 100) / 100;
        System.out.println("range: " + range);
        if (range >= ultraMin && range <= ultraMax) {
            System.out.println("extended");
            return true;
        } else {
            return false;
        }
    }

    // Climbing Down automation
    public void ClimbingDown() {
        // Start driving forward 0.5 speed
        Robot.driveTrain.driveForward(0.25);
        // Get ultrarange once front piston is off
        if (frontPistonUltra.getRangeInches() >= frontDeployDistance && !stageOneCompleted) {
            // Extending front pistons once
            if (!frontExtended) {
                Pneumatics.extendSingleSolenoid(frontShort);
                // Robot.pneumatics.extendSingleSolenoid(pivotPiston);
                frontExtended = true;
            }
            // Making sure robot continues to drive forward
            while (backPistonUltra.getRangeInches() < backDeployDistance) {
                Robot.driveTrain.driveForward(0.25);
            }
            // Extending back once above loop is no longer true
            if (!backExtended) {
                Pneumatics.extendSingleSolenoid(backShort);
                stageOneCompleted = true;
                backExtended = true;
            }
        }
        // Bringing robot down and ending auto period
        if (stageOneCompleted) {
            Robot.driveTrain.driveForward(0.25);
            if (frontExtended) {
                Pneumatics.retractSingleSolenoid(frontShort);
                frontExtended = false;
            }
            if (backExtended) {
                Pneumatics.retractSingleSolenoid(backShort);
                backExtended = false;
            }
            climbingDownCompleted = true;
        }
    }

    // Climbing Up automation
    public void ClimbingUp() {

        // Deploy all pistons
        if (!initialPistonDeploy) {
            Pneumatics.extendSingleSolenoid(backShort);
            Pneumatics.extendSingleSolenoid(frontShort);
            Pneumatics.extendSingleSolenoid(backLong);
            Pneumatics.extendSingleSolenoid(frontLong);
            initialPistonDeploy = true;
        }

        // Pausing before tipping forward
        try {
            Thread.sleep(1000);
        } catch (InterruptedException i) {
            i.printStackTrace();
            System.out.println(i);
            Thread.currentThread().interrupt();
        }

        // Tipping forward
        if (!pivoted) {
            Pneumatics.extendSingleSolenoid(pivotPiston);
            pivoted = true;
        }

        // Driving forward because front wheels should be on
        Robot.driveTrain.driveForward(0.75);

        if (frontUltrasonic.getRangeInches() <= climbingUpRetractDistance && !frontRetracted) {
            Pneumatics.retractSingleSolenoid(frontLong);
            Pneumatics.retractSingleSolenoid(frontShort);
            frontRetracted = true;
        }

        // Move forward, reach the distance for back retraction
        while (backPistonUltra.getRangeInches() > climbingUpRetractDistance) {
            Robot.driveTrain.driveForward(0.25);
        }
        Pneumatics.retractSingleSolenoid(backLong);
        Pneumatics.retractSingleSolenoid(backShort);

        climbingUpSuccess = true; // Use this Bool to give controls back to driver for Parking

    }

    // This will check for a climbing button being pressed and the procedure not
    // completed yet, then calling climbing methods
    public void checkClimbButtons() {

        if (Robot.joysticks.leftJoy.getRawButtonPressed(Variables.climbUp) && climbingUpSuccess == false) {

            ClimbingUp();

        } else if (Robot.joysticks.rightJoy.getRawButtonPressed(Variables.climbDown)
                && climbingDownCompleted == false) {

            ClimbingDown();

        } else {
        }

    }
    public void testLongClimb(){
        Pneumatics.extendSingleSolenoid(frontLong);
        try {
            Thread.sleep(0);
            System.out.println("SLEEPT");
        } catch (InterruptedException i) {
            i.printStackTrace();
            System.out.println(i);
            Thread.currentThread().interrupt();
        }
        
        Pneumatics.extendSingleSolenoid(backLong);
    }

    public void testFullClimb(String swi) {
        if (swi == "UP") {
            Pneumatics.extendSingleSolenoid(backShort);
           // Robot.pneumatics.retractSingleSolenoid(backLong);
            try {
                Thread.sleep(0);
                System.out.println("SLEEPT");
            } catch (InterruptedException i) {
                i.printStackTrace();
                System.out.println(i);
                Thread.currentThread().interrupt();
            }
            System.out.println("out of sleep");

            Pneumatics.extendSingleSolenoid(frontShort);
            try {
                Thread.sleep(1000);
                System.out.println("SLEEPT");
            } catch (InterruptedException i) {
                i.printStackTrace();
                System.out.println(i);
                Thread.currentThread().interrupt();
            }
            //Robot.pneumatics.extendSingleSolenoid(backLong);
           // Robot.pneumatics.retractSingleSolenoid(backLong);
            try {
                Thread.sleep(0);
                System.out.println("SLEEPT");
            } catch (InterruptedException i) {
                i.printStackTrace();
                System.out.println(i);
                Thread.currentThread().interrupt();
            }
            System.out.println("out of sleep");

           // Robot.pneumatics.extendSingleSolenoid(frontLong);

           // Robot.pneumatics.extendSingleSolenoid(frontLong);
           
          //  Robot.pneumatics.extendSingleSolenoid(backLong);
            // Robot.pneumatics.extendSingleSolenoid(frontLong);
           // Robot.pneumatics.extendSingleSolenoid(frontShort);
        } else if (swi == "DOWN") {
            Pneumatics.retractSingleSolenoid(frontShort);
            Pneumatics.retractSingleSolenoid(backShort);
            Pneumatics.retractSingleSolenoid(backLong);
            Pneumatics.retractSingleSolenoid(frontLong);
        }
    }

    public void testPivotPiston(String swi) {
        if (swi == "OUT") {
            Pneumatics.extendSingleSolenoid(pivotPiston);
        } else if (swi == "IN") {
            Pneumatics.retractSingleSolenoid(pivotPiston);
        }
    }

}