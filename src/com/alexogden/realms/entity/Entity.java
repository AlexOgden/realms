package com.alexogden.realms.entity;

import java.util.Random;

import com.alexogden.realms.graphics.Screen;
import com.alexogden.realms.graphics.Sprite;
import com.alexogden.realms.level.Level;

public abstract class Entity {
	
	protected double x, y;
	protected boolean removed = false;
	protected Level level;
	protected Sprite sprite;
	protected final Random RANDOM = new Random();
	
	public void update() {}
	
	public void render(Screen screen) {
		if(sprite != null) screen.renderSprite((int)x, (int)y, sprite, true);
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void init(Level level) {
		this.level = level;
	}

}
