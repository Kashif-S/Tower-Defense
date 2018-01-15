package towerdefenseks;

import java.awt.Color;
import java.awt.Polygon;

/*
 * @author Kashif Sayeed
 */

public class Enemy extends Sprite {

    int sped;
    int direction;
    int health;
    final static int up = 0;
    final static int down = 1;
    final static int right = 2;
    final static int left = 3;
    Color ecolor;

    public Enemy(double x, double y) {

        shape = new Polygon();
        shape.addPoint(-5, -5);
        shape.addPoint(5, -5);
        shape.addPoint(5, 5);
        shape.addPoint(-5, 5);
        active = true;
        drawShape = new Polygon();
        drawShape.addPoint(-5, -5);
        drawShape.addPoint(5, -5);
        drawShape.addPoint(5, 5);
        drawShape.addPoint(-5, 5);
        Xposition = x;
        Yposition = y;
    }

    public void UpdatePosition(boolean wrap) {
        super.UpdatePosition(false);
        angle += rotation;
        if (direction == up) {
            Yspeed = -sped;
            Xspeed = 0;
        } else if (direction == down) {
            Yspeed = sped;
            Xspeed = 0;
        } else if (direction == right) {
            Xspeed = sped;
            Yspeed = 0;
        } else if (direction == left) {
            Xspeed = -sped;
            Yspeed = 0;
        }
    }
    
    public void setDir(int dir){
        direction = dir;
    }

  
}
