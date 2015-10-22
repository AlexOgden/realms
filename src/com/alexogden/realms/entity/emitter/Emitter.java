package com.alexogden.realms.entity.emitter;

import com.alexogden.realms.entity.Entity;
import com.alexogden.realms.entity.Type;
import com.alexogden.realms.level.Level;

public class Emitter extends Entity {
	
	//TODO:Stuff below:
	//Ensure that this actually works.
	//Null variables are a bad idea for this.
	//private Type type;

	public Emitter(int x, int y, Type eType, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		//this.type = eType;
	}

}
