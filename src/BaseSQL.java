import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseSQL {
    private static String _server;
    private static String _port;
    private static String _database;
    private static String _username;
    private static String _password;

    public static void initConnection() throws IOException {
        Properties credits = new Properties();
        File fBdd = new File("config/creditentials.properties");
        FileInputStream source = new FileInputStream(fBdd);
        credits.loadFromXML(source);

        _server = credits.getProperty("server");
        _port = credits.getProperty("port");
        _database = credits.getProperty("database");
        _username = credits.getProperty("username");
        _password = credits.getProperty("password");
    }
}
