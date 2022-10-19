package flappybird;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import javax.imageio.ImageIO;
import java.util.TimerTask;
import java.util.Arrays;
import java.util.Vector;

class Game extends JFrame implements MouseListener{
	boolean win=false;
	private int width=400,height=450,mapRow=9,mapCol=9; 
	private JButton button[][]=new JButton[width][height]; 
	private int bombCount=10; 
	private JLabel bombnumber=new JLabel("??????稿????革??"+bombCount);

	private int map[][]=new int[mapRow][mapCol]; 
	private boolean buttonIsPress[][]=new boolean[mapRow][mapCol]; 
	private int mapAroundBomb[][]=new int[mapRow][mapCol]; 
	private int direct[][]={{0,0},{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}}; 
	
	private int timeCount=0; 
	private int timeContinue=1; 
	
	public void go(){

		setSize(width, height); 
		setResizable(false); 
		dispose(); 
		setTitle("Minesweeper"); 
		setLocationRelativeTo(this); 
		
		JPanel topPanel=new JPanel();
		
		bombnumber.setText("ヘ玡紆计"+bombCount); 
		topPanel.add(bombnumber);

		
		add(topPanel,BorderLayout.NORTH);

	
		JPanel centerButtonPanel = new JPanel();
        centerButtonPanel.setLayout(new GridLayout(mapRow,mapCol));
        for(int i=0;i<mapRow;i++){
        	for(int j=0;j<mapCol;j++){
        		button[i][j]=new JButton();
        		button[i][j].setBackground(Color.WHITE); 
        		button[i][j].setActionCommand(i+" "+j);
        		button[i][j].addMouseListener(this); 
        		centerButtonPanel.add(button[i][j]);
        	}
        }
        add(centerButtonPanel,BorderLayout.CENTER);
        

        setMap();
        aroundBomb();
        
        setVisible(true);
	}
	

	private void setMap(){
		int count=0;
		while(count!=10){
			int x=(int)(Math.random()*9),y=(int)(Math.random()*9); 
			if(map[x][y]==0){
				map[x][y]=1;
				count++;
			}
		}
	}
	

	private void aroundBomb(){
		for(int i=0;i<mapRow;i++){
			for(int j=0;j<mapCol;j++){
				if(map[i][j]==1){
					mapAroundBomb[i][j]=-1; 
				}else{
					for(int k=0;k<direct.length;k++){
						int row=i+direct[k][0],col=j+direct[k][1];
						if((row>=0 && row<mapCol && col>=0 && col<mapCol) && map[row][col]==1) mapAroundBomb[i][j]++;
					}
				}
			}
		}
	}
	


	@Override
	public void mouseClicked(MouseEvent e){
		String command[]=((JButton)e.getSource()).getActionCommand().split(" ");
		if(command[0].equals("r")){
		
			
			win=true;
			dispose();
		}else{
			int row=Integer.parseInt(command[0]),col=Integer.parseInt(command[1]);
			if(e.getButton()==MouseEvent.BUTTON1){
				if(map[row][col]==1 && !buttonIsPress[row][col]){
		
					
					button[row][col].setBackground(Color.RED); 
					for(int i=0;i<mapRow;i++)for(int j=0;j<mapCol;j++) if(map[i][j]==1) button[i][j].setText("*"); 
					timeContinue=0; 
					JOptionPane.showMessageDialog(null, "津筽"); 
					 dispose();
				}else{
					if(mapAroundBomb[row][col]==0 && !buttonIsPress[row][col]){

						
						Vector<postion> vector=new Vector<postion>(); 
						vector.add(new postion(row,col));

						for(int i=0;i<vector.size();i++){
							for(int j=0;j<direct.length;j++){
								int tempRow=direct[j][0]+vector.get(i).getRow(),tempCol=direct[j][1]+vector.get(i).getCol();
								if((tempRow>=0 && tempRow<mapRow) && (tempCol>=0 && tempCol<mapCol) && mapAroundBomb[tempRow][tempCol]==0){
									boolean flag=false;
									for(int k=0;k<vector.size();k++){
										if(tempRow==vector.get(k).getRow() && tempCol==vector.get(k).getCol()){
											flag=true;
											break;
										}
									}
									if(!flag) vector.add(new postion(tempRow,tempCol)); 
								}
							}
						}
		
						for(int i=0;i<vector.size();i++){
							for(int j=0;j<direct.length;j++){
								int tempRow=direct[j][0]+vector.get(i).getRow(),tempCol=direct[j][1]+vector.get(i).getCol();
								if((tempRow>=0 && tempRow<mapRow) && (tempCol>=0 && tempCol<mapCol)){

									if(mapAroundBomb[tempRow][tempCol]!=0) 
										button[tempRow][tempCol].setText(Integer.toString(mapAroundBomb[tempRow][tempCol]));
									button[tempRow][tempCol].setBackground(Color.GRAY); 
									buttonIsPress[tempRow][tempCol]=true; 
								}
							}
						}
					}else if(!buttonIsPress[row][col]){

						
						button[row][col].setText(Integer.toString(mapAroundBomb[row][col])); 
						button[row][col].setBackground(Color.GRAY); 
						buttonIsPress[row][col]=true; 
					}
				}
			}else if(buttonIsPress[row][col] && e.getButton()==MouseEvent.BUTTON2){
				
				
				buttonIsPress[row][col]=false; 
				button[row][col].setBackground(Color.WHITE); 
				bombCount++; 
				bombnumber.setText("ヘ玡紆计"+bombCount); 
			}else if(e.getButton()==MouseEvent.BUTTON3 && !buttonIsPress[row][col]){
			
				((JButton)e.getSource()).setBackground(Color.GREEN); 
				buttonIsPress[row][col]=true; 
				bombCount--; 
				bombnumber.setText("ヘ玡紆计"+bombCount);
				

				if(bombCount==0){
					boolean endGame=true;

					for(int i=0;i<mapRow;i++){
						for(int j=0;j<mapCol;j++){
							if(map[i][j]==1)if(buttonIsPress[i][j]!=true) endGame=false;
						}
					}
					if(endGame){
						timeContinue=0; 
						JOptionPane.showMessageDialog(null, "尺瘆闽"); 
						win=true;
						dispose(); 
					}
				}
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {

	}
	@Override
	public void mouseExited(MouseEvent e) {

	}
	@Override
	public void mousePressed(MouseEvent e) {

	}
	@Override
	public void mouseReleased(MouseEvent e) {

	}
}
class postion{
	private int row,col;
	postion(int row,int col){
		this.row=row;
		this.col=col;
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
}
