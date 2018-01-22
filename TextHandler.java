package field;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

public class TextHandler implements Serializable {
	private int i = 0;
	private int count = 0;
	private String gimme;
	private int anim;
	private int start = 0;
	private int end = 1;
	private int lastBlockLines;
	private String line1,line2,line3,line4;
	private String line1f,line2f,line3f,line4f;
	private boolean line1lock,line2lock,line3lock,line4lock;
	private int i1,i2,i3,i4;
	private boolean advance;
	private boolean lastBlock;
	private boolean textDone;
	private boolean eventDone;
	private int displayStart;
	private int displayEnd;
	private Graphics g;
	private Graphics2D g2;
	RoundRectangle2D rr;
	
	private String ok[];

	public TextHandler() {
		gimme = " ";
		line1 = line2 = line3 = line4 = "";
		line1f = line2f = line3f = line4f = "";
		line1lock=line2lock=line3lock=line4lock=false;
		i1=i2=i3=i4=0;
		advance=false;
		lastBlock=false;
		displayStart=0;
		displayEnd=3;
		textDone=false;
		eventDone=false;
		lastBlockLines=0;
		ok=new String[4];
		rr=new RoundRectangle2D.Float(25, Tile.TILESIZE * Main.TILES_ON_SCREEN * 2 / 3+10, Tile.TILESIZE * Main.TILES_ON_SCREEN - 50, Tile.TILESIZE * Main.TILES_ON_SCREEN / 3 -20, 20, 20);
	}

	
	public void textBox(Graphics g, String []s, int anim,boolean advance,boolean fastText) {

		this.g=g;
		this.advance=advance;

//		g.fillRect(25, Tile.TILESIZE * Main.TILES_ON_SCREEN * 2 / 3, Tile.TILESIZE * Main.TILES_ON_SCREEN - 50,
//				Tile.TILESIZE * Main.TILES_ON_SCREEN / 3 - 10);
		g2=(Graphics2D)g;
		g.setColor(new Color(143,188,214));
		RoundRectangle2D r2=new RoundRectangle2D.Float(21, Tile.TILESIZE * Main.TILES_ON_SCREEN * 2 / 3+6, Tile.TILESIZE * Main.TILES_ON_SCREEN - 42, Tile.TILESIZE * Main.TILES_ON_SCREEN / 3 -12, 22, 22);
		g2.fill(r2);
//		g2.fill3DRect(25, Tile.TILESIZE * Main.TILES_ON_SCREEN * 2 / 3, Tile.TILESIZE * Main.TILES_ON_SCREEN - 50, Tile.TILESIZE * Main.TILES_ON_SCREEN / 3 - 10, true);
		//g.setColor(new Color(225, 210, 186));
		g.setColor(Color.white);
		
		
		 

		g2.fill(rr);
		g.setColor(Color.BLUE);
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		// for(int i=0;i<s.length;i++) {
		// g.setColor(Color.BLACK);
		// g.drawString(a[i], 27, Tile.TILESIZE*Main.TILES_ON_SCREEN*2/3+10*(i+1));
		// g.drawString(a[1].charAt(i), 27+i,
		// Tile.TILESIZE*Main.TILES_ON_SCREEN*2/3+10*(i+1));
		// g.draw
		// }
		// hand.textBox(g,s, anim);

		// LINE 1

		if (line1f!=null&&line1f.length() == s[0].length()) {

			line1lock = true;
			line1f = s[0];
			g.drawString(line1f, 30, Tile.TILESIZE * Main.TILES_ON_SCREEN * 2 / 3 + 25);
		} else {

			line1f = new String(gimmeLittle(s[0], advance, anim, 1, fastText));
			g.drawString(line1f, 30, Tile.TILESIZE * Main.TILES_ON_SCREEN * 2 / 3 + 25);
		}

		// Line 2
		if (line2f!=null&&line2f.length() == s[1].length()) {
			
			line2lock=true;
			line2f = s[1];
			g.drawString(line2f, 30, Tile.TILESIZE * Main.TILES_ON_SCREEN * 2 / 3 + 40);
		} else if (line1lock) {

			line2f = new String(gimmeLittle(s[1], advance, anim, 2, fastText));
			g.drawString(line2f, 30, Tile.TILESIZE * Main.TILES_ON_SCREEN * 2 / 3 + 40);
		}
		// Line 3
		if (line3f!=null&&line3f.length() == s[2].length()) {

			line3lock=true;
			line3f = s[2];
			g.drawString(line3f, 30, Tile.TILESIZE * Main.TILES_ON_SCREEN * 2 / 3 + 55);
		} else if (line2lock) {
			line3f =gimmeLittle(s[2], advance, anim, 3, fastText);
			g.drawString(line3f, 30,Tile.TILESIZE * Main.TILES_ON_SCREEN * 2 / 3 + 55);
		}
		// Line 4
		if (line4!=null&&line4.length() == s[3].length()) {
			//asleep=true;
			line4lock=true;
			line4 = s[3];
			g.drawString(line4f, 30, Tile.TILESIZE * Main.TILES_ON_SCREEN * 2 / 3 + 70);
		} else if (line3lock) {
			line4f =gimmeLittle(s[3], advance, anim, 4, fastText);
			g.drawString(line4f, 30,Tile.TILESIZE * Main.TILES_ON_SCREEN * 2 / 3 + 70);
		}


	}
	
	
	
