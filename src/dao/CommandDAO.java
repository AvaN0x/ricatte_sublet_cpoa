package dao;

import java.util.ArrayList;

import models.Command;

public interface CommandDAO extends DAO<Command> {
    public abstract ArrayList<Command> getByClient(models.Client cli) throws Exception;
}
