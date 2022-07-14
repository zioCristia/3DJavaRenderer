package renderer.entity.builder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import renderer.entity.Entity;
import renderer.entity.IEntity;
import renderer.point.MyPoint;
import renderer.shapes.MyPolygon;
import renderer.shapes.Tetrahedron;

public class BasicEntityBuilder {
	public static IEntity createCube(double size, double centerX, double centerY, double centerZ) {
		MyPoint p1 = new MyPoint(centerX + size / 2, centerY + -size / 2, centerZ + -size / 2);
		MyPoint p2 = new MyPoint(centerX + size / 2, centerY + size / 2, centerZ + -size / 2);
		MyPoint p3 = new MyPoint(centerX + size / 2, centerY + size / 2, centerZ + size / 2);
		MyPoint p4 = new MyPoint(centerX + size / 2, centerY + -size / 2, centerZ + size / 2);
		MyPoint p5 = new MyPoint(centerX + -size / 2, centerY + -size / 2, centerZ + -size / 2);
		MyPoint p6 = new MyPoint(centerX + -size / 2, centerY + size / 2, centerZ + -size / 2);
		MyPoint p7 = new MyPoint(centerX + -size / 2, centerY + size / 2, centerZ + size / 2);
		MyPoint p8 = new MyPoint(centerX + -size / 2, centerY + -size / 2, centerZ + size / 2);

		Tetrahedron tetra = new Tetrahedron(
//				Color.BLUE,
				new MyPolygon(Color.RED, p1, p2, p3, p4), new MyPolygon(Color.BLUE, p5, p6, p7, p8),
				new MyPolygon(Color.YELLOW, p1, p2, p6, p5), new MyPolygon(Color.GREEN, p1, p5, p8, p4),
				new MyPolygon(Color.ORANGE, p2, p6, p7, p3), new MyPolygon(Color.CYAN, p4, p3, p7, p8));

		return new Entity(new ArrayList<Tetrahedron>(Arrays.asList(tetra)));
	}

	public static IEntity createRectangularCuboid(Color color, double initialX, double initialY, double initialZ,
			double finalX, double finalY, double finalZ) {
		MyPoint p1 = new MyPoint(initialX, initialY, initialZ);
		MyPoint p2 = new MyPoint(initialX, initialY, finalZ);
		MyPoint p3 = new MyPoint(initialX, finalY, finalZ);
		MyPoint p4 = new MyPoint(initialX, finalY, initialZ);
		MyPoint p5 = new MyPoint(finalX, initialY, initialZ);
		MyPoint p6 = new MyPoint(finalX, initialY, finalZ);
		MyPoint p7 = new MyPoint(finalX, finalY, finalZ);
		MyPoint p8 = new MyPoint(finalX, finalY, initialZ);
		
		Tetrahedron tetra = new Tetrahedron(
				color, true,
				new MyPolygon(p1, p2, p3, p4), new MyPolygon(p5, p6, p7, p8),
				new MyPolygon(p1, p2, p6, p5), new MyPolygon(p1, p5, p8, p4),
				new MyPolygon(p2, p6, p7, p3), new MyPolygon(p4, p3, p7, p8));

		return new Entity(new ArrayList<Tetrahedron>(Arrays.asList(tetra)));
	}
}
