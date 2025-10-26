// Generated from src/grammar/GameLang.g4 by ANTLR 4.13.2
package game.grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class GameLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, MOVE=3, ATTACK=4, AIM=5, DIR=6, NUM=7, END=8, WS=9;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "MOVE", "ATTACK", "AIM", "DIR", "NUM", "END", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'move'", "'attack'", "'aim'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "MOVE", "ATTACK", "AIM", "DIR", "NUM", "END", "WS"
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


	public GameLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "GameLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\tI\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u00057\b\u0005\u0001\u0006\u0004\u0006:\b\u0006"+
		"\u000b\u0006\f\u0006;\u0001\u0007\u0003\u0007?\b\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0004\bD\b\b\u000b\b\f\bE\u0001\b\u0001\b\u0000\u0000\t"+
		"\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r"+
		"\u0007\u000f\b\u0011\t\u0001\u0000\u0002\u0001\u000009\u0002\u0000\t\t"+
		"  N\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000"+
		"\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000"+
		"\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000"+
		"\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000"+
		"\u0011\u0001\u0000\u0000\u0000\u0001\u0013\u0001\u0000\u0000\u0000\u0003"+
		"\u0015\u0001\u0000\u0000\u0000\u0005\u0017\u0001\u0000\u0000\u0000\u0007"+
		"\u001c\u0001\u0000\u0000\u0000\t#\u0001\u0000\u0000\u0000\u000b6\u0001"+
		"\u0000\u0000\u0000\r9\u0001\u0000\u0000\u0000\u000f>\u0001\u0000\u0000"+
		"\u0000\u0011C\u0001\u0000\u0000\u0000\u0013\u0014\u0005(\u0000\u0000\u0014"+
		"\u0002\u0001\u0000\u0000\u0000\u0015\u0016\u0005)\u0000\u0000\u0016\u0004"+
		"\u0001\u0000\u0000\u0000\u0017\u0018\u0005m\u0000\u0000\u0018\u0019\u0005"+
		"o\u0000\u0000\u0019\u001a\u0005v\u0000\u0000\u001a\u001b\u0005e\u0000"+
		"\u0000\u001b\u0006\u0001\u0000\u0000\u0000\u001c\u001d\u0005a\u0000\u0000"+
		"\u001d\u001e\u0005t\u0000\u0000\u001e\u001f\u0005t\u0000\u0000\u001f "+
		"\u0005a\u0000\u0000 !\u0005c\u0000\u0000!\"\u0005k\u0000\u0000\"\b\u0001"+
		"\u0000\u0000\u0000#$\u0005a\u0000\u0000$%\u0005i\u0000\u0000%&\u0005m"+
		"\u0000\u0000&\n\u0001\u0000\u0000\u0000\'(\u0005U\u0000\u0000(7\u0005"+
		"P\u0000\u0000)*\u0005D\u0000\u0000*+\u0005O\u0000\u0000+,\u0005W\u0000"+
		"\u0000,7\u0005N\u0000\u0000-.\u0005L\u0000\u0000./\u0005E\u0000\u0000"+
		"/0\u0005F\u0000\u000007\u0005T\u0000\u000012\u0005R\u0000\u000023\u0005"+
		"I\u0000\u000034\u0005G\u0000\u000045\u0005H\u0000\u000057\u0005T\u0000"+
		"\u00006\'\u0001\u0000\u0000\u00006)\u0001\u0000\u0000\u00006-\u0001\u0000"+
		"\u0000\u000061\u0001\u0000\u0000\u00007\f\u0001\u0000\u0000\u00008:\u0007"+
		"\u0000\u0000\u000098\u0001\u0000\u0000\u0000:;\u0001\u0000\u0000\u0000"+
		";9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000<\u000e\u0001\u0000"+
		"\u0000\u0000=?\u0005\r\u0000\u0000>=\u0001\u0000\u0000\u0000>?\u0001\u0000"+
		"\u0000\u0000?@\u0001\u0000\u0000\u0000@A\u0005\n\u0000\u0000A\u0010\u0001"+
		"\u0000\u0000\u0000BD\u0007\u0001\u0000\u0000CB\u0001\u0000\u0000\u0000"+
		"DE\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000"+
		"\u0000FG\u0001\u0000\u0000\u0000GH\u0006\b\u0000\u0000H\u0012\u0001\u0000"+
		"\u0000\u0000\u0005\u00006;>E\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}