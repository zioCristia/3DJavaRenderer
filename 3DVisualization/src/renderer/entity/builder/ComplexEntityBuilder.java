package renderer.entity.builder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import renderer.entity.Entity;
import renderer.entity.IEntity;
import renderer.point.MyPoint;
import renderer.shapes.MyPolygon;
import renderer.shapes.Tetrahedron;
import renderer.utility.PackageCordinate;

public class ComplexEntityBuilder {
	private static final int X_TRUCK_DIMENSION = 1360;
	private static final int Y_TRUCK_DIMENSION = 245;
	private static final int Z_TRUCK_DIMENSION = 245;
	
	private static List<Color> colors = new ArrayList<Color>(Arrays.asList(Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, 
										Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, 
										Color.PINK, Color.RED, Color.WHITE));
	
	public static IEntity createPackageDisposition(List<PackageCordinate> packagesCordinates) {
		List<Tetrahedron> tetras = new ArrayList<Tetrahedron>();
		int colorIndex = 0;
		
		for(int i = 0; i < packagesCordinates.size(); i++) {
			double initialXRelative = packagesCordinates.get(i).getInitialX() - X_TRUCK_DIMENSION / 2;
			double initialYRelative = packagesCordinates.get(i).getInitialY() - Y_TRUCK_DIMENSION / 2;
			double initialZRelative = packagesCordinates.get(i).getInitialZ() - Z_TRUCK_DIMENSION / 2;
			double finalXRelative = packagesCordinates.get(i).getFinalX() - X_TRUCK_DIMENSION / 2;
			double finalYRelative = packagesCordinates.get(i).getFinalY() - Y_TRUCK_DIMENSION / 2;
			double finalZRelative = packagesCordinates.get(i).getFinalZ() - Z_TRUCK_DIMENSION / 2;
			
			if(packagesCordinates.get(i).isFragile())
				tetras.add(addRectangularCuboid(Color.YELLOW, initialXRelative, initialYRelative, initialZRelative, finalXRelative, finalYRelative, finalZRelative));
			else {
				tetras.add(addRectangularCuboid(colors.get(colorIndex), initialXRelative, initialYRelative, initialZRelative, finalXRelative, finalYRelative, finalZRelative));
				colorIndex++;
				if(colorIndex == colors.size()) 
					colorIndex = 0;
			}
		}
		
		return new Entity(tetras);
	}
	
	private static Tetrahedron addRectangularCuboid(Color color, double initialX, double initialY, double initialZ,
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

		return tetra;
//		return new Entity(new ArrayList<Tetrahedron>(Arrays.asList(tetra)));
	}
}
