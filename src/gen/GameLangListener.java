// Generated from src/grammar/GameLang.g4 by ANTLR 4.13.2
package game.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GameLangParser}.
 */
public interface GameLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GameLangParser#callMove}.
	 * @param ctx the parse tree
	 */
	void enterCallMove(GameLangParser.CallMoveContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameLangParser#callMove}.
	 * @param ctx the parse tree
	 */
	void exitCallMove(GameLangParser.CallMoveContext ctx);
}