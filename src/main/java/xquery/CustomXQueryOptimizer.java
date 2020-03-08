package xquery;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.w3c.dom.Node;

import java.util.*;

public class CustomXQueryOptimizer extends xqueryBaseVisitor<LinkedList<Node>> {
    private enum Step {
        INITIAL, CHECK_FOR, CHECK_WHERE, OPTIMIZE, REWRITE
    }

    private class Metadata {
        boolean optimizable = true;
        Step step = Step.INITIAL;
        Map<String, String> varAndParent = new HashMap<>();
        Map<String, LinkedList<String>> rootsAndAllVariables = new HashMap<>();
        Map<String, String> varAndPath = new HashMap<>();
        Map<String, Set<String>> varEqualVar = new HashMap<>();
        Map<String, List<String>> varEqualConst = new HashMap<>();
        List<String> varsJoined = new LinkedList<>();
    }

    public CustomXQueryOptimizer() {
        Metadata metadata = new Metadata();
    }

    @Override
    public LinkedList<Node> visitXqAp(xqueryParser.XqApContext ctx) {
        return super.visitXqAp(ctx);
    }

    @Override
    public LinkedList<Node> visitXqVar(xqueryParser.XqVarContext ctx) {
        return super.visitXqVar(ctx);
    }

    @Override
    public LinkedList<Node> visitXqJoin(xqueryParser.XqJoinContext ctx) {
        return super.visitXqJoin(ctx);
    }

    @Override
    public LinkedList<Node> visitXqFLWR(xqueryParser.XqFLWRContext ctx) {
        return super.visitXqFLWR(ctx);
    }

    @Override
    public LinkedList<Node> visitXqAll(xqueryParser.XqAllContext ctx) {
        return super.visitXqAll(ctx);
    }

    @Override
    public LinkedList<Node> visitXqParentheses(xqueryParser.XqParenthesesContext ctx) {
        return super.visitXqParentheses(ctx);
    }

    @Override
    public LinkedList<Node> visitXqCollection(xqueryParser.XqCollectionContext ctx) {
        return super.visitXqCollection(ctx);
    }

    @Override
    public LinkedList<Node> visitXqStringConstant(xqueryParser.XqStringConstantContext ctx) {
        return super.visitXqStringConstant(ctx);
    }

    @Override
    public LinkedList<Node> visitXqLet(xqueryParser.XqLetContext ctx) {
        return super.visitXqLet(ctx);
    }

    @Override
    public LinkedList<Node> visitXqTag(xqueryParser.XqTagContext ctx) {
        return super.visitXqTag(ctx);
    }

    @Override
    public LinkedList<Node> visitXqChildren(xqueryParser.XqChildrenContext ctx) {
        return super.visitXqChildren(ctx);
    }

    @Override
    public LinkedList<Node> visitForClause(xqueryParser.ForClauseContext ctx) {
        return super.visitForClause(ctx);
    }

    @Override
    public LinkedList<Node> visitLetClause(xqueryParser.LetClauseContext ctx) {
        return super.visitLetClause(ctx);
    }

    @Override
    public LinkedList<Node> visitWhereClause(xqueryParser.WhereClauseContext ctx) {
        return super.visitWhereClause(ctx);
    }

    @Override
    public LinkedList<Node> visitReturnClause(xqueryParser.ReturnClauseContext ctx) {
        return super.visitReturnClause(ctx);
    }

    @Override
    public LinkedList<Node> visitCondEmpty(xqueryParser.CondEmptyContext ctx) {
        return super.visitCondEmpty(ctx);
    }

    @Override
    public LinkedList<Node> visitCondParentheses(xqueryParser.CondParenthesesContext ctx) {
        return super.visitCondParentheses(ctx);
    }

    @Override
    public LinkedList<Node> visitCondSome(xqueryParser.CondSomeContext ctx) {
        return super.visitCondSome(ctx);
    }

    @Override
    public LinkedList<Node> visitCondEquality(xqueryParser.CondEqualityContext ctx) {
        return super.visitCondEquality(ctx);
    }

    @Override
    public LinkedList<Node> visitCondValueEquality(xqueryParser.CondValueEqualityContext ctx) {
        return super.visitCondValueEquality(ctx);
    }

    @Override
    public LinkedList<Node> visitCondAnd(xqueryParser.CondAndContext ctx) {
        return super.visitCondAnd(ctx);
    }

    @Override
    public LinkedList<Node> visitCondOr(xqueryParser.CondOrContext ctx) {
        return super.visitCondOr(ctx);
    }

    @Override
    public LinkedList<Node> visitCondNot(xqueryParser.CondNotContext ctx) {
        return super.visitCondNot(ctx);
    }

