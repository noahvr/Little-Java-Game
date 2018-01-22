package field;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class Tile {

	private int x;
	private int y;
	private int absx;
	private int absy;
	private final int xLoc;
	private final int yLoc;
	private int width=25;
	private int height=25;
	private BufferedImage image;
	private BufferedImage pastImage;
	private BufferedImage newImage= new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
	private boolean isEmpty;
	private boolean showPastImage=false;
	String imageName;
	public boolean passable;
	private Event e;

	public static final int TILESIZE=25;
	
	public Tile() {
		this(0,0,0,0);
	}
	public Tile(int xLoc, int yLoc,int x, int y) {
		this.x=x;
		this.y=y;
		absx=x;
		absy=y;
		this.xLoc=xLoc;
		this.yLoc=yLoc;
		passable=true;

		width=TILESIZE;
		height=TILESIZE;
		
		isEmpty=true;

		try {
			image = ImageIO.read(Tile.class.getResource("ent.bmp"));
			for(int i=0;i<height;i++) {
				for(int j=0;j<width;j++){
					newImage.setRGB(j, i, image.getRGB(j, i));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pastImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		
	}
	
	

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}


	
	public void render(Graphics g,int x, int y) {
		if(showPastImage)g.drawImage(pastImage, x, y, null);
		g.drawImage(newImage, x, y, null);
	
	}
	public void setImage(BufferedImage image, String imageName) {
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++){
				pastImage.setRGB(j, i, newImage.getRGB(j, i));
				}
			}
		this.image=image;
			for(int i=0;i<height;i++) {
				for(int j=0;j<width;j++){
					newImage.setRGB(j, i, image.getRGB(j, i));
			}
		}
		this.imageName=imageName;
		
	}
	
	public void setImage(BufferedImage image, Boolean opaque) {
		if(!opaque) {
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++){
				pastImage.setRGB(j, i, newImage.getRGB(j, i));
				}
			}
		}
		else {
			pastImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		}
		this.image=image;
			for(int i=0;i<height;i++) {
				for(int j=0;j<width;j++){
					newImage.setRGB(j, i, image.getRGB(j, i));
			}
		}
		
	}
	public BufferedImage getImage() {
		return newImage;
	}
	public String getImageName() {
		return imageName;
	}
	public void transform(int dx, int dy) {
		this.x=x+dx;
		this.y=y+dy;
	}
	public boolean isEmpty() {
		return isEmpty;
	}
	
	public void setEmpty(boolean isEmpty) {
		this.isEmpty=isEmpty;
	}

	public boolean isOn(int x, int y) {
		if(x-this.x<TILESIZE&&x-this.x>=0) {
			if(y-this.y<TILESIZE&&y-this.y>=0)return true;
		}
		return false;
	}
	public int getabsy() {
		return absy;
	}
	public int getabsx() {
		return absx;
	}
	public int[] getcoords() {
		int[]a=new int[2];
		a[0]=xLoc;
		a[1]=yLoc;
		return a;
	}

	
	public void setPassable(boolean passable) {
		this.passable=passable;
	}
	
	
	public void setEvent(Event e) {
		this.e=e;
	}
	public Event getEvent() {
		return e;
	}
}
