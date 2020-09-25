package dao.MySQL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.ProductDAO;
import models.Category;
import models.Product;

public class MySQLProductDAO extends BaseMySQL implements ProductDAO {

    private MySQLProductDAO() throws IOException {
        super();
    }

    private static MySQLProductDAO instance;

    @Override
    public Product getById(int id) throws Exception {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM produit WHERE id_produit=? LIMIT 1");
        query.setInt(1, id);
        ResultSet prodRes = query.executeQuery();

        query = con.prepareStatement("SELECT * FROM categorie WHERE id_categorie=? LIMIT 1");

        Product prod = null;
        while (prodRes.next()) {
            query.setInt(1, prodRes.getInt("id_categorie"));
            ResultSet categRes = query.executeQuery();
            while (categRes.next())
                prod = new Product(prodRes.getInt(1), prodRes.getString("nom"), prodRes.getString("description"),
                        prodRes.getFloat("tarif"), prodRes.getString("visuel"),
                        new Category(categRes.getInt(1), categRes.getString("titre"), categRes.getString("visuel")));
        }
        return prod;
    }

    @Override
    public boolean create(Product prod) throws Exception {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement(
                "INSERT INTO produit(nom, description, tarif, visuel, id_categorie) VALUES (?, ?, ?, ?, ?)");
        update.setString(1, prod.getNom());
        update.setString(2, prod.getDescription());
        update.setFloat(3, prod.getTarif());
        update.setString(4, prod.getVisuel());
        update.setInt(5, prod.getCategory().getId());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    @Override
    public boolean update(Product prod) throws Exception {
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
    public boolean delete(Product prod) throws Exception {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement("DELETE FROM produit WHERE id_produit=?");
        update.setInt(1, prod.getId());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    @Override
    public Product getByName(String name) throws Exception {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT id_produit FROM produit WHERE name=? LIMIT 1");
        query.setString(1, name);
        ResultSet prodRes = query.executeQuery();

        int prodId = 0;
        while (prodRes.next())
            prodId = prodRes.getInt(1);

        con.close();
        return getById(prodId);
    }

    @Override
    public ArrayList<Product> getByCategory(Category categ) throws Exception {
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
    public ArrayList<Product> getAll() throws Exception {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM produit");
        ResultSet prodRes = query.executeQuery();

        query = con.prepareStatement("SELECT * FROM categorie WHERE id_categorie=? LIMIT 1");

        ArrayList<Product> products = new ArrayList<Product>();
        while (prodRes.next()) {
            query.setInt(1, prodRes.getInt("id_categorie"));
            ResultSet categRes = query.executeQuery();
            while (categRes.next())
                products.add(new Product(prodRes.getInt(1), prodRes.getString("nom"), prodRes.getString("description"),
                        prodRes.getFloat("tarif"), prodRes.getString("visuel"),
                        new Category(categRes.getInt(1), categRes.getString("titre"), categRes.getString("visuel"))));
        }
        return products;
    }

    public static MySQLProductDAO getInstance() throws IOException {
        if (instance == null)
            instance = new MySQLProductDAO();
        return instance;
    }

}
