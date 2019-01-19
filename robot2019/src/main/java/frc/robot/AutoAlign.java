/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/* This class will take a direction input & a method that waits for an input to switch to watching for reflective tape.
    Then align the robot to the cargo ship/rocket autonomously.

*/

public class AutoAlign {

    Variables var = new Variables();
    DriveTrain drive = new DriveTrain();

    // Variables
    String direction = "";
    boolean alignFlag = false;

    public void checkDirecton() {

        // If left button pressed
        if (var.leftButton == 1) {

            direction.equals("left");
            // present to dashboard

        }

        // If right button pressed
        if (var.rightButton == 2) {

            direction.equals("right");
            // present to dashboard

        }

    }

    public void alignButton() {

        // WHen pressed
        if (var.alignButton == 3) {

            if (alignFlag == true) {

                alignFlag = false;
                // Upate dashboard with status

            } else {

                alignFlag = true;
                // Upate dashboard with status

            }

        }

        if (alignFlag == true) {

            checkFloorTape();
            alignFlag = false;
        }

    }

    public void checkFloorTape() {

        // If light detects tape
        if (var.centerLight == 4) {

            // Set speeds to 0
            drive.stop();
            //Call Check Sensor
            while (direction.equals("right") && var.centerLight == 4 && alignFlag == true) {
               //Call Check Sensor
                if (var.alignButton == 3) {
                    alignFlag = false;
                }
                drive.drive(1, -1);
                

            }
            //Call Check Sensor
            while (direction.equals("left") && var.centerLight == 4 && alignFlag == true) {
               //Call Check Sensor
                if (var.alignButton == 3) {
                    alignFlag = false;
                }
                drive.drive(1, -1);
                //Cal Check Sensor

            }

        }

    }

    

}
