package xquery;

import org.w3c.dom.Node;
import common.XMLTreeProcessor;

import java.util.*;

public class CustomXQueryVisitor extends xqueryBaseVisitor<LinkedList<Node>> {
    LinkedList<Node> nodes = new LinkedList<>();
    Map<String, LinkedList<Node>> variables = new HashMap<>();
    XMLTreeProcessor xml = new XMLTreeProcessor();

    @Override
    public LinkedList<Node> visitXqAp(xqueryParser.XqApContext ctx) {
        return visit(ctx.ap());
    }

    @Override
    public LinkedList<Node> visitXqVar(xqueryParser.XqVarContext ctx) {
        String var = ctx.Var().getText();
        if (variables.containsKey(var)) {
            this.nodes = variables.get(var);
        } else
            this.nodes = new LinkedList<>();
        return this.nodes;
    }

    @Override
    public LinkedList<Node> visitXqJoin(xqueryParser.XqJoinContext ctx) {
        LinkedList<Node> originalNodes = new LinkedList<>(this.nodes);
        LinkedList<Node> firstNodes = new LinkedList<>(visit(ctx.xq(0)));
        this.nodes = originalNodes;
        LinkedList<Node> secondNodes = new LinkedList<>(visit(ctx.xq(1)));
        List<xqueryParser.AttNameContext> firstAttributes = ctx.attributeList(0).attName();
        List<xqueryParser.AttNameContext> secondAttributes = ctx.attributeList(1).attName();
        if (firstAttributes.size()!=secondAttributes.size()) {
            System.out.println("Two Join Parts Have Different Attribute Size");
            return null;
        }
        List<Node> firstAttrNodes = new LinkedList<>();
        for (xqueryParser.AttNameContext attrCtx : firstAttributes) {
            firstAttrNodes.add(xml.makeAttribute(attrCtx.getText()));
        }
        List<Node> secondAttrNodes = new LinkedList<>();
        for (xqueryParser.AttNameContext attrCtx : secondAttributes) {
            secondAttrNodes.add(xml.makeAttribute(attrCtx.getText()));
        }

        HashMap<String, LinkedList<Node>> hashMap = new HashMap<>();
        for (Node node : firstNodes) {
            String key = xml.getKeyForNode(node, firstAttrNodes);
            if (hashMap.containsKey(key)) {
                hashMap.get(key).add(node);
            } else {
                LinkedList<Node> list = new LinkedList<>();
                list.add(node);
                hashMap.put(key, list);
            }
        }

        LinkedList<Node> nodes = new LinkedList<>();
        for (Node secondNode : secondNodes) {
            String key = xml.getKeyForNode(secondNode, secondAttrNodes);
            if (hashMap.containsKey(key)) {
                for (Node firstNode : hashMap.get(key)) {
                    LinkedList<Node> joinNodes = new LinkedList<>();
                    joinNodes.addAll(xml.getChildren(firstNode));
                    joinNodes.addAll(xml.getChildren(secondNode));
                    Node newNode = xml.makeElem(firstNode.getNodeName(), joinNodes);
                    nodes.add(newNode);
                }
            }
        }

        this.nodes = nodes;
        return this.nodes;
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
            } else {
                nodes.addAll(visit(ctx.returnClause()));
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
        visit(ctx.rp());
        this.nodes = xml.unique(this.nodes);
        return this.nodes;
    }

    @Override
    public LinkedList<Node> visitXqParentheses(xqueryParser.XqParenthesesContext ctx) {
        visit(ctx.xq());
        return this.nodes;
    }

    @Override
    public LinkedList<Node> visitXqCollection(xqueryParser.XqCollectionContext ctx) {
        LinkedList<Node> temp = new LinkedList<>();
        LinkedList<Node> origin = this.nodes;
        visit(ctx.xq(0));
        temp.addAll(this.nodes);
        this.nodes = origin;
        visit(ctx.xq(1));
        temp.addAll(this.nodes);
        return this.nodes = temp;
    }

