import IO.IOHelper;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import xquery.CustomXQueryVisitor;
import xquery.xqueryLexer;
import xquery.xqueryParser;

public class XQueryProcessor {
    public static void main(String[] args) {
        String xquery = "<LINES>\n" +
                "{for $a in (doc(\"j_caesar.xml\")/ACT)\n" +
                " let $s := $a/SCENE/SPEECH\n" +
                " return (for $l in $s/LINE\n" +
                "        where $l/text() = \"Is this a holiday? what! know you not,\"\n" +
                "        return $l)\n" +
                "}\n" +
                "</LINES>";
//        System.out.println(args.length);
//        StringBuilder queryBuilder = new StringBuilder();
//        for (String s : args) {
//            System.out.println(s);
//            queryBuilder.append(s);
//            queryBuilder.append(" ");
//        }
//        String xquery = queryBuilder.toString();
//        xquery = xquery.substring(0, xquery.length()-1);
//        System.out.println(xquery);
//        String xquery = args[0];
//        System.out.println(xquery);

        ANTLRInputStream input = new ANTLRInputStream(xquery);
        xqueryLexer lexer = new xqueryLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        xqueryParser parser = new xqueryParser(tokens);
        ParseTree tree = parser.xq();
//        System.out.println(tree.toStringTree());
        CustomXQueryVisitor xQueryVisitor = new CustomXQueryVisitor();
//        xQueryVisitor.visit(tree);
        IOHelper.outputResult(xQueryVisitor.visit(tree));
    }
}
