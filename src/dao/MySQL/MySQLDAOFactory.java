package dao.MySQL;

import java.io.IOException;

import dao.*;

public class MySQLDAOFactory extends DAOFactory {
    @Override
    public CategoryDAO getCategoryDAO() throws IOException {
        return MySQLCategoryDAO.getInstance();
    }

    @Override
    public ProductDAO getProductDAO() throws Exception {
        return MySQLProductDAO.getInstance();
    }

    @Override
    public ClientDAO getClientDAO() throws Exception {
        return MySQLClientDAO.getInstance();
    }

    @Override
    public CommandDAO getCommandDAO() throws Exception {
        return MySQLCommandDAO.getInstance();
    }

    public CommandLineDAO getCommandLineDAO() throws Exception {
        return MySQLCommandLineDAO.getInstance();
    }
}
