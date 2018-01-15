package towerdefenseks;

/*
 * @author Kashif Sayeed
 */

public class peashooter extends Turret {

    public peashooter(double  x,double y){
        super(x,y);
        img = TowerDefenseKS.peashooterImg;
        radius = 200;
        RateofFire = 10;
        Range = 3;
    }

}
