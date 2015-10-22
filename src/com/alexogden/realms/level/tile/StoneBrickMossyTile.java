package com.alexogden.realms.level.tile;

import com.alexogden.realms.graphics.Screen;
import com.alexogden.realms.graphics.Sprite;

public class StoneBrickMossyTile extends Tile {
	
	private static final Sprite tSprite = Sprite.stone_brick_mossy;

	public StoneBrickMossyTile() {
		super(tSprite);
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid(){
		return true;
	}

}
