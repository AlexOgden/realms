package com.alexogden.realms.level.tile;

import com.alexogden.realms.graphics.Screen;
import com.alexogden.realms.graphics.Sprite;

public class Tile {
	
	public int x,y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile();
	public static Tile grass_flowers_daisy = new GrassDaisyTile();
	public static Tile grass_flowers_rose = new GrassRoseTile();
	public static Tile grass_flowers_tulip = new GrassTulipTile();
	public static Tile grass_flowers_orange = new GrassOrangeFlowerTile();
	public static Tile stone_brick = new StoneBrickTile();
	public static Tile stone_brick_mossy = new StoneBrickMossyTile();
	public static Tile stone_brick_cracked = new StoneBrickCrackedTile();
	public static Tile stone_slab = new StoneSlabTile();
	public static Tile stone = new StoneTile();
	public static Tile sand = new SandTile();
	public static Tile planks_dark = new PlanksDarkTile();
	public static Tile planks_light = new PlanksLightTile();
	public static Tile planks_oak = new PlanksOakTile();
	public static Tile planks_acia = new PlanksAciaTile();
	public static Tile wood_floor = new WoodFloorTile();
	public static Tile snow = new SnowTile();
	public static Tile dirt = new DirtTile();
	

	public static final int GRASS = 0xff02FF02;
	public static final int GRASS_FLOWERS_DAISY = 0xffDDFF00;
	public static final int GRASS_FLOWERS_ROSE = 0xffFF5400;
	public static final int GRASS_FLOWERS_TULIP = 0xffFF7070;
	public static final int GRASS_FLOWERS_ORANGE = 0xffFF8850;
	public static final int STONE_BRICK = 0xff000000;
	public static final int STONE_BRICK_MOSSY = 0xff110000;
	public static final int STONE_BRICK_CRACKED = 0xff220000;
	public static final int STONE_SLAB = 0xff330033;
	public static final int STONE = 0xffAAFFEE;
	public static final int SAND = 0xffFFBA26;
	public static final int PLANKS_DARK = 0xff884400;
	public static final int PLANKS_LIGHT = 0xff996600;
	public static final int PLANKS_OAK = 0xff562900;
	public static final int PLANKS_ACIA = 0xff997A21;
	public static final int WOOD_FLOOR = 0xff722200;
	public static final int SNOW = 0xffDAF8FF;
	public static final int DIRT = 0xff5A3E29;
	
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen) {}
	
	public boolean solid() {
		return false;
	}
}