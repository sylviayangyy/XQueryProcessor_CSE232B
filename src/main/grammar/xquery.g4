grammar xquery;
import xpath;

xq
    : Var
    | StringConstant
    | ap
    | '(' xq ')'
    | xq ',' xq
    | xq '/' rp
    | xq '//' rp
    | '<' tagName '>{' xq '}</' tagName '>'
    | forClause letClause? whereClause? returnClause
    | letClause xq
    ;

forClause
    : 'for' Var 'in' xq (',' Var 'in' xq)*
    ;

letClause
    : 'let' Var ':=' xq (',' Var ':=' xq)*
    ;

whereClause
    : 'where' cond
    ;

returnClause
    : 'return' xq
    ;

cond
    : xq '=' xq
    | xq 'eq' xq
    | xq '==' xq
    | xq 'is' xq
    | 'empty(' xq ')'
    | 'some' Var 'in' xq (',' Var 'in' xq)* 'satisfies' cond
    |  '(' cond ')'
    | cond 'and' cond
    | cond 'or' cond
    | 'not' cond
    ;

Var: '$' Letter (Letter | Digit | '-')*;

StringConstant: (Letter | Digit | '_' | '.')*;
