package field;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class TESTER {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		System.out.println("Please enter the name of the save file use txt");
		String saveF=scan.nextLine();
//		System.out.println("Please enter the width of the save file");
//		int width=scan.nextInt();
//		System.out.println("Please enter the height of the save file");
//		int height=scan.nextInt();
		scan.close();
		Models m=new Models();
		m.loadAll();
		Tile[][] t=new Tile[11][11];
		for(int y=0;y<11;y++) {
			for(int x=0;x<11;x++) {
				
				t[y][x]=new Tile(x,y,y*Tile.TILESIZE, x*Tile.TILESIZE);
				
				t[y][x].setImage(Models.tiles[y][x],"MEMER");
			}
		}
	
		
		int[] pixels=new int[25*25];
		int[] emptypixels=new int[25*25];
		FileOutputStream fout=new FileOutputStream(saveF);
		ObjectOutputStream oout=new ObjectOutputStream(fout);
		oout.writeInt(11);
		oout.writeInt(11);
		for(int y=0;y<11;y++) {
			for(int x=0;x<11;x++) {
				t[y][x].getImage().getRGB(0, 0, 25, 25, pixels, 0,25);
				System.out.println(pixels);
				oout.writeObject(pixels);
				oout.write(';');
				pixels=emptypixels.clone();
			}
		}
		oout.close();
		//WORKS DONT BREAK
		
//		int[] pixels=new int[Models.tiles[10][10].getHeight()*Models.tiles[10][10].getWidth()];
//		Models.tiles[10][10].getRGB(0, 0, Models.tiles[10][10].getWidth(), Models.tiles[10][10].getHeight(), pixels, 0, Models.tiles[10][10].getWidth());
//		
//		FileOutputStream fout=new FileOutputStream("ok.txt");
//		ObjectOutputStream oout= new ObjectOutputStream(fout);
//		
//		oout.writeObject(pixels);
//		System.out.println(pixels);
			
		
		
	}

}
