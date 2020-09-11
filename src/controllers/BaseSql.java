package controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

public abstract class BaseSql {

    private static String server;
    private static String port;
    private static String database;
    private static String username;
    private static String password;

    public static void initConnection() {
        try {
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
                    server = eElement.getElementsByTagName("server").item(0).getTextContent();
                    port = eElement.getElementsByTagName("port").item(0).getTextContent();
                    database = eElement.getElementsByTagName("database").item(0).getTextContent();
                    username = eElement.getElementsByTagName("username").item(0).getTextContent();
                    password = eElement.getElementsByTagName("password").item(0).getTextContent();
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println("Erreur lecture " + e.getMessage());
        }
    }

    public static Connection startConnection() {
        String url = String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC", server, port, database);
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException sqle) {
            System.out.println("Erreur connexion " + sqle.getMessage());
        }
        return null;
    }
}
