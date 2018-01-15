package towerdefenseks;

import java.awt.Color;

/*
 * @author Kashif Sayeed
 */

public class Splitter extends Enemy{
 public Splitter(double x,double y){
      super(x,y);
      health = 1;
      sped = 10 ;
      ecolor = Color.YELLOW;
      rotation = 0.2;
  }
}
