package xquery;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.w3c.dom.Node;

import java.util.*;

public class CustomXQueryOptimizer extends xqueryBaseVisitor<String> {
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
    private Metadata metadata;

    public CustomXQueryOptimizer() {
        metadata = new Metadata();
    }

    @Override
    public String visitXqAp(xqueryParser.XqApContext ctx) {
        if (metadata.step == Step.CHECK_WHERE) {
            metadata.optimizable = false;
        }
        return visit(ctx.ap());
    }

    @Override
    // $b1 -> $tuple/b1/*
    public String visitXqVar(xqueryParser.XqVarContext ctx) {
        String var = ctx.Var().getText();
        if (metadata.step == Step.REWRITE) {
            String name = var.substring(1);
            var = "$tuple/" + name + "/*";
        }
        return var;
    }

    @Override
    public String visitXqJoin(xqueryParser.XqJoinContext ctx) {
        metadata.optimizable = false;
        return " join("
                + visit(ctx.xq(0)) + "," + visit(ctx.xq(1)) + ","
                + visit(ctx.attributeList(0)) + "," + visit(ctx.attributeList(1))
                + ")";
    }

    @Override
    public String visitXqFLWR(xqueryParser.XqFLWRContext ctx) {
        return super.visitXqFLWR(ctx);
    }

    @Override
    public String visitXqAll(xqueryParser.XqAllContext ctx) {
        if (metadata.step == Step.CHECK_WHERE)
            metadata.optimizable = false;
        return visit(ctx.xq()) + "//" + visit(ctx.rp());
    }

    @Override
    public String visitXqParentheses(xqueryParser.XqParenthesesContext ctx) {
        return "(" + visit(ctx.xq()) + ")";
    }

    @Override
    public String visitXqCollection(xqueryParser.XqCollectionContext ctx) {
        if (metadata.step == Step.CHECK_FOR || metadata.step == Step.CHECK_WHERE)
            metadata.optimizable = false;
            return visit(ctx.xq(0)) + "," + visit(ctx.xq(1));
    }

    @Override
    public String visitXqStringConstant(xqueryParser.XqStringConstantContext ctx) {
        if (metadata.step == Step.CHECK_FOR)
            metadata.optimizable = false;
        return ctx.StringConstant().getText();
    }

    @Override
    public String visitXqLet(xqueryParser.XqLetContext ctx) {
        metadata.optimizable = false;
        return visit(ctx.letClause()) + " " + visit(ctx.xq());
    }

    @Override
    public String visitXqTag(xqueryParser.XqTagContext ctx) {
        if (metadata.step == Step.CHECK_FOR || metadata.step == Step.CHECK_WHERE)
            metadata.optimizable = false;
        return "<" + ctx.tagName(0).getText() + ">{" + visit(ctx.xq()) + "}</" + ctx.tagName(1).getText() + ">";
    }

    @Override
    public String visitXqChildren(xqueryParser.XqChildrenContext ctx) {
        if (metadata.step == Step.CHECK_WHERE)
            metadata.optimizable = false;
        return visit(ctx.xq()) + "/" + visit(ctx.rp());
    }

    @Override
    public String visitForClause(xqueryParser.ForClauseContext ctx) {
        if (metadata.step == Step.OPTIMIZE) {
            for (int i = 0; i < ctx.Var().size(); i++) {
                String varName = ctx.Var(i).getText();
                String varPath = visit(ctx.xq(i));
                //add subquery(path) to var
                metadata.varAndPath.put(varName, varPath);
                // path -> 'doc(' || var
                    // 1. start with var with parent
                if (varPath.startsWith("$") && (!varPath.startsWith("$Undefined"))) {
                    String parent = varPath.split("/")[0];
                    metadata.varAndParent.put(varName, parent);
                    // find the root of the dependency relationship
                    String root = parent;
                    while (metadata.varAndParent.get(root) != null)
                        root = metadata.varAndParent.get(root);
                    // Add this var to the group of variables that depend on the root
                    metadata.rootsAndAllVariables.get(root).add(varName);
                } else {
                    // 2. start with root
                    metadata.varAndParent.put(varName, null);
                    LinkedList<String> vars = new LinkedList<>();
                    vars.add(varName);
                    metadata.rootsAndAllVariables.put(varName, vars);
                }
            }
        }

        String s = "";
        s += " for ";
        for (int i = 0; i < ctx.Var().size(); ++i) {
            s += ctx.Var(i).getText() + " in " + visit(ctx.xq(i));
            if (i != (ctx.Var().size() - 1)) {
                s += ",";
            }
        }
        return s;
    }

