package game;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.Token;

import game.grammar.GameLangParser;
import game.grammar.GameLangLexer;
import game.grammar.GameLangBaseListener;
import game.grammar.GameLangBaseVisitor;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

import game.math.Direction;

import java.io.BufferedReader;
import java.io.StringReader;

import game.StatementExtractor.StatementType;

public class Interpreter {

	GameController controller;
	
	long delay = 500;
	private int currentLineNumber = 0;

	public Interpreter(GameController pController) {
		controller = pController;
	}

	BufferedReader reader;
	TimerEvent activeInterpretEvent;

	List<String> programLines;
	int currentLine;

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

	        	StatementExtractor.Statement statement = StatementExtractor.parseLine(line);
	        	interpretStatement(statement);
	            //interpretLine(line);
	        }
	        else {
	        	// if parsing is done, destroy the BufferedReader, cancel the TimerEvent, and restore the active level's original state
	        	reader.close();
	        	reader = null;
	        	UpdateTimer.removeUpdateRoutine(activeInterpretEvent);
				// if parsing is done, check whether the level was won; if so, advance, otherwise restore original state
				boolean won = controller.getLevelRenderer().getLevel().getWinCondition().getAsBoolean();
				if (won) {
					controller.getLevelRenderer().setLevel(LevelTemplates.getLevel2());
				}
				else {
					controller.getLevelRenderer().getLevel().restoreOriginalState();
				}
				controller.clearExecutionHighlighting(); // Clear highlighting when done
				controller.getLevelRenderer().getLevel().getPlayer().resetState();
	        }
        }
        catch (Exception e) {
        	//e.printStackTrace();
        }
	}

	public void interpretStatement(StatementExtractor.Statement statement) {

		switch (statement.type) {
			case MOVE -> {
				executeMove((Direction)statement.args);
			}
			case ATTACK -> {
				executeAttack((int)statement.args);
			}
		}
	}

	private void handleSyntaxError(SyntaxError error) {
		System.out.println(error);
	}

    private void executeMove(Direction dir) {
    	controller.getLevelRenderer().getLevel().getPlayer().setDirection(dir);
    	controller.getLevelRenderer().getLevel().getPlayer().setWalking();
    	controller.getLevelRenderer().getLevel().movePlayer(dir);
    }

    private void executeAttack(int num) {
    	controller.getLevelRenderer().getLevel().getPlayer().setAttacking();
    	controller.getLevelRenderer().getLevel().playerAttack(num);
    }

    public class ErrorListener extends BaseErrorListener {

	    private List<SyntaxError> errors = new ArrayList<>();
	    private Consumer<SyntaxError> callback;

	    public ErrorListener(Consumer<SyntaxError> callback) {
	    	this.callback = callback;
	    }

	    public ErrorListener() {
	    	this(null);
	    }

	    public List<SyntaxError> getErrors() {
	        return errors;
	    }

	    @Override
	    public void syntaxError(Recognizer<?, ?> recognizer,
	                            Object offendingSymbol,
	                            int line,
	                            int charPositionInLine,
	                            String msg,
	                            RecognitionException e) 
	    {
	        String offending = "";
	        if (offendingSymbol instanceof Token t) {
	            offending = t.getText();
	        }
	        SyntaxError error = new SyntaxError(line, charPositionInLine, offending, msg);
	        errors.add(error);
	        // trigger callback
	        if (callback != null) callback.accept(error);
	    }
	}

	public void setDelay(long pDelay) {
		delay = pDelay;
	}

	public int getCurrentLineNumber() {
		return currentLineNumber;
	}
}