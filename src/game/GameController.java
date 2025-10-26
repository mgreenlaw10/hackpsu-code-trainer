package game;

import javafx.fxml.FXML;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
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

		// Setup syntax highlighting
		setupSyntaxHighlighting();

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

	private void setupSyntaxHighlighting() {
		// Apply syntax highlighting when text changes
		codeEditor.textProperty().addListener((obs, oldText, newText) -> {
			codeEditor.setStyleSpans(0, computeHighlighting(newText));
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
		Pattern directionPattern = Pattern.compile("\\b(UP|DOWN|LEFT|RIGHT)\\b");
		
		// Create a list to store all matches with their positions and types
		java.util.List<Match> matches = new java.util.ArrayList<>();
		
		// Find all move function matches
		Matcher matcher = moveFunctionPattern.matcher(text);
		while (matcher.find()) {
			matches.add(new Match(matcher.start(), matcher.end(), "function"));
		}
		
		// Find all direction constant matches
		matcher = directionPattern.matcher(text);
		while (matcher.find()) {
			matches.add(new Match(matcher.start(), matcher.end(), "constant"));
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
}