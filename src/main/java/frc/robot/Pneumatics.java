package frc.robot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

//Authors: ... (Add here)

public class Pneumatics {
  
  // This variable controls the onboard compressor
  Compressor compressor;

  boolean armsOut;
  boolean shooterOut;

  public Pneumatics(){
    // armsSolenoid = new DoubleSolenoid(1, 2, 3);
    //shooterSolenoid = new DoubleSolenoid(1, 0, 1);
    compressor = new Compressor(0);
    compressor.start();
    armsOut = false;
    shooterOut = false;

  }

  // This method extends the given solenoid
  public static void extendSingleSolenoid(Solenoid solenoid) {
		solenoid.set(true);
  }

  // This method retracts the given solenoid
  public static void retractSingleSolenoid(Solenoid solenoid) {
    solenoid.set(false);
  }
}


