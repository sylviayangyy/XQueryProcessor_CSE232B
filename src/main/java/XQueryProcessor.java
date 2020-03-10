import IO.IOHelper;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import xquery.CustomXQueryOptimizer;
import xquery.CustomXQueryVisitor;
import xquery.xqueryLexer;
import xquery.xqueryParser;

import java.io.FileInputStream;

public class XQueryProcessor {
    public static void main(String[] args) {
        try {
            FileInputStream query = new FileInputStream(args[0]);
//            String query = "for $s in doc(\"j_caesar.xml\")//SPEAKER, $a in doc(\"j_caesar.xml\")//ACT,\n" +
//                    "\n" +
//                    "    $sp in $a//SPEAKER, $stxt in $s/text()\n" +
//                    "\n" +
//                    "where $sp eq $s and $stxt = \"CAESAR\"\n" +
//                    "\n" +
//                    "return <act> { $a/TITLE/text()} </act>";
            ANTLRInputStream input = new ANTLRInputStream(query);
            xqueryLexer lexer = new xqueryLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            xqueryParser parser = new xqueryParser(tokens);
            ParseTree tree = parser.xq();
            CustomXQueryOptimizer xQueryOptimizer = new CustomXQueryOptimizer();
            String optimizedQuery = xQueryOptimizer.visit(tree);
            System.out.println("OptimizedQuery: ");
            System.out.println(optimizedQuery);
            System.out.println();

            ANTLRInputStream newInput = new ANTLRInputStream(optimizedQuery);
            lexer = new xqueryLexer(newInput);
            tokens = new CommonTokenStream(lexer);
            parser = new xqueryParser(tokens);
            tree = parser.xq();
            CustomXQueryVisitor xQueryVisitor = new CustomXQueryVisitor();
            IOHelper.outputResult(xQueryVisitor.visit(tree));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
