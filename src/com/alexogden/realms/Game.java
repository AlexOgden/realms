package com.alexogden.realms;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.alexogden.realms.entity.mob.player.Player;
import com.alexogden.realms.entity.mob.player.Sorcerer;
import com.alexogden.realms.entity.mob.player.Wizzard;
import com.alexogden.realms.graphics.Screen;
import com.alexogden.realms.input.Keyboard;
import com.alexogden.realms.input.Mouse;
import com.alexogden.realms.level.Level;
import com.alexogden.realms.level.TileCoordinate;
import com.alexogden.realms.util.Util;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 274;
	private static final int HEIGHT = 154;
	private static final int SCALE = 5;
	
	private static final String strVersion = "Realms - Developed by Alex Ogden\nPre-Alpha 0.7.7";
	private static final String gTitle = "Realms";
	
	private Screen screen;
	private Keyboard key;
	private Level level;
	private Player player;
	
	private int _frames = 0;
	private int _ticks = 0;

	private boolean running = false;
	private Thread thread;
	private JFrame frame;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		
		screen = new Screen(WIDTH, HEIGHT);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.SPAWN_LEVEL;
		
		TileCoordinate pspawn = new TileCoordinate(13, 15);
		player = new Wizzard(pspawn.x(), pspawn.y(), key);
		//player = new Sorcerer(pspawn.x(), pspawn.y(), key);
		player.init(level);
		level.addPlayer(player);
		
		addKeyListener(key);
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
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
	
	public static int getWindowWidth() {
		return WIDTH * SCALE;
	}
	
	public static int getWindowHeight() {
		return HEIGHT * SCALE;
	}

	public void run() {
		long last_Time = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		requestFocus();
		while (running) {
			long time_Now = System.nanoTime();
			delta += (time_Now - last_Time) / ns;
			last_Time = time_Now;

			while(delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				_frames = frames;
				_ticks = ticks;
				ticks = 0;
				frames = 0;
			}
		}
		stop();
	}

	public void tick() {
		key.update();
		level.update();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		double xScroll = player.getX() - screen.WIDTH / 2;
		double yScroll = player.getY() - screen.HEIGHT / 2;
		level.render((int)xScroll, (int)yScroll, screen);

		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		//Test for debug display overlay
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 19));
		
		TileCoordinate tc = new TileCoordinate((int)player.getX(), (int)player.getY());

		Util.drawString(g, strVersion + "\nFPS: " + _frames + "\n" + "TPS: " + _ticks + "\nX: " + (int)player.getX() + " Y: " + (int)player.getY() + " [16offset]" + "\nT-P_X: " + tc.x() + " T-P_Y: " + tc.y() +"\nLevel: " + 
		"level1(" + level.getWidth() + "x" + level.getHeight() + ")" + "\nPlayer:" + player.getClass().getSimpleName()
		+ "\nEntities(P:Pr:Pa:M): " + level.getPlayersSize() + ":" + level.getProjectilesSize() + ":" + level.getParticlesSize() + ":" + level.getMobsSize() + "\n\nSCORE: " + player.getPoints(), 5, 1);
		
		g.dispose();
		bs.show();

	}

	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(gTitle);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}

}