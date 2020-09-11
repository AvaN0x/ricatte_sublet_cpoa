package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Product;
import models.Category;

/**
 * ProductSql class
 */
public class ProductSql {

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

    public static void addProduct(Product p) {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            var res = request.executeUpdate(String.format("INSERT INTO `produit`(`id_produit`, `nom`, `description`, `tarif`, `visuel`, `id_categorie`) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\")", p.getId(), p.getNom(), p.getDescription(), p.getTarif(), p.getVisuel(), p.getCategorie().getId())); 
        } catch (SQLException sqle) {
            System.out.println("Error executeUpdate " + sqle.getMessage());
        }
    }

    public static void updateProduct(int id, String nom, String description, float tarif, String visuel, Category categorie) {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            var res = request.executeUpdate(String.format("UPDATE `produit` SET `nom`=\"%s\",`description`=\"%s\",`tarif`=\"%s\",`visuel`=\"%s\",`id_categorie`=\"%s\" WHERE \"%s\" WHERE id_produit = %s", nom, description, tarif, visuel, categorie.getId(), id)); 
        } catch (SQLException sqle) {
            System.out.println("Error executeUpdate " + sqle.getMessage());
        }
    }

    public static void remProduct(Product p) {
        remProduct(p.getId());
    }
    public static void remProduct(int id) {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            var res = request.executeUpdate(String.format("DELETE FROM `produit` WHERE id_produit = %s", id)); 
        } catch (SQLException sqle) {
            System.out.println("Error executeUpdate " + sqle.getMessage());
        }
    }

    public static ArrayList<Product> getProduits() {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            ResultSet res = request.executeQuery("SELECT id_produit, nom, description, tarif, produit.visuel AS pvisuel, id_categorie, titre, categorie.visuel AS cvisuel FROM `produit` JOIN `categorie` USING(id_categorie)");
            var produits = new ArrayList<Product>();

            while (res.next()) {
                int id_produit = res.getInt("id_produit");
                String nom = res.getString("nom");
                String description = res.getString("description");
                float tarif = res.getFloat("tarif");
                String pvisuel = res.getString("pvisuel");

                int id_categorie = res.getInt("id_categorie");
                String titre = res.getString("titre");
                String cvisuel = res.getString("cvisuel");
                produits.add(new Product(id_produit, nom, description, tarif, pvisuel, new Category(id_categorie, titre, cvisuel)));
            }
            return produits;
        } catch (SQLException sqle) {
            System.out.println("Error executeQuery " + sqle.getMessage());
            return null;
        } 
    }

}
