package flappybird;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class menu extends JFrame{
	
	public static void main(String[] args) {
	
		
		JFrame f=new JFrame("Game Manu");
		f.setSize(840, 842);
	
		f.setLocation(200, 50);

		String path = "dd.jpeg";

		ImageIcon background = new ImageIcon(path);

		JLabel label = new JLabel(background);

		label.setBounds(0, 0, f.getWidth(), f.getHeight());
		JPanel imagePanel = (JPanel) f.getContentPane();
		imagePanel.setOpaque(false);
		f.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));	
		
		
		JLabel label0=new JLabel();
		label0.setText("Do you want to play a game?");
		label0.setFont(new Font("Bold",1,34));
		label0.setForeground(Color.white);
		label0.setBounds(25, 650, 550, 50);
		
		JButton no=new JButton("NO!");
		no.setBounds(600, 650, 100, 50);
		no.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			System.exit(0);	
				
		};
		});
		
		JButton yes=new JButton("YES!");
		yes.setBounds(500, 650, 100, 50);
		yes.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
				FlappyBird go=new FlappyBird();
				go.go();
		};
		});
		
			
		
		f.add(yes);f.add(label0);f.add(no);
		f.setSize(750,750);
		f.setLayout(null);f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}	
}
