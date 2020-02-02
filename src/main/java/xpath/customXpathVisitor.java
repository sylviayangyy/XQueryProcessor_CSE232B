package xpath;
import org.w3c.dom.Node;
import java.util.LinkedList;


public class customXpathVisitor extends xpathBaseVisitor<LinkedList<Node>> {
    LinkedList<Node> nodes;
    XMLTreeProcessor xml;
    public customXpathVisitor() {
        nodes = new LinkedList<>();
        xml = new XMLTreeProcessor();
    }
    @Override
    public LinkedList<Node> visitApChildren(xpathParser.ApChildrenContext ctx) {
        visit(ctx.fileName());
        this.nodes = visit(ctx.rp());
        return this.nodes;
    }
    @Override public LinkedList<Node> visitApAll(xpathParser.ApAllContext ctx) {
        LinkedList<Node> temp = new LinkedList<>();
        visit(ctx.fileName());
        for (Node node : this.nodes) {
            temp.addAll(xml.getDescendantOrSelf(node));
        }
        this.nodes = temp;
        visit(ctx.rp());
        return this.nodes;
    }

    @Override public LinkedList<Node> visitRpText(xpathParser.RpTextContext ctx) {
        LinkedList<Node> temp = new LinkedList<>();
        for (Node node : this.nodes) {
            temp.addAll(xml.getTextNodes(node));
        }
        return this.nodes = temp;
    }

    @Override public LinkedList<Node> visitRpAny(xpathParser.RpAnyContext ctx) {
        LinkedList<Node> temp = new LinkedList<>();
        for (Node node : this.nodes) {
            temp.addAll(xml.getChildren(node));
        }
        return this.nodes = temp;

    }
    // rp / rp
    @Override public LinkedList<Node> visitRpChildren(xpathParser.RpChildrenContext ctx) {
        visit(ctx.rp(0));
        visit(ctx.rp(1));
        return this.nodes;
    }

    @Override public LinkedList<Node> visitRpTag(xpathParser.RpTagContext ctx) {
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

    @Override public LinkedList<Node> visitRpParent(xpathParser.RpParentContext ctx) {
        LinkedList<Node> temp = new LinkedList<>();
        for (Node node : this.nodes) {
            temp.addAll(xml.getParent(node));
        }
        return this.nodes = temp;
    }

    @Override public LinkedList<Node> visitRpParentheses(xpathParser.RpParenthesesContext ctx) {
        visit(ctx.rp());
        return this.nodes;
    }

    @Override public LinkedList<Node> visitRpAll(xpathParser.RpAllContext ctx) {
        LinkedList<Node> temp = visit(ctx.rp(0));
        for (Node node : this.nodes) {
            temp.addAll(xml.getDescendantOrSelf(node));
        }
        this.nodes = temp;
        this.nodes = visit(ctx.rp(1));
        return this.nodes;
    }

    @Override public LinkedList<Node> visitRpCurrent(xpathParser.RpCurrentContext ctx) {
        return this.nodes;
    }
    // rp '[' f ']'
    @Override public LinkedList<Node> visitRpFilter(xpathParser.RpFilterContext ctx) {
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

    @Override public LinkedList<Node> visitRpAttribute(xpathParser.RpAttributeContext ctx) {
        String name = ctx.attName().getText();
        LinkedList<Node> temp = new LinkedList<>();
        for (Node node : this.nodes) {
            temp.addAll(xml.getAttributeNode(node, name));
        }
        return this.nodes = temp;
    }

    @Override public LinkedList<Node> visitRpCollection(xpathParser.RpCollectionContext ctx) {
        LinkedList<Node> temp = new LinkedList<>();
        LinkedList<Node> origin = this.nodes;
        visit(ctx.rp(0));
        temp.addAll(this.nodes);
        this.nodes = origin;
        visit(ctx.rp(1));
        temp.addAll(this.nodes);
        return this.nodes = temp;
    }

    @Override public LinkedList<Node> visitFNot(xpathParser.FNotContext ctx) {
        if (visit(ctx.f()).isEmpty()) {
            return this.nodes;
        }
        return new LinkedList<>();
    }

    @Override public LinkedList<Node> visitFRp(xpathParser.FRpContext ctx) {
        LinkedList<Node> temp = new LinkedList<>();
        LinkedList<Node> origin = this.nodes;
        temp = visit(ctx.rp());
        this.nodes = origin;
        return temp;
    }

    @Override public LinkedList<Node> visitFEquality(xpathParser.FEqualityContext ctx) {
        LinkedList<Node> origin = this.nodes;
        LinkedList<Node> left = visit(ctx.rp(0));
        this.nodes = origin;
        LinkedList<Node> right = visit(ctx.rp(1));
        if (xml.isSame(left, right))
            return this.nodes;
        return new LinkedList<>();
    }

    @Override public LinkedList<Node> visitFParentheses(xpathParser.FParenthesesContext ctx) {
        visit(ctx.f());
        return this.nodes;
    }

    @Override public LinkedList<Node> visitFOr(xpathParser.FOrContext ctx) {
        if (visit(ctx.f(0)).isEmpty() && visit(ctx.f(1)).isEmpty())
            return this.nodes;
        return new LinkedList<>();
    }

    @Override public LinkedList<Node> visitFValueEquality(xpathParser.FValueEqualityContext ctx) {
        LinkedList<Node> origin = this.nodes;
        LinkedList<Node> left = visit(ctx.rp(0));
        this.nodes = origin;
        LinkedList<Node> right = visit(ctx.rp(1));
        if (xml.isEqual(left, right))
            return this.nodes;
        return new LinkedList<>();
    }

    @Override public LinkedList<Node> visitFAnd(xpathParser.FAndContext ctx) {
        if (visit(ctx.f(0)).isEmpty() || visit(ctx.f(1)).isEmpty())
            return this.nodes;
        return new LinkedList<>();
    }

    @Override public LinkedList<Node> visitApFileName(xpathParser.ApFileNameContext ctx) {
        this.nodes = xml.getRoot(ctx.getText());
        return this.nodes;
    }

    @Override public LinkedList<Node> visitTagName(xpathParser.TagNameContext ctx) {
        return visitChildren(ctx);
    }

    @Override public LinkedList<Node> visitAttName(xpathParser.AttNameContext ctx) {
        return visitChildren(ctx);
    }
}