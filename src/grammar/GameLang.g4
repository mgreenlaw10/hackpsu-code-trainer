grammar GameLang;
@header {package game.grammar;}

statement : callMove | callAttack | callAim;

callMove : MOVE '(' arg0=DIR ')' (END | EOF);

callAttack : ATTACK '(' arg0=NUM ')' (END | EOF);

callAim : AIM '(' arg0=DIR ')' (END | EOF);

MOVE : 'move';
ATTACK : 'attack';
AIM : 'aim';
DIR : 'UP' | 'DOWN' | 'LEFT' | 'RIGHT';
NUM : [0-9]+;
END : '\r'? '\n';
WS : [ \t]+ -> skip;