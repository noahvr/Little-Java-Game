package field;

import java.awt.Graphics;

public class Map {
	private int width;
	private int height;
	private Tile[][] map;
	private int x;
	private int y;
	
	private boolean navigate;
	//Class Methods
	
	//Instance Methods
	public void transform(int x, int y) {
		this.x+=x;
		this.y+=y;
	}
	//
	public Map(int width, int height, Tile[][] map) {
		this.width=width;
		this.height=height;
		//top to bottom, left to right
		this.map=new Tile[height][width];

		this.map=map.clone();
		navigate = true;
	}
	
	public void lolzer() {
		for(int i=0;i<height;i++) {
		for(int x=0;x<width;x++) {
			
		}
	}
	}
	
	public Tile[][] getMap(){
		return map;
	}
	public void render(Graphics g, Models m) {
		for(int i=0;i<height;i++) {
			for(int z=0;z<width;z++) {
				map[i][z].render(g, z*25+x, i*25+y, m);
			}
		}
	}
	
	public boolean navigate(long last) {
		if(System.nanoTime()-last<400000000)return false;
		else if(System.nanoTime()-last>=400000000)return true;
		else return false;
	}
	
	public Tile nextTile(int dir, MC mc) {
		
		if(dir==1)return this.getMap()[mc.getTile().getXLoc()][mc.getTile().getYLoc()-1];
		if(dir==2)return this.getMap()[mc.getTile().getXLoc()-1][mc.getTile().getYLoc()];
		if(dir==3)return this.getMap()[mc.getTile().getXLoc()][mc.getTile().getYLoc()+1];
		if(dir==4)return this.getMap()[mc.getTile().getXLoc()+1][mc.getTile().getYLoc()];
		else return null;
	}
}
