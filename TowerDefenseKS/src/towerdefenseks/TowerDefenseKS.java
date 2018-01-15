package towerdefenseks;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import java.applet.AudioClip;
import java.awt.event.MouseMotionListener;

/*
 * @author Kashif Sayeed
 */

public class TowerDefenseKS extends Applet implements ActionListener,MouseListener,MouseMotionListener,KeyListener{
    
    Image offscreen;
    Timer timer;
    Graphics offg;
    int mouseX = -100,mouseY = -100, mouseXclicked = -100,mouseYclicked = -100;
    int preMouseX = -100, preMouseY = -100;
    int screenX, screenY;
    ArrayList<Tile> tlist;
    ArrayList<Enemy>elist;
    public static Image peashooterImg, FlaregunImg;

    Turret selectedTurret;
    peashooter p;
    FireTurret f;
    Testificate t;

    public void init(){
        screenX = 1136;
        screenY = 675;
        peashooterImg = this.getImage(getCodeBase(),"Turret2front.png");
        FlaregunImg = this.getImage(getCodeBase(),"fireturret1.png");
        this.setSize(screenX, screenY);
        timer = new Timer(20, this);
        offscreen = createImage(this.getWidth(), this.getHeight());
        offg = offscreen.getGraphics();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        p = new peashooter(1050, 100);
        f = new FireTurret(950, 100);

        tlist = new ArrayList<Tile>();
        elist = new ArrayList<Enemy>();

    
SpawnEnemies(50,20,10);

         setMap();   

    }

    public void SpawnEnemies(int x,int w,int h){
        int y = 0;
        for (int i = 0; i < x; i++) {
            elist.add(new Testificate(73, y));
            elist.get(i).direction = Enemy.down;
            y -= 20;
        }
        for (int i = x; i < x+w; i++) {
            elist.add(new Splitter(73, y));
            elist.get(i).direction = Enemy.down;
            y -= 20;
        }
        for (int i = x+w; i <x+ w+h; i++) {
            elist.add(new Tank(73, y));
            elist.get(i).direction = Enemy.down;
            y -= 20;
        }
        
    }
     public void update(Graphics g) {
        paint(g);
    }
    
    public void paint(Graphics g) {
        offg.setColor(Color.black);
        offg.fillRect(0, 0, (screenX-200), screenY);
        offg.setColor(Color.GREEN);
        offg.fillRect(screenX-200, 0, screenX, screenY);
        offg.setColor(Color.red);
        for (int i = 0; i < tlist.size(); i++) {
            offg.setColor(Color.red);
            if (tlist.get(i).drawShape.contains(mouseX, mouseY)) {
                offg.setColor(Color.WHITE);
                tlist.get(i).mouseOver = true;
            } else {
                tlist.get(i).mouseOver = false;
            }
            if (tlist.get(i) instanceof Turret) {
                ((Turret) tlist.get(i)).paint(offg, this);
            } else {
                if (tlist.get(i) instanceof WayPoint == false)
                tlist.get(i).paint(offg);
            }
        }

        p.paint(offg, this);
        f.paint(offg, this);
        
        for(int i = 0; i < elist.size(); i++){
            offg.setColor(elist.get(i).ecolor);
            elist.get(i).paint(offg);
        }
            
offg.setColor(Color.GREEN);
        if (selectedTurret != null) {
            selectedTurret.paint(offg, this);
        }
        g.drawImage(offscreen, 0, 0, this);
        
        
        repaint();


}
     public void start(){
        timer.start();
    }

    public void stop() {
        timer.stop();
    }


    public int findClosest(Turret t) {
        int smallIndex = 0;
        double smallest = 0;

        for (int i = 0; i < elist.size(); i++) {
            Enemy e = elist.get(i);
            double x = t.Xposition - e.Xposition;
            double y = t.Yposition - e.Yposition;
            double c = Math.sqrt(x * x + y * y);


            if (i == 0) {
                smallIndex = 0;
                smallest = c;
            }

            if (c < smallest) {
                smallest = c;
                smallIndex = i;
            }
        }




        return smallIndex;
    }

