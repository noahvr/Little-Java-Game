package field;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;


import javax.swing.JFrame;

public class Main extends JFrame implements Runnable{
	private Models models = new Models();
	private Canvas canvas=new Canvas();
	private boolean pause;
	private BufferStrategy buffer;
	private boolean keyDown=false;
	long keyTime=0;
	Key w,a,s,d;
	int[] ican;
	int[] forcedmove;
	private int tick=0;
	static final int TILES_ON_SCREEN=11;
	
	int face;
	public Main() {
		w=new Key(0,'w');
		a=new Key(0,'a');
		s=new Key(0,'s');
		d=new Key(0,'d');
		face=1;
		setBounds(0,0,Tile.TILESIZE*TILES_ON_SCREEN,Tile.TILESIZE*TILES_ON_SCREEN);
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
				
		
		
		setLocationRelativeTo(null);
		
		add(canvas);
		setVisible(true);
		setResizable(false);
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
						else if(arg.getKeyChar()=='a') {
							a.setPriority(0);
							for (int i=0;i<4;i++) {
								if(ican[i]==2) {
									try{ican[i]=ican[i+1];ican[i+1]=2;}
									catch(Exception e) {ican[i]=0;}
								}
							}
							meme--;
						}
						else if(arg.getKeyChar()=='s') {
							s.setPriority(0);
							for (int i=0;i<4;i++) {
								if(ican[i]==3) {
									try{ican[i]=ican[i+1];ican[i+1]=3;}
									catch(Exception e) {ican[i]=0;}
								}
							}
							meme--;
						}
						else if(arg.getKeyChar()=='d') {
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
		
		//fix the screen not having the right amount of pixels
		canvas.setPreferredSize(getSize());
		forcedmove=new int[4];
		Tile [][] rofl=new Tile[11][11];
		
		for (int i=0;i<11;i++) {
			for(int o=0;o<11;o++) {
				rofl[o][i]=new Tile(o*Tile.TILESIZE,i*Tile.TILESIZE);
			}
		}
		
		Map map = new Map(11,11,rofl);
		MC mc = new MC(125,125,map);
		Models m = new Models();
		m.loadAll();

		pack();
		
		
		int key=0;
		long timeAtLastUpdate=0;	
		int face=0;
		long uncountedtime=System.nanoTime();
		float highest=0f;
		float lowest=0f;
		while(!pause) {
			do {
				do {
					for (int z=3;z>-1;z--) {
							if(ican[z]!=0) {
								key=ican[z];
								break;
							}
						}
					//TIME BASED 10 ticks per second
					

//					if(toSec(System.nanoTime()-uncountedtime)>highest)highest=(float)toSec(System.nanoTime()-uncountedtime);
//					if(toSec(System.nanoTime()-uncountedtime)<lowest)lowest=(float)toSec(System.nanoTime()-uncountedtime);
//					System.out.println("HIGHEST:/t"+highest);
//					System.out.println("LOWEST:/t"+lowest);
//					uncountedtime=System.nanoTime();
					
					if(System.nanoTime()-timeAtLastUpdate>toNano(.1f)){
						//parses input data
						//moves character
						if(key!=0) {
							if(mc.isFace(face)) {
								if(key==1) {
									map.transform(0, Tile.TILESIZE);
//									mc.setTile(map.nextTile(1, mc));
									}
								if(key==2) {
									map.transform(Tile.TILESIZE, 0);
//									mc.setTile(map.nextTile(2, mc));
								}
								if(key==3) {
									map.transform(0, -Tile.TILESIZE);
//									mc.setTile(map.nextTile(3, mc));
								}
								if(key==4) {
									map.transform(-Tile.TILESIZE, 0);
//									mc.setTile(map.nextTile(4, mc));
								}
								
							}
							else face=key;
								
								
							
						}
						timeAtLastUpdate=System.nanoTime();
						key=0;
						tick++;
						
					}
					//System.out.println(face);
					//Visual code - ALWAYs renders
					Graphics g = buffer.getDrawGraphics();
					g.setColor(Color.WHITE);
					g.fillRect(0, 0, getWidth(), getHeight());
					
					
						
					map.render(g,m);

					mc.render(g,ican,w,a,s,d,map);
//System.out.println(mc.getTile().getXLoc()+" "+mc.getTile().getYLoc());
				}while(buffer.contentsRestored());
				
				buffer.show();
				
			}while(buffer.contentsLost());
		}
	}
	
	public float toNano(float seconds) {
		float millesec=seconds*1000;
		float microsec=millesec*1000;
		float nanosec=microsec*1000;
		return nanosec;
	}
	
	public float toSec(float nano) {
		float micro=nano/1000;
		float milli=micro/1000;
		float sec=milli/1000;
		return sec;
	}
	
	//change variables
	
	//draw to screen
	
	public static void main(String[] args) throws IOException {

		(new Thread(new Main())).start();
		//(new Thread(new )).start();
		}
}
