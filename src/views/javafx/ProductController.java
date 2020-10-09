package views.javafx;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import models.*;

import java.net.URL;
import java.util.ResourceBundle;

import dao.*;

public class ProductController implements Initializable {
    @FXML
    private Label lbl_result;

    @FXML
    private ChoiceBox<Category> cb_categorie;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
            var list = daos.getCategoryDAO().getAll();
            this.cb_categorie.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            lbl_result.setText(e.getMessage());
        }
    }

    public void createClick() {
        try {
            var categ = new models.Category("", "");
        } catch (IllegalArgumentException e) {
            lbl_result.setText(e.getMessage());
        }
    }
}
