/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Encoder;

public class Arm {

    //Encoders
    Encoder Encoder1 = new Encoder(0, 1, true);

    double currentPos = 0;
    int currentCount = 0;

    //Gets position/pulse count
    public void getCount(){

        currentPos = Encoder1.getDistance();
        currentCount = Encoder1.get();

        System.out.println(currentCount);
        System.out.println(currentPos);


    }

    


}
