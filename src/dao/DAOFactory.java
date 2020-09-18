package dao;

public abstract class DAOFactory {
    public static DAOFactory getDAOFactory(Persistance target) {
        DAOFactory daoF = null;
        switch (target) {
            case MYSQL:
                daoF = new MySQLDAOFactory();
                break;
            case LISTE_MEMOIRE:
                // daoF = new ListeMemoireDAOFactory();
                break;
        }
        return daoF;
    }

    public abstract CategoryDAO getCategoryDAO() throws Exception;
}
