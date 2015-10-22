package com.alexogden.realms.entity.projectile;

import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import com.alexogden.realms.entity.emitter.ParticleEmitter;
import com.alexogden.realms.entity.mob.ChaserMob;
import com.alexogden.realms.entity.mob.Mob;
import com.alexogden.realms.entity.mob.RandomMob;
import com.alexogden.realms.entity.mob.player.Player;
import com.alexogden.realms.graphics.Screen;
import com.alexogden.realms.graphics.Sprite;

public class WizzardProjectile extends Projectile {
	
	protected final Random rand_Sprite_col = new Random();
	public static final short FIRE_RATE = 30;
	
	public WizzardProjectile(double x, double y, double dir, Player parent) {
		super(x, y, dir, parent);
		range = 200;
		damage = 40;
		speed = 3.6;
		sprite = new Sprite(5, rand_Sprite_col.nextInt(0xFFFFFF));
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update() {
		move();
	}
	
	protected void move() {
		mobCollision();
		if (!level.tileCollision(x, y, nx, ny, 5)){
			x += nx;
			y += ny;
		}
		else {
			remove();
			new ParticleEmitter((int)x, (int)y, 100, 30, level, -1);
		}
		
		if(distance() > range) remove();
	}
	
	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}
	
	public boolean mobCollision() {
		boolean collision = false;
		Rectangle thisE = this.bounds(); 
		Rectangle mob;
		List<Mob> mobs = level.getMobs();
		for (int i = 0; i < mobs.size(); i++) {
			mob = mobs.get(i).bounds();
			if(thisE.intersects(mob))
			{
				//mobs.get(i).remove();
				
				new ParticleEmitter((int)x, (int)y, 225, 60, level, 0xFF0000);
				if(!mobs.get(i).isDead()) {
					if(mobs.get(i) instanceof RandomMob) parent.addPoint(1);
					if(mobs.get(i) instanceof ChaserMob) parent.addPoint(2);
					mobs.get(i).kill();
				}
				
				remove();
				return false;
			}

		}
		return collision;
	}
	
	public Rectangle bounds() {
	    return (new Rectangle((int)x, (int)y, sprite.SIZE, sprite.SIZE));
	}

	public void render(Screen screen) {
		screen.renderProjectile((int)x, (int)y, this);
	}
}
