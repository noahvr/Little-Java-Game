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
		FileInputStream fin=new FileInputStream("ok.txt");
		ObjectInputStream oin=new ObjectInputStream(fin);
		o=oin.readObject();

		int[]pixels=new int[((int[])o).length];
		for(int i=0;i<pixels.length;i++) {
			pixels[i]=((int[])o)[i];
		}
		for(int i=0;i<((int[])o).length;i++) {
			System.out.println(pixels[i]);
		}
		BufferedImage image=new BufferedImage((int)Math.sqrt(pixels.length),(int)Math.sqrt(pixels.length),BufferedImage.TYPE_INT_ARGB);
		image.setRGB(0, 0, (int)Math.sqrt(pixels.length), (int)Math.sqrt(pixels.length), pixels, 0, (int)Math.sqrt(pixels.length));
		
		JFrame frame=new JFrame();
		frame.setVisible(true);
		frame.setSize(500, 500);
		Canvas canvas=new Canvas();
		frame.add(canvas);
		Graphics g=canvas.getGraphics();
		try {Thread.sleep(100);
		}
		catch(Exception e) {}
		g.drawImage(image, 0, 0, null);

	}

}
