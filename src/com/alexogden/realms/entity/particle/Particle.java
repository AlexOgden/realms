package com.alexogden.realms.entity.particle;

import com.alexogden.realms.entity.Entity;
import com.alexogden.realms.graphics.Screen;
import com.alexogden.realms.graphics.Sprite;

public class Particle extends Entity {
	private Sprite sprite;
	private int time = 0;
	private int life;
	protected double xx, yy, zz;
	protected double xa, ya, za;
	
	private int[] pColours = {0xFF3045, 0xBB32FF, 0x4F3FFF, 0xDD59FF, 0xFF750C, 0xFF0004, 0x0CFF3D, 0xFFEF19, 0xA8FFEF}; 

	public Particle(int x, int y, int life, int colour) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (RANDOM.nextInt(35) - 15);

		if (colour == -1) {
			sprite = new Sprite(2, pColours[RANDOM.nextInt(9)]);
		}
		else if(colour == -2) {
			sprite = Sprite.particle_normal;
		}
		else if(colour != -1 && colour != -2) {
			sprite = new Sprite(2, colour);
		}
		this.xa = RANDOM.nextGaussian();
		this.ya = RANDOM.nextGaussian();
		this.zz = RANDOM.nextFloat() + 3.6;
	}

	public void update() {
		time++;
		if(time >= 7500) time = 0;
		if(time > life) remove();
		za -= 0.1;

		if(zz < 0) {
			zz = 0;
			za *= -0.5955;
			xa *= 0.3;
			ya *= 0.3;
		}

		move((xx + xa), (yy + ya) + (zz + za));
	}

	public boolean collision(double x, double y) {
		boolean solid = false;
		for(int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 15) / 16;
			double yt = (y - c / 2 * 15) / 16;

			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if(c % 2 == 0) ix = (int) Math.floor(xt);
			if(c / 2 == 0) iy = (int) Math.floor(yt);
			if(level.getTile(ix, iy).solid() == true) solid = true;
		}
		return solid;
	}

	private void move(double x, double y) {
		if(collision(x, y)){
			this.xa *= -1;
			this.ya *= -1;
			this.za *= -1;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;

	}

	public void render(Screen screen) {
		screen.renderSprite((int)xx, ((int)yy + 4) - (int) zz, sprite, true);
	}
	
	public boolean isRemoved() {
		return removed;
	}

}