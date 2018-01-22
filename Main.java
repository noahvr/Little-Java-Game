package field;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Main extends JFrame implements Runnable, ActionListener {
	private Models models = new Models();
	private Canvas canvas = new Canvas();
	private boolean pause;
	private boolean inputBlock;
	private BufferStrategy buffer;
	private boolean keyDown = false;
	long keyTime = 0;
	Key w, a, s, d;
	int[] ican;
	int[] forcedmove;
	private int tick = 0;
	static final int TILES_ON_SCREEN = 11;
	boolean start = false;
	Container contentpane;
	private boolean skip = false;
	private boolean menuActive = true;
	private int face;
	private boolean fastText=false;

	public Main() {
		w = new Key(0, 'w');
		a = new Key(0, 'a');
		s = new Key(0, 's');
		d = new Key(0, 'd');
		face = 0;
		setBounds(0, 0, Tile.TILESIZE * TILES_ON_SCREEN, Tile.TILESIZE * TILES_ON_SCREEN);
		setVisible(true);

	}

	public void run() {
		try {
			start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// first thing of the game
	public void start()
			throws IOException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException {
		initialize();
		input();
	}

	// load the initial variables
	public void initialize() throws IOException {
		models.loadAll();

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

		add(canvas);
		setResizable(false);
		canvas.createBufferStrategy(3);
		buffer = canvas.getBufferStrategy();
		// KEY PRESSES DOING THINGS

		ican = new int[4];

		KeyListener key = new KeyAdapter() {

			private int meme = -1;

			public void keyPressed(KeyEvent arg) {
				// chain together movement commands
				// System.out.println("ITS "+keyTime+"Hmmmmm");
				if (!inputBlock) {
					keyTime = System.nanoTime();
					keyDown = true;
					// System.out.println(arg.getKeyChar()+" KEY IS DOWN");
					if (arg.getKeyChar() == 'w' && w.getPriority() == 0) {
						meme++;
						ican[meme] = 1;
						w.setPriority(1);
					}
					if (arg.getKeyChar() == 'a' && a.getPriority() == 0) {
						meme++;
						ican[meme] = 2;
						a.setPriority(1);
					}
					if (arg.getKeyChar() == 's' && s.getPriority() == 0) {
						meme++;
						ican[meme] = 3;
						s.setPriority(1);
					}
					if (arg.getKeyChar() == 'd' && d.getPriority() == 0) {
						meme++;
						ican[meme] = 4;
						d.setPriority(1);

					}
				}

				if (arg.getKeyChar() == 'i') {

					//System.out.println(start);
					start=true;
					skip=true;
					fastText=true;

				}
				if (arg.getKeyChar() == 's') {
					menuActive = !menuActive;
				}
				if (arg.getKeyChar() == 'w') {
					menuActive = !menuActive;
				}

			}

			public void keyTyped(KeyEvent arg) {
				// System.out.println(arg.getKeyChar());
				if(arg.getKeyChar()=='i') {

				}
				if (arg.getKeyChar() == 'p')
					pause = !pause;

			}

			public void keyReleased(KeyEvent arg) {
				keyDown = false;
				if(arg.getKeyChar()=='i') {
					fastText=false;
					skip=false;
					start=false;
				}
				if (arg.getKeyChar() == 'w') {
					w.setPriority(0);
					for (int i = 0; i < 4; i++) {
						if (ican[i] == 1) {
							try {
								ican[i] = ican[i + 1];
								ican[i + 1] = 1;
							} catch (Exception e) {
								ican[i] = 0;
							}
						}
					}
					meme--;
				} else if (arg.getKeyChar() == 'a') {
					a.setPriority(0);
					for (int i = 0; i < 4; i++) {
						if (ican[i] == 2) {
							try {
								ican[i] = ican[i + 1];
								ican[i + 1] = 2;
							} catch (Exception e) {
								ican[i] = 0;
							}
						}
					}
					meme--;
				} else if (arg.getKeyChar() == 's') {
					s.setPriority(0);
					for (int i = 0; i < 4; i++) {
						if (ican[i] == 3) {
							try {
								ican[i] = ican[i + 1];
								ican[i + 1] = 3;
							} catch (Exception e) {
								ican[i] = 0;
							}
						}
					}
					meme--;
				} else if (arg.getKeyChar() == 'd') {
					d.setPriority(0);
					for (int i = 0; i < 4; i++) {
						if (ican[i] == 4) {
							try {
								ican[i] = ican[i + 1];
								ican[i + 1] = 4;
							} catch (Exception e) {
								ican[i] = 0;
							}
						}
					}
					meme--;
				}

			}
		};
		canvas.addKeyListener(key);
	}

	// TODO THIS NEEDS REFACTORING
	public void input()
			throws IOException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException {

		// fix the screen not having the right amount of pixels
		canvas.setPreferredSize(getSize());
		forcedmove = new int[4];
		// Tile [][] rofl=new Tile[11][11];
		//
		// for (int i=0;i<11;i++) {
		// for(int o=0;o<11;o++) {
		// rofl[i][o]=new Tile(o,i,o*Tile.TILESIZE,i*Tile.TILESIZE);
		// }
		// }

		// Map map = new Map("beachhouse.txt");
		Map map = new Map("pppp.txt");
		// try {
		// map.load("20.txt");
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// }
		MC mc = new MC(125, 125, map);
		Models m = new Models();
		m.loadAll();

		pack();

		int key = 0;
		long timeAtLastUpdate = 0;
		long jingleLength = 0;
		long battleLength = 0;
		MainMenu menu = new MainMenu(this, buffer.getDrawGraphics(), buffer, m);
		int c = 0;
		// URL url=Main.class.getResource("titleTheme.wav");
		// URL veno=Main.class.getResource("venocry.wav");
		URL jingle = Main.class.getResource("GameFreakJingle.wav");
		URL battle = Main.class.getResource("LoadingBattle.wav");
		// Game Freak Jingle
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(jingle);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			jingleLength = clip.getMicrosecondLength();
			clip.start();
			while (!skip) {
				int i = 1;
				System.out.println(" ");
			}
			skip = false;
			clip.close();
			audioIn.close();

		} catch (Exception e) {
		}
		// Battle
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(battle);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			battleLength = clip.getMicrosecondLength();
			clip.start();
			while (!skip) {
				int i = 1;
				System.out.println(" ");
			}
			skip = false;
			clip.close();
			audioIn.close();
		} catch (Exception e) {
		}
		// Main Screen
		Clip clip = AudioSystem.getClip();
		try {
			URL title = Main.class.getResource("titleTheme.wav");
			AudioInputStream audioIn3 = AudioSystem.getAudioInputStream(title);

			clip.open(audioIn3);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
		}

		while (!start) {
			System.out.println(" ");
			c++;
			if (c > 1) {
				menu.startImage(true);
				if (c > 2)
					c = 0;
			} else {
				menu.startImage(false);
			}
		}
		skip = false;
		// Venosaur sound
		URL veno = Main.class.getResource("venocry.wav");
		try {
			AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(veno);
			Clip clip2 = AudioSystem.getClip();
			clip2.open(audioIn2);
			clip2.start();
		} catch (UnsupportedAudioFileException e) {
		} catch (LineUnavailableException e) {
		} catch (IOException e) {
		}
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}

		// canvas.setEnabled(false);
		// Main Menu

		while (!skip) {
			menu.Menu(this, clip, menuActive);
		}
		if (!menuActive) {
			System.exit(0);
		}
		// blip sound
		URL blip = Main.class.getResource("blip.wav");
		try {
			AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(blip);
			Clip clip2 = AudioSystem.getClip();
			clip2.open(audioIn2);
			clip2.start();
		} catch (UnsupportedAudioFileException e) {
		} catch (LineUnavailableException e) {
		} catch (IOException e) {
		}

		{

		}
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
		}

		int anim = 0;
		int frames = 0;
		while (!pause) {
			do {
				do {
					for (int z = 3; z > -1; z--) {
						if (ican[z] != 0) {
							key = ican[z];
							break;
						}
					}
					// TIME BASED 10 ticks per second

					// if(toSec(System.nanoTime()-uncountedtime)>highest)highest=(float)toSec(System.nanoTime()-uncountedtime);
					// if(toSec(System.nanoTime()-uncountedtime)<lowest)lowest=(float)toSec(System.nanoTime()-uncountedtime);
					// System.out.println("HIGHEST:/t"+highest);
					// System.out.println("LOWEST:/t"+lowest);
					// uncountedtime=System.nanoTime();

					if (System.nanoTime() - timeAtLastUpdate > toNano(.01f)) {
						frames += 1;
						if (frames == 100)
							frames = 0;
						// parses input data

						// provides reference point for animations
						if (frames % 20 == 0)
							anim += 1;
						if (anim == 6)
							anim = 0;

						// moves character
						// if(frames%5==0) {
						if (key != 0 && map.lock == 0&&!inputBlock) {
							if (face == key) {
								if (key == 1 && map.nextTile(key, mc) != null) {
									map.pan(0, 1, 1, key, mc);
									// mc.setTile(map.getMap()[mc.getTile().getcoords()[1]-1][mc.getTile().getcoords()[0]]);
								}
								if (key == 2 && map.nextTile(key, mc) != null) {
									map.pan(1, 0, 2, key, mc);
									// mc.setTile(map.getMap()[mc.getTile().getcoords()[1]][mc.getTile().getcoords()[0]-1]);
								}
								if (key == 3 && map.nextTile(key, mc) != null) {
									map.pan(0, -1, 3, key, mc);
									// mc.setTile(map.getMap()[mc.getTile().getcoords()[1]+1][mc.getTile().getcoords()[0]]);
								}
								if (key == 4 && map.nextTile(key, mc) != null) {
									map.pan(-1, 0, 4, key, mc);
									// mc.setTile(map.getMap()[mc.getTile().getcoords()[1]][mc.getTile().getcoords()[0]+1]);
								}
							}
							// }
							else
								face = key;

						}
						// continues if locked
						if (map.lock != 0) {
							switch (map.lock) {

							case 1:
								map.pan(0, 1, map.lock, key, mc);
								// System.out.println("UP");
								break;
							case 2:
								map.pan(1, 0, map.lock, key, mc);
								// System.out.println("LEFT");
								break;
							case 3:
								map.pan(0, -1, map.lock, key, mc);
								// System.out.println("DOWN");
								break;
							case 4:
								map.pan(-1, 0, map.lock, key, mc);
								// System.out.println("RIGHT");
								break;
							}
						}
						timeAtLastUpdate = System.nanoTime();
						key = 0;
						tick++;

					}
					// System.out.println(face);
					// Visual code - ALWAYs renders
					Graphics g = buffer.getDrawGraphics();
					g.setColor(Color.WHITE);
					g.fillRect(0, 0, getWidth(), getHeight());

					map.render(g);
					canvas.setBackground(Color.blue);
					mc.render(g, ican, map, anim);
					inputBlock=false;
					if (mc.getTile().getEvent() != null) {
						mc.getTile().getEvent().runEvent(g, map, mc, new int[] { 2, 3, 4 }, anim, TextCode.A,
								start,fastText);
						if(!mc.getTile().getEvent().isDone())inputBlock=true;
					}
					System.out.println(inputBlock);
					// System.out.println(mc.getTile().getcoords()[0]+"
					// "+mc.getTile().getcoords()[1]);
				} while (buffer.contentsRestored());

				buffer.show();

			} while (buffer.contentsLost());
		}
	}

	public float toNano(float seconds) {
		float millesec = seconds * 1000;
		float microsec = millesec * 1000;
		float nanosec = microsec * 1000;
		return nanosec;
	}

	public float toSec(float nano) {
		float micro = nano / 1000;
		float milli = micro / 1000;
		float sec = milli / 1000;
		return sec;
	}

	// change variables

	// draw to screen

	public static void main(String[] args) throws IOException {

		(new Thread(new Main())).start();
		// (new Thread(new )).start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("HELLO");
		if (arg0 != null) {
			System.out.println("BURRONT");
			start = !start;
		}

	}
}
