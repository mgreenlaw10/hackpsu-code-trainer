package game;

import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import org.fxmisc.richtext.CodeArea;

import game.struct.Level;
import game.struct.GameObject;

public class GameController {

	private Engine engine;
	private LevelRenderer levelRenderer;
	private Interpreter codeInterpreter;

	@FXML private AnchorPane root;
	@FXML private Canvas gameCanvas;
	@FXML private Button runButton;
	@FXML private CodeArea codeEditor;
	@FXML private Slider speedSlider;
	
	
	@FXML private Button gameTabButton;
	@FXML private Button docsTabButton;
	@FXML private StackPane gameTabContent;
	@FXML private ScrollPane docsTabContent;

	@FXML private void initialize() {
		levelRenderer = new LevelRenderer(gameCanvas);
		codeInterpreter = new Interpreter(this);
		
		// Enable line numbers for code editor
		codeEditor.setParagraphGraphicFactory(line -> {
			javafx.scene.control.Label lineNum = new javafx.scene.control.Label(String.valueOf(line + 1));
			lineNum.getStyleClass().add("lineno");
			return lineNum;
		});

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
		testLevel.saveStateAsOriginal();
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
		long delay = (long) speedSlider.getValue();
		codeInterpreter.setDelay(delay);
		codeInterpreter.interpretText(codeEditor.getText());
	}

    public LevelRenderer getLevelRenderer() {
		return levelRenderer;
	}
}