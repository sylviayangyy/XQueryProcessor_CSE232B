import IO.IOHelper;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import xquery.*;

import java.io.FileInputStream;

public class XQueryProcessor {
    public static void main(String[] args) {
        try {
//            FileInputStream query = new FileInputStream(args[0]);
            FileInputStream query = new FileInputStream("input.txt");
//            String query = "";
            ANTLRInputStream input = new ANTLRInputStream(query);
            xqueryLexer lexer = new xqueryLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            xqueryParser parser = new xqueryParser(tokens);
            ParseTree tree = parser.xq();
//            CustomXQueryLeftDeepOptimizer xQueryOptimizer = new CustomXQueryLeftDeepOptimizer();
            CustomXQueryBushyOptimizer xQueryBushyOptimizer = new CustomXQueryBushyOptimizer();
//            String optimizedQuery = xQueryOptimizer.visit(tree);
            String optimizedQuery = xQueryBushyOptimizer.visit(tree);

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