	public String[] display(String[] cuts) {
		if(!lastBlock&&textDone&&advance) {
			g.setColor(Color.WHITE);
			g2.fill(rr);
			line1lock=line2lock=line3lock=false;
			line1=line2=line3=line4="";
			line1f=line2f=line3f=line4f=null;
			displayEnd+=4;
			displayStart+=4;
			if(displayEnd==cuts.length-1) {
				lastBlock=true;
			}
			while(displayEnd>=cuts.length) {
				if(lastBlockLines==0)lastBlockLines=4;
				displayEnd--;
				lastBlock=true;
				lastBlockLines--;
			}
			textDone=false;
		}
		for (int i = displayStart; i <= displayEnd; i++) {
			
			ok[i%4]=cuts[i];
		}
		for(int i=0;i<ok.length;i++) {
			if(ok[i]==null)ok[i]="";
		}
		for(int i=displayStart;i<displayStart+ok.length;i++) {
			try{if(ok[i%4]==cuts[i])System.out.println("");}
			catch(Exception e) {ok[i%4]=" ";}
		}
		return ok;
	}
	
	
	public String[] shorten(String s) {
		int cut = 28;
		String[] cuts = new String[(s.length() / 28) + 1];
		for (int i = 0; i < cuts.length; i++) {
			if (i == cuts.length - 1) {
				cut = s.length() % cut;
				cuts[i] = s.substring(s.length() - cut, s.length());
			} else {
				cuts[i] = s.substring(cut * i, cut * (i + 1));

			}
		}

		return cuts;
	}
	
	
	public String[] trim(String[] cuts) {
		for(int i=0;i<cuts.length-1;i++) {
				cuts[i]=cuts[i].trim();
				if(i==3||i==7) {
					cuts[i]=cuts[i].concat("...");
				}
			}
		return cuts;
	}
	
	
	public String[] removeWraps(String[] cuts) {
		String move;
		int lastSpace;
		for (int i = 0; i < cuts.length; i++) {
			if (i == cuts.length - 1&&cuts[i].length()>=29) {
				String[] cuts2 = new String[cuts.length + 1];
				for (int p = 0; p < cuts.length; p++) {
					cuts2[p] = cuts[p];
					if(p==cuts.length-1)cuts2[p+1]="";
				}

					while (cuts2[i].length() >= 29) {
						cuts2[i] = cuts2[i].trim();
						lastSpace = cuts2[i].lastIndexOf(" ");
						move = cuts2[i].substring(lastSpace + 1, cuts2[i].length()) + " ";
						cuts2[i] = cuts2[i].substring(0, lastSpace);
						cuts2[i + 1] = move + cuts2[i + 1];
					}

				return cuts2;
			} 
			
			else if(i<cuts.length-1){
				lastSpace = cuts[i].lastIndexOf(" ");
				// lastSpace=cuts[i].lastIndexOf(" ");
				if (!(cuts[i].endsWith(" ") || cuts[i + 1].startsWith(" ")) || cuts[i].length() >= 29) {
					move = cuts[i].substring(lastSpace + 1, cuts[i].length());
					cuts[i] = cuts[i].substring(0, lastSpace);
					cuts[i + 1] = move + cuts[i + 1];
					while (cuts[i].length() >= 29) {
						cuts[i] = cuts[i].trim();
						lastSpace = cuts[i].lastIndexOf(" ");
						move = cuts[i].substring(lastSpace + 1, cuts[i].length()) + " ";
						cuts[i] = cuts[i].substring(0, lastSpace);
						cuts[i + 1] = move + cuts[i + 1];
					}

				}
			}

		}
		return cuts;
	}

	
	public String gimmeLittle(String s, boolean asleep, int anim, int line,boolean fastText) {
		count++;
		if(count==500)count=0;

		switch (line) {
		case 1:
			if(count%500!=0&&!fastText)return line1;
			if(fastText) {
				if(count%150!=0) {
					return line1;
				}
			}
			line1=s.substring(start, end * i1);
			gimme=line1;
			if(i1<s.length())i1++;
			else i1=0;
			break;
		case 2:
			if(count%500!=0&&!fastText)return line2;
			if(fastText) {
				if(count%150!=0) {
					return line2;
				}
			}
			line2 =s.substring(start, end * i2);
			gimme=line2;
			if(i2<s.length())i2++;
			else i2=0;
			break;
		case 3:
			if(count%500!=0&&!fastText)return line3;
			if(fastText) {
				if(count%150!=0) {
					return line3;
				}
			}
			line3 = s.substring(start, end * i3);
			gimme=line3;
			if(i3<s.length())i3++;
			else i3=0;
			break;
		case 4:
			if(count%500!=0&&!fastText)return line4;
			if(fastText) {
				if(count%150!=0) {
					return line4;
				}
			}
			line4=s.substring(start, end * i4);
			gimme=line4;
			if(i4<s.length())i4++;
			else i4=0;
			
			break;
		}
		if(lastBlockLines==line&&gimme.length()==s.length()) {
			eventDone=true;
		}
		//ARe we ready to see the next page?
		if(gimme.endsWith("...")&&gimme==line4) {
			textDone=true;
		}
		else if(!lastBlock){
			textDone=false;
		}
		
		return gimme;

	}
	
	public boolean handle(String s, Graphics g,int anim, boolean advance, boolean asleep, boolean fastText) {
		String[]array=shorten(s);
//		
//		for(int i=0;i<array.length;i++) {
//			System.out.println(array[i]);
//		}
//		try {Thread.sleep(3000);}
//		catch(Exception e) {}
//		
		array=removeWraps(array);
//		
//		for(int i=0;i<array.length;i++) {
//			System.out.println(array[i]);
//		}
//		try {Thread.sleep(3000);}
//		catch(Exception e) {}
//		
		array=trim(array);
//		
//		for(int i=0;i<array.length;i++) {
//			System.out.println(array[i]);
//		}
//		try {Thread.sleep(3000);}
//		catch(Exception e) {}
//		
		array=display(array);
//		
//		for(int i=0;i<array.length;i++) {
//			System.out.println(array[i]);
//		}
//		try {Thread.sleep(3000);}
//		catch(Exception e) {}

		textBox(g, array, anim, advance, fastText);
		if(advance&&lastBlock&&line1lock) {
			return true;
		}
//		System.out.println("LASTBLOCK"+lastBlock+"LASTBLOCKLINES"+lastBlockLines+" EVENTDONE "+eventDone);
		return false;
	}

}
