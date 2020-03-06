// Generated from /Users/vivian/XQueryProcessor/src/main/grammar/xquery.g4 by ANTLR 4.8
package xquery;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link xqueryParser}.
 */
public interface xqueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code xqAp}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqAp(xqueryParser.XqApContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqAp}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqAp(xqueryParser.XqApContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqVar}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqVar(xqueryParser.XqVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqVar}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqVar(xqueryParser.XqVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqJoin}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqJoin(xqueryParser.XqJoinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqJoin}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqJoin(xqueryParser.XqJoinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqFLWR}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqFLWR(xqueryParser.XqFLWRContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqFLWR}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqFLWR(xqueryParser.XqFLWRContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqAll}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqAll(xqueryParser.XqAllContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqAll}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqAll(xqueryParser.XqAllContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqParentheses}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqParentheses(xqueryParser.XqParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqParentheses}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqParentheses(xqueryParser.XqParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqCollection}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqCollection(xqueryParser.XqCollectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqCollection}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqCollection(xqueryParser.XqCollectionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqStringConstant}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqStringConstant(xqueryParser.XqStringConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqStringConstant}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqStringConstant(xqueryParser.XqStringConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqLet}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqLet(xqueryParser.XqLetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqLet}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqLet(xqueryParser.XqLetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqTag}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqTag(xqueryParser.XqTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqTag}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqTag(xqueryParser.XqTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqChildren}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqChildren(xqueryParser.XqChildrenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqChildren}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqChildren(xqueryParser.XqChildrenContext ctx);
	/**
	 * Enter a parse tree produced by {@link xqueryParser#forClause}.
	 * @param ctx the parse tree
	 */
	void enterForClause(xqueryParser.ForClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link xqueryParser#forClause}.
	 * @param ctx the parse tree
	 */
	void exitForClause(xqueryParser.ForClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link xqueryParser#letClause}.
	 * @param ctx the parse tree
	 */
	void enterLetClause(xqueryParser.LetClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link xqueryParser#letClause}.
	 * @param ctx the parse tree
	 */
	void exitLetClause(xqueryParser.LetClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link xqueryParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(xqueryParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link xqueryParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(xqueryParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link xqueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void enterReturnClause(xqueryParser.ReturnClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link xqueryParser#returnClause}.
	 * @param ctx the parse tree
	 */
	void exitReturnClause(xqueryParser.ReturnClauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condEmpty}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondEmpty(xqueryParser.CondEmptyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condEmpty}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondEmpty(xqueryParser.CondEmptyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condParentheses}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondParentheses(xqueryParser.CondParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condParentheses}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondParentheses(xqueryParser.CondParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condSome}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondSome(xqueryParser.CondSomeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condSome}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondSome(xqueryParser.CondSomeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condEquality}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondEquality(xqueryParser.CondEqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condEquality}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondEquality(xqueryParser.CondEqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condValueEquality}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondValueEquality(xqueryParser.CondValueEqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condValueEquality}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondValueEquality(xqueryParser.CondValueEqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condAnd}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondAnd(xqueryParser.CondAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condAnd}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondAnd(xqueryParser.CondAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condOr}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondOr(xqueryParser.CondOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condOr}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondOr(xqueryParser.CondOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condNot}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondNot(xqueryParser.CondNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condNot}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondNot(xqueryParser.CondNotContext ctx);
	/**
	 * Enter a parse tree produced by {@link xqueryParser#attributeList}.
	 * @param ctx the parse tree
	 */
	void enterAttributeList(xqueryParser.AttributeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link xqueryParser#attributeList}.
	 * @param ctx the parse tree
	 */
	void exitAttributeList(xqueryParser.AttributeListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code apChildren}
	 * labeled alternative in {@link xqueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterApChildren(xqueryParser.ApChildrenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code apChildren}
	 * labeled alternative in {@link xqueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitApChildren(xqueryParser.ApChildrenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code apAll}
	 * labeled alternative in {@link xqueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterApAll(xqueryParser.ApAllContext ctx);
	/**
	 * Exit a parse tree produced by the {@code apAll}
	 * labeled alternative in {@link xqueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitApAll(xqueryParser.ApAllContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpText}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpText(xqueryParser.RpTextContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpText}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpText(xqueryParser.RpTextContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpAny}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpAny(xqueryParser.RpAnyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpAny}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpAny(xqueryParser.RpAnyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpChildren}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpChildren(xqueryParser.RpChildrenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpChildren}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpChildren(xqueryParser.RpChildrenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpTag}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpTag(xqueryParser.RpTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpTag}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpTag(xqueryParser.RpTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpParent}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpParent(xqueryParser.RpParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpParent}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpParent(xqueryParser.RpParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpParentheses}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpParentheses(xqueryParser.RpParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpParentheses}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpParentheses(xqueryParser.RpParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpAll}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpAll(xqueryParser.RpAllContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpAll}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpAll(xqueryParser.RpAllContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpCurrent}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpCurrent(xqueryParser.RpCurrentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpCurrent}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpCurrent(xqueryParser.RpCurrentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpFilter}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpFilter(xqueryParser.RpFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpFilter}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpFilter(xqueryParser.RpFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpAttribute}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpAttribute(xqueryParser.RpAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpAttribute}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpAttribute(xqueryParser.RpAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpCollection}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpCollection(xqueryParser.RpCollectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpCollection}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpCollection(xqueryParser.RpCollectionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fNot}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterFNot(xqueryParser.FNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fNot}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitFNot(xqueryParser.FNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fRp}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterFRp(xqueryParser.FRpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fRp}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitFRp(xqueryParser.FRpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fEquality}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterFEquality(xqueryParser.FEqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fEquality}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitFEquality(xqueryParser.FEqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fParentheses}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterFParentheses(xqueryParser.FParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fParentheses}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitFParentheses(xqueryParser.FParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fOr}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterFOr(xqueryParser.FOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fOr}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitFOr(xqueryParser.FOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fValueEquality}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterFValueEquality(xqueryParser.FValueEqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fValueEquality}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitFValueEquality(xqueryParser.FValueEqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fAnd}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterFAnd(xqueryParser.FAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fAnd}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitFAnd(xqueryParser.FAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code apFileName}
	 * labeled alternative in {@link xqueryParser#fileName}.
	 * @param ctx the parse tree
	 */
	void enterApFileName(xqueryParser.ApFileNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code apFileName}
	 * labeled alternative in {@link xqueryParser#fileName}.
	 * @param ctx the parse tree
	 */
	void exitApFileName(xqueryParser.ApFileNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link xqueryParser#tagName}.
	 * @param ctx the parse tree
	 */
	void enterTagName(xqueryParser.TagNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link xqueryParser#tagName}.
	 * @param ctx the parse tree
	 */
	void exitTagName(xqueryParser.TagNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link xqueryParser#attName}.
	 * @param ctx the parse tree
	 */
	void enterAttName(xqueryParser.AttNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link xqueryParser#attName}.
	 * @param ctx the parse tree
	 */
	void exitAttName(xqueryParser.AttNameContext ctx);
}