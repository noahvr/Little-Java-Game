package field;

public class Tile {
	private int x;
	private int y;
	private int xLoc;
	private int yLoc;
	private boolean occupied;
	private boolean destination;
	
	public Tile(int x, int y) {
		this.x=x;
		this.y=y;
		destination=false;
		occupied=false;
		xLoc=x*40;
		yLoc=y*40;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}
	public void setOccupied(boolean occupied) {
		this.occupied=occupied;
	}
	public boolean isOccupied() {
		return occupied;
		
	}
	
	public void setDestination(boolean destination) {
		this.destination=destination;
	}
	public boolean isDestination() {
		return destination;
	}
	
}
