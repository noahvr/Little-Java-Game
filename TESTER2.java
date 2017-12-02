package field;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFrame;

public class TESTER2 {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		Object o;
		Tile[][] t=new Tile[11][11];
		for(int y=0;y<11;y++) {
			for(int x=0;x<11;x++) {
				t[y][x]=new Tile(x,y,y*25,x*25);
			}
		}
		Map map=new Map(11,11,t);
		Models m=new Models();
		m.loadAll();
		
		BufferedImage image=new BufferedImage(25,25,BufferedImage.TYPE_INT_ARGB);
		BufferedImage emptyimage=new BufferedImage(25,25,BufferedImage.TYPE_INT_ARGB);
		FileInputStream fin=new FileInputStream("try4.txt");
		ObjectInputStream oin=new ObjectInputStream(fin);
		int[]pixels =new int[25*25];
		int[]emptypixels=new int [25*25];
		for(int y=0;y<11;y++) {
			for(int x=0;x<11;x++) {
				o=oin.readObject();
				for(int i=0;i<((int[])o).length;i++){
					System.out.println(((int[])o)[i]);
				}
				System.out.println("------------");
				pixels=((int[])o).clone();
				image.setRGB(0, 0, 25, 25, pixels, 0,25);
				t[y][x].setImage(image, "aaa");
				
				pixels=emptypixels.clone();
				
				image.setRGB(0, 0, 25, 25, emptypixels, 0, 25);
			}
		}

		
		
		JFrame frame=new JFrame();
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.setResizable(false);
		Canvas canvas=new Canvas();
		frame.add(canvas);
		Graphics g=canvas.getGraphics();
		try {Thread.sleep(500);
		}
		catch(Exception e) {}

		map.render(g, m);
		
		
		//WORKS DONT BREAK
		
//		Object o;
//		FileInputStream fin=new FileInputStream("ok.txt");
//		ObjectInputStream oin=new ObjectInputStream(fin);
//		o=oin.readObject();
//
//		int[]pixels=new int[((int[])o).length];
//		for(int i=0;i<pixels.length;i++) {
//			pixels[i]=((int[])o)[i];
//		}
//		for(int i=0;i<((int[])o).length;i++) {
//			System.out.println(pixels[i]);
//		}
//		BufferedImage image=new BufferedImage((in)t)Math.sqrt(pixels.length),(int)Math.sqrt(pixels.length),BufferedImage.TYPE_INT_ARGB);
//		image.setRGB(0, 0, (int)Math.sqrt(pixels.length), (int)Math.sqrt(pixels.length), pixels, 0, (int)Math.sqrt(pixels.length));
//		
//		JFrame frame=new JFrame();
//		frame.setVisible(true);
//		frame.setSize(500, 500);
//		Canvas canvas=new Canvas();
//		frame.add(canvas);
//		Graphics g=canvas.getGraphics();
//		try {Thread.sleep(100);
//		}
//		catch(Exception e) {}
//		g.drawImage(image, 0, 0, null);

	}

}
