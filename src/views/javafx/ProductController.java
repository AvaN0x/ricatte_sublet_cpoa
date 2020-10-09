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
    private TextField tfNom;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfTarif;
    @FXML
    private Label lblResult;
    @FXML
    private ChoiceBox<models.Category> cbCategorie;

    private DAOFactory daos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
            var list = daos.getCategoryDAO().getAll();
            this.cbCategorie.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            lblResult.setText(e.getMessage());
            lblResult.getStyleClass().add("exception");
        }
    }

    public void createClick() {
        try {
            var prod = new Product(tfNom.getText(), taDescription.getText(), Float.parseFloat(tfTarif.getText()),
                    "null", cbCategorie.getValue());

            tfNom.setText("");
            taDescription.setText("");
            tfTarif.setText("");
            cbCategorie.setValue(null);

            lblResult.getStyleClass().remove("exception");
            lblResult.setText(prod.toString());

            daos.getProductDAO().create(prod);

        } catch (Exception e) {
            lblResult.setText(e.getClass().getSimpleName() + " : " + e.getMessage());
            if (tfTarif.getText().trim().isEmpty())
                lblResult.setText("\"Tarif\" must be specified");
            lblResult.getStyleClass().add("exception");
        }
    }
}
