package controllers;

import java.sql.*;

public abstract class BaseSql {
    public static Connection startConnection() {
        String url = "jdbc:mysql://localhost/iut_cpoa?serverTimezone=UTC";
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
}
