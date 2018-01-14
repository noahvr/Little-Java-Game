package field;


import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
	public BufferedImage pokemonLogo;
	
	
	public Models() {
		
	}
	
	public void loadAll() throws IOException{
		logo = ImageIO.read(Models.class.getResource("ent.bmp"));
		
		BufferedImage vikings = ImageIO.read(Models.class.getResource("viking.bmp"));
		viking=new BufferedImage[8];
		for(int i=0;i<8;i++) {
			viking[i]=clip(vikings,15*i,0,15,15);
		}	
		pokemon=ImageIO.read(Models.class.getResource("pokemon.png"));
		
		mcIdle=new BufferedImage[4];
		for(int x=0;x<4;x++) {
			mcIdle[x]=clip(pokemon,218+25*x,173,25,25);
		}
		mcUp=new BufferedImage[2];
		for(int x=0;x<2;x++) {
			mcUp[x]=clip(pokemon,392,173+32*x,25,32);
		}
		mcDown=new BufferedImage[2];
		for(int x=0;x<2;x++) {
			mcDown[x]=clip(pokemon,317,173+32*x,25,32);
		}
		mcRight=new BufferedImage[2];
		for(int x=0;x<2;x++) {
			mcRight[x]=clip(pokemon,367 ,173+32*x,25,32);
		}
		mcLeft=new BufferedImage[2];
		for(int x=0;x<2;x++) {
			mcLeft[x]=clip(pokemon,342,173+32*x,25,32);
		}
		
		tileset=ImageIO.read(Models.class.getResource("tiles-small.png"));
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
		
		pokemonLogo=ImageIO.read(Models.class.getResource("pokemon_logo_2.png"));
		
	}
	
	
	public BufferedImage clip(BufferedImage src, int x, int y, int w, int h) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		BufferedImage newImage = null;
		
		try {
			GraphicsDevice screen = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc= screen.getDefaultConfiguration();
			newImage = gc.createCompatibleImage(w, h, BufferedImage.TYPE_INT_ARGB);			
		}
		catch(Exception e){}
		if(newImage==null) {
			newImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
		}
		newImage=src.getSubimage(x, y, w, h);
		return newImage;
	}
}
