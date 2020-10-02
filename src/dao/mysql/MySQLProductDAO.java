package dao.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProductDAO;
import models.Category;
import models.Product;

public class MySQLProductDAO extends MySQLDAO implements ProductDAO {

    private MySQLProductDAO() throws IOException {
        super();
    }

    private static MySQLProductDAO instance;

    @Override
    public Product getById(int id) throws SQLException, IOException {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM produit WHERE id_produit=? LIMIT 1");
        query.setInt(1, id);
        ResultSet prodRes = query.executeQuery();

        Product prod = null;
        while (prodRes.next()) {
            prod = new Product(prodRes.getInt(1), prodRes.getString("nom"), prodRes.getString("description"),
                    prodRes.getFloat("tarif"), prodRes.getString("visuel"),
                    MySQLCategoryDAO.getInstance().getById(prodRes.getInt("id_categorie")));
        }
        return prod;
    }

    @Override
    public boolean create(Product prod) throws SQLException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement(
                "INSERT INTO produit(nom, description, tarif, visuel, id_categorie) VALUES (?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        update.setString(1, prod.getNom());
        update.setString(2, prod.getDescription());
        update.setFloat(3, prod.getTarif());
        update.setString(4, prod.getVisuel());
        update.setInt(5, prod.getCategory().getId());
        int result = update.executeUpdate();

        var keys = update.getGeneratedKeys();
        while (keys.next())
            prod.setId(keys.getInt(1));

        con.close();
        return result >= 1;
    }

    @Override
    public boolean update(Product prod) throws SQLException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement(
                "UPDATE produit SET nom=?, description=?, tarif=?, visuel=?, id_categorie=? WHERE id_produit=?");
        update.setString(1, prod.getNom());
        update.setString(2, prod.getDescription());
        update.setFloat(3, prod.getTarif());
        update.setString(4, prod.getVisuel());
        update.setInt(5, prod.getCategory().getId());
        update.setInt(6, prod.getId());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    @Override
    public boolean delete(Product prod) throws SQLException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement("DELETE FROM produit WHERE id_produit=?");
        update.setInt(1, prod.getId());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    @Override
    public Product getByName(String name) throws SQLException, IOException {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT id_produit FROM produit WHERE nom=? LIMIT 1");
        query.setString(1, name);
        ResultSet prodRes = query.executeQuery();

        int prodId = 0;
        while (prodRes.next())
            prodId = prodRes.getInt(1);

        con.close();
        return getById(prodId);
    }

    @Override
    public ArrayList<Product> getByCategory(Category categ) throws SQLException {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM produit WHERE id_categorie=?");
        query.setInt(1, categ.getId());
        ResultSet prodRes = query.executeQuery();

        ArrayList<Product> products = new ArrayList<Product>();
        while (prodRes.next())
            products.add(new Product(prodRes.getInt(1), prodRes.getString("nom"), prodRes.getString("description"),
                    prodRes.getFloat("tarif"), prodRes.getString("visuel"), categ));
        con.close();
        return products;
    }

    @Override
    public ArrayList<Product> getAll() throws SQLException, IOException {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM produit");
        ResultSet prodRes = query.executeQuery();

        ArrayList<Product> products = new ArrayList<Product>();
        while (prodRes.next()) {
            products.add(new Product(prodRes.getInt(1), prodRes.getString("nom"), prodRes.getString("description"),
                    prodRes.getFloat("tarif"), prodRes.getString("visuel"),
                    MySQLCategoryDAO.getInstance().getById(prodRes.getInt("id_categorie"))));
        }
        return products;
    }

    public static MySQLProductDAO getInstance() throws IOException {
        if (instance == null)
            instance = new MySQLProductDAO();
        return instance;
    }

}
