package renderer.entity;

import java.awt.Graphics;

public interface IEntity {
	void render(Graphics g);
	
	void rotate(boolean CW, double xDegreed, double yDegrees, double zDegrees);
}
