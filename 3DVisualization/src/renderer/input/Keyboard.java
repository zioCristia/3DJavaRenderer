package renderer.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	private boolean[] keys = new boolean[66568];
	private boolean left, right, up, down, forward, backward;

	public void update() {
		this.left = this.keys[KeyEvent.VK_LEFT] || this.keys[KeyEvent.VK_A];
		this.right = this.keys[KeyEvent.VK_RIGHT] || this.keys[KeyEvent.VK_D];
		this.forward = this.keys[KeyEvent.VK_UP] || this.keys[KeyEvent.VK_W];
		this.backward = this.keys[KeyEvent.VK_DOWN] || this.keys[KeyEvent.VK_S];
	}
	
	public boolean getDown() {
		return this.down;
	}
	
	public boolean[] getKeys() {
		return keys;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isUp() {
		return up;
	}

	public boolean isForward() {
		return forward;
	}

	public boolean isBackward() {
		return backward;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
