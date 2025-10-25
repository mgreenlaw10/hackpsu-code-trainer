package game;

import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

import game.struct.Level;
import game.struct.GameObject;

public class GameController {

	private Engine engine;
	private LevelRenderer levelRenderer;

	@FXML private AnchorPane root;
	@FXML private Canvas gameCanvas;

	@FXML private void initialize() {
		levelRenderer = new LevelRenderer(gameCanvas);

		Level testLevel = new Level(10);
		GameObject dog = new GameObject("dog", Engine.makeImage("/res/image/dog.png"));
		GameObject zyn = new GameObject("zyn", Engine.makeImage("/res/image/zyn.png"));
		testLevel.insert(dog, 1, 1);
		testLevel.insert(zyn, 5, 5);
		testLevel.insert(zyn.clone(), 6, 5);
		testLevel.insert(zyn.clone(), 7, 5);
		testLevel.insert(zyn.clone(), 8, 5);
		//testLevel.remove(zyn);

		levelRenderer.setLevel(testLevel);
		UpdateTimer.addDrawRoutine(levelRenderer::draw);
	}

	// pseudo-constructor
	public void setEngineHandle(Engine pEngine) {
		engine = pEngine;
	}

	@FXML void showGameTab() {

	}

	@FXML void showDocsTab() {
		
	}

	public LevelRenderer getLevelRenderer() {
		return levelRenderer;
	}
}