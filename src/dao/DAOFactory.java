package dao;

public abstract class DAOFactory {
    public static DAOFactory getDAOFactory(Persistance target) {
        DAOFactory daoF = null;
        switch (target) {
            case MySQL:
                daoF = new MySQLDAOFactory();
                break;
            case ListeMemoire:
                // daoF = new ListeMemoireDAOFactory();
                break;
        }
        return daoF;
    }

    public abstract CategoryDAO getCategoryDAO();
}
