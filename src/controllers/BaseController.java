package controllers;

import javafx.fxml.Initializable;

public abstract class BaseController implements Initializable {
    protected static dao.DAOFactory daos;

    public BaseController() {
        reloadPersistance();
    }

    public static void reloadPersistance() {
        daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
    }
}
