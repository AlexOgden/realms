package com.alexogden.realms.graphics;

public class Sprite {
	
	public final int SIZE;
	private int x, y;
	private int width, height;
	public int[] pixels;
	protected SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite grass_flowers_daisy = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite grass_flowers_rose = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite grass_flowers_tulip = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite grass_flowers_orange = new Sprite(16, 4, 0, SpriteSheet.tiles);
	public static Sprite stone_brick = new Sprite(16, 5, 0, SpriteSheet.tiles);
	public static Sprite stone_brick_mossy = new Sprite(16, 6, 0, SpriteSheet.tiles);
	public static Sprite stone_brick_cracked = new Sprite(16, 7, 0, SpriteSheet.tiles);
	public static Sprite stone_slab = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite stone = new Sprite(16, 1, 1, SpriteSheet.tiles);
	public static Sprite wood_floor = new Sprite(16, 2, 1, SpriteSheet.tiles);
	public static Sprite sand = new Sprite(16, 3, 1, SpriteSheet.tiles);
	public static Sprite planks_dark = new Sprite(16, 4, 1, SpriteSheet.tiles);
	public static Sprite planks_light = new Sprite(16, 5, 1, SpriteSheet.tiles);
	public static Sprite planks_oak = new Sprite(16, 6, 1, SpriteSheet.tiles);
	public static Sprite planks_acia = new Sprite(16, 7, 1, SpriteSheet.tiles);
	public static Sprite dirt = new Sprite(16, 0, 2, SpriteSheet.tiles);
	public static Sprite snow = new Sprite(16, 1, 2, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0x1B87E0);
	
	//Archer
	
	public static Sprite archer_fw = new Sprite(32, 1, 0, SpriteSheet.archer);
	public static Sprite archer_fw_1 = new Sprite(32, 1, 1, SpriteSheet.archer);
	public static Sprite archer_fw_2 = new Sprite(32, 1, 2, SpriteSheet.archer);
	
	public static Sprite archer_bw = new Sprite(32, 0, 0, SpriteSheet.archer);
	public static Sprite archer_dw_1 = new Sprite(32, 0, 1, SpriteSheet.archer);
	public static Sprite archer_dw_2 = new Sprite(32, 0, 2, SpriteSheet.archer);
	
	public static Sprite archer_right = new Sprite(32, 3, 0, SpriteSheet.archer);
	public static Sprite archer_right_1 = archer_right;
	public static Sprite archer_right_2 = new Sprite(32, 3, 1, SpriteSheet.archer);
	
	public static Sprite archer_left = new Sprite(32, 2, 0, SpriteSheet.archer);
	public static Sprite archer_left_1 = archer_left;
	public static Sprite archer_left_2 = new Sprite(32, 2, 1, SpriteSheet.archer);
	
	public static Sprite archer_dead = new Sprite(32, 3, 2, SpriteSheet.archer);
	
	//Sorcerer
	public static Sprite sorcerer_fw = new Sprite(32, 1, 0, SpriteSheet.sorcerer);
	public static Sprite sorcerer_fw_1 = new Sprite(32, 1, 1, SpriteSheet.sorcerer);
	public static Sprite sorcerer_fw_2 = new Sprite(32, 1, 2, SpriteSheet.sorcerer);
	
	public static Sprite sorcerer_bw = new Sprite(32, 0, 0, SpriteSheet.sorcerer);
	public static Sprite sorcerer_dw_1 = new Sprite(32, 0, 1, SpriteSheet.sorcerer);
	public static Sprite sorcerer_dw_2 = new Sprite(32, 0, 2, SpriteSheet.sorcerer);
	
	public static Sprite sorcerer_right = new Sprite(32, 3, 0, SpriteSheet.sorcerer);
	public static Sprite sorcerer_right_1 = sorcerer_right;
	public static Sprite sorcerer_right_2 = new Sprite(32, 3, 1, SpriteSheet.sorcerer);
	
	public static Sprite sorcerer_left = new Sprite(32, 2, 0, SpriteSheet.sorcerer);
	public static Sprite sorcerer_left_1 = sorcerer_left;
	public static Sprite sorcerer_left_2 = new Sprite(32, 2, 1, SpriteSheet.sorcerer);
	
	//Wizzard
	public static Sprite wizzard_fw = new Sprite(32, 1, 0, SpriteSheet.wizzard);
	public static Sprite wizzard_fw_1 = new Sprite(32, 1, 1, SpriteSheet.wizzard);
	public static Sprite wizzard_fw_2 = new Sprite(32, 1, 2, SpriteSheet.wizzard);
	
	public static Sprite wizzard_bw = new Sprite(32, 0, 0, SpriteSheet.wizzard);
	public static Sprite wizzard_dw_1 = new Sprite(32, 0, 1, SpriteSheet.wizzard);
	public static Sprite wizzard_dw_2 = new Sprite(32, 0, 2, SpriteSheet.wizzard);
	
	public static Sprite wizzard_right = new Sprite(32, 3, 0, SpriteSheet.wizzard);
	public static Sprite wizzard_right_1 = wizzard_right;
	public static Sprite wizzard_right_2 = new Sprite(32, 3, 1, SpriteSheet.wizzard);
	
	public static Sprite wizzard_left = new Sprite(32, 2, 0, SpriteSheet.wizzard);
	public static Sprite wizzard_left_1 = wizzard_left;
	public static Sprite wizzard_left_2 = new Sprite(32, 2, 1, SpriteSheet.wizzard);
	//Arrows
	public static Sprite arrow_top_left = new Sprite(16, 0, 0, SpriteSheet.arrows);
	public static Sprite arrow_top_right = new Sprite(16, 1, 0, SpriteSheet.arrows);
	public static Sprite arrow_bottom_right = new Sprite(16, 2, 0, SpriteSheet.arrows);
	public static Sprite arrow_bottom_left = new Sprite(16, 0, 1, SpriteSheet.arrows);
	public static Sprite arrow_down = new Sprite(16, 1, 1, SpriteSheet.arrows);
	public static Sprite arrow_up = new Sprite(16, 2, 1, SpriteSheet.arrows);
	public static Sprite arrow_right = new Sprite(16, 0, 2, SpriteSheet.arrows);
	public static Sprite arrow_left = new Sprite(16, 1, 2, SpriteSheet.arrows);
	
	//Particles
	public static Sprite particle_normal = new Sprite(2, 0xFF0000);
	
	protected Sprite(SpriteSheet sheet, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
		
	}
	
	public Sprite(int size, int colour) {
		this.width = size;
		this.height = size;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColour(colour);

	}
	
	public Sprite(int[] spritePixels, int width, int height) {
		if(width == height) SIZE = width;
		else SIZE = -1;
		
		this.width = width;
		this.height = height;
		this.pixels = spritePixels;
	}
	
	public Sprite(int width, int height, int colour) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColour(colour);
	}
	

	private void setColour(int colourID) {
		for(int i = 0; i < SIZE*SIZE; i++) {
			pixels[i] = colourID;
		}
		
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }

	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
			}
		}

	}

}