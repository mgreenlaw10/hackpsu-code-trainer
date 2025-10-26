// Generated from src/grammar/GameLang.g4 by ANTLR 4.13.2
package game.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GameLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GameLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GameLangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(GameLangParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameLangParser#callMove}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallMove(GameLangParser.CallMoveContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameLangParser#callRepeat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallRepeat(GameLangParser.CallRepeatContext ctx);
	/**
	 * Visit a parse tree produced by {@link GameLangParser#closeScope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCloseScope(GameLangParser.CloseScopeContext ctx);
}