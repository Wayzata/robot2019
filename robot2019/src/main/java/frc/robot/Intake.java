package frc.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class Intake{

    //"This is the motor that controls the intake wheel" (Rafael Dubeau 4).
    public TalonSRX intakeMotor;
    //Piston to push out the hatch
    public Solenoid pushPiston;
    
    public Intake(){

        intakeMotor = new TalonSRX(1);
        pushPiston = new Solenoid(0,1);

    }

    //Declare method to start the intake motor
    public void motorStart(){

        intakeMotor.set(.5);
        
    }

    //Method to stop the intake motor
    public void motorStop(){

        intakeMotor.set(0);

    }

    //Method to run the hecker in reverse
    public void motorReverse(){

        intakeMotor.set(-.5);
    }

    //Method to push hatch out
    public void pistonOut(){

        pushPiston.set(true);

    }
}

