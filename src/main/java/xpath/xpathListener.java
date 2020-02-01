package xpath;// Generated from /Users/vivian/XQueryProcessor/src/main/grammar/xpath.g4 by ANTLR 4.8
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link xpathParser}.
 */
public interface xpathListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code apChildren}
	 * labeled alternative in {@link xpathParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterApChildren(xpathParser.ApChildrenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code apChildren}
	 * labeled alternative in {@link xpathParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitApChildren(xpathParser.ApChildrenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code apAll}
	 * labeled alternative in {@link xpathParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterApAll(xpathParser.ApAllContext ctx);
	/**
	 * Exit a parse tree produced by the {@code apAll}
	 * labeled alternative in {@link xpathParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitApAll(xpathParser.ApAllContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpText}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpText(xpathParser.RpTextContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpText}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpText(xpathParser.RpTextContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpAny}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpAny(xpathParser.RpAnyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpAny}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpAny(xpathParser.RpAnyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpChildren}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpChildren(xpathParser.RpChildrenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpChildren}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpChildren(xpathParser.RpChildrenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpTag}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpTag(xpathParser.RpTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpTag}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpTag(xpathParser.RpTagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpParent}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpParent(xpathParser.RpParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpParent}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpParent(xpathParser.RpParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpParentheses}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpParentheses(xpathParser.RpParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpParentheses}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpParentheses(xpathParser.RpParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpAll}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpAll(xpathParser.RpAllContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpAll}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpAll(xpathParser.RpAllContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpCurrent}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpCurrent(xpathParser.RpCurrentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpCurrent}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpCurrent(xpathParser.RpCurrentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpFilter}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpFilter(xpathParser.RpFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpFilter}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpFilter(xpathParser.RpFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpAttribute}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpAttribute(xpathParser.RpAttributeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpAttribute}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpAttribute(xpathParser.RpAttributeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rpCollection}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterRpCollection(xpathParser.RpCollectionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpCollection}
	 * labeled alternative in {@link xpathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitRpCollection(xpathParser.RpCollectionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fNot}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterFNot(xpathParser.FNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fNot}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitFNot(xpathParser.FNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fRp}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterFRp(xpathParser.FRpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fRp}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitFRp(xpathParser.FRpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fEquality}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterFEquality(xpathParser.FEqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fEquality}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitFEquality(xpathParser.FEqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fParentheses}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterFParentheses(xpathParser.FParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fParentheses}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitFParentheses(xpathParser.FParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fOr}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterFOr(xpathParser.FOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fOr}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitFOr(xpathParser.FOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fValueEquality}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterFValueEquality(xpathParser.FValueEqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fValueEquality}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitFValueEquality(xpathParser.FValueEqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fAnd}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterFAnd(xpathParser.FAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fAnd}
	 * labeled alternative in {@link xpathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitFAnd(xpathParser.FAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code apFileName}
	 * labeled alternative in {@link xpathParser#fileName}.
	 * @param ctx the parse tree
	 */
	void enterApFileName(xpathParser.ApFileNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code apFileName}
	 * labeled alternative in {@link xpathParser#fileName}.
	 * @param ctx the parse tree
	 */
	void exitApFileName(xpathParser.ApFileNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link xpathParser#tagName}.
	 * @param ctx the parse tree
	 */
	void enterTagName(xpathParser.TagNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link xpathParser#tagName}.
	 * @param ctx the parse tree
	 */
	void exitTagName(xpathParser.TagNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link xpathParser#attName}.
	 * @param ctx the parse tree
	 */
	void enterAttName(xpathParser.AttNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link xpathParser#attName}.
	 * @param ctx the parse tree
	 */
	void exitAttName(xpathParser.AttNameContext ctx);
}