    @Override
    public String visitLetClause(xqueryParser.LetClauseContext ctx) {
        String s = "";
        s += " let ";
        for (int i = 0; i < ctx.Var().size(); ++i) {
            s += ctx.Var(i).getText() + ":=" + visit(ctx.xq(i));
            if (i != (ctx.Var().size() - 1)) {
                s += ",";
            }
        }
        return s;
    }

    @Override
    public String visitWhereClause(xqueryParser.WhereClauseContext ctx) {
        return " where " + visit(ctx.cond());
    }

    @Override
    public String visitReturnClause(xqueryParser.ReturnClauseContext ctx) {
        return " return " + visit(ctx.xq());
    }

    @Override
    public String visitCondEmpty(xqueryParser.CondEmptyContext ctx) {
        metadata.optimizable = false;
        return " empty(" + visit(ctx.xq()) + ")";
    }

    @Override
    public String visitCondParentheses(xqueryParser.CondParenthesesContext ctx) {
        return "(" + visit(ctx.cond()) + ")";
    }

    @Override
    public String visitCondSome(xqueryParser.CondSomeContext ctx) {
        metadata.optimizable = false;
        String s = "";
        s += " some ";
        for (int i = 0; i < ctx.Var().size(); ++i) {
            s += ctx.Var(i).getText() + " in " + visit(ctx.xq(i));
            if (i != (ctx.Var().size() - 1)) {
                s += ",";
            }
        }
        s += " satisfies " + visit(ctx.cond());
        return s;
    }

    @Override
    public String visitCondEquality(xqueryParser.CondEqualityContext ctx) {
        metadata.optimizable = false;
        return visit(ctx.xq(0)) + "==" + visit(ctx.xq(1));
    }

    @Override
    // (Var|Constant) ’eq’ (Var|Constant)
    public String visitCondValueEquality(xqueryParser.CondValueEqualityContext ctx) {
        if (metadata.step == Step.OPTIMIZE) {
            String left = visit(ctx.xq(0));
            String right = visit(ctx.xq(1));
            boolean leftIsVar = left.startsWith("$");
            boolean rightIsVar = right.startsWith("$");
            if (leftIsVar && rightIsVar) {
                metadata.varEqualVar.putIfAbsent(left, new HashSet<>());
                metadata.varEqualVar.get(left).add(right);
                metadata.varEqualVar.putIfAbsent(right, new HashSet<>());
                metadata.varEqualVar.get(right).add(left);
            } else if (leftIsVar) {
                metadata.varEqualConst.putIfAbsent(left, new LinkedList<>());
                metadata.varEqualConst.get(left).add(right);
            } else if (rightIsVar) {
                metadata.varEqualConst.putIfAbsent(right, new LinkedList<>());
                metadata.varEqualConst.get(right).add(left);
            }
        }
        return visit(ctx.xq(0)) + "=" + visit(ctx.xq(1));
    }

    @Override
    public String visitCondAnd(xqueryParser.CondAndContext ctx) {
        return visit(ctx.cond(0)) + " and " + visit(ctx.cond(1));
    }

    @Override
    public String visitCondOr(xqueryParser.CondOrContext ctx) {
        metadata.optimizable = false;
        return visit(ctx.cond(0)) + " or " + visit(ctx.cond(1));
    }

    @Override
    public String visitCondNot(xqueryParser.CondNotContext ctx) {
        metadata.optimizable = false;
        return " not " + visit(ctx.cond());
    }

    @Override
    public String visitAttributeList(xqueryParser.AttributeListContext ctx) {
        String s = "";
        s += "[";
        for (int i = 0; i < ctx.attName().size(); ++i) {
            s += ctx.attName(i).getText();
            if (i != (ctx.attName().size() - 1)) {
                s += ",";
            }
        }
        s += "]";
        return s;
    }

