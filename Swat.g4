grammar Swat;

primaryStatement
    :   statement*
    ;

statement
    :   definition STATEMENTEND
    |   condition
    |   loop
    ;

condition
    :   'if' WHITESPACE* LPAREN WHITESPACE* equation WHITESPACE* RPAREN ifBlock WHITESPACE* 'else' WHITESPACE* elseBlock
    |   'if' WHITESPACE* LPAREN WHITESPACE* equation WHITESPACE* RPAREN ifBlock
    ;

loop
    :   'while' WHITESPACE* LPAREN WHITESPACE* equation WHITESPACE* RPAREN loopBlock
    ;

ifBlock
    :   block
    ;

elseBlock
    :   block
    ;

loopBlock
    :   block
    ;

block
    :   WHITESPACE* '{' WHITESPACE* statement* WHITESPACE* '}' WHITESPACE*
    ;

definition
    :   'let' WHITESPACE identifier WHITESPACE* '=' WHITESPACE* expression
    ;

equation
   :   expression WHITESPACE* relop WHITESPACE* expression
   ;

expression
   :   expression WHITESPACE* (TIMES | DIV) WHITESPACE* expression
   |   expression WHITESPACE* (PLUS | MINUS) WHITESPACE* expression
   |   LPAREN WHITESPACE* expression WHITESPACE* RPAREN
   |   literal
   |   variable
   |
   ;

variable
   :   WORD
   ;

relop
   :   EQ
   |   GT
   |   LT
   |   NEQ
   ;

identifier
    :   WORD
    ;

literal
    :   NUMBER
    ;

fragment SIGN
   :   ('+' | '-')
   ;

LPAREN
   :   '('
   ;


RPAREN
   :   ')'
   ;


PLUS
   :   '+'
   ;


MINUS
   :   '-'
   ;


TIMES
   :   '*'
   ;


DIV
   :   '/'
   ;


GT
   :   '>'
   ;


LT
   :   '<'
   ;


EQ
   :   '=='
   ;

NEQ
   :   '!='
   ;

WORD
    :   [A-Za-z]+
    ;

NUMBER
    :   [0-9]+
    ;

WHITESPACE
    :   [ \t]+
    ;

STATEMENTEND
    :   ';'
    ;

NEWLINE
    :   ([\r\n] | '\r\n')
        -> skip
    ;