package controllers;

import java.sql.*;
import java.util.ArrayList;

import models.Client;

/**
 * ClientSql class
 */
public class ClientSql extends BaseSql {

    // public static void addClient(Client c) {
    // try {
    // Connection myConnection = startConnection();
    // Statement request = myConnection.createStatement();
    // var res = request.executeUpdate(String.format("INSERT INTO
    // `categorie`(`id_categorie`, `titre`, `visuel`) VALUES (%s, \"%s\", \"%s\")",
    // c.getId(), c.getTitle(), c.getVisuel()));
    // } catch (SQLException sqle) {
    // System.out.println("Error executeUpdate " + sqle.getMessage());
    // }
    // }

    // public static void updateClient(int id, String title, String visuel) {
    // try {
    // Connection myConnection = startConnection();
    // Statement request = myConnection.createStatement();
    // var res = request.executeUpdate(String.format("UPDATE `categorie` SET
    // `titre`= \"%s\",`visuel`= \"%s\" WHERE id_categorie = %s", title, visuel,
    // id));
    // } catch (SQLException sqle) {
    // System.out.println("Error executeUpdate " + sqle.getMessage());
    // }
    // }

    // public static void remClient(Client c) {
    // remClient(c.getId());
    // }
    // public static void remClient(int id) {
    // try {
    // Connection myConnection = startConnection();
    // Statement request = myConnection.createStatement();
    // var res = request.executeUpdate(String.format("DELETE FROM `categorie` WHERE
    // id_categorie = %s", id));
    // } catch (SQLException sqle) {
    // System.out.println("Error executeUpdate " + sqle.getMessage());
    // }
    // }

    // public static ArrayList<Client> getCategories() {
    // try {
    // Connection myConnection = startConnection();
    // Statement request = myConnection.createStatement();
    // ResultSet res = request.executeQuery("SELECT * FROM `categorie`");
    // var categories = new ArrayList<Client>();

    // while (res.next()) {
    // int id = res.getInt(1);
    // String title = res.getString("titre");
    // String visuel = res.getString("visuel");
    // categories.add(new Client(id, title, visuel));
    // }
    // return categories;
    // } catch (SQLException sqle) {
    // System.out.println("Error executeQuery " + sqle.getMessage());
    // return null;
    // }
    // }

}
