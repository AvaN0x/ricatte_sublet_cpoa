package dao;

import java.util.ArrayList;

import models.CommandLine;
import models.Product;

public interface CommandLineDAO {
    public abstract CommandLine getByProductCommandId(int prodId, int comId) throws Exception;

    public abstract ArrayList<CommandLine> getByCommandId(int id) throws Exception;

    public abstract ArrayList<CommandLine> getByProductId(int id) throws Exception;

    public abstract boolean create(CommandLine line, Product prod) throws Exception;

    public abstract boolean update(CommandLine line, Product prod) throws Exception;

    public abstract boolean delete(CommandLine line, Product prod) throws Exception;

    public abstract ArrayList<CommandLine> getAll() throws Exception;
}