    @Override
    public LinkedList<Node> visitAttributeList(xqueryParser.AttributeListContext ctx) {
        return super.visitAttributeList(ctx);
    }

    @Override
    public LinkedList<Node> visitApChildren(xqueryParser.ApChildrenContext ctx) {
        return super.visitApChildren(ctx);
    }

    @Override
    public LinkedList<Node> visitApAll(xqueryParser.ApAllContext ctx) {
        return super.visitApAll(ctx);
    }

    @Override
    public LinkedList<Node> visitRpText(xqueryParser.RpTextContext ctx) {
        return super.visitRpText(ctx);
    }

    @Override
    public LinkedList<Node> visitRpAny(xqueryParser.RpAnyContext ctx) {
        return super.visitRpAny(ctx);
    }

    @Override
    public LinkedList<Node> visitRpChildren(xqueryParser.RpChildrenContext ctx) {
        return super.visitRpChildren(ctx);
    }

    @Override
    public LinkedList<Node> visitRpTag(xqueryParser.RpTagContext ctx) {
        return super.visitRpTag(ctx);
    }

    @Override
    public LinkedList<Node> visitRpParent(xqueryParser.RpParentContext ctx) {
        return super.visitRpParent(ctx);
    }

    @Override
    public LinkedList<Node> visitRpParentheses(xqueryParser.RpParenthesesContext ctx) {
        return super.visitRpParentheses(ctx);
    }

    @Override
    public LinkedList<Node> visitRpAll(xqueryParser.RpAllContext ctx) {
        return super.visitRpAll(ctx);
    }

    @Override
    public LinkedList<Node> visitRpCurrent(xqueryParser.RpCurrentContext ctx) {
        return super.visitRpCurrent(ctx);
    }

    @Override
    public LinkedList<Node> visitRpFilter(xqueryParser.RpFilterContext ctx) {
        return super.visitRpFilter(ctx);
    }

    @Override
    public LinkedList<Node> visitRpAttribute(xqueryParser.RpAttributeContext ctx) {
        return super.visitRpAttribute(ctx);
    }

    @Override
    public LinkedList<Node> visitRpCollection(xqueryParser.RpCollectionContext ctx) {
        return super.visitRpCollection(ctx);
    }

    @Override
    public LinkedList<Node> visitFNot(xqueryParser.FNotContext ctx) {
        return super.visitFNot(ctx);
    }

    @Override
    public LinkedList<Node> visitFRp(xqueryParser.FRpContext ctx) {
        return super.visitFRp(ctx);
    }

    @Override
    public LinkedList<Node> visitFEquality(xqueryParser.FEqualityContext ctx) {
        return super.visitFEquality(ctx);
    }

    @Override
    public LinkedList<Node> visitFParentheses(xqueryParser.FParenthesesContext ctx) {
        return super.visitFParentheses(ctx);
    }

    @Override
    public LinkedList<Node> visitFOr(xqueryParser.FOrContext ctx) {
        return super.visitFOr(ctx);
    }

    @Override
    public LinkedList<Node> visitFValueEquality(xqueryParser.FValueEqualityContext ctx) {
        return super.visitFValueEquality(ctx);
    }

    @Override
    public LinkedList<Node> visitFAnd(xqueryParser.FAndContext ctx) {
        return super.visitFAnd(ctx);
    }

    @Override
    public LinkedList<Node> visitApFileName(xqueryParser.ApFileNameContext ctx) {
        return super.visitApFileName(ctx);
    }

    @Override
    public LinkedList<Node> visitTagName(xqueryParser.TagNameContext ctx) {
        return super.visitTagName(ctx);
    }

    @Override
    public LinkedList<Node> visitAttName(xqueryParser.AttNameContext ctx) {
        return super.visitAttName(ctx);
    }

    @Override
    public LinkedList<Node> visit(ParseTree tree) {
        return super.visit(tree);
    }

    @Override
    public LinkedList<Node> visitChildren(RuleNode node) {
        return super.visitChildren(node);
    }

    @Override
    public LinkedList<Node> visitTerminal(TerminalNode node) {
        return super.visitTerminal(node);
    }

    @Override
    public LinkedList<Node> visitErrorNode(ErrorNode node) {
        return super.visitErrorNode(node);
    }

    @Override
    protected LinkedList<Node> defaultResult() {
        return super.defaultResult();
    }

    @Override
    protected LinkedList<Node> aggregateResult(LinkedList<Node> aggregate, LinkedList<Node> nextResult) {
        return super.aggregateResult(aggregate, nextResult);
    }

    @Override
    protected boolean shouldVisitNextChild(RuleNode node, LinkedList<Node> currentResult) {
        return super.shouldVisitNextChild(node, currentResult);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
