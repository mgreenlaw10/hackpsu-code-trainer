package game;

import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;

import game.struct.Level;
import game.struct.GameObject;

public class GameController {

	private Engine engine;
	private LevelRenderer levelRenderer;
	private Interpreter codeInterpreter;

	@FXML private AnchorPane root;
	@FXML private Canvas gameCanvas;
	@FXML private Button runButton;
	@FXML private TextArea codeEditor;

	// Tab controls
	@FXML private Button gameTabButton;
	@FXML private Button docsTabButton;
	@FXML private StackPane gameTabContent;
	@FXML private ScrollPane docsTabContent;

	@FXML private void initialize() {
		levelRenderer = new LevelRenderer(gameCanvas);
		codeInterpreter = new Interpreter(this);

		levelRenderer.setLevel(LevelTemplates.getLevel1());
		UpdateTimer.addDrawRoutine(levelRenderer::draw);
	}

	// pseudo-constructor
	public void setEngineHandle(Engine pEngine) {
		engine = pEngine;
	}

	@FXML void showGameTab() {
		// Show game content, hide docs
		gameTabContent.setVisible(true);
		docsTabContent.setVisible(false);
		
		// Update button styles
		gameTabButton.getStyleClass().add("tab-active");
		docsTabButton.getStyleClass().remove("tab-active");
	}

	@FXML void showDocsTab() {
		// Show docs content, hide game
		gameTabContent.setVisible(false);
		docsTabContent.setVisible(true);
		
		// Update button styles
		docsTabButton.getStyleClass().add("tab-active");
		gameTabButton.getStyleClass().remove("tab-active");
	}

	@FXML void runCode() {
		codeInterpreter.interpretText(codeEditor.getText());
	}

    public LevelRenderer getLevelRenderer() {
		return levelRenderer;
	}
}