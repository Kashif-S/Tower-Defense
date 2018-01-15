package towerdefenseks;

import java.awt.Polygon;

/*
 * @author Kashif Sayeed
 */

public class Tile extends Sprite {
    
   boolean mouseOver;

    
    public Tile(double x,double y){
        shape = new Polygon();
        shape.addPoint(0, 0);
        shape.addPoint(0, 50);
        shape.addPoint(50, 50);
        shape.addPoint(50, 0);

        drawShape = new Polygon();
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 50);
        drawShape.addPoint(50, 50);
        drawShape.addPoint(50, 0);
        Xposition = x;
        Yposition = y;
        
    }
}
