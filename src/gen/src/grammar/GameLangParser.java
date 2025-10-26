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
		T__0=1, T__1=2, MOVE=3, ATTACK=4, DIR=5, NUM=6, END=7, WS=8;
	public static final int
		RULE_statement = 0, RULE_callMove = 1, RULE_callAttack = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"statement", "callMove", "callAttack"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'move'", "'attack'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "MOVE", "ATTACK", "DIR", "NUM", "END", "WS"
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
		public CallAttackContext callAttack() {
			return getRuleContext(CallAttackContext.class,0);
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
			setState(8);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MOVE:
				enterOuterAlt(_localctx, 1);
				{
				setState(6);
				callMove();
				}
				break;
			case ATTACK:
				enterOuterAlt(_localctx, 2);
				{
				setState(7);
				callAttack();
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
		public TerminalNode DIR() { return getToken(GameLangParser.DIR, 0); }
		public TerminalNode END() { return getToken(GameLangParser.END, 0); }
		public TerminalNode EOF() { return getToken(GameLangParser.EOF, 0); }
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			match(MOVE);
			setState(11);
			match(T__0);
			setState(12);
			((CallMoveContext)_localctx).arg0 = match(DIR);
			setState(13);
			match(T__1);
			setState(14);
			_la = _input.LA(1);
			if ( !(_la==EOF || _la==END) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class CallAttackContext extends ParserRuleContext {
		public Token arg0;
		public TerminalNode ATTACK() { return getToken(GameLangParser.ATTACK, 0); }
		public TerminalNode NUM() { return getToken(GameLangParser.NUM, 0); }
		public TerminalNode END() { return getToken(GameLangParser.END, 0); }
		public TerminalNode EOF() { return getToken(GameLangParser.EOF, 0); }
		public CallAttackContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callAttack; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GameLangListener ) ((GameLangListener)listener).enterCallAttack(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GameLangListener ) ((GameLangListener)listener).exitCallAttack(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GameLangVisitor ) return ((GameLangVisitor<? extends T>)visitor).visitCallAttack(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallAttackContext callAttack() throws RecognitionException {
		CallAttackContext _localctx = new CallAttackContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_callAttack);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			match(ATTACK);
			setState(17);
			match(T__0);
			setState(18);
			((CallAttackContext)_localctx).arg0 = match(NUM);
			setState(19);
			match(T__1);
			setState(20);
			_la = _input.LA(1);
			if ( !(_la==EOF || _la==END) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
		"\u0004\u0001\b\u0017\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0001\u0000\u0001\u0000\u0003\u0000\t\b\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0000\u0000\u0003\u0000\u0002\u0004\u0000\u0001\u0001\u0001\u0007"+
		"\u0007\u0014\u0000\b\u0001\u0000\u0000\u0000\u0002\n\u0001\u0000\u0000"+
		"\u0000\u0004\u0010\u0001\u0000\u0000\u0000\u0006\t\u0003\u0002\u0001\u0000"+
		"\u0007\t\u0003\u0004\u0002\u0000\b\u0006\u0001\u0000\u0000\u0000\b\u0007"+
		"\u0001\u0000\u0000\u0000\t\u0001\u0001\u0000\u0000\u0000\n\u000b\u0005"+
		"\u0003\u0000\u0000\u000b\f\u0005\u0001\u0000\u0000\f\r\u0005\u0005\u0000"+
		"\u0000\r\u000e\u0005\u0002\u0000\u0000\u000e\u000f\u0007\u0000\u0000\u0000"+
		"\u000f\u0003\u0001\u0000\u0000\u0000\u0010\u0011\u0005\u0004\u0000\u0000"+
		"\u0011\u0012\u0005\u0001\u0000\u0000\u0012\u0013\u0005\u0006\u0000\u0000"+
		"\u0013\u0014\u0005\u0002\u0000\u0000\u0014\u0015\u0007\u0000\u0000\u0000"+
		"\u0015\u0005\u0001\u0000\u0000\u0000\u0001\b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}