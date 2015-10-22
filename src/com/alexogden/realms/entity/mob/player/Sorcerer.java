package com.alexogden.realms.entity.mob.player;

import java.awt.Rectangle;

import com.alexogden.realms.entity.mob.Mob;
import com.alexogden.realms.entity.projectile.Projectile;
import com.alexogden.realms.entity.projectile.WizzardProjectile;
import com.alexogden.realms.graphics.Screen;
import com.alexogden.realms.graphics.Sprite;
import com.alexogden.realms.input.Keyboard;
import com.alexogden.realms.input.Mouse;

public class Sorcerer extends Mob implements Player {
	
	public Keyboard keyInput;
	public Sprite sprite;
	private int anim = 0;
	public boolean walking = false;
	
	private int score = 0;
	
	Projectile p;
	private int fireRate = 0;
	
	public Sorcerer(Keyboard input) {
		keyInput = input;
		dir = Direction.DOWN;
		fireRate = WizzardProjectile.FIRE_RATE;
	}
	
	public Sorcerer(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		keyInput = input;
		dir = Direction.DOWN;
		fireRate = WizzardProjectile.FIRE_RATE;
	}
	
	public void update() {
		if(fireRate > 0) fireRate--;
		
		int xa = 0, ya = 0;
		if(anim < 8000) anim++;
		else anim = 0;
		if(keyInput.up) ya--;
		if(keyInput.down) ya++;
		if(keyInput.left) xa--;
		if(keyInput.right) xa++;
		if(xa != 0 || ya != 0) {
			move(xa,ya);
			walking = true;
		} else {
			walking = false;
		}
		
		updateShooting();
		clear();
		
	}
	
	private void updateShooting() {
		if(Mouse.getButton() == 1 && fireRate == 0) {
			double dx = Math.abs(Mouse.getX()) - (300 * 3) / 2;
			double dy = Math.abs(Mouse.getY()) - (168 * 3) / 2;
			double dir = Math.atan2(dy, dx);
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
			sprite = Sprite.sorcerer_fw;
			if(walking) {
				if(anim % 20 > 10) {
					sprite = Sprite.sorcerer_fw_1;
				}
				else {
					sprite = Sprite.sorcerer_fw_2;
				}
			}
		}
		if(dir == Direction.DOWN) {
			sprite = Sprite.sorcerer_bw;
			if(walking) {
				if(anim % 20 > 10) {
					sprite = Sprite.sorcerer_dw_1;
				}
				else {
					sprite = Sprite.sorcerer_dw_2;
				}
			}
		}
		if(dir == Direction.RIGHT) {
			sprite = Sprite.sorcerer_right;
			if(walking) {
				if(anim % 20 > 10) {
					sprite = Sprite.sorcerer_right_1;
				}
				else {
					sprite = Sprite.sorcerer_right_2;
				}
			}
		}
		if(dir == Direction.LEFT) {
			sprite = Sprite.sorcerer_left;
			if(walking) {
				if(anim % 20 > 10) {
					sprite = Sprite.sorcerer_left_1;
				}
				else {
					sprite = Sprite.sorcerer_left_2;
				}
			}
		}
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
	
	public Rectangle bounds() {
	    return (new Rectangle((int)x, (int)y, sprite.SIZE, sprite.SIZE));
	}
	
	public void remove() {
		removed = true;
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
