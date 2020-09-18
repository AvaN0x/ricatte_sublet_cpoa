import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

public class BaseSQL {
    private static String _server;
    private static String _port;
    private static String _database;
    private static String _username;
    private static String _password;

    public static void initConnection() throws ParserConfigurationException, SAXException, IOException {
        File file = new File("creditentials.xml");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("sql");

        for (int itr = 0; itr < nodeList.getLength(); itr++) {
            Node node = nodeList.item(itr);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                _server = eElement.getElementsByTagName("server").item(0).getTextContent();
                _port = eElement.getElementsByTagName("port").item(0).getTextContent();
                _database = eElement.getElementsByTagName("database").item(0).getTextContent();
                _username = eElement.getElementsByTagName("username").item(0).getTextContent();
                _password = eElement.getElementsByTagName("password").item(0).getTextContent();
            }
        }
    }
}
