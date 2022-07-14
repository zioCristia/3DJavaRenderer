package renderer.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

	private int mouseX = -1;
	private int mouseY = -1;
	private int mouseB = -1;
	private int scroll = 0;

	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
	public int getButton() {
		return this.mouseB;
	}
	public ClickType getMouseB() {
		switch (this.mouseB) {
		case 1:
			return ClickType.LeftClick;
		case 2:
			return ClickType.ScrollClick;
		case 3:
			return ClickType.RightClick;
		default:
			return ClickType.Unkown;
		}
	}

	public void setMouseB(int mouseB) {
		this.mouseB = mouseB;
	}

	public boolean isScrollingUp() {
		return this.scroll == -1;
	}
	public boolean isScrollingDown() {
		return this.scroll == 1;
	}
	public void resetScroll() {
		this.scroll = 0;
	}
 
	public int getScroll() {
		return scroll;
	}

	public void setScroll(int scroll) {
		this.scroll = scroll;
	}

	public void resetButton() {
		this.mouseB = -1;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.mouseX = e.getX();
		this.mouseY = e.getY();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll = e.getWheelRotation();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("Clicked");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.mouseB = e.getButton();
//		System.out.println("Pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.mouseB = -1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
