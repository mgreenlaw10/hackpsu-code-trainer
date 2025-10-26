package game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

import game.math.Vec2d;
import game.struct.Level;
import game.struct.GameObject;
import game.struct.Player;

public class LevelRenderer {

	Level level;
	Canvas canvas;

	final int WIDTH = 600;
	final int HEIGHT = 600;

	public LevelRenderer(Canvas pCanvas) {
		canvas = pCanvas;
		canvas.setWidth(WIDTH);
		canvas.setHeight(HEIGHT);
	}

	public void setLevel(Level pLevel) {
		level = pLevel;
	}

	public void draw() {
		if (level == null) return;

		GraphicsContext graphics = canvas.getGraphicsContext2D();

		graphics.setImageSmoothing(false);

		double cw = canvas.getWidth();
		double ch = canvas.getHeight();

		graphics.clearRect(0, 0, cw, ch);

		//System.out.println(String.format("%f, %f", cw, ch));

		drawTileGrid(graphics, cw, ch);
		drawTileObjects(graphics, cw, ch);
	}

	private void drawTileGrid(GraphicsContext graphics, double cw, double ch) {
		graphics.save();

		int ls = level.getSize();
		double ds = getTileDrawSize(ls);
		// line sharpness correction
		double snap = 0.5;

		// vertical gridlines
		for (int col = 0; col <= ls; col++) {
			double dx = col * ds + snap;
			// do not snap last gridline to fit bounds
			if (col == ls) dx -= snap;
			graphics.strokeLine(dx, 0, dx, ch);
		}
		// horizontal gridlines
		for (int row = 0; row <= ls; row++) {
			double dy = row * ds + snap;
			// do not snap last gridline to fit bounds
			if (row == ls) dy -= snap;
			graphics.strokeLine(0, dy, cw, dy);
		}
		graphics.restore();
	}

	private void drawTileObjects(GraphicsContext graphics, double cw, double ch) {

		int ls = level.getSize();
		double ds = getTileDrawSize(ls);
		double snap = 1.0;

		var tileMap = level.getTileMap();
		for (int row = 0; row < ls; row++) {
			for (int col = 0; col < ls; col++) {

				var obj = tileMap[row][col];
				double dx = col * ds + snap;
				double dy = row * ds + snap;
				double is = ds - snap;
				if (level.getBackground() != null) {
					Image img = level.getBackground();
					graphics.drawImage(img, dx, dy, is, is);
				}
				if (obj instanceof Player player) {
					Image img = player.getImage();
					// transform for direction
					graphics.save();
					switch (player.getDirection()) {
						case UP -> {

						}
						case DOWN -> {
							rotate90(graphics, dx, dy, is, is);
							graphics.drawImage(img, -is / 2, -is / 2);
						}
						case LEFT -> {
							break; // default
						}
						case RIGHT -> {
							hflip(graphics, dx, dy, is);
							graphics.drawImage(img, 0, 0, is, is);
						}
					}
					graphics.restore();
				}	
				else if (obj != null) {
					Image img = obj.getImage();
					graphics.drawImage(img, dx, dy, is, is);
				}
			}
		}
	}

	private double getTileDrawSize(int levelSize) {
		double cw = canvas.getWidth();
		return cw / (double)levelSize;
	}

	public Level getLevel() {
		return level;
	}

	void hflip(GraphicsContext graphics, double x, double y, double w) { 
		graphics.translate(x + w, y);
		graphics.scale(-1, 1);
	}

	void rotate90(GraphicsContext graphics, double x, double y, double w, double h) {
		graphics.translate(x + w / 2, y + h / 2);
		graphics.rotate(90);
	}
}