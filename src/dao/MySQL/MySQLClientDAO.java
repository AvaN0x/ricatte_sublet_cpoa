package dao.MySQL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ClientDAO;
import models.Client;

public class MySQLClientDAO extends MySQLDAO implements ClientDAO {

    private MySQLClientDAO() throws IOException {
        super();
    }

    private static MySQLClientDAO instance;

    @Override
    public Client getById(int id) throws SQLException {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM client WHERE id_categorie=? LIMIT 1");
        query.setInt(1, id);
        ResultSet cliRes = query.executeQuery();

        Client cli = null;
        while (cliRes.next())
            cli = new Client(cliRes.getInt(1), cliRes.getString("nom"), cliRes.getString("prenom"),
                    cliRes.getString("identifiant"), cliRes.getString("mot_de_passe"), cliRes.getInt("adr_numero"),
                    cliRes.getString("adr_voie"), cliRes.getInt("adr_code_postal"), cliRes.getString("adr_ville"),
                    cliRes.getString("adr_pays"));
        con.close();
        return cli;
    }

    @Override
    public boolean create(Client cli) throws SQLException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement(
                "INSERT INTO client(nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        update.setString(1, cli.getNom());
        update.setString(2, cli.getPrenom());
        update.setString(3, cli.getIdentifiant());
        update.setString(4, cli.getMotDePasse());
        update.setInt(5, cli.getAdrNumero());
        update.setString(6, cli.getAdrVoie());
        update.setInt(7, cli.getAdrCodePostal());
        update.setString(8, cli.getAdrVille());
        update.setString(9, cli.getAdrPays());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    @Override
    public boolean update(Client cli) throws SQLException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement(
                "UPDATE client SET nom=?, prenom=?, identifiant=?, mot_de_passe=?, adr_numero=?, adr_voie=?, adr_code_postal=?, adr_ville=?, adr_pays=? WHERE id_client=?");
        update.setString(1, cli.getNom());
        update.setString(2, cli.getPrenom());
        update.setString(3, cli.getIdentifiant());
        update.setString(4, cli.getMotDePasse());
        update.setInt(5, cli.getAdrNumero());
        update.setString(6, cli.getAdrVoie());
        update.setInt(7, cli.getAdrCodePostal());
        update.setString(8, cli.getAdrVille());
        update.setString(9, cli.getAdrPays());
        update.setInt(10, cli.getId());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    @Override
    public boolean delete(Client cli) throws SQLException {
        Connection con = startConnection();
        PreparedStatement update = con.prepareStatement("DELETE FROM client WHERE id_client=?");
        update.setInt(1, cli.getId());
        int result = update.executeUpdate();
        con.close();
        return result >= 1;
    }

    @Override
    public ArrayList<Client> getAll() throws SQLException {
        Connection con = startConnection();
        PreparedStatement query = con.prepareStatement("SELECT * FROM client");
        ResultSet cliRes = query.executeQuery();

        ArrayList<Client> clis = new ArrayList<Client>();
        while (cliRes.next())
            clis.add(new Client(cliRes.getInt(1), cliRes.getString("nom"), cliRes.getString("prenom"),
                    cliRes.getString("identifiant"), cliRes.getString("mot_de_passe"), cliRes.getInt("adr_numero"),
                    cliRes.getString("adr_voie"), cliRes.getInt("adr_code_postal"), cliRes.getString("adr_ville"),
                    cliRes.getString("adr_pays")));
        con.close();
        return clis;
    }

    public static MySQLClientDAO getInstance() throws IOException {
        if (instance == null)
            instance = new MySQLClientDAO();
        return instance;
    }

}
