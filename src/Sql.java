import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Category;

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
        remCategory(7);
        var c = new Category(6, "Pantalon", "pantalon.png");
        addCategory(c);
        remCategory(c.id);
        addCategory(new Category(7, "Testing", "Things"));
        updateCategory(7, "Testing", "Nothing");
    }
    
    public static void addCategory(Category c) {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            var res = request.executeUpdate(String.format("INSERT INTO `categorie`(`id_categorie`, `titre`, `visuel`) VALUES (%s, \"%s\", \"%s\")", c.id, c.title, c.visuel)); 
        } catch (SQLException sqle) {
            System.out.println("Error executeUpdate " + sqle.getMessage());
        }
    }

    public static void updateCategory(int id, String title, String visuel) {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            var res = request.executeUpdate(String.format("UPDATE `categorie` SET `titre`= \"%s\",`visuel`= \"%s\" WHERE id_categorie = %s", title, visuel, id)); 
        } catch (SQLException sqle) {
            System.out.println("Error executeUpdate " + sqle.getMessage());
        }
    }

    public static void remCategory(Category c) {
        remCategory(c.id);
    }
    public static void remCategory(int id) {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            var res = request.executeUpdate(String.format("DELETE FROM `categorie` WHERE id_categorie = %s", id)); 
        } catch (SQLException sqle) {
            System.out.println("Error executeUpdate " + sqle.getMessage());
        }
    }



}