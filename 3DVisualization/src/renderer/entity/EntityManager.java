package renderer.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import renderer.entity.builder.BasicEntityBuilder;
import renderer.entity.builder.ComplexEntityBuilder;
import renderer.input.ClickType;
import renderer.input.Mouse;
import renderer.point.PointConverter;
import renderer.utility.PackageCordinate;

public class EntityManager {
	private static final double MOUSE_SENSITIVITY = 2.5;
//	private static final List<PackageCordinate> coordinates = new ArrayList<PackageCordinate>(Arrays.asList(
//			new PackageCordinate(0, 0, 0, -150, -100, -70), new PackageCordinate(0, 0, 0, 150, 100, 80),
//			new PackageCordinate(150, 100, 80, 250, 200, 150)));
	private static final List<PackageCordinate> coordinates = new ArrayList<PackageCordinate>(Arrays.asList(
			new PackageCordinate(0,0,0,140,80,73),
			new PackageCordinate(0,80,0,140,160,72),
			new PackageCordinate(0,0,73,120,80,145),
			new PackageCordinate(140,0,0,320,80,73),
			new PackageCordinate(140,80,0,320,160,73),
			new PackageCordinate(140,0,73,320,80,145),
			new PackageCordinate(320,0,0,480,80,74),
			new PackageCordinate(320,80,0,480,160,74),
			new PackageCordinate(320,0,74,480,80,146),
			new PackageCordinate(320,80,74,480,160,146),
			new PackageCordinate(320,0,146,480,80,218),
			new PackageCordinate(320,80,146,460,160,228),
			new PackageCordinate(480,0,0,620,80,74),
			new PackageCordinate(480,80,0,620,160,73),
			new PackageCordinate(480,0,74,620,80,147),
			new PackageCordinate(480,80,73,620,160,146),
			new PackageCordinate(480,0,147,620,80,220),
			new PackageCordinate(480,80,146,620,160,219),
			new PackageCordinate(620,0,0,760,80,72),
			new PackageCordinate(620,80,0,760,160,72),
			new PackageCordinate(620,0,72,760,80,144),
			new PackageCordinate(620,80,72,760,160,144),
			new PackageCordinate(620,0,144,760,80,216),
			new PackageCordinate(620,80,144,760,160,216),
			new PackageCordinate(760,0,0,900,80,72),
			new PackageCordinate(760,80,0,880,160,74),
			new PackageCordinate(760,0,72,880,80,146),
			new PackageCordinate(760,80,74,880,160,147),
			new PackageCordinate(760,0,146,880,80,219),
			new PackageCordinate(760,80,147,880,160,220),
			new PackageCordinate(900,0,0,1020,80,73),
			new PackageCordinate(900,80,0,1020,160,73),
			new PackageCordinate(900,0,73,1020,80,145),
			new PackageCordinate(900,80,73,1020,160,145),
			new PackageCordinate(900,0,145,1020,80,217),
			new PackageCordinate(900,80,145,1020,160,217),
			new PackageCordinate(1020,0,0,1100,80,73),
			new PackageCordinate(1020,80,0,1100,160,73),
			new PackageCordinate(1020,0,73,1100,80,145)));

	private List<IEntity> entities;
	private int initialX, initialY;

	public EntityManager() {
		this.entities = new ArrayList<IEntity>();
	}

	public void init() {
		this.entities.add(ComplexEntityBuilder.createPackageDisposition(coordinates));
//		this.entities.add(BasicEntityBuilder.createRectangularCuboid(Color.RED, 0, 0, 0, -150, -100, -70));
//		this.entities.add(BasicEntityBuilder.createRectangularCuboid(Color.BLUE, 0, 0, 0, 150, 100, 80));
//		this.entities.add(BasicEntityBuilder.createCube(100, 50, 0, 0));
	}

	public void update(Mouse mouse) {
		int x = mouse.getMouseX();
		int y = mouse.getMouseY();

		if (mouse.getMouseB() == ClickType.LeftClick) {
			int xDif = x - initialX;
			int yDif = y - initialY;

			rotate(true, 0, -yDif / MOUSE_SENSITIVITY, -xDif / MOUSE_SENSITIVITY);
		} else if (mouse.getMouseB() == ClickType.RightClick) {
			int xDif = x - initialX;

			rotate(true, -xDif / MOUSE_SENSITIVITY, 0, 0);
		}

		if (mouse.isScrollingUp()) {
			PointConverter.zoomOut();
		} else if (mouse.isScrollingDown()) {
			PointConverter.zoomIn();
		}
		mouse.resetScroll();

		initialX = x;
		initialY = y;
	}

	public void render(Graphics g) {
		for (IEntity entity : this.entities) {
			entity.render(g);
		}
	}

	private void rotate(boolean CW, double xDegreed, double yDegrees, double zDegrees) {
		for (IEntity entity : this.entities) {
			entity.rotate(CW, xDegreed, yDegrees, zDegrees);
		}
	}
}
