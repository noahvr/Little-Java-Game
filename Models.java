package field;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Models {
	public BufferedImage logo;
	public BufferedImage[] viking;

	public BufferedImage pokemon;
	public BufferedImage[]mcIdle;
	public BufferedImage[]mcUp;
	public BufferedImage[]mcDown;
	public BufferedImage[]mcLeft;
	public BufferedImage[]mcRight;
	public static BufferedImage[][]tiles;
	public BufferedImage tileset;
	public BufferedImage iconset;
	public BufferedImage [][]icons;
	
	
	public Models() {
		
	}
	
	public void loadAll() throws IOException{
		logo = ImageIO.read(Models.class.getResource("ent.bmp"));
		
		BufferedImage vikings = ImageIO.read(Models.class.getResource("viking.bmp"));
		viking=new BufferedImage[8];
		for(int i=0;i<8;i++) {
			viking[i]=clip(vikings,15*i,0,15,15);
		}	
		File pokemonf= new File("images/pokemon.png");
		pokemon=ImageIO.read(pokemonf);
		
		mcIdle=new BufferedImage[4];
		for(int x=0;x<4;x++) {
			mcIdle[x]=clip(pokemon,218+25*x,173,25,25);
		}
		mcUp=new BufferedImage[2];
		for(int x=0;x<2;x++) {
			mcUp[x]=clip(pokemon,393,173+27*x,25,27);
		}
		mcDown=new BufferedImage[2];
		for(int x=0;x<2;x++) {
			mcDown[x]=clip(pokemon,318,173+27*x,25,30);
		}
		mcRight=new BufferedImage[2];
		for(int x=0;x<2;x++) {
			mcRight[x]=clip(pokemon,369,173+27*x,25,27);
		}
		mcLeft=new BufferedImage[2];
		for(int x=0;x<2;x++) {
			mcLeft[x]=clip(pokemon,343,173+25*x,23,25);
		}
		
		File f=new File("src/field/tiles-small.png");
		System.out.println(f.exists());
		tileset=ImageIO.read(f);
		int tilesetH=tileset.getHeight()/Tile.TILESIZE;
		int tilesetW=tileset.getWidth()/Tile.TILESIZE;
		tiles=new BufferedImage[tilesetH][tilesetW];
		for(int y=0;y<tilesetH;y++) {
			for(int x=0;x<tilesetW;x++) {
				tiles[y][x]=clip(tileset,x*Tile.TILESIZE,y*Tile.TILESIZE,Tile.TILESIZE,Tile.TILESIZE);
			}
		}
		
		iconset=ImageIO.read(Models.class.getResource("24squareicon.png"));
		int iconsetH=iconset.getHeight()/34;
		int iconsetW=iconset.getWidth()/34;
		icons=new BufferedImage[iconsetH][iconsetW];
		for(int y=0;y<iconsetH;y++) {
			for(int x=0;x<iconsetW;x++) {
				icons[y][x]=clip(iconset,x*34,y*34,24,24);
			}
		}
		
		
	}
	
	
	public BufferedImage clip(BufferedImage src, int x, int y, int w, int h) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		BufferedImage newImage = null;
		
		try {
			GraphicsDevice screen = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc= screen.getDefaultConfiguration();
			newImage = gc.createCompatibleImage(w, h, Transparency.BITMASK);			
		}
		catch(Exception e){}
		if(newImage==null) {
			newImage = new BufferedImage(w,h,Transparency.BITMASK);
		}
		
		int[]pixels=new int[w*h];
		src.getRGB(x, y, w, h, pixels, 0, w);
		newImage.setRGB(0, 0, w,h,pixels,0,w);
		return newImage;
	}
}
