package com.alexogden.realms.graphics;

import com.alexogden.realms.entity.mob.Bandit;
import com.alexogden.realms.entity.mob.Mob;
import com.alexogden.realms.entity.projectile.Projectile;
import com.alexogden.realms.level.tile.Tile;

public class Screen {
	
	public int WIDTH;
	public int HEIGHT;
	public int[] pixels; 
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	
	public int xOffset, yOffset;
	
	public Screen(int WIDTH, int HEIGHT) {
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		pixels = new int[WIDTH * HEIGHT];
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}

		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for(int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= WIDTH || ya < 0 || ya >= HEIGHT) continue;
				pixels[xa + ya * WIDTH] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for(int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if(xa < -tile.sprite.SIZE || xa >= WIDTH || ya < 0 || ya >= HEIGHT) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * WIDTH] = tile.sprite.pixels[x + y * tile.sprite.SIZE]; 
			}
		}
	}
	
	public void renderProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yp;
			for(int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				if(xa < -p.getSpriteSize() || xa >= WIDTH || ya < 0 || ya >= HEIGHT) break;
				if (xa < 0) xa = 0;
				int col = p.getSprite().pixels[x + y * p.getSpriteSize()];
				if(col != 0xffFF00FF && col != 0xff490049) {
					pixels[xa + ya * WIDTH] = col;
				}
			}
		}
	}
	
	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}

		for (int y = 0; y < sheet.HEIGHT; y++) {
			int ya = y + yp;
			for (int x = 0; x < sheet.WIDTH; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= WIDTH || ya < 0 || ya >= HEIGHT)
					continue;
				pixels[xa + ya * WIDTH] = sheet.pixels[x + y * sheet.WIDTH];
			}
		}
	}
	
	public void renderMob(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < 32; y++) {
			int ya = y + yp;
			for(int x = 0; x < 32; x++) {
				int xa = x + xp;
				if(xa < -32 || xa >= WIDTH || ya < 0 || ya >= HEIGHT) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[x+y*sprite.SIZE];
				if(col != 0xffFF00FF && col != 0xff490049) pixels[xa + ya * WIDTH] = col;
			}
		}
	}
	
	public void renderMob(int xp, int yp, Mob mob) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < 32; y++) {
			int ya = y + yp;
			for(int x = 0; x < 32; x++) {
				int xa = x + xp;
				if(xa < -32 || xa >= WIDTH || ya < 0 || ya >= HEIGHT) break;
				if (xa < 0) xa = 0;
				int col = mob.getSprite().pixels[x + y * mob.getSprite().SIZE];
				if(mob instanceof Bandit && col == 0xff8C6239) col = 0xffFF0000;
				if(col != 0xffFF00FF && col != 0xff490049) pixels[xa + ya * WIDTH] = col;
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
		
}
