package game;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import game.grammar.GameLangParser;
import game.grammar.GameLangLexer;
import game.grammar.GameLangBaseListener;
import game.grammar.GameLangBaseVisitor;

import java.util.List;
import java.util.ArrayList;

import game.math.Direction;

import java.io.BufferedReader;
import java.io.StringReader;


public class Interpreter {

	GameController controller;
	
	long delay = 500;
	private int currentLineNumber = 0;

	public Interpreter(GameController pController) {
		controller = pController;
	}

	public void setDelay(long pDelay) {
		delay = pDelay;
	}

	public int getCurrentLineNumber() {
		return currentLineNumber;
	}

	BufferedReader reader;
	TimerEvent activeInterpretEvent;

	List<String> programLines;
	int currentLine;
	
	public void interpretText(String text) {
		// create iterator over codeEditor lines
		try {
			BufferedReader reader = new BufferedReader(new StringReader(text));
	        //reader = newReader;
	        programLines = new ArrayList<String>();
	        currentLine = 0;
	        String line;
	        while ((line = reader.readLine()) != null) {
		        programLines.add(line);
		    }
		    System.out.println(programLines);
	    } catch (Exception e) {
	        //e.printStackTrace();
	    }
	    activeInterpretEvent = new TimerEvent(this::interpretNextFromMemory, delay);
	    UpdateTimer.addUpdateRoutine(activeInterpretEvent);
	}

	void interpretNextFromMemory() {
		String line;
        // interpret line-by-line
        try {
        	if (currentLine < programLines.size()) {
        		line = programLines.get(currentLine++).strip();
        		interpretLine(line);
        	}
	        // if ((line = reader.readLine()) != null) {
	        //     interpretLine(line);
	        // }
	        else {
	        	// if parsing is done, destroy the BufferedReader, cancel the TimerEvent, and restore the active level's original state
	        	//reader.close();
	        	//reader = null;
	        	UpdateTimer.removeUpdateRoutine(activeInterpretEvent);

	        	// did the text beat the level?
	        	boolean won = controller.getLevelRenderer().getLevel().getWinCondition().getAsBoolean();
	        	// hard code
	        	if (won) controller.getLevelRenderer().setLevel(LevelTemplates.getLevel2());
	        	else controller.getLevelRenderer().getLevel().restoreOriginalState();
	        }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
	//temporary fix for lines
	public void interpretTextForLines(String text) {
		// create iterator over codeEditor lines
		currentLineNumber = 0; // Reset line counter
		try {
			BufferedReader newReader = new BufferedReader(new StringReader(text));
	        reader = newReader;
	    } catch (Exception e) {
	        //e.printStackTrace();
	    }
		activeInterpretEvent = new TimerEvent(this::interpretNextFromMemoryForLines, delay);
	    UpdateTimer.addUpdateRoutine(activeInterpretEvent);
	}
	//temporary fix for lines
	void interpretNextFromMemoryForLines() {
		if (reader == null) {
			return; // Exit early if reader is null
		}
		
		String line;
        // interpret line-by-line
        try {
	        if ((line = reader.readLine()) != null) {
	        	currentLineNumber++; // Increment line counter
	        	controller.highlightExecutingLine(currentLineNumber - 1); // Highlight current line (0-indexed)
	            interpretLine(line);
	        }
	        else {
	        	// if parsing is done, destroy the BufferedReader, cancel the TimerEvent, and restore the active level's original state
	        	reader.close();
	        	reader = null;
	        	UpdateTimer.removeUpdateRoutine(activeInterpretEvent);
				// if parsing is done, check whether the level was won; if so, advance, otherwise restore original state
				boolean won = controller.getLevelRenderer().getLevel().getWinCondition().getAsBoolean();
				if (won) controller.getLevelRenderer().setLevel(LevelTemplates.getLevel2());
				else controller.getLevelRenderer().getLevel().restoreOriginalState();
				controller.clearExecutionHighlighting(); // Clear highlighting when done
	        }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
	}

	public void interpretLine(String text) {

		// Ensure the input ends with a newline so the grammar's END token can be matched
		if (text == null) text = "\n";
		else if (!text.endsWith("\n")) text = text + "\n";

		GameLangLexer lexer = new GameLangLexer(CharStreams.fromString(text));
	    // lexer.removeErrorListeners();
	    // lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

	    CommonTokenStream tokens = new CommonTokenStream(lexer);

	    GameLangParser parser = new GameLangParser(tokens);
	    // parser.removeErrorListeners();
	    // parser.addErrorListener(ThrowingErrorListener.INSTANCE);

	    GameLangParser.CallMoveContext moveTree = parser.callMove();

	    ArgCollector moveCollector = new ArgCollector();

	    ParseTreeWalker.DEFAULT.walk(moveCollector, moveTree);

	    // if successfully parsed a move(dir), execute it
	    if (!moveCollector.getArgs().isEmpty()) {
	    	executeMove(moveCollector.getArgs().get(0));
	    }
	}

	public static class Executor extends GameLangBaseVisitor<Void> {

	}

	public static class ArgCollector extends GameLangBaseListener {

        private final List<String> args = new ArrayList<>();

        @Override
        public void exitCallMove(GameLangParser.CallMoveContext ctx) {
            String dirArg = ctx.arg0.getText();
            args.add(dirArg);
        }

        @Override
        public void exitCallRepeat(GameLangParser.CallRepeatContext ctx) {
        	String countArg = ctx.arg0.getText();
        	args.add(countArg);
        }

        public List<String> getArgs() {
            return args;
        }
    }

    private void executeMove(String args) {
    	Direction dir = Direction.parseString(args);
    	controller.getLevelRenderer().getLevel().movePlayer(dir);
    }

    int returnLine;
    int repeatCounter;
    int breakThreshold;

    private void executeRepeat(String args) {
    	System.out.println("called");
    	// Integer count = String.parseInt(args);
    	// repeatCounter = 0;
    	// breakThreshold = count;
    	// returnLine = currentLine;
    }

    private void jumpBackToLoop() {
    	if (repeatCounter >= breakThreshold) {
    		return;
    	}
    	currentLine = returnLine;
    }
	
}