import IO.IOHelper;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import xpath.customXpathVisitor;
import xpath.xpathLexer;
import xpath.xpathParser;

public class XPathProcessor {
    public static void main(String[] args) {
//        String xpath = "doc(\"j_caesar.xml\")//ACT//SPEECH/../../*/../TITLE";
        String xpath = args[0];
        ANTLRInputStream input = new ANTLRInputStream(xpath);
        xpathLexer lexer = new xpathLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        xpathParser parser = new xpathParser(tokens);
        ParseTree tree = parser.ap();
        customXpathVisitor xPathVisitor = new customXpathVisitor();
        xPathVisitor.visit(tree);
        IOHelper.outputResult(xPathVisitor.visit(tree));
    }
}
