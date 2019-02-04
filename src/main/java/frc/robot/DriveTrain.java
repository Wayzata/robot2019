package frc.robot;

/*
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID;

public class DriveTrain {

    TalonSRX frontLeft;
    TalonSRX frontRight;
    TalonSRX backLeft;
    TalonSRX backRight;
    
    public DriveTrain() {
        frontLeft = new TalonSRX(Variables.fLeftMotor);
        backLeft = new TalonSRX(Variables.bLeftMotor);

        frontRight = new TalonSRX(Variables.fRightMotor);
        backRight = new TalonSRX(Variables.bRightMotor);

        double currentPos = 0;

    }


    public void stop() {

        frontLeft.set(ControlMode.PercentOutput, 0);
        backLeft.set(ControlMode.PercentOutput, 0);
        frontRight.set(ControlMode.PercentOutput, 0);
        backRight.set(ControlMode.PercentOutput, 0);

    }


    public void drive(double leftSpeed, double rightSpeed) {

        frontLeft.set(ControlMode.PercentOutput, leftSpeed);
        backLeft.set(ControlMode.PercentOutput, leftSpeed);
        frontRight.set(ControlMode.PercentOutput, rightSpeed);
        backRight.set(ControlMode.PercentOutput, rightSpeed);
    }

    public void tankDrive(Joystick leftJoystick, Joystick rightJoystick){

        drive((-1 * leftJoystick.getY(GenericHID.Hand.kLeft)*Variables.driveLimiter), (rightJoystick.getY(GenericHID.Hand.kRight)*Variables.driveLimiter));
    }

    public void testDrive(){
        
        //frontRight.setSelectedSensorPosition(473);
        frontRight.set(ControlMode.PercentOutput, .2);
        
        System.out.println("Selected sensor " + frontRight.getSelectedSensorPosition());
        //Use getSelectedSensorPosition; it updates more often

    }

    public void resetStuff(int x) {
        frontRight.setSelectedSensorPosition(x);
    }
}

*/