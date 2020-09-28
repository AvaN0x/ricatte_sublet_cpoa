package dao.ListeMemoire;

import java.io.IOException;

import dao.*;

public class ListeMemoireDAOFactory extends DAOFactory {
    @Override
    public CategoryDAO getCategoryDAO() throws IOException {
        return ListeMemoireCategoryDAO.getInstance();
    }

    @Override
    public ProductDAO getProductDAO() throws Exception {
        return ListeMemoireProductDAO.getInstance();
    }

    @Override
    public ClientDAO getClientDAO() throws Exception {
        return ListeMemoireClientDAO.getInstance();
    }

    @Override
    public CommandDAO getCommandDAO() throws Exception {
        return ListeMemoireCommandDAO.getInstance();
    }
}
