package frc.robot;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.vision.VisionRunner;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Authors: Thomas, Rafael, ... (Add here)

public class Vision {
    // I can see the light

    // These variables define the resolution of the camera
    public static final int IMG_WIDTH = 400;
    public static final int IMG_HEIGHT = 400;

    public static final int margin = 50;

    // The variable for the front facing camera surrounded by LEDs
    private UsbCamera camera;
    // This variable controls the thread used for vision processing
    private VisionThread visionThread;
    // This Array of Hulls stores the output of the GRIP algorithm
    private Rect[] boundingBoxes;
    // This variable is true if there are valid vision targets in sight and false
    // otherwise
    private boolean valid;

    // The constructor starts the camera and vision processing
    public Vision() {
        // Starts the camera and sets it to the correct resolution
        camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(IMG_WIDTH, IMG_HEIGHT);

        // Sets up and begins the thread for vision processing
        visionThread = new VisionThread(camera, new GripPipeline(), new VisionRunner.Listener<GripPipeline>() {
            @Override
            public void copyPipelineOutputs(GripPipeline pipeline) {
                // This if statement runs if we get an output from the vision software
                if (pipeline.findContoursOutput().size() >= 2) {
                    // Takes the output of the GRIP algorithm and places it into an array of Hull
                    // objects
                    ArrayList<MatOfPoint> MatList = pipeline.findContoursOutput();
                    
                    //System.out.println("-------------------------------------------");

                    boundingBoxes = new Rect[MatList.size()];
                    for (int x = 0; x < boundingBoxes.length; x++) {
                        boundingBoxes[x] = Imgproc.boundingRect(MatList.get(x));
                        //System.out.println("Bounding Box " + x + ": \tCenterX: " + (boundingBoxes[x].x + boundingBoxes[x].width) + "\tArea: " + boundingBoxes[x].area());
                    }

                    boundingBoxes = getValidRects(boundingBoxes);
                    //System.out.println("Left Bounding Box: \tCenterX: " + (boundingBoxes[0].x + boundingBoxes[0].width) + "\tArea: " + boundingBoxes[0].area());
                    //System.out.println("Right Bounding Box: \tCenterX: " + (boundingBoxes[1].x + boundingBoxes[1].width) + "\tArea: " + boundingBoxes[1].area());
                    
                    SmartDashboard.putBoolean("Alinged", isCentered());
                }
            }
        });
        visionThread.start();
    }

    // Returns whether or not the vision targets are centered for the robot
    public boolean isCentered(){
        double mid = getHorizontalMidpoint();

        if(mid > IMG_WIDTH/2 - margin && mid < IMG_WIDTH/2 + margin){
            return true;
        }
        return false;
    }

    // Finds the two Hulls that correspond to the vision targets
    public Rect[] getValidRects(Rect[] boxes) {
        boxes = sortRects(boxes);

        // Assigns the right and left hulls
        Rect leftRect = (boxes[0].x < boxes[1].x ? boxes[0] : boxes[1]);
        Rect rightRect = (boxes[0].x > boxes[1].x ? boxes[0] : boxes[1]);

        return new Rect[] { leftRect, rightRect };
    }

    // Sorts the hulls from largest area to smallest area using a selection sort
    public Rect[] sortRects(Rect[] boxes) {
        // the index of the lowest value in the unsorted portion of the array
        int greatest = 0;
        for (int x = 0; x < boxes.length; x++)
            for (int i = x; i < boxes.length; i++) {
                // finds the index of the greatest element in the array
                if (boxes[i].area() > (i == x ? Integer.MIN_VALUE : boxes[greatest].area()))
                    greatest = i;
                // swaps the element at index x with the greatest element to the right of it
                Rect temp = boxes[x];
                boxes[x] = boxes[greatest];
                boxes[greatest] = temp;
            }

        return boxes;
    }

    // Returns the distance (in pixels) between the center of the two largest hulls
    // (most probably the vision targets)
    public double getHorizontalSeparationDistance() {
        return Math.abs((boundingBoxes[0].x + boundingBoxes[0].width / 2) - (boundingBoxes[1].x +boundingBoxes[1].width / 2));
    }

    // Returns the point directly halfway in between the centers of the two largest
    // hulls (most probably the vision targets)
    public double getHorizontalMidpoint() {
        // The midpoint formula
        return ((boundingBoxes[0].x + boundingBoxes[0].width / 2) + (boundingBoxes[1].x +boundingBoxes[1].width / 2)) / 2;
    }

    // Returns a the Hull at a given index
    public Rect getRect(int index) {
        return boundingBoxes[index];
    }

    // Returns whether or not the vision targets are valid
    public boolean isValid() {
        return valid;
    }



}