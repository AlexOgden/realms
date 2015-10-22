package com.alexogden.realms.entity.mob;

import java.awt.Rectangle;

import com.alexogden.realms.entity.Entity;
import com.alexogden.realms.entity.projectile.Projectile;
import com.alexogden.realms.entity.projectile.WizzardProjectile;
import com.alexogden.realms.graphics.Screen;

public abstract class Mob extends Entity {
	protected boolean isMoving = false;
	protected boolean walking = false;
	protected boolean dead = false;
	
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	
	protected Direction dir;
	
	protected void move(double xa, double ya) {
		if(xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if(xa > 0) dir = Direction.RIGHT;
		if(xa < 0) dir = Direction.LEFT;
		if(ya > 0) dir = Direction.DOWN;
		if(ya < 0) dir = Direction.UP;

		while (xa != 0) {
			if (Math.abs(xa) > 1) {
				if (!collision(abs(xa), ya)) {
					this.x += abs(xa);
				}
				xa-= abs(xa);
			} else {
				if (!collision(abs(xa), ya)) {
					this.x += xa;
				}
				xa = 0;
			}
		}

		while (ya != 0) {
			if (Math.abs(ya) > 1) {
				if (!collision(xa, abs(ya))) {
					this.y += abs(ya);
				}
				ya-= abs(ya);
			} else {
				if (!collision(xa, abs(ya))) {
					this.y += ya;
				}
				ya = 0;
			}
		}

	}

	private int abs(double value) {
		if (value < 0) {
			return -1;
		} else {
			return 1;
		}
	}
	
	public abstract void update();
	
	protected void shoot(double x, double y, double dir) {
		Projectile p = new WizzardProjectile(x, y, dir, null);
		level.add(p);
	}
	
	public boolean collision(double xa, double ya) {
		boolean solid = false;
		for(int c = 0; c < 4; c++) {
			double xt = ((x + xa) + c % 2 * 31 - 16) / 16;
			double yt = ((y + ya) + c / 2 * 15) / 16;		//Y-Axis collision, with head room
															//int yt = ((y + ya) + c / 2 * 32 - 16) >> 4;	//Y-Axis full collision
			if(level.getTile((int)xt, (int)yt).solid() == true) solid = true;
		}
		return solid;
	}
	
	public Rectangle bounds() {
	    return (new Rectangle((int)x - 16, (int)y - 16, sprite.SIZE + 1, sprite.SIZE - 5));
	}
	
	public void kill() {
		
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void render(Screen screen) {
		
	}
}
