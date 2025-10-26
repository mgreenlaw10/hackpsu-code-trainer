grammar GameLang;
@header {package game.grammar;}

statement : callMove | callRepeat;

callMove : MOVE '(' arg0=DIR ')' END;

callRepeat : REPEAT '('arg0=NUM ')' '{' END;

closeScope : '}' END;

MOVE : 'move';
REPEAT : 'repeat';
DIR : 'UP' | 'DOWN' | 'LEFT' | 'RIGHT';
NUM : [0-9]+;
END : '\n' | EOF;