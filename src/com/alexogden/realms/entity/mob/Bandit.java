package com.alexogden.realms.entity.mob;

import java.util.List;

import com.alexogden.realms.graphics.AnimatedSprite;
import com.alexogden.realms.graphics.Screen;
import com.alexogden.realms.graphics.SpriteSheet;
import com.alexogden.realms.level.Node;
import com.alexogden.realms.util.Vector2i;

public class Bandit extends Mob {
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.archer_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.archer_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.archer_left, 32, 32, 2);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.archer_right, 32, 32, 2);
	
	private AnimatedSprite animSprite = down;

	private double xa = 0;
	private double ya = 0;
	private List<Node> path;
	private int time = 0;

	public Bandit(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
	}
	
	private void move() {
		xa = 0;
		ya = 0;

		int px = (int)level.getPlayerAt(0).getX();
		int py = (int)level.getPlayerAt(0).getY();

		Vector2i start = new Vector2i((int)getX() >> 4, (int)getY() >> 4);
		Vector2i destination = new Vector2i(px >> 4, py >> 4);

		if (time % 2 == 0) path = level.findPath(start, destination);

		if (path != null) {
			if (path.size() > 0) {
				Vector2i vec = path.get(path.size() - 1).tile;
				if (x < vec.getX() << 4) xa++;
				if (x > vec.getX() << 4) xa--;
				if (y < vec.getY() << 4) ya++;
				if (y > vec.getY() << 4) ya--;
			}
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else {
			walking = false;
		}

	}
	
	public void update() {
		time++;
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		
		if (xa < 0) {
			animSprite = left;
			animSprite.setFrameRate(10);
			dir = Direction.LEFT;
		}
		
		if (xa > 0) {
			animSprite = right;
			animSprite.setFrameRate(10);
			dir = Direction.RIGHT;
		}
		
		if (ya < 0) {
			animSprite = up;
			animSprite.setFrameRate(7);
			if(animSprite.getFrame() == 0)
				animSprite.setFrame(1);
			else
				animSprite.setFrame(2);
			dir = Direction.UP;
		}
		
		if (ya > 0) {
			animSprite = down;
			animSprite.setFrameRate(7);
			if(animSprite.getFrame() == 0)
				animSprite.setFrame(1);
			else
				animSprite.setFrame(2);
			dir = Direction.DOWN;
		}
		move();
		
	}
	
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int) (y - 16), this);
	}

}
