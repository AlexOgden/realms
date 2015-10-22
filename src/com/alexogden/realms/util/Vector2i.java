package com.alexogden.realms.util;

public class Vector2i {
	
	private int x;
	private int y;
	
	public Vector2i() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2i(Vector2i vec) {
		set(vec.x, vec.y);
	}
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2i add(Vector2i vec) {
		this.x += vec.x;
		this.y += vec.y;
		return this;
	}
	
	public Vector2i subtract(Vector2i vec) {
		this.x -= vec.x;
		this.y -= vec.y;
		return this;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Vector2i setX(int x) {
		this.x = x;
		return this;
	}
	
	public Vector2i setY(int y) {
		this.y = y;
		return this;
	}
	
	public boolean equals(Object object) {
		if(!(object instanceof Vector2i)) return false;
		Vector2i vec = (Vector2i) object;
		if(vec.getX() == this.getX() && vec.getY() == this.getY()) return true;
		return false;
	}

}
