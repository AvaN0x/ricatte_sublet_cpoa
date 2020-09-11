import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Sql class
 */
public class Sql {

    public static Connection startConnection() {
        String url =
        "jdbc:mysql://localhost/iut_cpoa";
        url += "?serverTimezone=UTC";
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
        // var connection = startConnection();
    }
    
    public void addCategory(Category c) {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            ResultSet res = request.executeQuery(String.format("INSERT INTO `categorie`(`id_categorie`, `titre`, `visuel`) VALUES (%s, %s, %s)"), c.id_categorie, c.titre, c.visuel); 
        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }
    }

}