    @Override
    public LinkedList<Node> visitXqStringConstant(xqueryParser.XqStringConstantContext ctx) {
        String constant0 = ctx.StringConstant().getText();
        String constant = constant0.substring(1, constant0.length() - 1);
        return this.nodes = xml.singleNodeToList(xml.makeText(constant));
    }

    @Override
    public LinkedList<Node> visitXqLet(xqueryParser.XqLetContext ctx) {
        HashMap<String, LinkedList<Node>> map = new HashMap<>(this.variables);
        visit(ctx.letClause());
        visit(ctx.xq());
        this.variables = map;
        return this.nodes;
    }

    @Override
    public LinkedList<Node> visitXqTag(xqueryParser.XqTagContext ctx) {
        LinkedList<Node> nodes = new LinkedList<>();
        String tagName1 = ctx.tagName(0).getText();
        String tagName2 = ctx.tagName(1).getText();
        if (tagName1.equals(tagName2)) {
            LinkedList<Node> xquery = visit(ctx.xq());
            nodes.add(xml.makeElem(tagName1, xquery));
        } else {
            System.out.println("mismatch for <> in visitXqTag!!!");
        }
        return this.nodes = nodes;
    }

    @Override
    public LinkedList<Node> visitXqChildren(xqueryParser.XqChildrenContext ctx) {
        visit(ctx.xq());
        visit(ctx.rp());
        this.nodes = xml.unique(this.nodes);
        return this.nodes;
    }

    @Override
    public LinkedList<Node> visitForClause(xqueryParser.ForClauseContext ctx) {
        return null;
    }

    @Override
    // [let Var_1 := xq_1, ..., Var_n := xq_n](C)
    public LinkedList<Node> visitLetClause(xqueryParser.LetClauseContext ctx) {
        for (int i = 0; i < ctx.Var().size(); i++) {
            Map<String, LinkedList<Node>> map = new HashMap<>(this.variables);
            LinkedList<Node> xq = visit(ctx.xq(i));
            this.variables = map;
            this.variables.put(ctx.Var(i).getText(), xq);
        }
        return null;
    }

    @Override
    public LinkedList<Node> visitWhereClause(xqueryParser.WhereClauseContext ctx) {
        return visit(ctx.cond());
    }

    @Override
    public LinkedList<Node> visitReturnClause(xqueryParser.ReturnClauseContext ctx) {
        return visit(ctx.xq());
    }

    @Override
    public LinkedList<Node> visitCondEmpty(xqueryParser.CondEmptyContext ctx) {
        LinkedList<Node> nodes = this.nodes;
        LinkedList<Node> xq = visit(ctx.xq());
        if (xq.isEmpty()) {
            return this.nodes = nodes;
        }
        else return new LinkedList<>();
    }

    @Override
    public LinkedList<Node> visitCondParentheses(xqueryParser.CondParenthesesContext ctx) {
        return visit(ctx.cond());
    }

    @Override
    public LinkedList<Node> visitCondSome(xqueryParser.CondSomeContext ctx) {
        HashMap<String, LinkedList<Node>> map = new HashMap<>(this.variables);
        LinkedList<Node> nodes = this.nodes;
        for (int i = 0; i < ctx.Var().size(); ++i) {
            String varName = ctx.Var(i).getText();
            LinkedList<Node> xq = visit(ctx.xq(i));
            this.variables.put(varName, xq);
        }
        LinkedList<Node> cond = visit(ctx.cond());
        this.variables = map;
        this.nodes = nodes;
        return cond;
    }

    @Override
    public LinkedList<Node> visitCondEquality(xqueryParser.CondEqualityContext ctx) {
        LinkedList<Node> nodes = this.nodes;
        LinkedList<Node> xq1 = visit(ctx.xq(0));
        this.nodes = nodes;
        LinkedList<Node> xq2 = visit(ctx.xq(1));
        this.nodes = nodes;
        if (xml.isSame(xq1, xq2)) {
            return this.nodes;
        }
        return new LinkedList<>();
    }

