package com.alexogden.realms.entity.mob;

import java.util.List;

import com.alexogden.realms.entity.emitter.ParticleEmitter;
import com.alexogden.realms.entity.mob.player.Player;
import com.alexogden.realms.graphics.AnimatedSprite;
import com.alexogden.realms.graphics.Screen;
import com.alexogden.realms.graphics.Sprite;
import com.alexogden.realms.graphics.SpriteSheet;

public class RandomMob extends Mob {
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.archer_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.archer_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.archer_left, 32, 32, 2);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.archer_right, 32, 32, 2);
	
	private AnimatedSprite animSprite = down;
	private int time = 0;
	private int xa = 1;
	private int ya = 0;
	
	public RandomMob(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.archer_bw;
		animSprite.setFrameRate(10);
	}

	public void update() {
		List<Player> players = level.getPlayers(this, 90);

		time++;
		if (time % (RANDOM.nextInt(60) + 20) == 0) {
			xa = RANDOM.nextInt(3) - 1;
			ya = RANDOM.nextInt(3) - 1;
			if (RANDOM.nextInt(55) == 0) {
				xa = 0;
				ya = 0;
			}
		}

		if (players.size() > 0) {
			for (int i = 0; i < players.size(); i++) {
				if(this.bounds().intersects(players.get(i).bounds())) {
					xa = 0;
					ya = 0;
				}
			}
		}

		if (walking)
			animSprite.update();
		else
			animSprite.setFrame(0);

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
			animSprite.setFrameRate(8);
			if (animSprite.getFrame() == 0)
				animSprite.setFrame(1);
			else
				animSprite.setFrame(2);
			dir = Direction.UP;
		}

		if (ya > 0) {
			animSprite = down;
			animSprite.setFrameRate(8);
			if (animSprite.getFrame() == 0)
				animSprite.setFrame(1);
			else
				animSprite.setFrame(2);
			dir = Direction.DOWN;
		}

		if (dead) {
			walking = false;
		} else {
			if (xa != 0 || ya != 0) {
				move(xa, ya);
				walking = true;
			} else {
				walking = false;
			}
		}

	}
	
	public void kill() {
		this.sprite = Sprite.archer_dead;
		new ParticleEmitter((int)x, (int)y, 1000, 200, level, 0xFF0000);
		dead = true;
	}
	
	public void render(Screen screen) {
		if(!dead)
			sprite = animSprite.getSprite();
		else
			sprite = Sprite.archer_dead;
		screen.renderMob((int) (x - 16), (int) (y - 16), this);
	}

}
