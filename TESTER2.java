package field;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFrame;

public class TESTER2 implements KeyListener {
	private boolean advance = false;
	private boolean done = false;
	private int displayStart = 0;
	private int displayEnd = 3;

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		TESTER2 t = new TESTER2();
		JFrame frame = new JFrame();
		frame.setSize(200, 200);
		frame.addKeyListener(t);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String s[] = t.shorten(
				"Hello travelerr from afar.  My name is jimmy mc phasmic the 3rd of LIt ville and we would like to welcome you to the pokemon world or some shit like that.  do not be concerned.  ginger is on my computer right now and being very cute.");
		// String s[] = t.shorten(
		// "JING GON JING LON i have come from afar my name is jafar better get down
		// before i blasy u with my ak guess u nigas forgot about dre o no the fights
		// out im gonna punch yo lights OUTFIELDMOUTFIELD maybe baby in my lady
		// ssahdy");
		s = t.removeWraps(s);
		 s=t.trim(s);
		for (int i = 0; i < s.length; i++) {
			System.out.println(s[i]);
			System.out.println(s[i].length());

		}
	}

	public String[] trim(String[] cuts) {
		for (int i = 0; i < cuts.length - 1; i++) {
			cuts[i] = cuts[i].trim();
			if (i == 3 || i == 7) {
				cuts[i] = cuts[i].concat("...");
			}
		}
		return cuts;
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

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == 'a') {
			advance = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
