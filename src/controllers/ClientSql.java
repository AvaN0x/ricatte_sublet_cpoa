package controllers;

import java.sql.*;
import java.util.ArrayList;

import models.Client;

/**
 * ClientSql class
 */
public class ClientSql extends BaseSql {
    public static void addObject(Client c) {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            // request.executeUpdate(
            // String.format("INSERT INTO `client`(`id_client`, `nom`, `prenom`) VALUES
            // (\"%s\", \"%s\", \"%s\")",
            // c.getId(), c.getNom(), c.getPrenom()));
            request.executeUpdate(String.format(
                    "INSERT INTO `client`(`id_client`, `nom`, `prenom`, `identifiant`, `mot_de_passe`, `adr_numero`, `adr_voie`, `adr_code_postal`, `adr_ville`, `adr_pays`)"
                            + "VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\")",
                    c.getId(), c.getNom(), c.getPrenom(), "", "", "", "", "", "", ""));

            myConnection.close();
        } catch (SQLException sqle) {
            System.out.println("Error executeUpdate " + sqle.getMessage());
        }
    }

    public static void updateObject(int id, String nom, String prenom) {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            request.executeUpdate(String.format("UPDATE `client` SET `nom`=\"%s\",`prenom`=\"%s\" WHERE id_client = %s",
                    nom, prenom, id));
            myConnection.close();
        } catch (SQLException sqle) {
            System.out.println("Error executeUpdate " + sqle.getMessage());
        }
    }

    public static void remObject(Client c) {
        remObject(c.getId());
    }

    public static void remObject(int id) {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            request.executeUpdate(String.format("DELETE FROM `client` WHERE id_client = %s", id));
            myConnection.close();
        } catch (SQLException sqle) {
            System.out.println("Error executeUpdate " + sqle.getMessage());
        }
    }

    public static ArrayList<Client> getObjects() {
        try {
            Connection myConnection = startConnection();
            Statement request = myConnection.createStatement();
            ResultSet res = request.executeQuery("SELECT id_client, nom, prenom FROM `client`");
            var clients = new ArrayList<Client>();

            while (res.next()) {
                int id = res.getInt("id_client");
                String nom = res.getString("nom");
                String prenom = res.getString("prenom");

                clients.add(new Client(id, nom, prenom));
            }
            myConnection.close();
            return clients;
        } catch (SQLException sqle) {
            System.out.println("Error executeQuery " + sqle.getMessage());
            return null;
        }
    }

}
