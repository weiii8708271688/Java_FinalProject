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
public class Bird {
    public static float x, y, vx, vy, bx, by;
    public static final int RAD = 25;
    private Image img;
    public Bird() {
        x = FlappyBird.WIDTH/2;
        y = FlappyBird.HEIGHT/2;
        try {
            img = ImageIO.read(new File("devil.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void physics() {
        x+=vx;
        y+=vy;
        vy+=0.75;
    }
    public void physics1(){
        
    }
    public void update(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawImage(img, Math.round(x-RAD),Math.round(y-RAD),2*RAD,2*RAD, null);
    }
    public void jump() {
        vy = -8;
    }
    
    public void reset() {
        x = 640/2;
        y = 640/2;
        vx = vy = 0;
    }


    public void setimg(int k) {
        if(k == 1) {
            try {
                img = ImageIO.read(new File("devil.png"));
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                img = ImageIO.read(new File("devil_goldon.png"));
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
