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
		T__0=1, T__1=2, MOVE=3, DIR=4, NL=5;
	public static final int
		RULE_callMove = 0;
	private static String[] makeRuleNames() {
		return new String[] {
			"callMove"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'move'", null, "'\\n'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "MOVE", "DIR", "NL"
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
	public static class CallMoveContext extends ParserRuleContext {
		public Token arg;
		public TerminalNode MOVE() { return getToken(GameLangParser.MOVE, 0); }
		public TerminalNode DIR() { return getToken(GameLangParser.DIR, 0); }
		public TerminalNode NL() { return getToken(GameLangParser.NL, 0); }
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
		enterRule(_localctx, 0, RULE_callMove);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2);
			match(MOVE);
			setState(3);
			match(T__0);
			setState(4);
			((CallMoveContext)_localctx).arg = match(DIR);
			setState(5);
			match(T__1);
			setState(6);
			_la = _input.LA(1);
			if ( !(_la==EOF || _la==NL) ) {
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
		"\u0004\u0001\u0005\t\u0002\u0000\u0007\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0000\u0000\u0001"+
		"\u0000\u0000\u0001\u0001\u0001\u0005\u0005\u0007\u0000\u0002\u0001\u0000"+
		"\u0000\u0000\u0002\u0003\u0005\u0003\u0000\u0000\u0003\u0004\u0005\u0001"+
		"\u0000\u0000\u0004\u0005\u0005\u0004\u0000\u0000\u0005\u0006\u0005\u0002"+
		"\u0000\u0000\u0006\u0007\u0007\u0000\u0000\u0000\u0007\u0001\u0001\u0000"+
		"\u0000\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}