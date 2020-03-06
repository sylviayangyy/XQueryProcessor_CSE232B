import IO.IOHelper;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import xpath.CustomXPathVisitor;
import xpath.xpathLexer;
import xpath.xpathParser;

public class XPathProcessor {
    public static void main(String[] args) {
//        String xpath = "doc(\"j_caesar.xml\")/ACT/SCENE/SPEECH/LINE/text()";
        String xpath = args[0];
        ANTLRInputStream input = new ANTLRInputStream(xpath);
        xpathLexer lexer = new xpathLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        xpathParser parser = new xpathParser(tokens);
        ParseTree tree = parser.ap();
        CustomXPathVisitor xPathVisitor = new CustomXPathVisitor();
//        xPathVisitor.visit(tree);
        IOHelper.outputResult(xPathVisitor.visit(tree));
    }
}
