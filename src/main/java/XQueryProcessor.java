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
//            String query = "<result>{  for $a in (for $s in doc(\"j_caesar.xml\")//ACT return $s), \n" +
//                    "\n" +
//                    "                     $sc in (for $t in $a/SCENE return $t),\n" +
//                    "\n" +
//                    "                     $sp in (for $d in $sc/SPEECH return $d)\n" +
//                    "\n" +
//                    "                 where $sp/LINE/text() = \"Et tu, Brute! Then fall, Caesar.\"\n" +
//                    "\n" +
//                    "                 return <who>{$sp/SPEAKER/text()}</who>,\n" +
//                    "\n" +
//                    "                           <when>{\n" +
//                    "\n" +
//                    "                                           <act>{$a/TITLE/text()}</act>,\n" +
//                    "\n" +
//                    "                                           <scene>{$sc/TITLE/text()}</scene>\n" +
//                    "\n" +
//                    "                           }</when>\n" +
//                    "\n" +
//                    "}\n" +
//                    "\n" +
//                    "</result>";
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
