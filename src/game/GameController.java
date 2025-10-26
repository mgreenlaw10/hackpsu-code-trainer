package game;

import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import game.struct.Level;
import game.struct.GameObject;

public class GameController {

	private Engine engine;
	private LevelRenderer levelRenderer;
	private Interpreter codeInterpreter;
	
	// Track current level index
	private int currentLevelIndex = 0;

	@FXML private AnchorPane root;
	@FXML private Canvas gameCanvas;
	@FXML private Button runButton;
	@FXML private CodeArea codeEditor;
	@FXML private Slider speedSlider;
	@FXML private Label speedValueLabel;

	// Stats labels
	@FXML private Label coinsLabel;
	@FXML private Label energyLabel;

	// Tab controls
	@FXML private Button gameTabButton;
	@FXML private Button tasksTabButton;
	@FXML private Button docsTabButton;
	@FXML private VBox gameTabContent;
	@FXML private ScrollPane tasksTabContent;
	@FXML private ScrollPane docsTabContent;

	// Win screen overlay components
	private VBox winOverlay;
	private boolean isWinScreenShowing = false;

	@FXML private void initialize() {
    	levelRenderer = new LevelRenderer(gameCanvas);
		levelRenderer.setController(this); // Set controller reference
    codeInterpreter = new Interpreter(this);
		// Enable line numbers for code editor
		codeEditor.setParagraphGraphicFactory(line -> {
			javafx.scene.control.Label lineNum = new javafx.scene.control.Label(String.valueOf(line + 1));
			lineNum.getStyleClass().add("lineno");
			return lineNum;
		});
		setupSyntaxHighlighting();
		
		// Load the first level
		loadLevel(0);
		
		UpdateTimer.addDrawRoutine(levelRenderer::draw);
		
		// Initialize win screen overlay
		initializeWinScreen();
		
		// Set up slider value label listener
		updateSpeedLabel(speedSlider.getValue());
		speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
			updateSpeedLabel(newVal.doubleValue());
		});
	}

	// pseudo-constructor
	public void setEngineHandle(Engine pEngine) {
		engine = pEngine;
	}

	@FXML void showGameTab() {
		// Hide all content
		gameTabContent.setVisible(true);
		tasksTabContent.setVisible(false);
		docsTabContent.setVisible(false);
		
		// Update button styles - remove active from all, then add to current
		gameTabButton.getStyleClass().remove("tab-active");
		tasksTabButton.getStyleClass().remove("tab-active");
		docsTabButton.getStyleClass().remove("tab-active");
		gameTabButton.getStyleClass().add("tab-active");
	}
	@FXML void showTasksTab() {
		// Hide all content
		gameTabContent.setVisible(false);
		tasksTabContent.setVisible(true);
		docsTabContent.setVisible(false);
		
		// Update button styles - remove active from all, then add to current
		gameTabButton.getStyleClass().remove("tab-active");
		tasksTabButton.getStyleClass().remove("tab-active");
		docsTabButton.getStyleClass().remove("tab-active");
		tasksTabButton.getStyleClass().add("tab-active");
	}
	@FXML void showDocsTab() {
		// Hide all content
		gameTabContent.setVisible(false);
		tasksTabContent.setVisible(false);
		docsTabContent.setVisible(true);
		
		// Update button styles - remove active from all, then add to current
		gameTabButton.getStyleClass().remove("tab-active");
		tasksTabButton.getStyleClass().remove("tab-active");
		docsTabButton.getStyleClass().remove("tab-active");
		docsTabButton.getStyleClass().add("tab-active");
	}

	@FXML void runCode() {
		long delay = (long) speedSlider.getValue();
		codeInterpreter.setDelay(delay);
		// Use the line-aware interpreter entry so each executed line is highlighted
		codeInterpreter.interpretTextForLines(codeEditor.getText());
	}
	private void setupSyntaxHighlighting() {
		// Debounce text changes to avoid re-parsing on every keystroke.
		PauseTransition pause = new PauseTransition(Duration.millis(250));
		codeEditor.textProperty().addListener((obs, oldText, newText) -> {
			pause.setOnFinished(e -> {
				codeEditor.setStyleSpans(0, computeHighlighting(newText));
			});
			pause.playFromStart();
		});
		
		// Apply initial highlighting
		codeEditor.setStyleSpans(0, computeHighlighting(codeEditor.getText()));
	}
	private StyleSpans<Collection<String>> computeHighlighting(String text) {
		if (text == null || text.isEmpty()) {
			StyleSpansBuilder<Collection<String>> builder = new StyleSpansBuilder<>();
			builder.add(Collections.emptyList(), 0);
			return builder.create();
		}
		
		StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
		
		// Define patterns for syntax highlighting
		Pattern moveFunctionPattern = Pattern.compile("\\bmove\\b(?=\\s*\\()");
		Pattern attackFunctionPattern = Pattern.compile("\\battack\\b(?=\\s*\\()");
		Pattern directionPattern = Pattern.compile("\\b(UP|DOWN|LEFT|RIGHT)\\b");
		Pattern numberPattern = Pattern.compile("\\b\\d+\\b");
		
		// Create a list to store all matches with their positions and types
		java.util.List<Match> matches = new java.util.ArrayList<>();
		
		// Find all move function matches
		Matcher matcher = moveFunctionPattern.matcher(text);
		while (matcher.find()) {
			matches.add(new Match(matcher.start(), matcher.end(), "function"));
		}
		
		// Find all attack function matches
		matcher = attackFunctionPattern.matcher(text);
		while (matcher.find()) {
			matches.add(new Match(matcher.start(), matcher.end(), "function"));
		}
		
		// Find all direction constant matches
		matcher = directionPattern.matcher(text);
		while (matcher.find()) {
			matches.add(new Match(matcher.start(), matcher.end(), "constant"));
		}
		
		// Find all number matches
		matcher = numberPattern.matcher(text);
		while (matcher.find()) {
			matches.add(new Match(matcher.start(), matcher.end(), "number"));
		}
		
		// Sort matches by start position
		matches.sort((a, b) -> Integer.compare(a.start, b.start));
		
		// Remove overlapping matches (keep the first one)
		java.util.List<Match> nonOverlapping = new java.util.ArrayList<>();
		int lastEnd = 0;
		for (Match match : matches) {
			if (match.start >= lastEnd) {
				nonOverlapping.add(match);
				lastEnd = match.end;
			}
		}
		
		// Build spans
		int lastPos = 0;
		for (Match match : nonOverlapping) {
			// Add normal text before the match
			if (match.start > lastPos) {
				spansBuilder.add(Collections.emptyList(), match.start - lastPos);
			}
			// Add highlighted text
			spansBuilder.add(Collections.singletonList(match.type), match.end - match.start);
			lastPos = match.end;
		}
		
		// Add remaining text as normal
		if (lastPos < text.length()) {
			spansBuilder.add(Collections.emptyList(), text.length() - lastPos);
		}
		
		return spansBuilder.create();
	}
	
	// Helper class to store match information
	private static class Match {
		final int start;
		final int end;
		final String type;
		
		Match(int start, int end, String type) {
			this.start = start;
			this.end = end;
			this.type = type;
		}
	}

    public LevelRenderer getLevelRenderer() {
		return levelRenderer;
	}
		// Method to highlight the currently executing line
	public void highlightExecutingLine(int lineIndex) {
		// Clear previous execution highlighting
		clearExecutionHighlighting();
		
		// Apply execution highlighting to the current line
		if (lineIndex >= 0 && lineIndex < codeEditor.getParagraphs().size()) {
			codeEditor.setParagraphStyle(lineIndex, Collections.singletonList("executing-line"));
		}
	}

	// Method to clear execution highlighting
	public void clearExecutionHighlighting() {
		// Remove execution highlighting from all lines
		for (int i = 0; i < codeEditor.getParagraphs().size(); i++) {
			codeEditor.setParagraphStyle(i, Collections.emptyList());
		}
	}
	
	// Update the speed label to show delay in seconds
	private void updateSpeedLabel(double milliseconds) {
		double seconds = milliseconds / 1000.0;
		speedValueLabel.setText(String.format("%.2fs", seconds));
	}

	// Initialize the win screen overlay
	private void initializeWinScreen() {
		// Create win overlay container
		winOverlay = new VBox(20);
		winOverlay.setAlignment(Pos.CENTER);
		winOverlay.getStyleClass().add("win-overlay");
		winOverlay.setVisible(false);
		winOverlay.setManaged(false);
		
		// create win message
		Label winTitle = new Label("Level Complete!");
		winTitle.getStyleClass().add("win-title");
		
		Label winMessage = new Label("You solved the coding challenge!");
		winMessage.getStyleClass().add("win-message");
		
		// ccreate next level button
		Button nextButton = new Button("Next Level");
		nextButton.getStyleClass().addAll("button", "primary");
		nextButton.setOnAction(e -> {
			hideWinScreen();
			nextLevel();
		});
		
	
		
		// Add components to overlay
		winOverlay.getChildren().addAll(winTitle, winMessage, nextButton);
		
		// Add overlay to the game tab content (on top of canvas)
		gameTabContent.getChildren().add(winOverlay);
	}
	
	// Show the win screen
	public void showWinScreen() {
		if (!isWinScreenShowing && winOverlay != null) {
			winOverlay.setVisible(true);
			winOverlay.setManaged(true);
			isWinScreenShowing = true;
		}
	}
	
	// Hide the win screen
	public void hideWinScreen() {
		if (isWinScreenShowing && winOverlay != null) {
			winOverlay.setVisible(false);
			winOverlay.setManaged(false);
			isWinScreenShowing = false;
		}
	}
	
	// Load a specific level by index
	private void loadLevel(int levelIndex) {
		currentLevelIndex = levelIndex;
		Level level = null;
		
		switch (levelIndex) {
			case 0:
				level = LevelTemplates.getLevel1();
				break;
			case 1:
				level = LevelTemplates.getLevel2();
				break;
			default:
				// If no more levels, loop back to first level
				level = LevelTemplates.getLevel1();
				currentLevelIndex = 0;
				break;
		}
		
		if (level != null) {
			levelRenderer.setLevel(level);
			clearExecutionHighlighting();
			updateStats();
		}
	}
	
	// Update the stats display
	public void updateStats() {
		Level level = levelRenderer.getLevel();
		if (level != null) {
			var player = level.getPlayer();
			if (player != null) {
				coinsLabel.setText(String.valueOf(player.getCoins()));
				energyLabel.setText(String.valueOf(player.getEnergy()));
			}
		}
	}
	
	// Advance to next level
	private void nextLevel() {
		loadLevel(currentLevelIndex + 1);
	}
	
	

}