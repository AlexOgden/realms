package com.alexogden.realms.level.tile;

import com.alexogden.realms.graphics.Screen;
import com.alexogden.realms.graphics.Sprite;

public class WaterTile extends Tile {
	
	private double slowSpeed = 0.5;

	public WaterTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return false;
	}
	
	public boolean isSlow() {
		return true;
	}
	
	public double getSlowSpeed() {
		return slowSpeed;
	}

}
