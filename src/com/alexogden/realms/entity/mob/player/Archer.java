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

public class Archer extends Mob implements Player {
	
	public Keyboard keyInput;
	public Sprite sprite;
	int anim = 0;
	public boolean walking = false;
	
	private int score = 0;
	
	Projectile p;
	private int fireRate = 0;
	
	public Archer(Keyboard input) {
		keyInput = input;
		dir = Direction.DOWN;
		fireRate = WizzardProjectile.FIRE_RATE; //TODO: Change to Arrow
	}
	
	public Archer(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		keyInput = input;
		dir = Direction.DOWN;
		fireRate = WizzardProjectile.FIRE_RATE; //TODO: Change to Arrow
	}
	
	public void update() {
		if(fireRate > 0) fireRate--;
		
		double walkRate = 1;
		
		if(keyInput.shift) {
			walkRate = 1.5;
		}else{
			walkRate = 1;
		}
		
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
		
		anim++;
		updateShooting();
		clear();
	}
	
	private void updateShooting() {
		if(Mouse.getButton() == 1 && fireRate == 0) {
			double dx = Math.abs(Mouse.getX()) - Game.getWindowWidth() / 2;
			double dy = Math.abs(Mouse.getY()) - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
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
			fireRate = WizzardProjectile.FIRE_RATE; //TODO: Change to Arrow
		}
	}
	
	protected void shoot(double x, double y, double dir) {
		Projectile p = new WizzardProjectile(x, y, dir, this);
		level.add(p);
	}
	
	public void render(Screen screen) {
		if(dir == Direction.UP) { 
			sprite = Sprite.archer_fw;
			if(walking) {
				if(anim % 20 > 10) {
					sprite = Sprite.archer_fw_1;
				}
				else {
					sprite = Sprite.archer_fw_2;
				}
			}
		}
		if(dir == Direction.DOWN) {
			sprite = Sprite.archer_bw;
			if(walking) {
				if(anim % 20 > 10) {
					sprite = Sprite.archer_dw_1;
				}
				else {
					sprite = Sprite.archer_dw_2;
				}
			}
		}
		if(dir == Direction.RIGHT) {
			sprite = Sprite.archer_right;
			if(walking) {
				if(anim % 20 > 10) {
					sprite = Sprite.archer_right_1;
				}
				else {
					sprite = Sprite.archer_right_2;
				}
			}
		}
		if(dir == Direction.LEFT) {
			sprite = Sprite.archer_left;
			if(walking) {
				if(anim % 20 > 10) {
					sprite = Sprite.archer_left_1;
				}
				else {
					sprite = Sprite.archer_left_2;
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
	    return (new Rectangle((int)x, (int)y, sprite.SIZE - 1, sprite.SIZE - 1));
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
