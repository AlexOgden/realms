package com.alexogden.realms.entity.projectile;

import com.alexogden.realms.entity.Entity;
import com.alexogden.realms.entity.mob.player.Player;
import com.alexogden.realms.graphics.Sprite;

public abstract class Projectile extends Entity {
	
	protected final float xOrigin, yOrigin;
	protected double angle;
	protected double x, y;
	protected double nx, ny;
	protected double speed;
	protected double range;
	protected double damage;
	protected double distance;
	
	protected Player parent;
	
	protected Sprite sprite;
	
	public Projectile(double x, double y, double dir, Player parent) {
		xOrigin = (float) x;
		yOrigin = (float) y;
		angle = dir;
		this.x = x;
		this.y = y;
		this.parent = parent;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getSpriteSize() {
		return sprite.SIZE;
	}
	
	protected void move() {}

}
