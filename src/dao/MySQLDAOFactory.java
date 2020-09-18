package dao;

import dao.MySQL.MySQLCategoryDAO;

public class MySQLDAOFactory extends DAOFactory {
    @Override
    public CategoryDAO getCategoryDAO() {
        return MySQLCategoryDAO.getInstance();
    }
}
