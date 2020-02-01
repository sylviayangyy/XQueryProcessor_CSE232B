package xpath;// Generated from /Users/vivian/XQueryProcessor/src/main/grammar/xpath.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link xpathParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface xpathVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code apChildren}
	 * labeled alternative in {@link xpathParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApChildren(xpathParser.ApChildrenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code apAll}
	 * labeled alternative in {@link xpathParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApAll(xpathParser.ApAllContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpText}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpText(xpathParser.RpTextContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpAny}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpAny(xpathParser.RpAnyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpChildren}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpChildren(xpathParser.RpChildrenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpTag}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpTag(xpathParser.RpTagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpParent}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpParent(xpathParser.RpParentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpParentheses}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpParentheses(xpathParser.RpParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpAll}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpAll(xpathParser.RpAllContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpCurrent}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpCurrent(xpathParser.RpCurrentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpFilter}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpFilter(xpathParser.RpFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpAttribute}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpAttribute(xpathParser.RpAttributeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rpCollection}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpCollection(xpathParser.RpCollectionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fNot}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFNot(xpathParser.FNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fRp}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFRp(xpathParser.FRpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fEquality}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFEquality(xpathParser.FEqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fParentheses}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFParentheses(xpathParser.FParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fOr}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFOr(xpathParser.FOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fValueEquality}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFValueEquality(xpathParser.FValueEqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fAnd}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFAnd(xpathParser.FAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code apFileName}
	 * labeled alternative in {@link xpathParser#fileName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApFileName(xpathParser.ApFileNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link xpathParser#tagName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagName(xpathParser.TagNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link xpathParser#attName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttName(xpathParser.AttNameContext ctx);
}