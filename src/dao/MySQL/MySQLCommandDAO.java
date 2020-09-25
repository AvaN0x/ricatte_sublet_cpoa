package dao.MySQL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;

import dao.CommandDAO;
import models.Command;
import models.CommandLine;
import models.Product;

public class MySQLCommandDAO extends MySQLDAO implements CommandDAO {

    private MySQLCommandDAO() throws IOException {
        super();
    }

    private static MySQLCommandDAO instance;

    @Override
    public Command getById(int id) throws SQLException, IOException {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM commande WHERE id_commande=? LIMIT 1");
        query.setInt(1, id);
        ResultSet comRes = query.executeQuery();

        query = con.prepareStatement("SELECT * FROM ligne_commande WHERE id_commande=?");

        Command cmd = null;
        while (comRes.next()) {
            cmd = new Command(comRes.getInt("id_commande"), comRes.getDate("date_commande").toLocalDate(),
                    MySQLClientDAO.getInstance().getById(comRes.getInt("id_client")));

            // ! Can't use MySQLCommandLineDAO => Stack Overflow
            query.setInt(1, comRes.getInt("id_commande"));
            ResultSet lineRes = query.executeQuery();
            HashMap<Product, CommandLine> lines = new HashMap<Product, CommandLine>();
            while (lineRes.next()) { // multiple result
                lines.put(MySQLProductDAO.getInstance().getById(comRes.getInt("id_produit")),
                        new CommandLine(cmd, comRes.getInt("quantite"), comRes.getFloat("tarif_unitaire")));
            }

            cmd.setCommandLines(lines);

        }

        con.close();
        return cmd;
    }

    @Override
    public boolean create(Command cmd) throws SQLException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement("INSERT INTO commande(date_commande, id_client) VALUES (?, ?)");
        update.setDate(1, (Date) Date.from(cmd.getDateCommand().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        update.setInt(2, cmd.getClient().getId());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    @Override
    public boolean update(Command cmd) throws SQLException {
        Connection con = startConnection();
        PreparedStatement update = con
                .prepareStatement("UPDATE commande SET date_commande=?, id_client=? WHERE id_commande=?");
        update.setDate(1, (Date) Date.from(cmd.getDateCommand().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        update.setInt(2, cmd.getClient().getId());
        update.setInt(3, cmd.getId());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    @Override
    public boolean delete(Command cmd) throws SQLException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement("DELETE FROM commande WHERE id_commande=?");
        update.setInt(1, cmd.getId());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    @Override
    public ArrayList<Command> getAll() throws SQLException, IOException {

        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM commande");
        ResultSet comRes = query.executeQuery();

        query = con.prepareStatement("SELECT * FROM ligne_commande WHERE id_commande=?");

        ArrayList<Command> cmds = new ArrayList<Command>();
        while (comRes.next()) {
            Command cmd = new Command(comRes.getInt("id_commande"), comRes.getDate("date_commande").toLocalDate(),
                    MySQLClientDAO.getInstance().getById(comRes.getInt("id_client")));

            // ! Can't use MySQLCommandLineDAO => Stack Overflow
            query.setInt(1, comRes.getInt("id_commande"));
            ResultSet lineRes = query.executeQuery();
            HashMap<Product, CommandLine> lines = new HashMap<Product, CommandLine>();
            while (lineRes.next()) { // multiple result
                lines.put(MySQLProductDAO.getInstance().getById(comRes.getInt("id_produit")),
                        new CommandLine(cmd, comRes.getInt("quantite"), comRes.getFloat("tarif_unitaire")));
            }

            cmd.setCommandLines(lines);
            cmds.add(cmd);
        }

        con.close();
        return cmds;
    }

    public static MySQLCommandDAO getInstance() throws IOException {
        if (instance == null)
            instance = new MySQLCommandDAO();
        return instance;
    }
}
