package game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import game.math.Vec2d;
import game.struct.Level;
import game.struct.GameObject;
import game.struct.Player;
import game.struct.Monster;

public class LevelRenderer {

	int levelNum;
	Level level;
	Canvas canvas;
	GameController controller;
	boolean wasWon = false;

	final int WIDTH = 600;
	final int HEIGHT = 600;

	public LevelRenderer(Canvas pCanvas) {
		levelNum = 1;
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
							rotate90(graphics, dx, dy, is, is, 1);
							graphics.drawImage(img, 0, 0, is, is);
						}
						case DOWN -> {
							rotate90(graphics, dx, dy, is, is, 3);
							graphics.drawImage(img, 0, 0, is, is);
						}
						case LEFT -> {
							graphics.drawImage(img, dx, dy, is, is);
						}
						case RIGHT -> {
							hflip(graphics, dx, dy, is);
							graphics.drawImage(img, 0, 0, is, is);
						}
					}
					
					graphics.restore();
				}
				else if (obj instanceof Monster monster) {
					Image img = monster.getImage();
					int health = monster.getHealth();
					graphics.drawImage(img, dx, dy, is, is);
					graphics.save();
					graphics.setFont(new Font("Consolas", 48));
					graphics.setFill(Color.WHITE);
					drawCenteredText(graphics, health + "", dx + is/2, dy + is/2);
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

	void vflip(GraphicsContext graphics, double x, double y, double h) { 
		graphics.translate(x, y + h);
		graphics.scale(1, -1);
	}

	void rotate90(GraphicsContext graphics, double x, double y, double w, double h, int count) {
		graphics.translate(x + w / 2, y + h / 2);
		graphics.rotate(90 * count);
		graphics.translate(-(w / 2), -(h / 2));
	}

	void drawCenteredText(GraphicsContext graphics, String text, double cx, double cy) {
		Font font = graphics.getFont(); // use current font
	    Text tempText = new Text(text);
	    tempText.setFont(font);

	    double width = tempText.getLayoutBounds().getWidth();
	    double height = tempText.getLayoutBounds().getHeight();

	    double x = cx - width / 2.0;
	    double y = cy + height / 4.0;

	    graphics.fillText(text, x, y);
	}
}