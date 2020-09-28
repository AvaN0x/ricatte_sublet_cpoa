package dao.ListeMemoire;

import java.util.ArrayList;

import dao.ClientDAO;
import models.Client;

public class ListeMemoireClientDAO implements ClientDAO {
    private static ListeMemoireClientDAO instance;

    private ArrayList<Client> data;

    private ListeMemoireClientDAO() {
        this.data = new ArrayList<Client>();
    }

    @Override
    public Client getById(int id) throws IllegalArgumentException {
        int i = data.indexOf(new Client(id));
        if (i == -1)
            throw new IllegalArgumentException("No client have this id");
        return data.get(i);
    }

    @Override
    public boolean create(Client cli) {
        while (data.contains(cli))
            cli.setId(cli.getId() + 1);
        return data.add(cli);
    }

    @Override
    public boolean update(Client cli) throws IllegalArgumentException {
        int i = data.indexOf(cli);
        if (i == -1)
            throw new IllegalArgumentException("This client doesn't exist");
        data.set(i, cli);
        return true;
    }

    @Override
    public boolean delete(Client cli) throws IllegalArgumentException {
        int i = data.indexOf(cli);
        if (i == -1)
            throw new IllegalArgumentException("This client doesn't exist");
        return cli.equals(data.remove(i));
    }

    @Override
    public ArrayList<Client> getAll() {
        return data;
    }

    public static ListeMemoireClientDAO getInstance() {
        if (instance == null)
            instance = new ListeMemoireClientDAO();

        return instance;
    }
}
