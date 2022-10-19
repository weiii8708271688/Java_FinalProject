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
public class Bird1 {
    public float x,x1, y, vx, vy;

    public boolean dec=false;
    public static final int RAD = 25;
    public Image img;
    public Image bullet;
    public Bird1() {
        x = 600;
        y = 320;
        try {
            img = ImageIO.read(new File("bat3.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void physics() {
        x-=vx;
        vx=7;
 
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
