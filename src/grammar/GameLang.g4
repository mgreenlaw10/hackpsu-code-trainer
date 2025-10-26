grammar GameLang;
@header {package game.grammar;}

statement : callMove | callRepeat;

// allow either an explicit newline (END) or EOF at the end of a call
callMove : MOVE '(' arg0=DIR ')' (END | EOF);

callRepeat : REPEAT '(' arg0=NUM ')' '{' (END | EOF);

closeScope : '}' (END | EOF);

MOVE : 'move';
REPEAT : 'repeat';
DIR : 'UP' | 'DOWN' | 'LEFT' | 'RIGHT';
NUM : [0-9]+;
// END is a real newline token. EOF is handled in parser rules above.
END : '\r'? '\n';

WS : [ \t]+ -> skip;