package frc.robot;

import java.util.ArrayList;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.vision.VisionRunner;
import edu.wpi.first.vision.VisionThread;

public class Vision {

    // These variables define the resolution of the camera
    public static final int IMG_WIDTH = 400;
    public static final int IMG_HEIGHT = 400;

    // The variable for the front facing camera surrounded by LEDs
    private UsbCamera camera;
    // This variable controls the thread used for vision processing
    private VisionThread visionThread;
    // This Array of Hulls stores the output of the GRIP algorithm
    private Hull[] hulls;

    // The constructor starts the camera and vision processing
    public Vision(){
        // Starts the camera and sets it to the correct resolution
        camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(IMG_WIDTH, IMG_HEIGHT);

        // Sets up and begins the thread for vision processing
        visionThread = new VisionThread(camera, new GripPipeline(), new VisionRunner.Listener<GripPipeline>() {
            @Override
            public void copyPipelineOutputs(GripPipeline pipeline){
                // This if statement runs if we get an output from the vision software
                if(! pipeline.convexHullsOutput().isEmpty()){
            
                    // Takes the output of the GRIP algorithm and places it into an array of Hull objects
                    ArrayList<MatOfPoint> MatList = pipeline.convexHullsOutput();

                    hulls = new Hull[MatList.size()];
                    for(int x = 0; x < hulls.length; x++){
                        hulls[x] = new Hull(MatList.get(x));
                    }
                    
                    // Arranges the hulls from greatest area to smallest area
                    sortHulls();
                }
            }
        });
        visionThread.start();
    }

    // Sorts the hulls from largest area to smallest area using a selection sort
    public Hull[] sortHulls(){
        //the index of the lowest value in the unsorted portion of the array
		int greatest = 0;
		for(int x = 0; x < hulls.length; x++)
			for(int i = x; i < hulls.length; i++) {
				//finds the index of the greatest element in the array
				if(hulls[i].getArea() > (i == x ? Integer.MIN_VALUE : hulls[greatest].getArea()))
					greatest = i;
			//swaps the element at index x with the greatest element to the right of it
			Hull temp = hulls[x];
			hulls[x] = hulls[greatest];
			hulls[greatest] = temp;
        }
        
        return hulls;
    }

    // Returns the distance (in pixels) between the center of the two largest hulls (most probably the vision targets)
    public double getSeparationDistance(){
        // The distance formula
        return Math.sqrt(Math.pow(hulls[0].getCenter().x - hulls[1].getCenter().x, 2) + Math.pow(hulls[0].getCenter().y - hulls[1].getCenter().y, 2));
    }

    // Returns the point directly halfway in between the centers of the two largest hulls (most probably the vision targets)
    public Point getMidpoint(){
        // The midpoint formula
        return new Point((hulls[0].getCenter().x + hulls[1].getCenter().x) / 2, (hulls[0].getCenter().y + hulls[1].getCenter().y) / 2);
    }

    // Returns a the Hull at a given index
    public Hull getHull(int index){
        return hulls[index];
    }


}