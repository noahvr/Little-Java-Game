package field;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.swing.JFrame;

public class MapEditor implements MouseListener,Runnable{
		JFrame frame=new JFrame();
		private int mapX;
		private int mapY;
		private Canvas canvas=new Canvas();
		Main main= new Main();
		Tile [][] rofl;
		Map map;
		private int tileup=0;
		private int tileside=1;
		Models m = new Models();
		
		public MapEditor() throws IOException{
			
		}
				@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		//first thing of the game
		public void start() throws IOException {
			Scanner scan=new Scanner(System.in);
			System.out.println("Enter the width of your new map");
			mapX=scan.nextInt();
			System.out.println("Enter the height of your new map");
			mapY=scan.nextInt();
			rofl=new Tile[mapY][mapX];
			map = new Map(mapX,mapY,rofl);
			initialize();
			input();
		}
		//load the initial variables
		public void initialize() throws IOException {
			m.loadAll();
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			if(mapY*Tile.TILESIZE>928)	frame.setBounds(0,0,600+mapX*Tile.TILESIZE,mapY*Tile.TILESIZE);		
			else frame.setBounds(0,0,600+mapX*Tile.TILESIZE,928);
			
			frame.setLocationRelativeTo(null);
			
			frame.add(canvas);
			frame.setVisible(true);
			frame.setResizable(false);
			
			
			
			
			

			//KEY PRESSES DOING THINGS
			
			
		}
		BufferedImage curIm;
		@Override

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
			System.out.println(e.getButton());
			if(e.getX()<=575) {
				for(int y=0;y<29;y++) {
					for(int x=0;x<23;x++) {
						if((e.getY()-y*Tile.TILESIZE<Tile.TILESIZE&&e.getY()-y*Tile.TILESIZE>=0)&&(e.getX()-x*Tile.TILESIZE<32&&e.getX()-x*Tile.TILESIZE>=0)) {
							curIm=Models.tiles[y][x];
							//System.out.println(curIm);
							return;
						}
					}
				}

				
			}
			else {
				//map.getMap()[e.getY()/Tile.TILESIZE][(e.getX()-32)/Tile.TILESIZE].setImage(curIm);
//				System.out.println(e.getX()+" "+e.getY());
//				System.out.println(map.getMap()[10][10].getX()
				for(int y=0;y<mapY;y++) {
					for(int x=0;x<mapX;x++) {
						if(map.getMap()[y][x].isOn(e.getX(), e.getY())) {
							if(curIm.getTransparency()==BufferedImage.TRANSLUCENT) {
								map.getMap()[y][x].setImage(curIm,false);
							}
							else {
								map.getMap()[y][x].setImage(curIm,true);
							}
							return;
						}
						//System.out.println(map.getMap()[y][x].getX());
					}
				}
				//System.out.println(map.getMap()[e.getY()/32+1][(e.getX()-32)/32+1].getYLoc()+" "+map.getMap()[e.getY()/32+1][(e.getX()-32)/32+1].getXLoc());
			}
			if(e.getButton()==3) {
				Scanner scan=new Scanner(System.in);
				System.out.println("Please enter the name of the save file use txt");
				String saveF=scan.next();
				scan.close();
//				Models m=new Models();
//				m.loadAll();
			
				
				int[] pixels=new int[25*25];
				int[] emptypixels=new int[25*25];
				try {
				FileOutputStream fout=new FileOutputStream(saveF);
				ObjectOutputStream oout=new ObjectOutputStream(fout);
				oout.writeInt(mapX);
				oout.writeInt(mapY);
				for(int y=0;y<mapY;y++) {
					for(int x=0;x<mapX;x++) {
						rofl[y][x].getImage().getRGB(0, 0, 25, 25, pixels, 0,25);
						//System.out.println(pixels);
						oout.writeObject(pixels);
						pixels=emptypixels.clone();
					}
				}
				oout.close();
				}
				catch(Exception e1) {}
			}
			if(e.getButton()==e.BUTTON2) {
				
			}
	
	}
		@Override	
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
			
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		public void input() throws IOException {
			//fix the screen not having the right amount of pixels

			canvas.addMouseListener(this);

			for (int i=0;i<mapY;i++) {
				for(int o=0;o<mapX;o++) {
					rofl[i][o]=new Tile(o,i,o*Tile.TILESIZE,i*Tile.TILESIZE);
				}
			}
			


			m.loadAll();
			Graphics g = canvas.getGraphics();
			map.transform(580,0);
			
			//frame 2
			
		
			while(true) {
				//frame 1	
				
				
				map.render(g);
				g.setColor(Color.white);
				
			
				//System.out.println(Models.tiles[1].length);//frame 2
				for(int y=0;y<28;y++) {
					for(int x=0;x<23;x++) {
						g.drawImage(Models.tiles[y][x], Tile.TILESIZE*x, Tile.TILESIZE*y, null);
						if(Models.tiles[y][x].getTransparency()==BufferedImage.TRANSLUCENT) {
							
						}
					}
				}
				//System.out.println(map.getMap()[0][1].getXLoc()+" "+map.getMap()[0][1].getYLoc());
			if(curIm!=null)g.drawImage(curIm, frame.getWidth()-100, frame.getHeight()-100, null);
			}
			
			}
		
		public void picker(BufferedImage[][]loler) {
			for(int y=0;y<29;y++) {
				for(int x=0;x<23;x++) {
					int a =y*32+32;
					int b=x*32+32;
				}
			}
		}
		
	public static void main(String[] args) throws IOException {
		MapEditor m=new MapEditor();
		try {
			m.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	


}
