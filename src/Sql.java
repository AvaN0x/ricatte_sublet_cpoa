import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Sql class
 */
public class Sql {

    public static Connection startConnection() {
        String url =
        "jdbc:mysql://localhost/iut_cpoa?serverTimezone=UTC";
        String login = "root";
        String pwd = "root";
        Connection myConnection = null;
        try {
        myConnection = DriverManager.getConnection(url, login, pwd);
        } catch (SQLException sqle) {
        System.out.println("Erreur connexion " + sqle.getMessage());
        }
        return myConnection;
    }

    public static void main(String[] args) {
        var connection = startConnection();
    }
    
}