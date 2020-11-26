package dao.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;

import dao.CommandDAO;
import models.Client;
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
                lines.put(MySQLProductDAO.getInstance().getById(lineRes.getInt("id_produit")),
                        new CommandLine(cmd, lineRes.getInt("quantite"), lineRes.getFloat("tarif_unitaire")));
            }

            cmd.setCommandLines(lines);

        }

        con.close();
        return cmd;
    }

    @Override
    public boolean create(Command cmd) throws SQLException, IOException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement("INSERT INTO commande(date_commande, id_client) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        update.setDate(1, Date.valueOf(cmd.getDateCommand()));
        update.setInt(2, cmd.getClient().getId());
        int result = update.executeUpdate();

        boolean lineResult = true;
        var keys = update.getGeneratedKeys();
        while (keys.next()) {
            cmd.setId(keys.getInt(1));
            for (HashMap.Entry<Product, CommandLine> line : cmd.getCommandLines().entrySet()) {
                if (!MySQLCommandLineDAO.getInstance().create(line.getValue(), line.getKey()))
                    lineResult = false;
            }
        }
        con.close();

        return result >= 1 && lineResult;
    }

    @Override
    public boolean update(Command cmd) throws SQLException, IOException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement(
                "UPDATE commande SET date_commande=?, id_client=? WHERE id_commande=?",
                Statement.RETURN_GENERATED_KEYS);
        update.setDate(1, Date.valueOf(cmd.getDateCommand()));
        update.setInt(2, cmd.getClient().getId());
        update.setInt(3, cmd.getId());
        int result = update.executeUpdate();

        boolean lineResult = true;
        if (result > 0) {
            PreparedStatement select = con.prepareStatement("DELETE FROM ligne_commande WHERE id_commande=?");
            select.setInt(1, cmd.getId());
            result = select.executeUpdate();
            con.close();

            for (HashMap.Entry<Product, CommandLine> line : cmd.getCommandLines().entrySet()) {
                if (!MySQLCommandLineDAO.getInstance().create(line.getValue(), line.getKey()))
                    lineResult = false;
            }
        }

        return result >= 1 && lineResult;
    }

    @Override
    public boolean delete(Command cmd) throws SQLException, IOException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement("DELETE FROM commande WHERE id_commande=?");
        update.setInt(1, cmd.getId());
        int result = update.executeUpdate();
        con.close();

        boolean lineResult = true;
        for (HashMap.Entry<Product, CommandLine> line : cmd.getCommandLines().entrySet()) {
            boolean res = MySQLCommandLineDAO.getInstance().delete(line.getValue(), line.getKey());
            if (!res)
                lineResult = false;
        }

        return result >= 1 && lineResult;
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
                lines.put(MySQLProductDAO.getInstance().getById(lineRes.getInt("id_produit")),
                        new CommandLine(cmd, lineRes.getInt("quantite"), lineRes.getFloat("tarif_unitaire")));
            }

            cmd.setCommandLines(lines);
            cmds.add(cmd);
        }

        con.close();
        return cmds;
    }

    @Override
    public ArrayList<Command> getByClient(Client cli) throws SQLException, IOException {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM commande WHERE id_client=?");
        query.setInt(1, cli.getId());
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
                lines.put(MySQLProductDAO.getInstance().getById(lineRes.getInt("id_produit")),
                        new CommandLine(cmd, lineRes.getInt("quantite"), lineRes.getFloat("tarif_unitaire")));
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
