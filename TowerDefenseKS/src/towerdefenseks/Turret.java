package towerdefenseks;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

/*
 * @author Kashif Sayeed
 */

public class Turret extends Tile{

    Image img;
    double radius;
    boolean isClicked;
     ArrayList<Bullet>blist;
     int RateofFire, Range;

    public Turret(double x, double y){
        super(x,y);
        blist = new ArrayList<Bullet>();
    }

    public boolean inRange(Enemy e){
        double x = e.Xposition - Xposition;
        double y = e.Yposition - Yposition;
        double c = Math.sqrt(x*x + y*y);

         return c < radius;
    }

      public void paint(Graphics g, ImageObserver io) {
          super.paint(g);
          g.drawImage(img,(int) Xposition,(int) Yposition, io);
          if (mouseOver){
          g.drawOval((int)(Xposition - radius)+25, (int)(Yposition - radius)+25, (int)radius*2, (int)radius*2);
    }
        for (int i = 0; i < blist.size(); i++) {
        blist.get(i).paint(g);
    }
      }
      public void UpdatePosition(boolean wrap){
          super.UpdatePosition(wrap);
          for (int i = 0; i < blist.size(); i++) {
          blist.get(i).UpdatePosition(false);

          if (blist.get(i).counter > 100 || blist.get(i).active == false){
              // the formula to get the counter to end when the bullet hits the radius circle is radius / 2
              blist.remove(i);
          }
    }
    
      }

}
