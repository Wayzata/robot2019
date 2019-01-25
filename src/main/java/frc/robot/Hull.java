package frc.robot;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;

public class Hull {

    // These variables store the center Point and the value of the area
    private Point center;
    private double area;

    // The constructor takes a MatOfPoint as an argument. From this, it calculates and stores the center and area of the shape
    public Hull(MatOfPoint mat){
        center = calculateCenter(mat);
        area = calculateArea(mat);
    }

    // Finds the area of the shape outlined by a MatOfPoint
    public static Point calculateCenter(MatOfPoint pointMat){
        double centerY = 0;
        double centerX = 0;

        Point[] points = pointMat.toArray();

        for(int x = 0; x < points.length; x++){
            centerX += points[x].x;
            centerY += points[x].y;
        }

        centerX /= points.length;
        centerY /= points.length;

        return new Point(centerX, centerY);
    }

    // Calculates the area of the shape outlined by a MatOfPoint
    public static double calculateArea(MatOfPoint pointMat){
        double area = 0.0;

        Point[] points = pointMat.toArray();

        for(int x = 0; x < points.length-1; x++){
            area += (points[x].x * points[x+1].y - points[x].y * points[x+1].x);
        }
        area += (points[points.length-1].x * points[1].y - points[points.length-1].y * points[1].x);

        area /= 2.0;

        return Math.abs(area);
    }


    // Sets the center of the Hull to a new Point
    public void setCenter(Point newCenter){
        center = newCenter;
    }

    // Sets the area of the Hull to a new value
    public void setArea(double newArea){
        area = newArea;
    }


    // Returns the center Point
    public Point getCenter(){
        return center;
    }

    // Returns the area
    public double getArea(){
        return area;
    }

}