    @Override
    public void actionPerformed(ActionEvent e) {



        for (int i = 0; i < tlist.size(); i++) {
            tlist.get(i).UpdatePosition(false);
            changeDir(i);
            if (tlist.get(i) instanceof Turret && elist.isEmpty() == false) {

                int idx = findClosest((Turret) tlist.get(i));
                if (((Turret) tlist.get(i)).inRange(elist.get(idx))) {
                    double x = tlist.get(i).Xposition - elist.get(idx).Xposition;
                    double y = tlist.get(i).Yposition - elist.get(idx).Yposition;
                    double a = Math.atan(y / x);
                    if (elist.get(idx).Xposition < tlist.get(i).Xposition) {
                        a += Math.PI;
                    }
                    if (((Turret) tlist.get(i)).counter > ((Turret) tlist.get(i)).RateofFire && elist.isEmpty() == false) {
                        if (((Turret) tlist.get(i)) instanceof FireTurret) {
                                         ((Turret) tlist.get(i)).blist.add(new Bullet((tlist.get(i).Xposition + 26), (tlist.get(i).Yposition + 26), a));
                                             ((Turret) tlist.get(i)).blist.add(new Bullet((tlist.get(i).Xposition + 26), (tlist.get(i).Yposition + 26), a + Math.random() * 0.1));
                                                 ((Turret) tlist.get(i)).blist.add(new Bullet((tlist.get(i).Xposition + 26), (tlist.get(i).Yposition + 26), a - Math.random() * 0.1));

                            ((Turret) tlist.get(i)).counter = 0;
                        } else {

                            ((Turret) tlist.get(i)).blist.add(new Bullet((tlist.get(i).Xposition + 26), (tlist.get(i).Yposition + 26), a));

                            ((Turret) tlist.get(i)).counter = 0;
                        }
                    }
                }


            }
        }

        handleMouse();

         
        p.UpdatePosition(false);
        f.UpdatePosition(false);

        if (selectedTurret != null) {
            selectedTurret.UpdatePosition(false);
        }
       
       
         for(int i = 0; i < elist.size(); i++){
            elist.get(i).UpdatePosition(false);
            if (elist.get(i).Xposition > (927)){
                elist.get(i).Xposition = 73;
                elist.get(i).Yposition = 0;
                elist.get(i).setDir(Enemy.down);

            }

            if (elist.get(i).active == false) {
                elist.remove(i);
            }
    }
     handleCollisions();

      if (elist.isEmpty() == true) {
            for (int i = 0; i < tlist.size(); i++) {
                if (tlist.get(i) instanceof Turret) {
                    ((Turret) tlist.get(i)).blist.clear();
                }
            }
        }
    }
            
    public void resetMouse() {
        mouseXclicked = -100;
        mouseYclicked = -100;
    }

    public void changeDir(int i) {
       for(int j = 0; j < elist.size(); j++)
            if (tlist.get(i) instanceof WayPoint) {
                double xdir = elist.get(j).Xposition;
                double ydir = elist.get(j).Yposition;


                if (elist.get(j).direction == Enemy.down) {
                   ydir -= 26;
                }

                 if (elist.get(j).direction == Enemy.up) {
                 ydir += 26;
                }

                 if (elist.get(j).direction == Enemy.right) {
                 xdir -= 26;
                }

                 if (elist.get(j).direction == Enemy.left) {
                  xdir += 26;
                }

                   if (tlist.get(i).drawShape.contains(xdir,ydir)) {
                        elist.get(j).setDir(((WayPoint) tlist.get(i)).dirp);
                    }
            }
        
    }

