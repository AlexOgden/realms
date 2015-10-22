package com.alexogden.realms.entity.mob.player;

import java.awt.Rectangle;

import com.alexogden.realms.graphics.Screen;
import com.alexogden.realms.level.Level;

public interface Player {
	
	public void update();
	public void render(Screen screen);
	public void init(Level lvl);
	public void remove();
	public double getX();
	public double getY();
	public int getSpriteSize();
	public Rectangle bounds();
	public void addPoint(int points);
	public void subtractPoint();
	public int getPoints();
	void clear();

}
