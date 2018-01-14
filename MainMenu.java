package field;




import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainMenu{
		JFrame frame;
		Graphics g;
		BufferStrategy buffer;
		Models m;
		JButton but=null;
		boolean f=false;		
		Color actPurp=new Color(112,104,128);
		Color offPurp=new Color(64,64,72);
		Color offWhite=new Color(144,144,144);
		public MainMenu() {
			this(null,null,null,null);
		}
		public MainMenu(JFrame frame, Graphics g, BufferStrategy buffer, Models m) {
			this.frame=frame;
			this.g=g;
			this.buffer=buffer;
			this.m=m;
			
			
		}
		public void startImage(boolean string) throws IOException {
			Graphics g = buffer.getDrawGraphics();
			//white sheet
			g.setColor(Color.WHITE);
			try {Thread.sleep(100);}
			catch(Exception e) {}
			g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
			//green bars
			
			try {Thread.sleep(100);}
			catch(Exception e) {}
			g.setColor(Color.decode("4247648"));
			g.fillRect(0, 0, frame.getWidth(), frame.getHeight()/24);	
			try {Thread.sleep(100);}
			catch(Exception e) {}
			g.setColor(new Color(0, 120, 0));
			g.fillRect(0, frame.getHeight()*20/24, frame.getWidth(), frame.getHeight()*3/24);
			//black bars
			try {Thread.sleep(100);}
			catch(Exception e) {}
			g.setColor(Color.black);
			g.fillRect(0, frame.getHeight()/24, frame.getWidth(), frame.getHeight()*3/24);
			try {Thread.sleep(100);}
			catch(Exception e) {}
			g.fillRect(0, frame.getHeight()*16/24-10, frame.getWidth(), frame.getHeight()*5/24);
			try {Thread.sleep(100);}
			catch(Exception e) {}
			//red rectang;e
			g.setColor(new Color(224,120,88));
			g.fillRect(0, frame.getHeight()*4/24, frame.getWidth(), frame.getHeight()*11/24+4);
			
			g.drawImage(m.pokemonLogo, 10,1, null);
			g.drawImage(ImageIO.read(MainMenu.class.getResource("venoRip.png")), 148, 98,null);
			g.drawImage(ImageIO.read(MainMenu.class.getResource("leafgreen.png")), 50, 98,null);
			//start message
			if(string) {
				g.setColor(Color.WHITE);
				g.drawString("PRESS START", 60,230);
			}
			buffer.show();
			g.dispose();
			
		}
		
		public void Menu(JFrame a,Clip clip,boolean isTop) {

			Graphics g=buffer.getDrawGraphics();
			g.setColor(new Color(80,88,144));
			g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
			if(but==null) {
				clip.close();
				but=new JButton();
			}
			Color c1,c2,c3,c4;
			if(isTop) {
				c1=actPurp;
				c2=Color.white;
				c3=offPurp;
				c4=offWhite;
			}
			else {
				c1=offPurp;
				c2=offWhite;
				c3=actPurp;
				c4=Color.white;
			}
			g.setColor(Color.BLACK);
			g.fillRect(20,0, frame .getWidth()-50, 170);
			g.setColor(c1);
			g.fillRect(	21, 1, frame.getWidth()-52,168);
			g.setColor(c2);
			g.fillRect(	26, 6, frame.getWidth()-62,158);
			
			
			g.setColor(Color.black);
			g.fillRect(20, 175, frame .getWidth()-50, 50);
			g.setColor(c3);
			g.fillRect(21, 176, frame.getWidth()-52, 48);
			g.setColor(c4);
			g.fillRect(26, 181, frame.getWidth()-62, 38);
			buffer.show();
			g.dispose();

			
			
//			g.fillRect(0, 0,frame.getWidth() ,frame..getHeight());
			
			//g.drawImage(Models.tiles[1][1], but.getX(), but.getY(), null);
			//g.setColor(Color.WHITE);
			//g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		}
		

}
