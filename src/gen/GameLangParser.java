// Generated from src/grammar/GameLang.g4 by ANTLR 4.13.2
package game.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class GameLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, MOVE=5, REPEAT=6, DIR=7, NUM=8, END=9;
	public static final int
		RULE_statement = 0, RULE_callMove = 1, RULE_callRepeat = 2, RULE_closeScope = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"statement", "callMove", "callRepeat", "closeScope"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'{'", "'}'", "'move'", "'repeat'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "MOVE", "REPEAT", "DIR", "NUM", "END"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "GameLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GameLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public CallMoveContext callMove() {
			return getRuleContext(CallMoveContext.class,0);
		}
		public CallRepeatContext callRepeat() {
			return getRuleContext(CallRepeatContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameLangListener ) ((GameLangListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameLangListener ) ((GameLangListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameLangVisitor ) return ((GameLangVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_statement);
		try {
			setState(10);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MOVE:
				enterOuterAlt(_localctx, 1);
				{
				setState(8);
				callMove();
				}
				break;
			case REPEAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(9);
				callRepeat();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CallMoveContext extends ParserRuleContext {
		public Token arg0;
		public TerminalNode MOVE() { return getToken(GameLangParser.MOVE, 0); }
		public TerminalNode END() { return getToken(GameLangParser.END, 0); }
		public TerminalNode DIR() { return getToken(GameLangParser.DIR, 0); }
		public CallMoveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callMove; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameLangListener ) ((GameLangListener)listener).enterCallMove(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameLangListener ) ((GameLangListener)listener).exitCallMove(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameLangVisitor ) return ((GameLangVisitor<? extends T>)visitor).visitCallMove(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallMoveContext callMove() throws RecognitionException {
		CallMoveContext _localctx = new CallMoveContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_callMove);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			match(MOVE);
			setState(13);
			match(T__0);
			setState(14);
			((CallMoveContext)_localctx).arg0 = match(DIR);
			setState(15);
			match(T__1);
			setState(16);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CallRepeatContext extends ParserRuleContext {
		public Token arg0;
		public TerminalNode REPEAT() { return getToken(GameLangParser.REPEAT, 0); }
		public TerminalNode END() { return getToken(GameLangParser.END, 0); }
		public TerminalNode NUM() { return getToken(GameLangParser.NUM, 0); }
		public CallRepeatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callRepeat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameLangListener ) ((GameLangListener)listener).enterCallRepeat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameLangListener ) ((GameLangListener)listener).exitCallRepeat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameLangVisitor ) return ((GameLangVisitor<? extends T>)visitor).visitCallRepeat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallRepeatContext callRepeat() throws RecognitionException {
		CallRepeatContext _localctx = new CallRepeatContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_callRepeat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			match(REPEAT);
			setState(19);
			match(T__0);
			setState(20);
			((CallRepeatContext)_localctx).arg0 = match(NUM);
			setState(21);
			match(T__1);
			setState(22);
			match(T__2);
			setState(23);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CloseScopeContext extends ParserRuleContext {
		public TerminalNode END() { return getToken(GameLangParser.END, 0); }
		public CloseScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_closeScope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameLangListener ) ((GameLangListener)listener).enterCloseScope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameLangListener ) ((GameLangListener)listener).exitCloseScope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameLangVisitor ) return ((GameLangVisitor<? extends T>)visitor).visitCloseScope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CloseScopeContext closeScope() throws RecognitionException {
		CloseScopeContext _localctx = new CloseScopeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_closeScope);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(T__3);
			setState(26);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\t\u001d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0001\u0000\u0003"+
		"\u0000\u000b\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0000\u0000\u0004\u0000\u0002\u0004\u0006\u0000\u0000\u0019\u0000"+
		"\n\u0001\u0000\u0000\u0000\u0002\f\u0001\u0000\u0000\u0000\u0004\u0012"+
		"\u0001\u0000\u0000\u0000\u0006\u0019\u0001\u0000\u0000\u0000\b\u000b\u0003"+
		"\u0002\u0001\u0000\t\u000b\u0003\u0004\u0002\u0000\n\b\u0001\u0000\u0000"+
		"\u0000\n\t\u0001\u0000\u0000\u0000\u000b\u0001\u0001\u0000\u0000\u0000"+
		"\f\r\u0005\u0005\u0000\u0000\r\u000e\u0005\u0001\u0000\u0000\u000e\u000f"+
		"\u0005\u0007\u0000\u0000\u000f\u0010\u0005\u0002\u0000\u0000\u0010\u0011"+
		"\u0005\t\u0000\u0000\u0011\u0003\u0001\u0000\u0000\u0000\u0012\u0013\u0005"+
		"\u0006\u0000\u0000\u0013\u0014\u0005\u0001\u0000\u0000\u0014\u0015\u0005"+
		"\b\u0000\u0000\u0015\u0016\u0005\u0002\u0000\u0000\u0016\u0017\u0005\u0003"+
		"\u0000\u0000\u0017\u0018\u0005\t\u0000\u0000\u0018\u0005\u0001\u0000\u0000"+
		"\u0000\u0019\u001a\u0005\u0004\u0000\u0000\u001a\u001b\u0005\t\u0000\u0000"+
		"\u001b\u0007\u0001\u0000\u0000\u0000\u0001\n";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}