    @Override
    public LinkedList<Node> visitCondValueEquality(xqueryParser.CondValueEqualityContext ctx) {
        LinkedList<Node> nodes = this.nodes;
        LinkedList<Node> xq1 = visit(ctx.xq(0));
        this.nodes = nodes;
        LinkedList<Node> xq2 = visit(ctx.xq(1));
        this.nodes = nodes;
        if (xml.isEqual(xq1, xq2)) {
            return this.nodes;
        }
        return new LinkedList<>();
    }

    @Override
    public LinkedList<Node> visitCondAnd(xqueryParser.CondAndContext ctx) {
        if (visit(ctx.cond(0)).isEmpty() || visit(ctx.cond(1)).isEmpty())
            return new LinkedList<>();;
        return this.nodes;
    }

    @Override
    public LinkedList<Node> visitCondOr(xqueryParser.CondOrContext ctx) {
        if (visit(ctx.cond(0)).isEmpty() && visit(ctx.cond(1)).isEmpty())
            return new LinkedList<>();
        return this.nodes;
    }

    @Override
    public LinkedList<Node> visitCondNot(xqueryParser.CondNotContext ctx) {
        HashSet<Node> leftSet = new HashSet<Node>(this.nodes);
        HashSet<Node> rightSet = new HashSet<Node>(visit(ctx.cond()));
        HashSet<Node> difference = new HashSet<Node>();
        difference.addAll(leftSet);
        difference.removeAll(rightSet);

        LinkedList<Node> results = new LinkedList<Node>(difference);
        return results;
    }

    @Override
    public LinkedList<Node> visitApChildren(xqueryParser.ApChildrenContext ctx) {
        visit(ctx.fileName());
        this.nodes = visit(ctx.rp());
        return this.nodes;
    }

    @Override
    public LinkedList<Node> visitApAll(xqueryParser.ApAllContext ctx) {
        LinkedList<Node> temp = new LinkedList<>();
        visit(ctx.fileName());
        for (Node node : this.nodes) {
            temp.addAll(xml.getDescendantOrSelf(node));
        }
        this.nodes = temp;
        visit(ctx.rp());
        return this.nodes;
    }

    @Override
    public LinkedList<Node> visitRpText(xqueryParser.RpTextContext ctx) {
        LinkedList<Node> temp = new LinkedList<>();
        for (Node node : this.nodes) {
            temp.addAll(xml.getTextNodes(node));
        }
        return this.nodes = temp;
    }

    @Override
    public LinkedList<Node> visitRpAny(xqueryParser.RpAnyContext ctx) {
        LinkedList<Node> temp = new LinkedList<>();
        for (Node node : this.nodes) {
            temp.addAll(xml.getChildren(node));
        }
        return this.nodes = temp;
    }

    @Override
    public LinkedList<Node> visitRpChildren(xqueryParser.RpChildrenContext ctx) {
        visit(ctx.rp(0));
        visit(ctx.rp(1));
        return this.nodes;
    }

    @Override
    public LinkedList<Node> visitRpTag(xqueryParser.RpTagContext ctx) {
        LinkedList<Node> temp = new LinkedList<>();
        String tagName = ctx.tagName().getText();

        for (Node node : this.nodes) {
            LinkedList<Node> children = xml.getChildren(node);
            for (Node child : children) {
                if (child.getNodeName().equals(tagName))
                    temp.add(child);
            }
        }
        return this.nodes = temp;
    }

    @Override
    public LinkedList<Node> visitRpParent(xqueryParser.RpParentContext ctx) {
        LinkedList<Node> temp = new LinkedList<>();
        for (Node node : this.nodes) {
            temp.addAll(xml.getParent(node));
        }
        return this.nodes = temp;
    }

    @Override
    public LinkedList<Node> visitRpParentheses(xqueryParser.RpParenthesesContext ctx) {
        visit(ctx.rp());
        return this.nodes;
    }

    @Override
    public LinkedList<Node> visitRpAll(xqueryParser.RpAllContext ctx) {
        visit(ctx.rp(0));
        LinkedList<Node> temp = new LinkedList<>();
        for (Node node : this.nodes) {
            temp.addAll(xml.getDescendantOrSelf(node));
        }
        this.nodes = temp;
        visit(ctx.rp(1));
        return this.nodes;
    }

