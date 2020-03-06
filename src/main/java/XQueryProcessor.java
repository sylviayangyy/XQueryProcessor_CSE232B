import IO.IOHelper;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import xquery.CustomXQueryVisitor;
import xquery.xqueryLexer;
import xquery.xqueryParser;

import java.io.FileInputStream;

public class XQueryProcessor {
    public static void main(String[] args) {
        try {
            FileInputStream query = new FileInputStream(args[0]);
            ANTLRInputStream input = new ANTLRInputStream(query);
            xqueryLexer lexer = new xqueryLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            xqueryParser parser = new xqueryParser(tokens);
            ParseTree tree = parser.xq();
            CustomXQueryVisitor xQueryVisitor = new CustomXQueryVisitor();
            IOHelper.outputResult(xQueryVisitor.visit(tree));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
