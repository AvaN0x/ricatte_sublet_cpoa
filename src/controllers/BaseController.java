package controllers;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public abstract class BaseController implements javafx.fxml.Initializable {
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

    protected void showAlert(String title, String header, String content, Alert.AlertType type) {
        var alert = new Alert(type);
        alert.initOwner(_view);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    protected void showErrorAlert(String header, String content) {
        var alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(_view);
        alert.setTitle("Une erreur est survenue !");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
