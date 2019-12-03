package com.LADLAB.LADLAB;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.LADLAB.LADLAB.Objects.Block;
import com.LADLAB.LADLAB.Objects.ColorBlock;
import com.LADLAB.LADLAB.Objects.Player;
import com.LADLAB.LADLAB.Objects.Player2;
import com.LADLAB.LADLAB.Objects.ScoreBoard;

import Framework.KeyInput;
import Framework.ObjectId;

public class Game extends JFrame implements Runnable {
private static final long serialVersionUID = 1L;
	


	public static boolean running = false;
	public final static int WIDTH = 10 * 32;
	public final static int HEIGHT = 7 * 32;
	public final static int SCALE = 3;
	public final static String TITLE = "Domination";
	
	public static int frames = 0;
	public static int updates = 0;
	
	public static int GameState = 1;
	
	private BufferedImage level, levelhh;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	//Main thread
	private Thread thread;
	private JFrame frame;
	
	//Declaration
	Menu menu;
	PlayerSelect player;
	Handler handler;
	EndScreen en;
	ScoreBoard board;
	
	public void init() {
		
		handler = new Handler();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		levelhh = loader.loadImage("/levelhh.png");
		
		handler.addObject(new Player(100, 100, handler, ObjectId.Player));
		handler.addObject(new Player2(880, 500, handler, ObjectId.Player2));
		
		loadImageLevel(levelhh);
		
		this.addKeyListener(new KeyInput(handler));
	}
	
	public void run() {
		//Controlling the tick time
		init();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double ns = 1000000000.0 / 60.0;
		double delta = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
			tick();
			updates++;
			delta--;
			}
			paint();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " updates, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
 		
}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Game() {
		//Initialization
		Dimension d = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(d);
		frame = this;
		menu = new Menu();
		player = new PlayerSelect();
		en = new EndScreen();
		board = new ScoreBoard(handler);
		
	}
	
	public void tick() {
		//updates everything
		if (Player.healthBarHealth <= 0 || Player2.healthBarHealth <= 0 || ScoreBoard.blueScore >= 42 || ScoreBoard.redScore >= 42) {
			GameState = 4;
		}
		if (GameState == 1) {
		menu.tick();
		}
		if (GameState == 2) {
		player.tick();
		}
		if (GameState == 3) {
			handler.tick();
			board.tick();
		}
		if (GameState == 4) {
			en.tick();
		}
	}
	
	public void paint() {
		//paints to screen
		BufferStrategy buffer = getBufferStrategy();
		if (buffer == null) {
			createBufferStrategy(3);
			return;
		}
	
	Graphics g = buffer.getDrawGraphics();
	
	g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	
	g.setColor(Color.WHITE);
	
	//GAMESTATES
	if (GameState == 1) {
		menu.paint(g);
	}
	if (GameState == 2) {
		player.paint(g);
	}
	if (GameState == 3) {
		handler.paint(g);
		board.paint(g);
	}
	if (GameState == 4) {
		en.paint(g);
	}

	g.dispose();
	buffer.show();
	}
	
	private void loadImageLevel(BufferedImage image) {
		//LEVEL READER
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println(w);
		
		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if (red == 255 && green == 255 && blue == 255) {
					handler.addObject(new Block(xx * 32, yy * 32, ObjectId.Block, handler));
				}
				if (red == 0 && green == 255 && blue == 0) {
					handler.addObject(new ColorBlock(xx * 32, yy * 32, ObjectId.ColorBlock, handler));
				}
			}
		}
		
	}
	
	//main
	public static void main(String[] args) {
		Game game = new Game();
		game.setResizable(false);
		game.setTitle(TITLE);
		game.pack();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setLocationRelativeTo(null);
		game.setVisible(true);
		game.start();
	}
}