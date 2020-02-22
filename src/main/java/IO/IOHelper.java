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

//            System.out.println("origin size: " + nodes.size());
//            Set<Node> nodeSet = new HashSet<>();
//            for (Node node : nodes) {
//                if (!nodeSet.contains(node)) {
//                    nodeSet.add(node);
//                }
//            }
//            System.out.println("unique size: " + nodeSet.size());
            System.out.println("Output: ");
            for (Node node : nodes) {
                StringWriter writer = new StringWriter();
                transformer.transform(new DOMSource(node), new StreamResult(writer));
                String output = writer.toString();
                System.out.println(output.substring(output.indexOf("?>") + 2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}