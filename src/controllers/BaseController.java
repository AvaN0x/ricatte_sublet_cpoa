package controllers;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

public abstract class BaseController implements Initializable {
    protected static dao.DAOFactory _daos;
    protected Stage _view;

    public BaseController() {
        reloadPersistance();
    }

    public static void reloadPersistance() {
        _daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
    }

    public void setVue(Stage view) {
        this._view = view;
    }

    public void fermer() {
        this._view.close();
    }
}
