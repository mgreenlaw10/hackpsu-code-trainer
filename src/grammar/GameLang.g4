grammar GameLang;
@header {package game.grammar;}

callMove : MOVE '(' arg=DIR ')' (NL | EOF);

MOVE : 'move';
DIR : 'UP' | 'DOWN' | 'LEFT' | 'RIGHT';
NL : '\n';