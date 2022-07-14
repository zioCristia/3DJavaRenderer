package renderer.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import renderer.point.MyPoint;
import renderer.point.PointConverter;

public class MyPolygon {
	private Color color;
	private MyPoint[] points;
	
	public MyPolygon(Color color, MyPoint... points) {
		this.color = color;
		this.points = new MyPoint[points.length];
		
		for (int i = 0; i < points.length; i++) {
			MyPoint p = points[i];
			
			this.points[i] = new MyPoint(p.getX(), p.getY(), p.getZ());
		}
	}
	
	public MyPolygon(MyPoint... points) {
		this.color = Color.WHITE;
		this.points = new MyPoint[points.length];
		
		for (int i = 0; i < points.length; i++) {
			MyPoint p = points[i];
			
			this.points[i] = new MyPoint(p.getX(), p.getY(), p.getZ());
		}
	}
	
	public void render(Graphics g) {
		Polygon poly = new Polygon();
		for (int i = 0; i < this.points.length; i++) {
			Point p = PointConverter.convertPoint(this.points[i]);
			poly.addPoint(p.x, p.y);
		}
		g.setColor(this.color);
		g.fillPolygon(poly);
	}

	public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
		for(MyPoint p : points) {
			PointConverter.rotateAxisX(p, CW, xDegrees);
			PointConverter.rotateAxisY(p, CW, yDegrees);
			PointConverter.rotateAxisZ(p, CW, zDegrees);
		}
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public static MyPolygon[] sortPolygons(MyPolygon[] polygons) {
		List<MyPolygon> polygonsList = new ArrayList<MyPolygon>();
		
		for(MyPolygon poly : polygons) {
			polygonsList.add(poly);
		}
		
		Collections.sort(polygonsList, new Comparator<MyPolygon>() {
			@Override
			public int compare(MyPolygon p1, MyPolygon p2) {
				double p1AvarageX = p1.getAvarageX();
				double p2AvarageX = p2.getAvarageX();
				double diff = p2AvarageX - p1AvarageX;
				
				if (diff == 0)
					return 0;
				
				return diff < 0 ? 1 : -1;
			}
		});
		
		for (int i =  0; i < polygons.length; i++) {
			polygons[i] = polygonsList.get(i);
		}
		
		return polygons;
	}

	protected double getAvarageX() {
		double sum = 0;
		for(MyPoint p : this.points) {
			sum += p.getX();
		}
		
		return sum / this.points.length;
	}
}
