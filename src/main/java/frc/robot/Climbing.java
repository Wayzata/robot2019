package frc.robot;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Solenoid;

import java.io.IOException;
import java.lang.Thread;


public class Climbing {

    // Sensor
    // private AnalogInput ultraSonic;

    // Testing Pistons

    // UltraSonic Range Calculation Values
    final double slope = .0090645714;
    final double yint = .0062214286;
    final double ultraMin = 8.0, ultraMax = 9.0;

    // Ultrasonic Automation Ranges - Different because possible off balance coming off
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
    //time based end climb
    long autoStartTime;
	long timeInAuto;
    

    public Climbing() {
        frontUltrasonic = new Ultrasonic(0, 1);
        frontPistonUltra = new Ultrasonic(2, 3);
        backPistonUltra = new Ultrasonic(4, 5);
        frontLong = new Solenoid(1, 0);
        frontShort = new Solenoid(1, 1);
        backLong = new Solenoid(1, 2);
        backShort = new Solenoid(1, 3);
        pivotPiston = new Solenoid(1, 4);
    }

    // Actual code that controls pistons - Can change
    public boolean CheckUltraRange(AnalogInput ultrasonic){
        double range = (double) Math.round(((ultrasonic.getAverageVoltage()-yint)/slope)*100)/100;
        System.out.println("range: " + range);
        if (range >= ultraMin && range <= ultraMax){
            System.out.println("extended");
            return true;
        }
        else{
            return false;
        }
    }

    // Climbing Down automation
    public void ClimbingDown(){
        // Start driving forward 0.5 speed
        Robot.driveTrain.driveForward(0.25);
        // Get ultrarange once front piston is off
        if (frontPistonUltra.getRangeInches() >= frontDeployDistance && !stageOneCompleted){
            // Extending front pistons once
            if (!frontExtended) {
                Robot.pneumatics.extendSingleSolenoid(frontShort);
                //Robot.pneumatics.extendSingleSolenoid(pivotPiston);
                frontExtended = true;
            }
            // Making sure robot continues to drive forward
            while(backPistonUltra.getRangeInches() < backDeployDistance){
                Robot.driveTrain.driveForward(0.25);
            }
            // Extending back once above loop is no longer true
            if(!backExtended){
                Robot.pneumatics.extendSingleSolenoid(backShort);
                stageOneCompleted = true;
                backExtended = true;
            }
        }
        // Bringing robot down and ending auto period
        if (stageOneCompleted){
            Robot.driveTrain.driveForward(0.25);
            if (frontExtended){
                Robot.pneumatics.retractSingleSolenoid(frontShort);
                frontExtended = false;
            }
            if (backExtended){
                Robot.pneumatics.retractSingleSolenoid(backShort);
                backExtended = false;
            }
            climbingDownCompleted = true;
        }
    }

    // Climbing Up automation
    public void ClimbingUp(){

        //Deploy all pistons
        if (!initialPistonDeploy){
            Robot.pneumatics.extendSingleSolenoid(backShort);
            Robot.pneumatics.extendSingleSolenoid(frontShort);
            Robot.pneumatics.extendSingleSolenoid(backLong);
            Robot.pneumatics.extendSingleSolenoid(frontLong);
            initialPistonDeploy = true;
        }

        // Pausing before tipping forward
        try {
            Thread.sleep(1000);
        }catch(InterruptedException i){
            i.printStackTrace();
            System.out.println(i);
            Thread.currentThread().interrupt();
        }
        
        // Tipping forward
        if (!pivoted){
            Robot.pneumatics.extendSingleSolenoid(pivotPiston);
            pivoted = true;
        }
        
        // Driving forward because front wheels should be on
        Robot.driveTrain.driveForward(0.75);

        if (frontUltrasonic.getRangeInches() <= climbingUpRetractDistance && !frontRetracted){
            Robot.pneumatics.retractSingleSolenoid(frontLong);
            Robot.pneumatics.retractSingleSolenoid(frontShort);
            frontRetracted = true;
        }
    
        //Move forward, reach the distance for back retraction   
        while(backPistonUltra.getRangeInches() > climbingUpRetractDistance)
        {
            Robot.driveTrain.driveForward(0.25);
        }
        Robot.pneumatics.retractSingleSolenoid(backLong);
        Robot.pneumatics.retractSingleSolenoid(backShort);
       
        climbingUpSuccess = true;   //Use this Bool to give controls back to driver for Parking
        
    }

    //This will check for a climbing button being pressed and the procedure not completed yet, then calling climbing methods
    public void checkClimbButtons() {

        if (Joysticks.leftJoy.getRawButtonPressed(Variables.climbUp) && climbingUpSuccess == false) {

            ClimbingUp();

        } else if (Joysticks.leftJoy.getRawButtonPressed(Variables.climbDown) && climbingDownCompleted == false) {

            ClimbingDown();

        } else {
        }


    }
    
}