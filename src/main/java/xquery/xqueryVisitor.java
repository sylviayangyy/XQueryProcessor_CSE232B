// Generated from /Users/vivian/XQueryProcessor/src/main/grammar/xquery.g4 by ANTLR 4.8
package xquery;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link xqueryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface xqueryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code xqAp}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqAp(xqueryParser.XqApContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqVar}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqVar(xqueryParser.XqVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqJoin}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqJoin(xqueryParser.XqJoinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqFLWR}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqFLWR(xqueryParser.XqFLWRContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqAll}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqAll(xqueryParser.XqAllContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqParentheses}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqParentheses(xqueryParser.XqParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqCollection}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqCollection(xqueryParser.XqCollectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqStringConstant}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqStringConstant(xqueryParser.XqStringConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqLet}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqLet(xqueryParser.XqLetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqTag}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqTag(xqueryParser.XqTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqChildren}
	 * labeled alternative in {@link xqueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqChildren(xqueryParser.XqChildrenContext ctx);
	/**
	 * Visit a parse tree produced by {@link xqueryParser#forClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForClause(xqueryParser.ForClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link xqueryParser#letClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetClause(xqueryParser.LetClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link xqueryParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClause(xqueryParser.WhereClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link xqueryParser#returnClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnClause(xqueryParser.ReturnClauseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condEmpty}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondEmpty(xqueryParser.CondEmptyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condParentheses}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondParentheses(xqueryParser.CondParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condSome}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondSome(xqueryParser.CondSomeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condEquality}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondEquality(xqueryParser.CondEqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condValueEquality}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondValueEquality(xqueryParser.CondValueEqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condAnd}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondAnd(xqueryParser.CondAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condOr}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondOr(xqueryParser.CondOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condNot}
	 * labeled alternative in {@link xqueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondNot(xqueryParser.CondNotContext ctx);
	/**
	 * Visit a parse tree produced by {@link xqueryParser#attributeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeList(xqueryParser.AttributeListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code apChildren}
	 * labeled alternative in {@link xqueryParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApChildren(xqueryParser.ApChildrenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code apAll}
	 * labeled alternative in {@link xqueryParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApAll(xqueryParser.ApAllContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpText}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpText(xqueryParser.RpTextContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpAny}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpAny(xqueryParser.RpAnyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpChildren}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpChildren(xqueryParser.RpChildrenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpTag}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpTag(xqueryParser.RpTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpParent}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpParent(xqueryParser.RpParentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpParentheses}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpParentheses(xqueryParser.RpParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpAll}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpAll(xqueryParser.RpAllContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpCurrent}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpCurrent(xqueryParser.RpCurrentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpFilter}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpFilter(xqueryParser.RpFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpAttribute}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpAttribute(xqueryParser.RpAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpCollection}
	 * labeled alternative in {@link xqueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpCollection(xqueryParser.RpCollectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fNot}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFNot(xqueryParser.FNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fRp}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFRp(xqueryParser.FRpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fEquality}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFEquality(xqueryParser.FEqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fParentheses}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFParentheses(xqueryParser.FParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fOr}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFOr(xqueryParser.FOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fValueEquality}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFValueEquality(xqueryParser.FValueEqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fAnd}
	 * labeled alternative in {@link xqueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFAnd(xqueryParser.FAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code apFileName}
	 * labeled alternative in {@link xqueryParser#fileName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApFileName(xqueryParser.ApFileNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link xqueryParser#tagName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagName(xqueryParser.TagNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link xqueryParser#attName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttName(xqueryParser.AttNameContext ctx);
}