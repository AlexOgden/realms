package com.alexogden.realms.entity.mob.player;

import java.awt.Rectangle;

import com.alexogden.realms.Game;
import com.alexogden.realms.entity.mob.Mob;
import com.alexogden.realms.entity.projectile.Projectile;
import com.alexogden.realms.entity.projectile.WizzardProjectile;
import com.alexogden.realms.graphics.Screen;
import com.alexogden.realms.graphics.Sprite;
import com.alexogden.realms.input.Keyboard;
import com.alexogden.realms.input.Mouse;

public class Wizzard extends Mob implements Player {
	
	public Keyboard keyInput;
	public Sprite sprite;
	int anim = 0;
	public boolean walking = false;
	
	private int score = 0;
	
	Projectile p;
	private int fireRate = 0;
	
	public Wizzard(Keyboard input) {
		keyInput = input;
		dir = Direction.DOWN;
		fireRate = WizzardProjectile.FIRE_RATE;
	}
	
	public Wizzard(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		keyInput = input;
		dir = Direction.DOWN;
		fireRate = WizzardProjectile.FIRE_RATE;
	}
	
	public void update() {
		if(fireRate > 0) fireRate--;
		
		double walkRate = 1;

		walkRate = (keyInput.shift) ? 1.8 : 1;
		
		double xa = 0, ya = 0;
		
		if(keyInput.up) {
			ya -= walkRate;
		} else if(keyInput.down) {
			ya =+ walkRate;
		}

		if(keyInput.left) {
			xa -= walkRate;
		} else if(keyInput.right) {
			xa += walkRate;
		}
		
		if(xa != 0 || ya != 0) {
			move(xa,ya);
			walking = true;
		} else {
			walking = false;
		}
		
		++anim;
		updateShooting();
		clear();
	}
	
	private void updateShooting() {
		if(Mouse.getButton() == 1 && fireRate == 0) {
			double dx = Math.abs(Mouse.getX()) - Game.getWindowWidth() / 2;
			double dy = Math.abs(Mouse.getY()) - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			//If player entity is still, change direction of entity based on projectile dir
			if(!walking) {
				if(Math.round(dir) == -1) this.dir = Direction.UP;
				if(Math.round(dir) == -2) this.dir = Direction.UP;
				if(Math.round(dir) == 0) this.dir = Direction.RIGHT;
				if(Math.round(dir) == 1) this.dir = Direction.DOWN;
				if(Math.round(dir) == 2) this.dir = Direction.DOWN;
				if(Math.round(dir) == 3) this.dir = Direction.LEFT;
				if(Math.round(dir) == -3) this.dir = Direction.LEFT;
				System.out.println(Math.round(dir));
			}
			shoot(x, y, dir);
			fireRate = WizzardProjectile.FIRE_RATE;
		}
	}
	
	protected void shoot(double x, double y, double dir) {
		Projectile p = new WizzardProjectile(x, y, dir, this);
		level.add(p);
	}
	
	public void render(Screen screen) {
		if(dir == Direction.UP) { 
			sprite = Sprite.wizzard_fw;
			if(walking) {
				if(anim % 20 > 10) {
					sprite = Sprite.wizzard_fw_1;
				}
				else {
					sprite = Sprite.wizzard_fw_2;
				}
			}
		}
		if(dir == Direction.DOWN) {
			sprite = Sprite.wizzard_bw;
			if(walking) {
				if(anim % 20 > 10) {
					sprite = Sprite.wizzard_dw_1;
				}
				else {
					sprite = Sprite.wizzard_dw_2;
				}
			}
		}
		if(dir == Direction.RIGHT) {
			sprite = Sprite.wizzard_right;
			if(walking) {
				if(anim % 20 > 10) {
					sprite = Sprite.wizzard_right_1;
				}
				else {
					sprite = Sprite.wizzard_right_2;
				}
			}
		}
		if(dir == Direction.LEFT) {
			sprite = Sprite.wizzard_left;
			if(walking) {
				if(anim % 20 > 10) {
					sprite = Sprite.wizzard_left_1;
				}
				else {
					sprite = Sprite.wizzard_left_2;
				}
			}
		}
		if(anim == Integer.MAX_VALUE)
			anim = 0;
		screen.renderMob((int) (x-16), (int) (y-16), sprite);
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public int getSpriteSize() {
		return this.sprite.SIZE;
	}
	
	public void remove() {
		removed = true;
	}
	
	public Rectangle bounds() {
	    return (new Rectangle((int)x - 16, (int)y - 16, sprite.SIZE + 1, sprite.SIZE + 1));
	}
	
	public void clear() {
		for(int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if(p.isRemoved()) level.getProjectiles().remove(i);
		}
	}
	
	public void addPoint(int points) {
		score += points;
	}

	public void subtractPoint() {
		score--;
	}

	public int getPoints() {
		return score;
	}
}
