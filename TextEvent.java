package field;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

public class TextEvent extends Event {
	private TextCode code;

	private TextHandler hand = new TextHandler();
	//when the event is over asleep should be true
	private boolean asleep=false;

	private boolean eventDone;
	// Text events


	public TextEvent(TextCode code) {
		this.code = code;
		asleep=false;
		eventDone=false;
	}

	@Override
	public void runEvent(Graphics g, Map map, MC mc, int[] keys, int anim, TextCode c, boolean advance,boolean fastText) {

		if(asleep) {
			return;
		}
		eventDone=hand.handle(getText(), g, anim, advance, asleep, fastText);
		asleep=eventDone;
	}

	public String getText() {
		String s;
		switch (code) {
		default:
			s = "whoops default code";
		case A:
			s = "Sometimes people go up to the castle and disappear!  Theres a rumor that the baron is actually a vampire..  Don't tell anybody!  I heard one guy went up there on a really dark and stormy night and just BAM! bing bang! badda bop!  and nobdoy ever saw that laddie again.  Sad story, but true";
			break;
		case B:
			s = "JHILANYE, JHAROLL, DOBRIKASTNE, DOBRESHNARODNIR";
			break;
		case C:
			s = "";
			break;
		}
		return s;
	}
	
	public boolean asleep() {
		return asleep;
	}
	
	public void ifAsleep() {
		if(asleep) {
			
		}
	}
	public boolean isDone() {
		return eventDone;
	}

}
