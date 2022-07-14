package renderer.point;

import java.awt.Point;

import renderer.Display;

public class PointConverter {
	private static final int SCALE_FACTOR = 500;
	private static final double ZOOM_FACTOR = 1.2;
	
	private static double scale = 1;

	public static double getScale() {
		return scale;
	}

	public static void setScale(double scale) {
		PointConverter.scale = scale;
	}

	public static Point convertPoint(MyPoint point3D) {
		double x3d = point3D.getY() * scale;
		double y3d = point3D.getZ() * scale;
		double depth = point3D.getX() * scale;
		double[] newVal = scale(x3d, y3d, depth);

		int x2d = (int) (Display.getScreenWidth() / 2 + newVal[0]);
		int y2d = (int) (Display.getScreenHight() / 2 - newVal[1]);
//		int x2d = (int) (newVal[0] + );
//		int y2d = (int) (newVal[1]);

		Point point2D = new Point(x2d, y2d);

		return point2D;
	}

	private static double[] scale(double x3d, double y3d, double depth) {
		double dist = Math.sqrt(x3d * x3d + y3d * y3d);
		double theta = Math.atan2(y3d, x3d);
		double depth2 = 15 - depth;

		double localScale = Math.abs(SCALE_FACTOR / (depth2 + SCALE_FACTOR));
		dist *= localScale;

		double[] newVal = new double[2];
		newVal[0] = dist * Math.cos(theta);
		newVal[1] = dist * Math.sin(theta);

		return newVal;
	}

	public static void rotateAxisX(MyPoint p, boolean CW, double degrees) {
		double radius = Math.sqrt(p.getY() * p.getY() + p.getZ() * p.getZ());
		double theta = Math.atan2(p.getZ(), p.getY());
		theta += 2 * Math.PI / 360 * degrees * (CW ? -1 : 1);

		p.setY(radius * Math.cos(theta));
		p.setZ(radius * Math.sin(theta));

	}

	public static void rotateAxisY(MyPoint p, boolean CW, double degrees) {
		double radius = Math.sqrt(p.getX() * p.getX() + p.getZ() * p.getZ());
		double theta = Math.atan2(p.getX(), p.getZ());
		theta += 2 * Math.PI / 360 * degrees * (CW ? -1 : 1);

		p.setX(radius * Math.sin(theta));
		p.setZ(radius * Math.cos(theta));

	}

	public static void rotateAxisZ(MyPoint p, boolean CW, double degrees) {
		double radius = Math.sqrt(p.getY() * p.getY() + p.getX() * p.getX());
		double theta = Math.atan2(p.getY(), p.getX());
		theta += 2 * Math.PI / 360 * degrees * (CW ? -1 : 1);

		p.setY(radius * Math.sin(theta));
		p.setX(radius * Math.cos(theta));

	}

	public static void zoomOut() {
		scale *= ZOOM_FACTOR;
	}

	public static void zoomIn() {
		scale /= ZOOM_FACTOR;
	}
}