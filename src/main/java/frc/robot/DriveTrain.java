package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID;

public class DriveTrain {

    //Creating the objects for the 4 motors
    TalonSRX frontLeft;
    TalonSRX frontRight;
    TalonSRX backLeft;
    TalonSRX backRight;
    
    public DriveTrain() {

        //Initializing the 4 motors
        frontLeft = new TalonSRX(Variables.fLeftMotor);
        backLeft = new TalonSRX(Variables.bLeftMotor);

        frontRight = new TalonSRX(Variables.fRightMotor);
        backRight = new TalonSRX(Variables.bRightMotor);

        //Used for ??
        //double currentPos = 0;

    }

    //Method used for autonomous programing, stops motors
    public void stop() {

        frontLeft.set(ControlMode.PercentOutput, 0);
        backLeft.set(ControlMode.PercentOutput, 0);
        frontRight.set(ControlMode.PercentOutput, 0);
        backRight.set(ControlMode.PercentOutput, 0);

    }

    //Method used to set motor speed
    public void drive(double leftSpeed, double rightSpeed) {

        frontLeft.set(ControlMode.PercentOutput, leftSpeed);
        backLeft.set(ControlMode.PercentOutput, leftSpeed);
        frontRight.set(ControlMode.PercentOutput, rightSpeed);
        backRight.set(ControlMode.PercentOutput, rightSpeed);

    }

    //Method used to simulate tank drive by calling Drive function and sending Joystick values 
    public void tankDrive(Joystick leftJoystick, Joystick rightJoystick){

        drive((-1 * leftJoystick.getY(GenericHID.Hand.kLeft)*Variables.driveLimiter), (rightJoystick.getY(GenericHID.Hand.kRight)*Variables.driveLimiter));
    }

    //Method used to do specific drive testing
    public void testDrive(){
        
        //frontRight.setSelectedSensorPosition(473);
        frontRight.set(ControlMode.PercentOutput, .2);
        
        System.out.println("Selected sensor " + frontRight.getSelectedSensorPosition());
        //Use getSelectedSensorPosition; it updates more often

    }

    //Lol what
    public void resetStuff(int x) {
        frontRight.setSelectedSensorPosition(x);
    }
}