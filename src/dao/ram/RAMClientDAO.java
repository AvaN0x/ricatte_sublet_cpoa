package dao.ram;

import java.util.ArrayList;

import dao.ClientDAO;
import models.Client;

public class RAMClientDAO implements ClientDAO {
    private static RAMClientDAO instance;

    private ArrayList<Client> data;

    private RAMClientDAO() {
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
        while (data.contains(cli)) {
            if (data.get(cli.getId()).getIdentifiant() == cli.getIdentifiant())
                throw new IllegalAccessError("Duplicate key `identifiant`");
            cli.setId(cli.getId() + 1);
        }
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
        boolean isUsed;
        try {
            isUsed = RAMCommandDAO.getInstance().getByClient(cli).size() > 0;
        } catch (IllegalArgumentException e) {
            isUsed = false;
        }
        if (isUsed)
            throw new IllegalArgumentException("This client is used in commands");
        return cli.equals(data.remove(i));
    }

    @Override
    public ArrayList<Client> getAll() {
        return data;
    }

    public static RAMClientDAO getInstance() {
        if (instance == null)
            instance = new RAMClientDAO();

        return instance;
    }
}
