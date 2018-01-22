package field;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class Event implements Serializable{

	public abstract void runEvent(Graphics g, Map map, MC mc, int [] keys,int anim, TextCode c,boolean inputblock,boolean fastText);
	public abstract boolean asleep();
	public abstract void ifAsleep();
	public abstract boolean isDone();
}
