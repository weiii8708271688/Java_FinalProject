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

/** ��
 *
 * @author User
 */
public class bufff {
    public float x,x1, y, vx, vy;
    public boolean po=false;
    public static final int RAD = 25;
    public Image img;
    public Image bullet;
    public bufff() {
        x = 600;
        y = 320;
        try {
            img = ImageIO.read(new File("bbbb.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void physics() {
        x-=vx;
        vx=4;
 
    }
    public void physics1(Graphics g){


    }
    public void update(Graphics g) {
        g.setColor(Color.BLACK);
        if(po){
            g.drawImage(img, Math.round(x-RAD),Math.round(y-RAD),2*RAD,2*RAD, null);  
        }
       if(x<0){
        x = 600;
        y=(int)(Math.random()*540)+200;
        }
         
    }

}
