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
	
	
	
	
	
}
