package field;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tile {
	private int x;
	private int y;

	static int xmeme;
	static int ymeme;
	private int width=25;
	private int height=25;
	private BufferedImage image;
	private BufferedImage newImage= new BufferedImage(width,height,1);
	private boolean isEmpty;
	private int xLoc;
	private int yLoc;
	
	public Tile() {
		this(0,0,0,0);
	}
	public Tile(int xLoc, int yLoc, int x, int y) {
		this.x=x;
		this.y=y;
		this.xLoc=xLoc;
		this.yLoc=yLoc;
		width=25;
		height=25;
		
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
		return y+ymeme;
	}

	public int getX() {
		return x+xmeme;
	}

	
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
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
	public int getXLoc() {
		return xLoc;
	}
	
	public int getYLoc(){
		return yLoc;
	}
}
