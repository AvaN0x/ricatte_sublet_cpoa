package dao.MySQL;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BaseMySQL {
    private static String _server;
    private static String _port;
    private static String _database;
    private static String _username;
    private static String _password;

    public BaseMySQL() throws IOException {
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

    public Connection startConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC", _server, _port, _database);
        return DriverManager.getConnection(url, _username, _password);
    }
}
