package field;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Arrays;

import javax.swing.JFrame;

public class Main extends JFrame implements Runnable{
	private Models models = new Models();
	private Canvas canvas=new Canvas();
	private boolean pause;
	private BufferStrategy buffer;
	private char dir;
	private char nextDir;
	private boolean stationary=true;
	boolean keyDown=false;
	private boolean switchSprite=true;
	long keyTime=0;
	Set<Key> keysDown;
	Key w,a,s,d;
	int[] ican;
	int[] forcedmove;
	public Main() {
		w=new Key(0,'w');
		a=new Key(0,'a');
		s=new Key(0,'s');
		d=new Key(0,'d');
	
	}
	public void run() {
		try {
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//first thing of the game
	public void start() throws IOException {
		initialize();
		input();
	}
	//load the initial variables
	public void initialize() throws IOException {
		models.loadAll();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
				
		setBounds(0,0,500,500);
		
		setLocationRelativeTo(null);
		
		add(canvas);
		setVisible(true);
		canvas.createBufferStrategy(3);
		buffer=canvas.getBufferStrategy();
		
		//KEY PRESSES DOING THINGS
		
		

		ican = new int[4];
		
		 KeyListener key = new KeyAdapter() {
			 
			
			 private int meme=-1;
			 
			 public void keyPressed(KeyEvent arg) {
				 //chain together movement commands
//				 System.out.println("ITS "+keyTime+"Hmmmmm");
				 
				 keyTime=System.nanoTime();
				 keyDown=true;
				 //System.out.println(arg.getKeyChar()+" KEY IS DOWN");
				 if(arg.getKeyChar()=='w'&&w.getPriority()==0) {
					 meme++;ican[meme]=1;w.setPriority(1);
				 }
				 if(arg.getKeyChar()=='a'&&a.getPriority()==0) {
					 meme++;ican[meme]=2;a.setPriority(1);
				 }
				 if(arg.getKeyChar()=='s'&&s.getPriority()==0) {
					 meme++;ican[meme]=3;s.setPriority(1);
				 }
				 if(arg.getKeyChar()=='d'&&d.getPriority()==0) {
					 meme++;ican[meme]=4;d.setPriority(1); 
					 
				 }


			 }
				public void keyTyped(KeyEvent arg) {
					//System.out.println(arg.getKeyChar());
					dir=arg.getKeyChar();
					if (arg.getKeyChar()=='p')pause=!pause;		
					
				}
				public void keyReleased(KeyEvent arg) {
						keyDown=false;
						if(arg.getKeyChar()=='w') {
							w.setPriority(0);
							for (int i=0;i<4;i++) {
								if(ican[i]==1) {
									try{ican[i]=ican[i+1];ican[i+1]=1;}
									catch(Exception e) {ican[i]=0;}
								}
							}
							meme--;
						}
						if(arg.getKeyChar()=='a') {
							a.setPriority(0);
							for (int i=0;i<4;i++) {
								if(ican[i]==2) {
									try{ican[i]=ican[i+1];ican[i+1]=2;}
									catch(Exception e) {ican[i]=0;}
								}
							}
							meme--;
						}
						if(arg.getKeyChar()=='s') {
							s.setPriority(0);
							for (int i=0;i<4;i++) {
								if(ican[i]==3) {
									try{ican[i]=ican[i+1];ican[i+1]=3;}
									catch(Exception e) {ican[i]=0;}
								}
							}
							meme--;
						}
						if(arg.getKeyChar()=='d') {
							d.setPriority(0);
							for (int i=0;i<4;i++) {
								if(ican[i]==4) {
									try{ican[i]=ican[i+1];ican[i+1]=4;}
									catch(Exception e) {ican[i]=0;}
								}
							}
							meme--;
						}
						
						
				}
			};
		canvas.addKeyListener(key);
	}
	
	public void input() throws IOException {
		MC mc = new MC(222,222);
		//fix the screen not having the right amount of pixels
		canvas.setPreferredSize(getSize());
		forcedmove=new int[4];
		
		
		while(!pause) {
			do {
				do {
					Graphics g = buffer.getDrawGraphics();
					g.setColor(Color.WHITE);
					g.fillRect(0, 0, getWidth(), getHeight());
					pack();
						//if no input

							
							mc.render(g,ican,w,a,s,d);
							
						
						
							//if no buttons are pressed or the player is trying to go in a different direction and we have gone the minimum distance stop
							
						
						
					
//					System.out.println(stationary);
					//if moving do this

				}while(buffer.contentsRestored());
				
				buffer.show();
				
			}while(buffer.contentsLost());
		}
	}
	
	//change variables
	
	//draw to screen
	
	public static void main(String[] args) throws IOException {

		(new Thread(new Main())).start();
		//(new Thread(new )).start();
		}
}
