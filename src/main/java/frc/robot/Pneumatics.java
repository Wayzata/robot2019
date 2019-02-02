/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
/**
 * Add your docs here.
 */
public class Pneumatics {
    // DoubleSolenoid armsSolenoid;
    //DoubleSolenoid shooterSolenoid;
    boolean armsOut;
    boolean shooterOut;

  public Pneumatics(){
    // armsSolenoid = new DoubleSolenoid(1, 2, 3);
    //shooterSolenoid = new DoubleSolenoid(1, 0, 1);
    armsOut = false;
    shooterOut = false;

  }
  public void retractSolenoid(DoubleSolenoid solenoid) {
		solenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	public void extendSolenoid(DoubleSolenoid solenoid) {
		solenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void extendSingleSolenoid(Solenoid solenoid) {
		solenoid.set(true);
  }

  public void retractSingleSolenoid(Solenoid solenoid) {
    solenoid.set(false);
  }
}


