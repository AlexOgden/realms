package com.alexogden.realms.entity.projectile;

import com.alexogden.realms.entity.mob.player.Player;
import com.alexogden.realms.graphics.Screen;
import com.alexogden.realms.graphics.Sprite;

public class ArrowProjectile extends Projectile {
	
	public static final short FIRE_RATE = 10;
	
	public ArrowProjectile(int x, int y, double dir, Player parent) {
		super(x, y, dir, parent);
		range = 200;
		damage = 40;
		speed = 3.2;
		sprite = Sprite.arrow_top_right;

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update() {
		move();
	}
	
	protected void move() {
		if (!level.tileCollision(x, y, nx, ny, 2)){
			x += nx;
			y += ny;
		} else {remove();}
		if(distance() > range) remove();
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}
	
	public void render(Screen screen) {
		screen.renderProjectile((int)x, (int)y, this);
	}

}
