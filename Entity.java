package field;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
	private int x;
	private int y;
	private BufferedImage sprite;
	private boolean locked;
	
	Models m = new Models();
	int meme;
	
	
	
	public Entity() throws IOException {
		x=y=0;
		sprite=null;
		m.loadAll();
	}
	//draw to screen
	public void render() {
		
	}
	
	public void moveMe(char dire) {

		//RATE OF 10 pixel PER second
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public int[] moveToTile(Tile tile) {
		//goes through all points that are not on the actual tiles
		int[] moves=new int[24];
		if(tile.getX()>this.getX()) {
			for(int i=0;i<moves.length;i++) {
				moves[i]=this.getX()+i+1;
			}
		}
		else if(tile.getX()<this.getX()) {
			for(int i=0;i<moves.length;i++) {
				moves[i]=this.getX()-i-1;
			}
		}
		else if(tile.getY()>this.getY()) {
			for(int i=0;i<moves.length;i++) {
				moves[i]=this.getY()+i+1;
			}
		}
		else if(tile.getY()<this.getY()) {
			for(int i=0;i<moves.length;i++) {
				moves[i]=this.getY()-i-1;
			}
		}
		return moves;
	}
	
	
	
	
}
