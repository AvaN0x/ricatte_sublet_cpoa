package dao.MySQL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CommandLineDAO;
import models.CommandLine;
import models.Product;

public class MySQLCommandLineDAO extends MySQLDAO implements CommandLineDAO {

    private MySQLCommandLineDAO() throws IOException {
        super();
    }

    private static MySQLCommandLineDAO instance;

    @Override
    public ArrayList<CommandLine> getByCommandId(int id) throws SQLException, IOException {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM ligne_commande WHERE id_commande=?");
        query.setInt(1, id);
        ResultSet lineRes = query.executeQuery();

        ArrayList<CommandLine> line = new ArrayList<CommandLine>();
        while (lineRes.next())
            line.add(new CommandLine(MySQLCommandDAO.getInstance().getById(lineRes.getInt("id_commande")),
                    lineRes.getInt("quantite"), lineRes.getFloat("raif_unitaire")));
        con.close();
        return line;
    }

    @Override
    public ArrayList<CommandLine> getByProductId(int id) throws SQLException, IOException {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM ligne_commande WHERE id_produit=?");
        query.setInt(1, id);
        ResultSet lineRes = query.executeQuery();

        ArrayList<CommandLine> line = new ArrayList<CommandLine>();
        while (lineRes.next())
            line.add(new CommandLine(MySQLCommandDAO.getInstance().getById(lineRes.getInt("id_commande")),
                    lineRes.getInt("quantite"), lineRes.getFloat("raif_unitaire")));
        con.close();
        return line;
    }

    @Override
    public boolean create(CommandLine line, Product prod) throws SQLException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement(
                "INSERT INTO ligne_commande(id_commande, id_produit, quantite, tarif_unitaire) VALUES (?, ?, ?, ?)");
        update.setInt(1, line.getCommand().getId());
        update.setInt(2, prod.getId());
        update.setInt(3, line.getQuantite());
        update.setFloat(4, line.getTarifUnitaire());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    @Override
    public boolean update(CommandLine line, Product prod) throws SQLException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement(
                "UPDATE ligne_commande SET quantite=?, tarif_unitaire=? WHERE id_commande=? AND id_produit=?");
        update.setInt(1, line.getQuantite());
        update.setFloat(2, line.getTarifUnitaire());
        update.setInt(3, line.getCommand().getId());
        update.setInt(4, prod.getId());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    @Override
    public boolean delete(CommandLine line, Product prod) throws SQLException {
        Connection con = startConnection();
        PreparedStatement update = con
                .prepareStatement("DELETE FROM ligne_commande WHERE id_commande=? AND id_produit=?");
        update.setInt(1, line.getCommand().getId());
        update.setInt(2, prod.getId());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    @Override
    public ArrayList<CommandLine> getAll() throws SQLException, IOException {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM ligne_commande");
        ResultSet lineRes = query.executeQuery();

        ArrayList<CommandLine> line = new ArrayList<CommandLine>();
        while (lineRes.next())
            line.add(new CommandLine(MySQLCommandDAO.getInstance().getById(lineRes.getInt("id_commande")),
                    lineRes.getInt("quantite"), lineRes.getFloat("raif_unitaire")));
        con.close();
        return line;
    }

    public static MySQLCommandLineDAO getInstance() throws IOException {
        if (instance == null)
            instance = new MySQLCommandLineDAO();
        return instance;
    }

}
