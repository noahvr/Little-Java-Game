package field;

import java.awt.Graphics;

public class SignTextEvent extends TextEvent{
	private int signDir;
	public SignTextEvent(TextCode code) {
		super(code);
		signDir=1;
		// TODO Auto-generated constructor stub
	}
	public void runEvent(Graphics g, Map map, MC mc, int[] keys, int anim, TextCode c, boolean inputblock,boolean fastText) {
		if(signDir!=mc.getDir()) {
			return;
		}
		super.runEvent(g, map, mc, keys, anim, c, inputblock,fastText);
	}
}
