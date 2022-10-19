package flappybird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class shoot {
    public static float x, y, vx=8, vy;

    public boolean reload;
    public static final int RAD = 25;
    private Image img;
    public shoot() {
        x = FlappyBird.WIDTH/2+20;
        y = FlappyBird.HEIGHT/2+20;
        try {
            img = ImageIO.read(new File("bullet.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void physics() {
        x+=vx;
        
    }
    public void physics1(){
        
    }
    public void update(Graphics g) {
        g.setColor(Color.BLACK);
        reload=false;
        g.drawImage(img, Math.round(x-RAD),Math.round(y-RAD),2*RAD,2*RAD, null);
        if(x>640){
            x = Bird.x+20;
            y = Bird.y+20;
        }
    }
    public void jump() {
        vy = -8;
    }
    
    public void reset() {
        x = 640/2;
        y = 640/2;
        vx = vy = 0;
    }
}
