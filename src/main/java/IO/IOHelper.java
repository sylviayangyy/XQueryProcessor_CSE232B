package IO;

import org.w3c.dom.Node;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IOHelper {
    public static void outputResult(List<Node> nodes) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            Set<Node> nodeSet = new HashSet<>();
            for (Node node : nodes) {
                if (!nodeSet.contains(node)) {
                    nodeSet.add(node);
                }
            }
            System.out.println("Output: ");
            for (Node node : nodeSet) {
                StringWriter writer = new StringWriter();
                transformer.transform(new DOMSource(node), new StreamResult(writer));
                System.out.println(writer.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}