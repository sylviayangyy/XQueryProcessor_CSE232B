import IO.IOHelper;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import xquery.*;

public class XQueryProcessor {
    public static void main(String[] args) {
        try {
//            FileInputStream query = new FileInputStream(args[0]);
            String query = "for $b1 in doc(\"book.xml\")/book,\n" +
                    "    $aj in $b1/author/first/text(),\n" +
                    "    $a1 in $b1/author,\n" +
                    "    $af1 in $a1/first,\n" +
                    "    $al1 in $a1/last,\n" +
                    "    \n" +
                    "    $b2 in doc(\"book.xml\")/book,\n" +
                    "    $a2 in $b2/author,\n" +
                    "    $af21 in $a2/first,\n" +
                    "    $al21 in $a2/last,\n" +
                    "    \n" +
                    "    $b3 in doc(\"book.xml\")/book,\n" +
                    "    $a3 in $b3/author,\n" +
                    "    $af3 in $a3/first,\n" +
                    "    $al3 in $a3/last,\n" +
                    "    \n" +
                    "    $b4 in doc(\"book.xml\")/book,\n" +
                    "    $a4 in $b4/author,\n" +
                    "    $af4 in $a4/first,\n" +
                    "    $al4 in $a4/last,\n" +
                    "    \n" +
                    "    $b5 in doc(\"book.xml\")/book,\n" +
                    "    $a5 in $b5/author,\n" +
                    "    $af5 in $a5/first,\n" +
                    "    $al5 in $a5/last,\n" +
                    "    \n" +
                    "    $b6 in doc(\"book.xml\")/book,\n" +
                    "    $a6 in $b6/author,\n" +
                    "    $af6 in $a6/first,\n" +
                    "    $al6 in $a6/last\n" +
                    "    \n" +
                    "where $aj eq \"W.\" and\n" +
                    "      $a1 eq $a2 and $a2 eq $a3 and\n" +
                    "      $a3 eq $a6 and $a3 eq $a5 and $a3 eq $a4\n" +
                    "      \n" +
                    "return <triplet> {$b1, $b2, $b3, $b4, $b5, $b6} </triplet>";
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

//            ANTLRInputStream newInput = new ANTLRInputStream(optimizedQuery);
//            lexer = new xqueryLexer(newInput);
//            tokens = new CommonTokenStream(lexer);
//            parser = new xqueryParser(tokens);
//            tree = parser.xq();
//            CustomXQueryVisitor xQueryVisitor = new CustomXQueryVisitor();
//            IOHelper.outputResult(xQueryVisitor.visit(tree));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
