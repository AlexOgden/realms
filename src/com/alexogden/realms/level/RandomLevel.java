package com.alexogden.realms.level;

import java.util.Random;

public class RandomLevel extends Level {

	private static final Random random = new Random();
	
	public RandomLevel(int width, int height) {
		super(width, height);
	}
	
	protected void generateLevel() {
		for(int y = 0; y < HEIGHT; y++) {
			for(int x = 0; x < WIDTH; x++) {
				tilesInt[x + y * WIDTH] = random.nextInt(4);
			}
		}
	}

}
