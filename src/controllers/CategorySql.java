package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Category;

/**
 * CategorySql class
 */
public class CategorySql {

    public static Connection startConnection() {
        String url = "jdbc:mysql://localhost/iut_cpoa";
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

    public static void addCategory(Category c) {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            var res = request.executeUpdate(String.format("INSERT INTO `categorie`(`id_categorie`, `titre`, `visuel`) VALUES (%s, \"%s\", \"%s\")", c.getId(), c.getTitle(), c.getVisuel())); 
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
        remCategory(c.getId());
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

    public static ArrayList<Category> getCategories() {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            ResultSet res = request.executeQuery("SELECT * FROM `categorie`");
            var categories = new ArrayList<Category>();

            while (res.next()) {
                int id = res.getInt(1);
                String title = res.getString("titre");
                String visuel = res.getString("visuel");
                categories.add(new Category(id, title, visuel));
            }
            return categories;
        } catch (SQLException sqle) {
            System.out.println("Error executeQuery " + sqle.getMessage());
            return null;
        } 
    }

}
