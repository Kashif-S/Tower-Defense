
package towerdefenseks;

import java.awt.Polygon;

/*
 * @author Kashif Sayeed
 */

public class Bullet extends Sprite {

    public Bullet(double x, double y, double a){
        shape = new Polygon();
        shape.addPoint(-2,-2);
        shape.addPoint(2, -2);
        shape.addPoint(2, 2);
        shape.addPoint(-2, 2);

        drawShape = new Polygon();
        drawShape.addPoint(-2, -2);
        drawShape.addPoint(2, -2);
        drawShape.addPoint(2, 2);
        drawShape.addPoint(-2, 2);
        Xposition = x;
        Yposition = y;
        angle = a;
        active = true;
        Xspeed = Math.cos(a)*10;
        Yspeed = Math.sin(a)*10;
    }
}
