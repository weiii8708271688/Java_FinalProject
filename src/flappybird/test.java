package flappybird;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.event.*;

public class test extends JFrame{
	private boolean a1=false,b1;
	private int count=0;
	public void start() {
		JFrame f=new JFrame("Button Example");
		final JButton b=new JButton("Game1");
		b.setBounds(0, 0, 200, 200);
		b.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			FlappyBird g1=new FlappyBird();
			g1.go();
			b.setEnabled(false);
		 	a1=g1.win();
			 if(g1.getScore()>2000){
				 a1=true;
			 }
			 count++;
			
		}
		});
		final JButton a=new JButton("Game2");
		a.setBounds(200, 0, 200, 200);
		a.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			Game g2 =new Game();
			g2.go();
			a.setEnabled(false);
			b1=g2.win;
			count++;
			
		}
		});
		JButton c=new JButton("finish");
		c.setBounds(0, 200, 400, 200);
		c.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(count==2){
					if(a1==false||b1==false){
						JOptionPane.showMessageDialog(null, "Game Over You Lose! Next time Bro!","Game Over!", JOptionPane.WARNING_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "You Win! Great Job Bro!","Game Over!", JOptionPane.WARNING_MESSAGE);
					}
					System.exit(0);
				}
			}
		});
		
		f.add(b);f.add(a);f.add(c);
		f.setSize(400, 400);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}	
	
}
