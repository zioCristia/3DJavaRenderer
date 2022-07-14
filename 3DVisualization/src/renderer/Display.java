package renderer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import renderer.entity.EntityManager;
import renderer.input.Mouse;

public class Display extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private Thread thread;
	private JFrame frame;
	private static String title = "3D Visualization";
	private static final int SCREEN_WIDTH = 1200;
	private static final int SCREEN_HIGHT = 800;
	private static boolean running = false;

	private EntityManager entityManager;

	private Mouse mouse;

	public Display() {
		this.frame = new JFrame();

		Dimension size = new Dimension(SCREEN_WIDTH, SCREEN_HIGHT);
		this.setPreferredSize(size);
		
		this.entityManager = new EntityManager();

		this.mouse = new Mouse();
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		this.addMouseWheelListener(mouse);
	}

	public static void main(String[] args) {
		Display display = new Display();

		display.frame.setTitle(title);
		display.frame.add(display);
		display.frame.pack();
		display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.frame.setLocationRelativeTo(null);
		display.frame.setResizable(false);
		display.frame.setVisible(true);

		display.start();
	}

	public synchronized void start() {
		running = true;
		this.thread = new Thread(this, "Display");
		this.thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60;
		double delta = 0;
		int frames = 0;

		this.entityManager.init();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				update();
				delta--;
				render();
				frames++;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer -= 1000;
				this.frame.setTitle(title + " | " + frames + " fps");
				frames = 0;
			}
		}

		stop();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SCREEN_WIDTH * 2, SCREEN_HIGHT * 2);

//		drawFromMatrix(g, matrix);

		this.entityManager.render(g);

		g.dispose();
		bs.show();

	}

	public static int getScreenHight() {
		return SCREEN_HIGHT;
	}
	
	public static int getScreenWidth() {
		return SCREEN_WIDTH;
	}

	private void update() {
		this.entityManager.update(this.mouse);
	}

	public void drawFromMatrix(Graphics g, int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				switch (matrix[i][j]) {
				case 0:
					g.setColor(Color.BLACK);
					break;
				case 1:
					g.setColor(Color.GREEN);
					break;
				case 2:
					g.setColor(Color.BLUE);
					break;
				case 3:
					g.setColor(Color.RED);
					break;
				case 4:
					g.setColor(Color.YELLOW);
					break;

				default:
					g.setColor(Color.WHITE);
					break;
				}

//				g.setColor(Color.GREEN);
				g.fillRect(i * 100, j * 100, i * 100 + 100, j * 100 + 100);
//				System.out.print(matrix[i][j]);
			}
//			System.out.println("");
		}
	}
}
