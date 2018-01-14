package field;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class imagetest {

	public static void main(String[] args) throws IOException {
		imagetest test=new imagetest();
		// TODO Auto-generated method stub
		BufferedImage buf=null;
		buf=ImageIO.read(imagetest.class.getResource("pokemon.png"));
		BufferedImage [][] snips=new BufferedImage[9][21];
		BufferedImage[] mc=new BufferedImage[8];
		
		JFrame frame=new JFrame();
		frame.setBounds(70, 70, buf.getWidth()+100, buf.getHeight()+100);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Canvas canvas=new Canvas();
		frame.add(canvas);
		Graphics g=canvas.getGraphics();
		canvas.setBackground(Color.GREEN);

		System.out.println("HELO");
		test.snip(25,25,buf,snips);
		for(int i=0;i<mc.length;i++) {
			mc[i]=snips[7][9+i];
			System.out.println(snips[7][9+1]);
		}		
		try {Thread.sleep(100);}
		catch(Exception e) {}
		for(int i=0;i<8;i++) {
				g.drawImage(mc[i], 100+i*25, 100, null);
				System.out.println(mc[i]);
		}
	}
	
	public BufferedImage[][] snip(int w, int h, BufferedImage pic,BufferedImage[][]he ) {
		for(int i=0;i<9;i++) {
			for(int c=0;c<21;c++) {
				he[i][c]=pic.getSubimage(c*w, i*h, w, h);
			}
			
		}
		return he;
	}

}
