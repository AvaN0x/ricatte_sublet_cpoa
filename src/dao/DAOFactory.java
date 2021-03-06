package dao;

public abstract class DAOFactory {
    public static DAOFactory getDAOFactory(Persistance target) {
        DAOFactory daoF = null;
        switch (target) {
            case MYSQL:
                daoF = new dao.mysql.MySQLDAOFactory();
                break;
            case RAM:
                daoF = new dao.ram.RAMDAOFactory();
                break;
        }
        return daoF;
    }

    public abstract CategoryDAO getCategoryDAO() throws Exception;

    public abstract ProductDAO getProductDAO() throws Exception;

    public abstract ClientDAO getClientDAO() throws Exception;

    public abstract CommandDAO getCommandDAO() throws Exception;
}
