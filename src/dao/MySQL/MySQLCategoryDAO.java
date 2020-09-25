package dao.MySQL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.CategoryDAO;
import models.Category;

public class MySQLCategoryDAO extends BaseMySQL implements CategoryDAO {

    private MySQLCategoryDAO() throws IOException {
        super();
    }

    private static MySQLCategoryDAO instance;

    public Category getById(int id) throws SQLException {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM categorie WHERE id_categorie=? LIMIT 1");
        query.setInt(1, id);
        ResultSet categRes = query.executeQuery();

        Category categ = null;
        while (categRes.next())
            categ = new Category(categRes.getInt(1), categRes.getString("titre"), categRes.getString("visuel"));
        con.close();
        return categ;
    }

    public boolean create(Category categ) throws SQLException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement("INSERT INTO categorie(titre, visuel) VALUES (?, ?)");
        update.setString(1, categ.getTitle());
        update.setString(2, categ.getVisuel());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    public boolean update(Category categ) throws SQLException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement("UPDATE categorie SET titre=?, visuel=? WHERE id_categorie=?");
        update.setString(1, categ.getTitle());
        update.setString(2, categ.getVisuel());
        update.setInt(3, categ.getId());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    public boolean delete(Category categ) throws SQLException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement("DELETE FROM categorie WHERE id_categorie=?");
        update.setInt(1, categ.getId());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    public Category getByTitle(String title) throws SQLException {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT id_categorie FROM categorie WHERE titre=? LIMIT 1");
        query.setString(1, title);
        ResultSet categRes = query.executeQuery();

        int categId = 0;
        while (categRes.next())
            categId = categRes.getInt(1);
        con.close();
        return getById(categId);
    }

    public static MySQLCategoryDAO getInstance() throws IOException {
        if (instance == null)
            instance = new MySQLCategoryDAO();
        return instance;
    }

}
