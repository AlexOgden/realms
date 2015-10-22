package com.alexogden.realms.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	private boolean[] keys = new boolean[120];
	public boolean up, down, left, right;
	public boolean shift;
	
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		shift = keys[KeyEvent.VK_SHIFT];
		
		for(int i = 0; i < keys.length; i++) {
			if(keys[i]) {
				System.out.print("KEY:" + i + "\n");
			}
		}
	}
   
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
}
