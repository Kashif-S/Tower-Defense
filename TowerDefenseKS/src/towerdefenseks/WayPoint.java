package towerdefenseks;

/*
 * @author Kashif Sayeed
 */

public class WayPoint extends Tile {

    int dirp;

    public WayPoint(double x,double y, int dirp){
        super (x,y);
        this.dirp = dirp;
    }
}
