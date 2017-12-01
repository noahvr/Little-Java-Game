package field;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tile {
	private int x;
	private int y;
	private int absx;
	private int absy;

	private int width=25;
	private int height=25;
	private BufferedImage image;
	private BufferedImage newImage= new BufferedImage(width,height,1);
	private boolean isEmpty;

	public static final int TILESIZE=25;
	
	public Tile() {
		this(0,0);
	}
	public Tile(int x, int y) {
		this.x=x;
		this.y=y;
		absx=x;
		absy=y;

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
		
	}
	
	

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}


	
	public void render(Graphics g,int x, int y, Models m) {
		g.drawImage(newImage, x, y, null);
	
	}
	public void setImage(BufferedImage image) {
		this.image=image;
			for(int i=0;i<height;i++) {
				for(int j=0;j<width;j++){
					newImage.setRGB(j, i, image.getRGB(j, i));
			}
		}
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
}
