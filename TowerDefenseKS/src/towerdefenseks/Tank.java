package towerdefenseks;

import java.awt.Color;

/*
 * @author Kashif Sayeed
 */

public class Tank extends Enemy {
    public Tank(double x,double y){
      super(x,y);
      health = 500;
      sped = 1;
      ecolor = Color.magenta;
      rotation = 0.05;
  }

}
