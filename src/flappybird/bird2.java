/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class bird2 {
    public float x,x1, y, vx, vy;
    public int life;
    public boolean dec=false;
    public int RAD = 50;
    public Image img;
    public Image bullet;
    public bird2() {
        x = 600;
        y = 250;
        life = 5;
        try {
            img = ImageIO.read(new File("oh.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void physics() {
        x-=vx;
        vx=(float)4.0001;
 
    }
    public void physics1(Graphics g){
        dec=false;

    }
    public void update(Graphics g) {

        g.setColor(Color.BLACK);
         if(dec){
          g.drawImage(img, Math.round(x-RAD),Math.round(y-RAD),2*RAD,2*RAD, null);  
          }  
       
       if(x<0){
        x = 600;
        y=((int)(Math.random()*500)+250)-100;
        dec=true;
        }
         
    }

}
