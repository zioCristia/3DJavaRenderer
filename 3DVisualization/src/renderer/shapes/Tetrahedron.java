package renderer.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Tetrahedron {
	private static final double DECAY_FACTOR = 0.9;
	
	private MyPolygon[] polygons;
	private Color color;

	public Tetrahedron(Color color, boolean decayColor, MyPolygon... polygons) {
		this.color = color;
		this.polygons = polygons;
		if (decayColor)
			this.setDecayingPolygonColor();
		else
			this.setPolygonColor();
		
		this.sortPolygons();
	}

	public Tetrahedron(MyPolygon... polygons) {
		this.color = Color.WHITE;
		this.polygons = polygons;
		this.sortPolygons();
	}

	public void render(Graphics g) {
		for (MyPolygon poly : this.polygons) {
			poly.render(g);
		}
	}

	public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
		for (MyPolygon p : this.polygons) {
			p.rotate(CW, xDegrees, yDegrees, zDegrees);
		}
		this.sortPolygons();
	}
	
	public MyPolygon[] getPolygons() {
		return this.polygons;
	}

	private void sortPolygons() {
		MyPolygon.sortPolygons(this.polygons);
	}

	private void setPolygonColor() {
		for (MyPolygon poly : this.polygons) {
			poly.setColor(this.color);

		}
	}

	private void setDecayingPolygonColor() {
		for(MyPolygon poly : this.polygons) {
			poly.setColor(this.color);
			int r = (int) (this.color.getRed() * DECAY_FACTOR);
			int g = (int) (this.color.getGreen() * DECAY_FACTOR);
			int b = (int) (this.color.getBlue() * DECAY_FACTOR);
			this.color = new Color(r,g,b);
		}
		
	}
}
