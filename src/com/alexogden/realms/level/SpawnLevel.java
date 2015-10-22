package com.alexogden.realms.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.alexogden.realms.entity.mob.ChaserMob;
import com.alexogden.realms.entity.mob.RandomMob;

public class SpawnLevel extends Level {

	public SpawnLevel(String path) {
		super(path);
	}
	
	protected void generateLevel() {

	}
	
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = WIDTH = image.getWidth();
			int h = HEIGHT = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to load level file!");
		}
		
		add(new ChaserMob(50, 12));
		add(new ChaserMob(150, 24));
		//add(new Bandit(50, 15));
		
		for(int i = 0; i < 2; i++) {
			add(new RandomMob(5, 10));
			add(new RandomMob(30, 12));
			add(new RandomMob(100, 20));
		}
		
		
	}

}
