package dao.RAM;

import java.io.IOException;

import dao.*;

public class RAMDAOFactory extends DAOFactory {
    @Override
    public CategoryDAO getCategoryDAO() throws IOException {
        return RAMCategoryDAO.getInstance();
    }

    @Override
    public ProductDAO getProductDAO() throws Exception {
        return RAMProductDAO.getInstance();
    }

    @Override
    public ClientDAO getClientDAO() throws Exception {
        return RAMClientDAO.getInstance();
    }

    @Override
    public CommandDAO getCommandDAO() throws Exception {
        return RAMCommandDAO.getInstance();
    }
}
