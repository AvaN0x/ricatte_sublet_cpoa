package controllers;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public abstract class BaseController implements javafx.fxml.Initializable {
    protected static dao.DAOFactory _daos;
    protected Stage _view;

    public static void reloadPersistance(dao.Persistance persistance) {
        _daos = dao.DAOFactory.getDAOFactory(persistance);
    }

    public void setVue(Stage view) {
        this._view = view;
    }

    protected void fermer() {
        this._view.close();
    }

    protected void showAlert(String title, String header, String content, Alert.AlertType type) {
        var alert = new Alert(type);
        alert.initOwner(_view);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    protected void showErrorAlert(String header, String content) {
        showAlert("Une erreur est survenue !", header, content, Alert.AlertType.ERROR);
    }
}
