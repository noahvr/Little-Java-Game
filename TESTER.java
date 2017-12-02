package field;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;

public class TESTER {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Models m=new Models();
		m.loadAll();
		BufferedImage[][] b=new BufferedImage[11][11];

		for(int i=0;i<11;i++) {
			for(int j=0;j<11;j++) {
				b[i][j]=Models.tiles[10][10];
			}
			
		}
		int[] pixels=new int[Models.tiles[10][10].getHeight()*Models.tiles[10][10].getWidth()];
		Models.tiles[10][10].getRGB(0, 0, Models.tiles[10][10].getWidth(), Models.tiles[10][10].getHeight(), pixels, 0, Models.tiles[10][10].getWidth());
		
		FileOutputStream fout=new FileOutputStream("ok.txt");
		ObjectOutputStream oout= new ObjectOutputStream(fout);
		

		oout.writeObject(pixels);
		System.out.println(pixels);
			
		
		
	}

}
