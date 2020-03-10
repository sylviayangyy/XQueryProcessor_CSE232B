package xquery;

import java.util.*;

public class CustomXQueryOptimizer extends xqueryBaseVisitor<String> {
    private enum Step {
        INITIAL, CHECK_FOR, CHECK_WHERE, CHECK_RETURN, OPTIMIZE, REWRITE_RETURN
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
        if (metadata.step == Step.REWRITE_RETURN) {
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
        if (metadata.step != Step.INITIAL || !metadata.optimizable) {
            return xqFLWRToString(ctx);
        }

        metadata.step = Step.CHECK_FOR;
        visit(ctx.forClause());
        if (!metadata.optimizable) {
            return xqFLWRToString(ctx);
        }

        if (ctx.letClause() != null) {
            return xqFLWRToString(ctx);
        }

        metadata.step = Step.CHECK_WHERE;
        visit(ctx.whereClause());
        if (!metadata.optimizable) {
            return xqFLWRToString(ctx);
        }

        metadata.step = Step.CHECK_RETURN;
        visit(ctx.returnClause());
        if (!metadata.optimizable) {
            return xqFLWRToString(ctx);
        }

        metadata.step = Step.OPTIMIZE;
        visit(ctx.forClause());
        int treeNum = metadata.rootsAndAllVariables.size();
        if (treeNum <= 1) {
            return xqFLWRToString(ctx);
        }
        visit(ctx.whereClause());

        String first = getJoinSubQuery();
        treeNum--;
        while (treeNum >= 1) {
            List<String> firstJoinAttributes = new LinkedList<>();
            List<String> secondJoinAttributes = new LinkedList<>();
            List<String> secondVars = metadata.rootsAndAllVariables.entrySet().iterator().next().getValue();
            for (String firstVar : metadata.varsJoined) {
                Set<String> firstVarEqualVar = metadata.varEqualVar.getOrDefault(firstVar, new HashSet<>());
                if (firstVarEqualVar.size() == 0) {
                    continue;
                }
                for (String secondVar : secondVars) {
                    if (firstVarEqualVar.contains(secondVar)) {
                        firstJoinAttributes.add(firstVar.substring(1));
                        secondJoinAttributes.add(secondVar.substring(1));
                        metadata.varEqualVar.get(firstVar).remove(secondVar);
                        metadata.varEqualVar.get(secondVar).remove(firstVar);
                    }
                }
            }
            String second = getJoinSubQuery();
            first = "join ( \n" + first + ",\n\n" + second + ",\n\n [" + String.join(",", firstJoinAttributes) + "], [" + String.join(",", secondJoinAttributes) + "])";
            treeNum--;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("for $tuple in ");
        sb.append(first);
        sb.append("\n\n");
        metadata.step = Step.REWRITE_RETURN;
        sb.append(visit(ctx.returnClause()));
        return sb.toString();
    }

    private String getJoinSubQuery() {
        String root = metadata.rootsAndAllVariables.keySet().iterator().next();
        StringBuilder sb = new StringBuilder();
        sb.append(" for ");
        List<String> vars = metadata.rootsAndAllVariables.remove(root);
        //for clause
        List<String> varInPath = new LinkedList<>();
        for (String var : vars) {
            varInPath.add(var + " in " + metadata.varAndPath.get(var));
        }
        sb.append(String.join(",\n", varInPath));
        sb.append("\n");

        //where clause
        List<String> whereConds = new LinkedList<>();
        //var eq const
        for (String var : vars) {
            List<String> constants = metadata.varEqualConst.getOrDefault(var, new LinkedList<>());
            if (constants.size() != 0) {
                for (String s : constants) {
                    whereConds.add(var + " eq " + s);
                }
            }
            metadata.varEqualConst.remove(var);
        }
        //var eq var, both vars are in the same tree
        for (String left : vars) {
            for (String right : vars) {
                if (metadata.varEqualVar.getOrDefault(left, new HashSet<>()).contains(right)) {
                    whereConds.add(left + " eq " + right);
                    metadata.varEqualVar.get(left).remove(right);
                    metadata.varEqualVar.get(right).remove(left);
                }
            }
        }
        if (whereConds.size() > 0) {
            sb.append(" where ");
            sb.append(String.join(" and ", whereConds));
            sb.append("\n");
        }

        //return clause
        sb.append(" return ");
        sb.append("<tuple>{");
        List<String> returnVars = new LinkedList<>();
        for (String var : vars) {
            returnVars.add("<" + var.substring(1) + ">{" + var + "}</" + var.substring(1) + ">");
        }
        sb.append(String.join(",", returnVars));
        sb.append("}</tuple>");
        metadata.varsJoined.addAll(vars);
        return sb.toString();
    }

    private String xqFLWRToString(xqueryParser.XqFLWRContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.forClause()));
        if (ctx.letClause() != null) {
            sb.append(visit(ctx.letClause()));
        }
        if (ctx.whereClause() != null) {
            sb.append(visit(ctx.whereClause()));
        }
        sb.append(visit(ctx.returnClause()));
        return sb.toString();
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
                // 1. start with var
                if (varPath.startsWith("$") && (!varPath.startsWith("$Undefined"))) {
                    String parent = varPath.split("/")[0];
                    metadata.varAndParent.put(varName, parent);
                    // find the root of this tree
                    String root = parent;
                    while (metadata.varAndParent.get(root) != null)
                        root = metadata.varAndParent.get(root);
                    // Add this var to the tree depend on the root
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

        StringBuilder sb = new StringBuilder();
        sb.append(" for ");
//        String s = "";
//        s += " for ";
        if (ctx.Var().size() != ctx.xq().size()) {
            System.err.println("The number of var and xq in for clause are not consistent.");
        }
        for (int i = 0; i < ctx.Var().size(); i++) {
            sb.append(ctx.Var(i).getText());
            sb.append(" in ");
            sb.append(visit(ctx.xq(i)));
//            s += ctx.Var(i).getText() + " in " + visit(ctx.xq(i));
            if (i != (ctx.Var().size() - 1)) {
                sb.append(",");
//                s += ",";
            }
        }
        return sb.toString();
    }

