/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;




/**
 *
 * @author User
 */
public class FlappyBird implements ActionListener, KeyListener {
    public Boolean aa;

    public static final  int  WIDTH = 640, HEIGHT = 480;
    private int FPS=60,rec;
    private Bird bird;
    private Bird1 bird1;
    private bird2 Bird2;
    private shoot shoot;
    private JFrame frame;
    private JPanel panel;
    private bufff bufff;
    private ArrayList<Rectangle> rects;
    private int time;
    private Timer t;
    private boolean game = true;
    private boolean paused;
    private double mode=0.001,scroll;
    private int item=0,timer1=0;
    private boolean star=true;
    public boolean dec1=false;
    public float c;
    public static int tt;
    
    public void go() {
        
        frame = new JFrame("Flappy Bird");

        bufff= new bufff();
        shoot = new shoot();
        bird = new Bird();
        bird1=new Bird1();
        Bird2 = new bird2();
        rects = new ArrayList<Rectangle>();
        panel = new GamePanel(this, bird, rects,bird1,shoot,bufff,Bird2);
        
        frame.addKeyListener(this);

        frame.add(panel);
        frame.setSize(WIDTH, HEIGHT);
        frame.dispose();
        frame.setVisible(true);
        frame.addKeyListener(this);
        
        paused = true;
        
        t = new Timer(100/FPS, this);
        t.start();

    }
    


    @Override
    public void actionPerformed(ActionEvent e) {
        panel.repaint();
        
        if(!paused) {
            bird.physics();
            bird1.physics();
            bufff.physics();
            shoot.physics();
            shoot.reload=true;
            
            Bird2.physics();


            if(scroll % 70== 0) {
          
                Rectangle r = new Rectangle(WIDTH, 0, GamePanel.PIPE_W, (int) ((Math.random()*HEIGHT)/7 + (0.27)*HEIGHT));
                int h2 = (int) ((Math.random()*HEIGHT)/5 + (0.2)*HEIGHT);
                Rectangle r2 = new Rectangle(WIDTH, HEIGHT - h2, GamePanel.PIPE_W, h2);
                rects.add(r);
                rects.add(r2);

  
              
            }
            
            ArrayList<Rectangle> toRemove = new ArrayList<Rectangle>();
            for(Rectangle r : rects) {
                r.x-=3;
                if(r.x + r.width <= 0) {
                    toRemove.add(r);
                }
                if(r.contains(bird.x, bird.y)&&star) {
                    JOptionPane.showMessageDialog(frame, "You lose!\n"+"Your score was: "+time+".");
                    game = false;                 
                    frame.dispose();
                }
            }
            if(bird.x>(bird1.x-bird1.RAD)&&bird.x<bird1.x&&bird.y<bird1.y+(bird1.RAD)&&bird.y>bird1.y-(bird1.RAD)&&star&&bird1.dec){
                JOptionPane.showMessageDialog(frame, "You lose!\n"+"Your score was: "+time+".");
                    game = false;                 
                    frame.dispose();
            }
            if(!star&&bird.x>(bird1.x-bird1.RAD)&&bird.y<bird1.y+(bird1.RAD)&&bird.y>bird1.y-(bird1.RAD)){
                bird1.dec=false;
            }


            if(shoot.x>(bird1.x-bird1.RAD)&&shoot.x<bird1.x&&shoot.y<bird1.y+(bird1.RAD)&&shoot.y>bird1.y-(bird1.RAD)&&bird1.dec){
                shoot.x=641;
                
                bird1.dec=false;
            }

            if(bird.x>(bufff.x-bufff.RAD)&&bird.y<bufff.y+(bufff.RAD)&&bird.y>bufff.y-(bufff.RAD)&&bufff.po){
                shoot.vx=18;
                bufff.po=false;
                rec=getScore();
                item++;
            }
            
            //------------------------------------bird2

            if(bird.x>(Bird2.x-Bird2.RAD)&&bird.x<Bird2.x&&bird.y<Bird2.y+(Bird2.RAD)&&bird.y>Bird2.y-(Bird2.RAD)&&star&&Bird2.dec){
                JOptionPane.showMessageDialog(frame, "You lose!\n"+"Your score was: "+time+".");
                    game = false;                 
                    frame.dispose();
            }
            if(!star&&bird.x>(Bird2.x-Bird2.RAD)&&bird.y<Bird2.y+(Bird2.RAD)&&bird.y>Bird2.y-(Bird2.RAD)){
                Bird2.dec=false;
            }


            if(shoot.x>(Bird2.x-Bird2.RAD)&&shoot.x<Bird2.x&&shoot.y<Bird2.y+(Bird2.RAD)&&shoot.y>Bird2.y-(Bird2.RAD)&&Bird2.dec){
                shoot.x=641;
                Bird2.life--;
                Bird2.RAD += 10;
                if(Bird2.life == 0) {
                    Bird2.dec=false;
                    Bird2.life = 5;
                    Bird2.RAD = 50;
                }
            }



            if(getScore()-rec>200&&bufff.x<0){
                shoot.vx=5;
                bufff.po=true;

            }
            

            if(time>2500){
                JOptionPane.showMessageDialog(frame, "You win");
                game = false;       
                frame.dispose();
            }
            rects.removeAll(toRemove);
            time++;
            bird.vy+=0.001;
            if(timer1>0){
                timer1--;
                if(timer1==1){
                    star=true;
                    bird.setimg(1);
                }
            }
         
            if(time>750){
                scroll+=2;
                bird1.x-=4;
               
            }
            else{
                scroll++;
            }
  

            if(bird.y > HEIGHT || bird.y+bird.RAD < 0) {
                JOptionPane.showMessageDialog(frame, "You lose!\n"+"Your score was: "+time+".");
                 game = false;        
                frame.dispose();
            }

            if(!game) {
                rects.clear();
                bird.reset();
                time = 0;
                scroll = 0;
                paused = true;
            }
        }
    }
    public int getutem(){
        return item;
    }
    
    public int getScore() {
        return time;
    }

    
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP) {
            bird.jump();
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
            paused = false;
        }
        else if(e.getKeyCode()==KeyEvent.VK_1){
            if(item>0){
            item--;
            star=false;
            timer1=180;
            bird.setimg(0);
            }
        }

        
    }
    public void keyReleased(KeyEvent e) {
    
    }
    public void keyTyped(KeyEvent e) {
        
    }
    
    public boolean paused() {
        return paused;
    }
    public boolean win(){
        return game;
    }
}
