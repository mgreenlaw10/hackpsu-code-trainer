// Generated from src/grammar/GameLang.g4 by ANTLR 4.13.2
package game.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GameLangParser}.
 */
public interface GameLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GameLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(GameLangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(GameLangParser.StatementContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link GameLangParser#callAttack}.
	 * @param ctx the parse tree
	 */
	void enterCallAttack(GameLangParser.CallAttackContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameLangParser#callAttack}.
	 * @param ctx the parse tree
	 */
	void exitCallAttack(GameLangParser.CallAttackContext ctx);
	/**
	 * Enter a parse tree produced by {@link GameLangParser#callAim}.
	 * @param ctx the parse tree
	 */
	void enterCallAim(GameLangParser.CallAimContext ctx);
	/**
	 * Exit a parse tree produced by {@link GameLangParser#callAim}.
	 * @param ctx the parse tree
	 */
	void exitCallAim(GameLangParser.CallAimContext ctx);
}