package dao;

import java.io.IOException;

import dao.MySQL.MySQLCategoryDAO;

public class MySQLDAOFactory extends DAOFactory {
    @Override
    public CategoryDAO getCategoryDAO() throws IOException {
        return MySQLCategoryDAO.getInstance();
    }
}
