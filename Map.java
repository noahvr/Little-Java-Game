package field;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamTokenizer;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

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
		for(int y1=0;y1<height;y1++) {
			for(int x1=0;x1<width;x1++) {
				getMap()[y1][x1].transform(x, y);
			}
		}
	}
	//
	public Map() {
		
	}
	public Map(String file) throws IOException, ClassNotFoundException {
		Object o;
		
		BufferedImage image=new BufferedImage(25,25,BufferedImage.TYPE_INT_ARGB);
		BufferedImage emptyimage=new BufferedImage(25,25,BufferedImage.TYPE_INT_ARGB);
		FileInputStream fin=new FileInputStream(file);
		ObjectInputStream oin=new ObjectInputStream(fin);
		int[]pixels =new int[25*25];
		int[]emptypixels=new int [25*25];
		width=oin.readInt();
		height=oin.readInt();
		
		map=new Tile[height][width];
		
		for (int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				map[i][j]=new Tile(j,i,j*Tile.TILESIZE,i*Tile.TILESIZE);
			}
		}
		for(int y=0;y<width;y++) {
			for(int x=0;x<height;x++) {
				o=oin.readObject();
				for(int i=0;i<((int[])o).length;i++){
					System.out.println(((int[])o)[i]);
				}
				System.out.println("------------");
				pixels=((int[])o).clone();
				image.setRGB(0, 0, 25, 25, pixels, 0,25);
				getMap()[y][x].setImage(image, "aaa");
				
				pixels=emptypixels.clone();
				
				image.setRGB(0, 0, 25, 25, emptypixels, 0, 25);
			}
		}
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
	public void render(Graphics g) {
		for(int i=0;i<height;i++) {
			for(int z=0;z<width;z++) {
				map[i][z].render(g, map[i][z].getX(), map[i][z].getY());
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
	
	public void load(String file) throws IOException, ClassNotFoundException {

		Object o;
		
		BufferedImage image=new BufferedImage(25,25,BufferedImage.TYPE_INT_ARGB);
		BufferedImage emptyimage=new BufferedImage(25,25,BufferedImage.TYPE_INT_ARGB);
		FileInputStream fin=new FileInputStream(file);
		ObjectInputStream oin=new ObjectInputStream(fin);
		int[]pixels =new int[25*25];
		int[]emptypixels=new int [25*25];
		for(int y=0;y<11;y++) {
			for(int x=0;x<11;x++) {
				o=oin.readObject();
				for(int i=0;i<((int[])o).length;i++){
					System.out.println(((int[])o)[i]);
				}
				System.out.println("------------");
				pixels=((int[])o).clone();
				image.setRGB(0, 0, 25, 25, pixels, 0,25);
				getMap()[y][x].setImage(image, "aaa");
				
				pixels=emptypixels.clone();
				
				image.setRGB(0, 0, 25, 25, emptypixels, 0, 25);
			}
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
