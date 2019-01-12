package frc.robot;
import edu.wpi.first.wpilibj.Talon;

//4 motors
// front left motors = 4
//back left motor = 3
// front right motor = 5
// back right motor = 6

public class DriveTrain {
    private Talon frontLeftMotor;
    private Talon backLeftMotor;
    private Talon frontRightMotor;
    private Talon backRightMotor;
    private final double speedMultiplier = .5;

    public DriveTrain() {
        frontLeftMotor = new Talon(4);
        backLeftMotor = new Talon(3);
        frontRightMotor = new Talon(5);
        backRightMotor = new Talon(6);

    }

    public void leftMove(double speed) {
        /*
         * if(speed < .05){ speed = 0; }
         * 
         */
        if (speed < 0) {

            frontLeftMotor.set(-1 * (Math.pow(speed, 2) * speedMultiplier));
            backLeftMotor.set(-1 * (Math.pow(speed, 2) * speedMultiplier));

        } else {

            frontLeftMotor.set(1 * (Math.pow(speed, 2) * speedMultiplier));
            backLeftMotor.set(1 * (Math.pow(speed, 2) * speedMultiplier));

        }
    }

    public void rightMove(double speed) {
        /*
         * if(speed < .05){ speed = 0; }
         */
        if (speed < 0) {

            frontLeftMotor.set(-1 * (Math.pow(speed, 2) * speedMultiplier));
            backLeftMotor.set(-1 * (Math.pow(speed, 2) * speedMultiplier));

        } else {

            frontLeftMotor.set(1 * (Math.pow(speed, 2) * speedMultiplier));
            backLeftMotor.set(1 * (Math.pow(speed, 2) * speedMultiplier));

        }
    }

}