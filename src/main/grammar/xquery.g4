grammar xquery;
import xpath;

xq
    : Var                                               # xqVar
    | StringConstant                                    # xqStringConstant
    | ap                                                # xqAp
    | '(' xq ')'                                        # xqParentheses
    | xq ',' xq                                         # xqCollection
    | xq '/' rp                                         # xqChildren
    | xq '//' rp                                        # xqAll
    | '<' tagName '>' '{' xq '}' '</' tagName '>'             # xqTag
    | forClause letClause? whereClause? returnClause    # xqFLWR
    | letClause xq                                      # xqLet
    | 'join' '(' xq ',' xq ',' attributeList ',' attributeList ')'  #xqJoin
    ;

forClause
    : 'for ' Var ' in ' xq (',' Var ' in ' xq)*
    ;

letClause
    : 'let ' Var ':=' xq (',' Var ':=' xq)*
    ;

whereClause
    : 'where ' cond
    ;

returnClause
    : 'return ' xq
    ;

cond
    : xq ('=' | 'eq') xq                                         # condValueEquality
    | xq ('==' | 'is') xq                                        # condEquality
    | 'empty' '(' xq ')'                                            # condEmpty
    | 'some' Var ' in ' xq (',' Var ' in ' xq)* 'satisfies' cond     # condSome
    |  '(' cond ')'                                              # condParentheses
    | cond 'and' cond                                            # condAnd
    | cond ' or ' cond                                             # condOr
    | 'not' cond                                                 # condNot
    ;

attributeList
    : '[' (attName (',' attName)* )? ']'
    ;

Var: '$' Letter (Letter | Digit | '-')*;