    @Override
    public String visitLetClause(xqueryParser.LetClauseContext ctx) {
//        String s = "";
        StringBuilder sb = new StringBuilder();
        sb.append(" let ");
//        s += " let ";
        for (int i = 0; i < ctx.Var().size(); ++i) {
//            sb.append(ctx.Var(i).getText() + ":=" + visit(ctx.xq(i))) ;
            sb.append(ctx.Var(i).getText());
            sb.append(":=");
            sb.append(visit((ctx.xq(i))));
            if (i != (ctx.Var().size() - 1)) {
//                s += ",";
                sb.append(",");
            }
        }
        return sb.toString();
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
//        String s = "";
        StringBuilder sb = new StringBuilder();
        sb.append(" some ");
//        s += " some ";
        for (int i = 0; i < ctx.Var().size(); ++i) {
            sb.append(ctx.Var(i).getText());
            sb.append(" in ");
            sb.append(visit(ctx.xq(i)));
//            s += ctx.Var(i).getText() + " in " + visit(ctx.xq(i));
            if (i != (ctx.Var().size() - 1)) {
                sb.append(",");
//                s += ",";
            }
        }
        sb.append(" satisfies ");
        sb.append(visit(ctx.cond()));
//        s += " satisfies " + visit(ctx.cond());
        return sb.toString();
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
//        String s = "";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
//        s += "[";
        for (int i = 0; i < ctx.attName().size(); ++i) {
            sb.append(ctx.attName(i).getText());
//            s += ctx.attName(i).getText();
            if (i != (ctx.attName().size() - 1)) {
                sb.append(",");
//                s += ",";
            }
        }
        sb.append("]");
//        s += "]";
        return sb.toString();
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
        return "doc(\"" + ctx.getText() + "\")";
    }
}
