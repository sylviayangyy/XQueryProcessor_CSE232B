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
//            FileInputStream query = new FileInputStream(args[0]);
            String query = "for $b1 in doc(\"book.xml\")//book,\n" +
                    "\n" +
                    "$aj in $b1/author/first/text(),\n" +
                    "\n" +
                    "$a1 in $b1/author, \n" +
                    "\n" +
                    "$af1 in $a1/first/text(),\n" +
                    "\n" +
                    "$al1 in $a1/last/text(),\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "$b2 in doc(\"book.xml\")//book,\n" +
                    "\n" +
                    "$a21 in $b2/author,\n" +
                    "\n" +
                    "$af21 in $a21/first/text(),\n" +
                    "\n" +
                    "$al21 in $a21/last/text(),\n" +
                    "\n" +
                    "$a22 in $b2/author,\n" +
                    "\n" +
                    "$af22 in $a22/first/text(),\n" +
                    "\n" +
                    "$al22 in $a22/last/text(),\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "$b3 in doc(\"book.xml\")//book,\n" +
                    "\n" +
                    "$a3 in $b3/author,\n" +
                    "\n" +
                    "$af3 in $a3/first/text(),\n" +
                    "\n" +
                    "$al3 in $a3/last/text()\n" +
                    "\n" +
                    "where $aj eq \"W.\" and $af1 eq $af21 and $al1 eq $al21 and $af22 eq $af3 and $al22 eq $al3\n" +
                    "\n" +
                    "return <triplet> { $b1, $b2, $b3} </triplet>\n" +
                    "\n";
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
