package controllers;

import java.sql.*;
import java.util.ArrayList;

import models.Category;

/**
 * CategorySql class
 */
public class CategorySql extends BaseSql {

    public static void addObject(Object obj) {
        System.out.println("FK YEAH");
        try {
            Category c = (Category) obj;
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            request.executeUpdate(String.format(
                    "INSERT INTO `categorie`(`id_categorie`, `titre`, `visuel`) VALUES (%s, \"%s\", \"%s\")", c.getId(),
                    c.getTitle(), c.getVisuel()));
            myConnection.close();
        } catch (SQLException sqle) {
            System.out.println("Error executeUpdate " + sqle.getMessage());
        }
    }

    public static void updateObject(int id, String title, String visuel) {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            request.executeUpdate(
                    String.format("UPDATE `categorie` SET `titre`= \"%s\",`visuel`= \"%s\" WHERE id_categorie = %s",
                            title, visuel, id));
            myConnection.close();
        } catch (SQLException sqle) {
            System.out.println("Error executeUpdate " + sqle.getMessage());
        }
    }

    public static void remObject(Category c) {
        remObject(c.getId());
    }

    public static void remObject(int id) {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            request.executeUpdate(String.format("DELETE FROM `categorie` WHERE id_categorie = %s", id));
            myConnection.close();
        } catch (SQLException sqle) {
            System.out.println("Error executeUpdate " + sqle.getMessage());
        }
    }

    public static ArrayList<Category> getObjects() {
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
            myConnection.close();
            return categories;
        } catch (SQLException sqle) {
            System.out.println("Error executeQuery " + sqle.getMessage());
            return null;
        }
    }

}