    public void handleMouse() {
        if (p.drawShape.contains(mouseXclicked, mouseYclicked)) {
            p.isClicked = true;
            selectedTurret = new peashooter(p.Xposition, p.Yposition);
            selectedTurret.mouseOver = true;
        }
        if (f.drawShape.contains(mouseXclicked, mouseYclicked)) {
            f.isClicked = true;
            selectedTurret = new FireTurret(f.Xposition, f.Yposition);
            selectedTurret.mouseOver = true;
        }



        if (p.isClicked == true && selectedTurret != null) {
            selectedTurret.Xposition = mouseX - 25;
            selectedTurret.Yposition = mouseY - 25;
        }
         if (f.isClicked == true && selectedTurret != null) {
            selectedTurret.Xposition = mouseX - 25;
            selectedTurret.Yposition = mouseY - 25;
         }


        for (int i = 0; i < tlist.size(); i++) {
            if (tlist.get(i).drawShape.contains(mouseXclicked, mouseYclicked)) {
                if (p.isClicked == true && tlist.get(i) instanceof WayPoint == false) {
                    selectedTurret = null;
                    p.isClicked = false;

                    double x = tlist.get(i).Xposition;
                    double y = tlist.get(i).Yposition;

                    tlist.add(new peashooter(x, y));
                    tlist.remove(i);
                }
                 if (f.isClicked == true && tlist.get(i) instanceof WayPoint == false) {
                    selectedTurret = null;
                    f.isClicked = false;
                    double x = tlist.get(i).Xposition;
                    double y = tlist.get(i).Yposition;

                    tlist.add(new FireTurret(x, y));
                    tlist.remove(i);

            }
        }


    
         }
    }
    
    // when checking for collision, the second parameter has the offset.
    public boolean collision(Sprite thing1, Sprite thing2, int offsetX, int offsetY) {

        int x, y;
        for (int i = 0; i < thing1.shape.npoints; i++) {
            x = thing1.drawShape.xpoints[i];
            y = thing1.drawShape.ypoints[i];

            if (thing2.drawShape.contains(x + offsetX, y + offsetY)) {
                return true;
            }
        }

        for (int i = 0; i < thing2.shape.npoints; i++) {
            x = thing2.drawShape.xpoints[i];
            y = thing2.drawShape.ypoints[i];

            if (thing1.drawShape.contains(x, y)) {
                return true;
            }
        }
        return false;

    }

    public void handleCollisions() {
        for (int k = 0; k < tlist.size(); k++) {
        
                    for (int j = 0; j < elist.size(); j++) {
    if (tlist.get(k) instanceof Turret && elist.isEmpty() == false) {
                for (int i = 0; i < ((Turret) tlist.get(k)).blist.size(); i++) {

                        if (collision(((Turret) tlist.get(k)).blist.get(i), elist.get(j), 0, 0)) {
                            ((Turret) tlist.get(k)).blist.get(i).active = false;
                            elist.get(j).health -= 1;
                            
                        }
                        if (elist.get(j).health <= 0){
                            elist.get(j).active= false;
                        }
                    }
                }
            }
        }
    }


    public void mouseClicked(MouseEvent e) {
        mouseXclicked = e.getX();
        mouseYclicked = e.getY();

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void setMap() {
        try {
            BufferedReader br;
            br = new BufferedReader(new FileReader("Map.txt"));
            String line = br.readLine();

            int x = 0, y = 0;
            while (line != null) {

                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == 'x') {
                        tlist.add(new Tile(x, y));
                    }
            

                    if (line.charAt(i) == 'l') {
                        tlist.add(new WayPoint(x, y, Enemy.left));
                    }
                   
                    if (line.charAt(i) == 'r') {
                        tlist.add(new WayPoint(x, y, Enemy.right));
                    }
                   
                    if (line.charAt(i) == 'u') {
                        tlist.add(new WayPoint(x, y, Enemy.up));
                    }
                    
                    if (line.charAt(i) == 'd') {
                        tlist.add(new WayPoint(x, y, Enemy.down));
                    }
                    x += 52;
                }

                line = br.readLine();
                x = 0;
                y += 52;
            }

            br.close();

            
            

        }catch(IOException e){
            
        }
   
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_C){
            resetMouse();
            selectedTurret = null;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

   
}
