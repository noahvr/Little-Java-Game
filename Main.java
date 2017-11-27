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
				
		setBounds(0,0,275,275);
		
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
				rofl[o][i]=new Tile(o,i,o*25,i*25);
			}
		}
		
		Map map = new Map(11,11,rofl);
		MC mc = new MC(125,125,map);
		Models m = new Models();
		m.loadAll();

		pack();
		int key;
		long timer=System.nanoTime();
		while(!pause) {
			do {
				do {
					Graphics g = buffer.getDrawGraphics();
					g.setColor(Color.WHITE);
					g.fillRect(0, 0, getWidth(), getHeight());
					
						//if no input
							//Movement code
						
						//forced movement
					key=0;
						for (int z=3;z>-1;z--) {
							if(ican[z]!=0) {
								key=ican[z];
								break;
							}
						}
						if(key!=0&&map.navigate(timer)) {
							if(key==1&&map.nextTile(1, mc).isEmpty()==true) {
								map.transform(0, 25);
								mc.setTile(map.nextTile(1, mc));
							}
							if(key==2&&map.nextTile(2, mc).isEmpty()==true) {
								map.transform(25, 0);
								mc.setTile(map.nextTile(2, mc));
							}
							if(key==3&&map.nextTile(3, mc).isEmpty()==true) {
								map.transform(0, -25);
								mc.setTile(map.nextTile(3, mc));
							}
							if(key==4&&map.nextTile(4, mc).isEmpty()==true) {
								map.transform(-25, 0);
								mc.setTile(map.nextTile(4, mc));
							}
							timer=System.nanoTime();
						}
							

							map.render(g,m);

							mc.render(g,ican,w,a,s,d,map);
							System.out.println(mc.getTile().getXLoc()+" "+mc.getTile().getYLoc());

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
