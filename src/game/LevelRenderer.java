package game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

import game.math.Vec2d;
import game.struct.Level;
import game.struct.GameObject;

public class LevelRenderer {

	Level level;
	Canvas canvas;
	GameController controller;
	boolean wasWon = false;

	final int WIDTH = 600;
	final int HEIGHT = 600;

	public LevelRenderer(Canvas pCanvas) {
		canvas = pCanvas;
		canvas.setWidth(WIDTH);
		canvas.setHeight(HEIGHT);
	}

	public void setLevel(Level pLevel) {
		level = pLevel;
		wasWon = false; // Reset win state when level changes
	}

	public void setController(GameController pController) {
		controller = pController;
	}
	
	public void resetWinState() {
		wasWon = false;
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
		
		// Check win condition and show win screen if won
		if (level.getWinCondition() != null && controller != null) {
			boolean isWon = level.getWinCondition().getAsBoolean();
			if (isWon && !wasWon) {
				wasWon = true;
				// Delay showing the win screen slightly to allow final render
				javafx.application.Platform.runLater(() -> {
					controller.showWinScreen();
				});
			} else if (!isWon && wasWon) {
				wasWon = false;
			}
		}
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
		graphics.save();

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
				if (obj != null) {
					Image img = obj.getImage();
					graphics.drawImage(img, dx, dy, is, is);
				}
			}
		}

		graphics.restore();
	}

	private double getTileDrawSize(int levelSize) {
		double cw = canvas.getWidth();
		return cw / (double)levelSize;
	}

	public Level getLevel() {
		return level;
	}
}