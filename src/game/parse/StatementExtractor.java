package game;

import java.util.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import game.grammar.GameLangLexer;
import game.grammar.GameLangParser;
import game.grammar.GameLangBaseVisitor;

import game.math.Direction;

public class StatementExtractor {

	public enum StatementType {
		MOVE,
		ATTACK,
		AIM
	}

	public static final class Statement {

		public final StatementType type;
		public final Object args;

		private Statement(StatementType pType, Object pArgs) {
			type = pType;
			args = pArgs;
		}

		public static Statement MOVE(String dirArg) {
			return new Statement(StatementType.MOVE, Direction.parseString(dirArg));
		}

		public static Statement ATTACK(String numArg) {
			return new Statement(StatementType.ATTACK, Integer.parseInt(numArg));
		}

		public static Statement AIM(String dirArg) {
			return new Statement(StatementType.AIM, Direction.parseString(dirArg));
		}
	}

	public static class StatementVisitor extends GameLangBaseVisitor<Statement> {

		@Override
		public Statement visitStatement(GameLangParser.StatementContext context) {

			if (context.callMove() != null) return visitCallMove(context.callMove());
			if (context.callAttack() != null) return visitCallAttack(context.callAttack());
			if (context.callAim() != null) return visitCallAim(context.callAim());
			// should never reach
			return null;
		}

		@Override
        public Statement visitCallMove(GameLangParser.CallMoveContext context) {
            String dirArg = context.arg0.getText();
            return Statement.MOVE(dirArg);
        }

        @Override
        public Statement visitCallAttack(GameLangParser.CallAttackContext context) {
            String numArg = context.arg0.getText();
            return Statement.ATTACK(numArg);
        }

        @Override
        public Statement visitCallAim(GameLangParser.CallAimContext context) {
        	String dirArg = context.arg0.getText();
        	return Statement.AIM(dirArg);
        }
	}

	public static Statement parseLine(String line) {

        CharStream cs = CharStreams.fromString(line);
        GameLangLexer lex = new GameLangLexer(cs);
        CommonTokenStream ts = new CommonTokenStream(lex);
        GameLangParser parser = new GameLangParser(ts);

        parser.removeErrorListeners();

        GameLangParser.StatementContext context = parser.statement();
        StatementVisitor visitor = new StatementVisitor();

        return visitor.visit(context);
    }
}