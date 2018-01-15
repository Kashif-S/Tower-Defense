package towerdefenseks;

import java.awt.Color;

/*
 * @author Kashif Sayeed
 */

public class Testificate extends Enemy {

  public Testificate(double x,double y){
      super(x,y);
      health = 3;
      sped = 2;
      ecolor = Color.BLUE;
      rotation = 0.1;
  }


}
