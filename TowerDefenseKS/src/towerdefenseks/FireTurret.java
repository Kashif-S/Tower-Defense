package towerdefenseks;

/*
 * @author Kashif Sayeed
 */

public class FireTurret extends Turret{


     public FireTurret(double x, double y){
         super(x,y);
        img = TowerDefenseKS.FlaregunImg;
        radius = 80;
        RateofFire = 0;
        Range = 16;

}
}
