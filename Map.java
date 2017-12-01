package field;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StreamTokenizer;

import javax.imageio.ImageIO;

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
		for(int y1=0;y1<11;y1++) {
			for(int x1=0;x1<11;x1++) {
				getMap()[y1][x1].transform(x, y);
			}
		}
	}
	//
	public Map() {
		
	}
	public Map(File f) {
		
	}
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
				map[i][z].render(g, map[i][z].getX(), map[i][z].getY(), m);
			}
		}
	}
	
	public int seconds=1;
	public int milliseconds=seconds*1000;
	public int nanoseconds=milliseconds*1000000;
	
	public boolean navigate(long last) {
		if(System.nanoTime()-last<.5*nanoseconds)return false;
		else if(System.nanoTime()-last>=.5*nanoseconds)return true;
		else return false;
	}

	public Tile nextTile(int dir, MC mc) {
		try {
			if(dir==1)return this.getMap()[mc.getTile().getcoords()[1]-1][mc.getTile().getcoords()[0]];
			if(dir==2)return this.getMap()[mc.getTile().getcoords()[1]][mc.getTile().getcoords()[0]-1];
			if(dir==3)return this.getMap()[mc.getTile().getcoords()[1]+1][mc.getTile().getcoords()[0]];
			if(dir==4)return this.getMap()[mc.getTile().getcoords()[1]][mc.getTile().getcoords()[0]+1];
		}
		catch(Exception e) {return null;}
		return null;
	}
	
	public void edit() throws IOException {
		String value="hi";
		FileOutputStream fos=new FileOutputStream("res/meme.txt");
		DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
		outStream.writeUTF(value);
		outStream.close();
	}
	
	public void load(File f) {

		try {
			
			StreamTokenizer st=new StreamTokenizer(new FileInputStream(f));
			st.whitespaceChars(';',';');
			BufferedImage b;
			int token;
			while((token=st.nextToken())!=st.TT_EOF) {
			for(int y=0;y<Math.sqrt(getMap().length);y++) {
				for(int x=0;x<Math.sqrt(getMap().length);x++) {
					b=ImageIO.read((getClass().getResource(st.sval)));
					getMap()[y][x].setImage(b);
				}
			}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void place(Entity e, Tile t) {
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
