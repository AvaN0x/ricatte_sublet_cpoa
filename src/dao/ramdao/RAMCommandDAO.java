package dao.RAM;

import java.util.ArrayList;

import dao.CommandDAO;
import models.Command;

public class RAMCommandDAO implements CommandDAO {
    private static RAMCommandDAO instance;

    private ArrayList<Command> data;

    private RAMCommandDAO() {
        this.data = new ArrayList<Command>();
    }

    @Override
    public Command getById(int id) throws IllegalArgumentException {
        int i = data.indexOf(new Command(id));
        if (i == -1)
            throw new IllegalArgumentException("No command have this id");
        return data.get(i);
    }

    @Override
    public boolean create(Command cmd) {
        while (data.contains(cmd))
            cmd.setId(cmd.getId() + 1);
        return data.add(cmd);
    }

    @Override
    public boolean update(Command cmd) throws IllegalArgumentException {
        int i = data.indexOf(cmd);
        if (i == -1)
            throw new IllegalArgumentException("This command doesn't exist");
        data.set(i, cmd);
        return true;
    }

    @Override
    public boolean delete(Command cmd) throws IllegalArgumentException {
        int i = data.indexOf(cmd);
        if (i == -1)
            throw new IllegalArgumentException("This command doesn't exist");
        return cmd.equals(data.remove(i));
    }

    @Override
    public ArrayList<Command> getAll() {
        return data;
    }

    public static RAMCommandDAO getInstance() {
        if (instance == null)
            instance = new RAMCommandDAO();

        return instance;
    }

}
