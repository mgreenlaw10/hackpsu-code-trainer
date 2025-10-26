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

import java.util.List;
import java.util.ArrayList;

import game.math.Direction;

import java.io.BufferedReader;
import java.io.StringReader;


public class Interpreter {

	GameController controller;

	long delay = 500;

	public Interpreter(GameController pController) {
		controller = pController;
	}

	public void setDelay(long pDelay) {
		delay = pDelay;
	}

	BufferedReader reader;
	TimerEvent activeInterpretEvent;

	public void interpretText(String text) {
		// create iterator over codeEditor lines
		try {
			BufferedReader newReader = new BufferedReader(new StringReader(text));
	        reader = newReader;
	    } catch (Exception e) {
	        //e.printStackTrace();
	    }
	    activeInterpretEvent = new TimerEvent(this::interpretNextFromMemory, delay);
	    UpdateTimer.addUpdateRoutine(activeInterpretEvent);
	}

	void interpretNextFromMemory() {
		if (reader == null) {
			return; // Exit early if reader is null
		}
		
		String line;
        // interpret line-by-line
        try {
	        if ((line = reader.readLine()) != null) {
	            interpretLine(line);
	        }
	        else {
	        	// if parsing is done, destroy the BufferedReader, cancel the TimerEvent, and restore the active level's original state
	        	reader.close();
	        	reader = null;
	        	UpdateTimer.removeUpdateRoutine(activeInterpretEvent);
	        	controller.getLevelRenderer().getLevel().restoreOriginalState();
	        }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public void interpretLine(String text) {

		GameLangLexer lexer = new GameLangLexer(CharStreams.fromString(text));
	    // lexer.removeErrorListeners();
	    // lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

	    CommonTokenStream tokens = new CommonTokenStream(lexer);

	    GameLangParser parser = new GameLangParser(tokens);
	    // parser.removeErrorListeners();
	    // parser.addErrorListener(ThrowingErrorListener.INSTANCE);

	    GameLangParser.CallMoveContext tree = parser.callMove();

	    ArgCollector collector = new ArgCollector();
	    ParseTreeWalker.DEFAULT.walk(collector, tree);

	    // if successfully parsed a move(dir), execute it
	    if (!collector.getArgs().isEmpty()) {
	    	executeMove(collector.getArgs().get(0));
	    }
	}

	public static class ArgCollector extends GameLangBaseListener {
        private final List<String> args = new ArrayList<>();

        @Override
        public void exitCallMove(GameLangParser.CallMoveContext ctx) {
            String argText = ctx.arg.getText();
            args.add(argText);
        }

        public List<String> getArgs() {
            return args;
        }
    }

    private void executeMove(String args) {

    	Direction dir = Direction.parseString(args);
    	controller.getLevelRenderer().getLevel().movePlayer(dir);
    }
	
}