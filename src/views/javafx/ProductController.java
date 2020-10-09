package views.javafx;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import dao.*;
import models.Product;

public class ProductController implements Initializable {
    @FXML
    private TextField tf_nom;
    @FXML
    private TextArea ta_description;
    @FXML
    private TextField tf_tarif;
    @FXML
    private Label lbl_result;
    @FXML
    private ChoiceBox<models.Category> cb_categorie;

    private DAOFactory daos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
            var list = daos.getCategoryDAO().getAll();
            this.cb_categorie.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            lbl_result.setText(e.getMessage());
        }
    }

    public void createClick() {
        try {
            var prod = new Product(tf_nom.getText(), ta_description.getText(), Float.parseFloat(tf_tarif.getText()),
                    "null", cb_categorie.getValue());

            daos.getProductDAO().create(prod);

            tf_nom.setText("");
            ta_description.setText("");
            tf_tarif.setText("");
            cb_categorie.setValue(null);

            lbl_result.setText(prod.toString());

        } catch (Exception e) {
            lbl_result.setText(e.getMessage());
        }
    }
}
