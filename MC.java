package field;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class MC extends Entity {
	private int x;
	private int y;
	private int xTile;
	private int yTile;
	private int xTile2;
	private int yTile2;
	private double xu;
	private double xd;
	private double yu;
	private double yd;
	private long curtime;
	private int direction;
	private boolean stationary=true;
	private int pixMoved;
	private BufferedImage[] curSprite=m.mcIdle;
	private int face;
	private int key;
	Key w;
	Key a;
	Key s;
	Key d;
	int inc=0;
	private int xLock=0;
	private int yLock=0;
	private int xLoc;
	private int yLoc;
	private int tilePointerX=0;
	private int tilePointerY=0;
	private Tile tile;
	private int lastFace=1;
	private Tile lastTile;
	private int betweenPix=0;
	private Tile nextTile;
	private int[] betweenPixArray=new int[24];
	private int xMid;
	private int yMid;

	
	public MC() throws IOException{
		
		x=0;
		y=0;
		xu=0;
		xd=0;
		yu=0;
		yd=0;
		direction='q';
		pixMoved=0;
		face=1;
		key=0;
		yMid=0;
		xMid=0;
	}
	public MC(int x, int y, Map map) throws IOException {
		this();
		this.x=x;
		this.y=y;
		setTile(map.spawn);
		System.out.println(tile);
		lastTile=null;
 		xMid=tile.getX();
		yMid=tile.getY();
		face=1;
		
	}
	
	public void render(Graphics g,int[] keys,Map map,int anim) {

		this.face=face;

		boolean input=false;
		key=0;
		//forced movement
		for (int z=3;z>-1;z--) {
			if(keys[z]!=0) {
				key=keys[z];
				input=true;
				break;
			}
		}	
		if(map.lock!=0) {
			face=map.lock;
		}
		//Moving
		if(key!=0||map.lock!=0) {
			faceFindMove(g,anim);
		}
		//Moving
		else {
			faceFindIdle(g,anim);
		}
		//computation(key);
	
		
		
		


	}
	
	public long time() {
		return System.nanoTime();
	}
	
	public void faceFindMove(Graphics g, int anim) {
		direction=face;
		switch(face) {
		case 1:	if(anim%2==0)g.drawImage(m.mcUp[0], xMid,yMid, null);
				else if(anim%2==1) g.drawImage(m.mcUp[1], xMid,yMid, null);
				break;
		case 2:	if(anim%2==0)g.drawImage(m.mcLeft[0], xMid,yMid, null);
				else if(anim%2==1) g.drawImage(m.mcLeft[1], xMid,yMid, null);
				break;
		case 3:	if(anim%2==0)g.drawImage(m.mcDown[0], xMid,yMid, null);
				else if(anim%2==1) g.drawImage(m.mcDown[1], xMid,yMid, null);
				break;
		case 4:	if(anim%2==0)g.drawImage(m.mcRight[0],xMid,yMid, null);
				else if(anim%2==1) g.drawImage(m.mcRight[1],xMid,yMid, null);

				break;
		}	
		//System.out.println("array "+betweenPixArray[1]);	
		
		}
		
		public void faceFindIdle(Graphics g, int anim) {
			direction=face;
			switch(face) {
			case 1:	g.drawImage(m.mcIdle[3],xMid,yMid, null);
					break;
			case 2:	g.drawImage(m.mcIdle[1], xMid,yMid, null);
					break;
			case 3:	g.drawImage(m.mcIdle[0], xMid,yMid, null);
					break;
			case 4:	g.drawImage(m.mcIdle[2], xMid,yMid, null);
					break;
			}	
	}
	public void moveMe(int key, Graphics g) {
		//are you allowed to go where you want?
		
		//if this method was called too recently return
		if(System.nanoTime()-curtime<5000000)return;
		
		
		//otherwise change coordintes and update direction;
		else {
			curtime=System.nanoTime();
			if(key==1&&yLock!=-1) {
				direction=1;pixMoved++;curSprite=m.mcUp;
				
			}
			if(key==2&&xLock!=-1) {
				direction=2;pixMoved++;curSprite=m.mcLeft;
			}
			if(key==3&&yLock!=1) {
				direction=3;pixMoved++;curSprite=m.mcDown;
			}
			if(key==4&&xLock!=1) {
				direction=4;pixMoved++;curSprite=m.mcRight;
			}
			
			
			}	
		
		
		}
	
	public void smoothMove(int x, int y, int x1, int y1) {
		x=x*40;
		y=y*40;
		x1=x1*40;
		y1=y1*40;
		
		if(y>y1) {
			inc++;
			this.y=y-inc;
			if(y==y1)inc=0;
			stationary=false;
		}
		if(y<y1) {
			inc++;
			this.y=y+inc;
			if(y==y1)inc=0;
			stationary=false;
		}
		if(x<x1) {
			inc++;
			this.x=x+inc;
			if(x==x1)inc=0;
			stationary=false;
		}if(x>x1) {
			inc++;
			this.x=x-inc;
			if(x==x1)inc=0;
			stationary=false;
		}
		else return;
	}
	
//	public void turn(char dir,Graphics g){
//		if (!(dir==face)) {
//			face=dir;
//		}
//		if(dir==face) {
//			moveMe(face,g);	
//		}
//	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getDir() {
		return direction;
	}
	
	public boolean stationary() {
		return stationary;
	}
	
	public int moved() {
		return pixMoved;
	}
	
	public void switchSprite(){
		curSprite[0]=curSprite[1];
	}
	
	public void computation(int key) {
		xLock=0;
		yLock=0;
		if(x==0)xLock=-1;
		if(x==450)xLock=1;
		if(y==0)yLock=-1;
		if(y==450)yLock=1;
		
		//compute which tile on
		
		xTile=x/25;
		yTile=y/25;
		
		//compute which tile is next in line
		
		if(key==1)yTile2=yTile-1;
		if(key==2)xTile2=xTile=-1;
		if(key==3)yTile2=yTile+1;
		if(key==4)xTile2=xTile+1;
		
		Tile [][] rofl=new Tile[12][12];
		
		for (int i=0;i<12;i++) {
			for(int o=0;o<12;o++) {
				rofl[o][i]=new Tile(o,i,o*40,i*40);
			}
		}
		
		rofl[0][1].getX();
		//rofl[tilePointerX] [tilePointerY].se	
	}
	
	public void setLocation(int xLoc, int yLoc) {
		this.xLoc=xLoc;
		this.yLoc=yLoc;
	}
	
	public int[] getTileCoords() {
		int[] a=new int[2];
		a[0]=xLoc;
		a[1]=yLoc;
		return a;
	}
	
	public void setTile(Tile tile) {
		lastTile=this.tile;
		this.tile=tile;
		
		xLoc=tile.getcoords()[0];
		yLoc=tile.getcoords()[1];
	}
	public Tile getTile() {
		return tile;
	}
	
	public boolean isFace(int face) {
		if(lastFace==face)return true;
		else{
			lastFace=face;
			return false;
		}
	}
	
	public void setFace(int face) {
		this.face=face;
	}
	
	public Tile getLastTile() {
		return lastTile;
	}


}
