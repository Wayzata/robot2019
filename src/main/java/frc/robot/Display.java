package frc.robot;

import java.awt.*;
import javax.swing.*;

import org.opencv.core.Rect;

public class Display extends JFrame {

    private Rect[] rectangles;

    private Thread thread;

    public Display(){
        thread = new Thread(new DrawThread());
        thread.start();
    }

    @Override
    public void paintComponents(Graphics g){
        g.drawRect(0, 0, Vision.IMG_WIDTH, Vision.IMG_HEIGHT);

        for(int x = 0; x < rectangles.length; x++){
            g.drawRect(rectangles[x].x, rectangles[x].y, rectangles[x].width, rectangles[x].height);
        }
    }

    public void setRectangles(Rect[] rectangles){
        this.rectangles = rectangles;
    }

    public void clearRectangles(){
        rectangles = new Rect[0];
    }

    public void stopDrawing(){
        thread.interrupt();
    }

    private class DrawThread implements Runnable {
        @Override
        public void run(){
            while(! Thread.interrupted()){
                repaint();
            }
        }
    }

}