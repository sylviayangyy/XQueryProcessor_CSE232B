grammar xpath;

ap
    : 'doc("' fileName '")' '/' rp          # apChildren
    | 'doc("' fileName '")' '//' rp         # apAll
    ;

rp
    : tagName                               # rpTag
    | '*'                                   # rpAny
    | '.'                                   # rpCurrent
    | '..'                                  # rpParent
    | 'text()'                              # rpText
    | '@' attName                           # rpAttribute
    | '(' rp ')'                            # rpParentheses
    | rp '/' rp                             # rpChildren
    | rp '//' rp                            # rpAll
    | rp '[' f ']'                          # rpFilter
    | rp ',' rp                             # rpCollection
    ;

f
    : rp                                    # fRp
    | rp ('=' | 'eq') rp                    # fValueEquality
    | rp ('==' | 'is') rp                   # fEquality
    | '(' f ')'                             # fParentheses
    | f 'and' f                             # fAnd
    | f ' or ' f                              # fOr
    | 'not' f                               # fNot
    ;

fileName
    : (Letter | Digit | '_' | '.')*         #apFileName
    ;

StringConstant: '"' [_A-Za-z0-9.!, ?:;]* '"';

tagName
    : Letter (Letter | Digit | '-')*;

attName
    : Letter (Letter | Digit | '-')*;

Letter: [a-zA-Z];
Digit: [0-9];

WhiteSpace: [ \n\t\r]+ -> skip;