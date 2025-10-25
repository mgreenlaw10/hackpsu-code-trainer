// Generated from src/grammar/TestGrammar.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TestGrammarParser}.
 */
public interface TestGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TestGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(TestGrammarParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(TestGrammarParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(TestGrammarParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(TestGrammarParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(TestGrammarParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(TestGrammarParser.FactorContext ctx);
}