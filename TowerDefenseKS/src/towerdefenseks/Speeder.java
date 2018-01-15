package towerdefenseks;

import java.awt.Color;

/*
 * @author Kashif Sayeed
 */

public class Speeder extends Enemy {
 public Speeder(double x,double y){
      super(x,y);
      sped = 15;
      ecolor = Color.YELLOW;
      rotation = 1.5;
}
}
