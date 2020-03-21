import IO.IOHelper;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import xquery.*;

import java.io.FileInputStream;

public class XQueryProcessor {
    public static void main(String[] args) {
        try {
            FileInputStream query = new FileInputStream(args[0]);
//            FileInputStream query = new FileInputStream("input.txt");
//            String query = "";
            String label = args[1];

            ANTLRInputStream input = new ANTLRInputStream(query);
            xqueryLexer lexer = new xqueryLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            xqueryParser parser = new xqueryParser(tokens);
            ParseTree tree = parser.xq();
            String optimizedQuery = "";
            if (label.equals("-L")) {
                CustomXQueryLeftDeepOptimizer xQueryOptimizer = new CustomXQueryLeftDeepOptimizer();
                optimizedQuery = xQueryOptimizer.visit(tree);
            } else if (label.equals("-B")) {
                CustomXQueryBushyOptimizer xQueryBushyOptimizer = new CustomXQueryBushyOptimizer();
                optimizedQuery = xQueryBushyOptimizer.visit(tree);
            } else {
                System.err.println("The input label is invalid");
                return;
            }
//            String optimizedQuery = xQueryOptimizer.visit(tree);

//            System.out.println("OptimizedQuery: ");
//            System.out.println(optimizedQuery);
//            System.out.println();
            IOHelper.outputRewrittenToFile(optimizedQuery, "RewrittenQuery.txt");

            ANTLRInputStream newInput = new ANTLRInputStream(optimizedQuery);
            lexer = new xqueryLexer(newInput);
            tokens = new CommonTokenStream(lexer);
            parser = new xqueryParser(tokens);
            tree = parser.xq();
            CustomXQueryVisitor xQueryVisitor = new CustomXQueryVisitor();
//            IOHelper.outputResult(xQueryVisitor.visit(tree));
            IOHelper.outputResultToFile(xQueryVisitor.visit(tree), "Result.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