    @Override
    public String visitApChildren(xqueryParser.ApChildrenContext ctx) {
        return visit(ctx.fileName()) + "/" + visit(ctx.rp());
    }

    @Override
    public String visitApAll(xqueryParser.ApAllContext ctx) {
        return visit(ctx.fileName()) + "//" + visit(ctx.rp());
    }

    @Override
    public String visitRpText(xqueryParser.RpTextContext ctx) {
        return "text()";
    }

    @Override
    public String visitRpAny(xqueryParser.RpAnyContext ctx) {
        return "*";
    }

    @Override
    public String visitRpChildren(xqueryParser.RpChildrenContext ctx) {
        return visit(ctx.rp(0)) + "/" + visit(ctx.rp(1));
    }

    @Override
    public String visitRpTag(xqueryParser.RpTagContext ctx) {
        return ctx.tagName().getText();
    }

    @Override
    public String visitRpParent(xqueryParser.RpParentContext ctx) {
        return "..";
    }

    @Override
    public String visitRpParentheses(xqueryParser.RpParenthesesContext ctx) {
        return "(" + visit(ctx.rp()) + ")";
    }

    @Override
    public String visitRpAll(xqueryParser.RpAllContext ctx) {
        return visit(ctx.rp(0)) + "//" + visit(ctx.rp(1));
    }

    @Override
    public String visitRpCurrent(xqueryParser.RpCurrentContext ctx) {
        return ".";
    }

    @Override
    public String visitRpFilter(xqueryParser.RpFilterContext ctx) {
        return visit(ctx.rp()) + "[" + visit(ctx.f()) + "]";
    }

    @Override
    public String visitRpAttribute(xqueryParser.RpAttributeContext ctx) {
        return "@" + ctx.attName().getText();
    }

    @Override
    public String visitRpCollection(xqueryParser.RpCollectionContext ctx) {
        return visit(ctx.rp(0)) + "," + visit(ctx.rp(1));
    }

    @Override
    public String visitFNot(xqueryParser.FNotContext ctx) {
        return " not " + visit(ctx.f());
    }

    @Override
    public String visitFRp(xqueryParser.FRpContext ctx) {
        return visit(ctx.rp());
    }

    @Override
    public String visitFEquality(xqueryParser.FEqualityContext ctx) {
        return visit(ctx.rp(0)) + "==" + visit(ctx.rp(1));
    }

    @Override
    public String visitFParentheses(xqueryParser.FParenthesesContext ctx) {
        return "(" + visit(ctx.f()) + ")";
    }

    @Override
    public String visitFOr(xqueryParser.FOrContext ctx) {
        return visit(ctx.f(0)) + " or " + visit(ctx.f(1));
    }

    @Override
    public String visitFValueEquality(xqueryParser.FValueEqualityContext ctx) {
        return visit(ctx.rp(0)) + "=" + visit(ctx.rp(1));
    }

    @Override
    public String visitFAnd(xqueryParser.FAndContext ctx) {
        return visit(ctx.f(0)) + " and " + visit(ctx.f(1));
    }

    @Override
    public String visitApFileName(xqueryParser.ApFileNameContext ctx) {
        return "doc(" + ctx.getText() + ")";
    }

    @Override
    public String visitTagName(xqueryParser.TagNameContext ctx) {
        return super.visitTagName(ctx);
    }

    @Override
    public String visitAttName(xqueryParser.AttNameContext ctx) {
        return super.visitAttName(ctx);
    }

    @Override
    public String visit(ParseTree tree) {
        return super.visit(tree);
    }

    @Override
    public String visitChildren(RuleNode node) {
        return super.visitChildren(node);
    }

    @Override
    public String visitTerminal(TerminalNode node) {
        return super.visitTerminal(node);
    }

    @Override
    public String visitErrorNode(ErrorNode node) {
        return super.visitErrorNode(node);
    }

    @Override
    protected String defaultResult() {
        return super.defaultResult();
    }

    @Override
    protected String aggregateResult(String aggregate, String nextResult) {
        return super.aggregateResult(aggregate, nextResult);
    }

    @Override
    protected boolean shouldVisitNextChild(RuleNode node, String currentResult) {
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
