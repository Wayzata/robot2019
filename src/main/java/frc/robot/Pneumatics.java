package frc.robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

//Authors: ... (Add here)

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