    @Override
    public LinkedList<Node> visitRpCurrent(xqueryParser.RpCurrentContext ctx) {
        return this.nodes;
    }

    @Override
    public LinkedList<Node> visitRpFilter(xqueryParser.RpFilterContext ctx) {
        LinkedList<Node> temp = new LinkedList<>();
        visit(ctx.rp());
        LinkedList<Node> rps = this.nodes;
        for (Node rp : rps) {
            this.nodes = new LinkedList<>();
            if (rp != null)
                this.nodes.add(rp);
            if (!visit(ctx.f()).isEmpty()) {
                temp.add(rp);
            }
        }
        return this.nodes = temp;
    }

    @Override
    public LinkedList<Node> visitRpAttribute(xqueryParser.RpAttributeContext ctx) {
        String name = ctx.attName().getText();
        LinkedList<Node> temp = new LinkedList<>();
        for (Node node : this.nodes) {
            temp.addAll(xml.getAttributeNode(node, name));
        }
        return this.nodes = temp;
    }

    @Override
    public LinkedList<Node> visitRpCollection(xqueryParser.RpCollectionContext ctx) {
        LinkedList<Node> temp = new LinkedList<>();
        LinkedList<Node> origin = this.nodes;
        visit(ctx.rp(0));
        temp.addAll(this.nodes);
        this.nodes = origin;
        visit(ctx.rp(1));
        temp.addAll(this.nodes);
        return this.nodes = temp;
    }

    @Override
    public LinkedList<Node> visitFNot(xqueryParser.FNotContext ctx) {
        HashSet<Node> leftSet = new HashSet<Node>(this.nodes);
        HashSet<Node> rightSet = new HashSet<Node>(visit(ctx.f()));
        HashSet<Node> difference = new HashSet<Node>();
        difference.addAll(leftSet);
        difference.removeAll(rightSet);

        LinkedList<Node> results = new LinkedList<Node>(difference);
        return results;
    }

    @Override
    public LinkedList<Node> visitFRp(xqueryParser.FRpContext ctx) {
        LinkedList<Node> temp = new LinkedList<>();
        LinkedList<Node> origin = this.nodes;
        temp = visit(ctx.rp());
        this.nodes = origin;
        return temp;
    }

    @Override
    public LinkedList<Node> visitFEquality(xqueryParser.FEqualityContext ctx) {
        LinkedList<Node> origin = this.nodes;
        LinkedList<Node> left = visit(ctx.rp(0));
        this.nodes = origin;
        LinkedList<Node> right = visit(ctx.rp(1));
        if (xml.isSame(left, right))
            return this.nodes;
        return new LinkedList<>();
    }

    @Override
    public LinkedList<Node> visitFParentheses(xqueryParser.FParenthesesContext ctx) {
        visit(ctx.f());
        return this.nodes;
    }

    @Override
    public LinkedList<Node> visitFOr(xqueryParser.FOrContext ctx) {
        if (visit(ctx.f(0)).isEmpty() && visit(ctx.f(1)).isEmpty())
            return this.nodes;
        return new LinkedList<>();
    }

    @Override
    public LinkedList<Node> visitFValueEquality(xqueryParser.FValueEqualityContext ctx) {
        LinkedList<Node> origin = this.nodes;
        LinkedList<Node> left = visit(ctx.rp(0));
        this.nodes = origin;
        LinkedList<Node> right = visit(ctx.rp(1));
        if (xml.isEqual(left, right))
            return this.nodes;
        return new LinkedList<>();
    }

    @Override
    public LinkedList<Node> visitFAnd(xqueryParser.FAndContext ctx) {
        if (visit(ctx.f(0)).isEmpty() || visit(ctx.f(1)).isEmpty())
            return this.nodes;
        return new LinkedList<>();
    }

    @Override
    public LinkedList<Node> visitApFileName(xqueryParser.ApFileNameContext ctx) {
        this.nodes = xml.getRoot(ctx.getText());
        return this.nodes;
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
