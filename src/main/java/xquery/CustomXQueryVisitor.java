package xquery;

import common.XMLTreeProcessor;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CustomXQueryVisitor extends xqueryBaseVisitor<LinkedList<Node>> {
    LinkedList<Node> nodes = new LinkedList<>();
    Map<String, LinkedList<Node>> variables = new HashMap<>();
    XMLTreeProcessor xml = new XMLTreeProcessor();

    @Override
    public LinkedList<Node> visitXqAp(xqueryParser.XqApContext ctx) {
        return super.visitXqAp(ctx);
    }

    @Override
    public LinkedList<Node> visitXqVar(xqueryParser.XqVarContext ctx) {
        return super.visitXqVar(ctx);
    }

    @Override
    public LinkedList<Node> visitXqFLWR(xqueryParser.XqFLWRContext ctx) {
        LinkedList<Node> nodes = new LinkedList<>();
        Map<String, LinkedList<Node>> tempVarsBeforeForClause = new HashMap<>(this.variables);
        forLoops(0, ctx.forClause().Var().size(), nodes, ctx);
        this.variables = tempVarsBeforeForClause;
        this.nodes = nodes;
        return this.nodes;
    }

    private void forLoops(int curVarNum, int totalVarNum, LinkedList<Node> nodes, xqueryParser.XqFLWRContext ctx) {
        if (curVarNum == totalVarNum) {
            Map<String, LinkedList<Node>> tempVarsBeforeLetClause = new HashMap<>(this.variables);
            if (ctx.letClause()!=null) {
                visit(ctx.letClause());
            }
            if (ctx.whereClause()!=null) {
                if (visit(ctx.whereClause()).size()>0 ) {
                    nodes.addAll(visit(ctx.returnClause()));
                }
            }
            this.variables = tempVarsBeforeLetClause;
        } else {
            String varName = ctx.forClause().Var(curVarNum).getText();
            LinkedList<Node> varValue = visit(ctx.forClause().xq(curVarNum));
            for (Node node : varValue) {
                this.variables.put(varName, xml.singleNodeToList(node));
                forLoops(curVarNum+1, totalVarNum, nodes, ctx);
            }
        }
    }

    @Override
    public LinkedList<Node> visitXqAll(xqueryParser.XqAllContext ctx) {
        visit(ctx.xq());
        LinkedList<Node> temp = new LinkedList<>();
        for (Node node : this.nodes) {
            temp.addAll(xml.getDescendantOrSelf(node));
        }
        this.nodes = temp;
        this.nodes = xml.unique(temp);
        return this.nodes;
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
        visit(ctx.xq());
        LinkedList<Node> temp = new LinkedList<>();
        for (Node node : this.nodes) {
            temp.addAll(xml.getChildren(node));
        }
        this.nodes = temp;
        this.nodes = xml.unique(temp);
        return this.nodes;
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
}
