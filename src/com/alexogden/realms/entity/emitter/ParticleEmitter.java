package com.alexogden.realms.entity.emitter;

import com.alexogden.realms.entity.Type;
import com.alexogden.realms.entity.particle.Particle;
import com.alexogden.realms.level.Level;

public class ParticleEmitter extends Emitter {
	
	private int life;

	public ParticleEmitter(int x, int y, int life, int amount, Level level, int colour) {
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;
		for(int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, this.life, colour));
		}
	}

}
