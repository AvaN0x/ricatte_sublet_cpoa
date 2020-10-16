package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import models.Product;

public class ProductController extends BaseController {
    @FXML
    private TextField tfNom;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfTarif;
    @FXML
    private ChoiceBox<models.Category> cbCategorie;
    @FXML
    private TableView<Product> tvProduits;
    @FXML
    private TableColumn<Product, String> colNom;
    @FXML
    private TableColumn<Product, String> colDescription;
    @FXML
    private TableColumn<Product, Float> colTarif;
    @FXML
    private TableColumn<Product, String> colCategorie;
    @FXML
    private Button btnCreate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            updateCategsBox();

            btnCreate.setDisable(true);

            tfNom.textProperty().addListener((observable, oldValue, newValue) -> {
                btnCreate.setDisable(newValue == null || taDescription.getText() == null || tfTarif.getText() == null
                        || cbCategorie.getValue() == null);
            });
            taDescription.textProperty().addListener((observable, oldValue, newValue) -> {
                btnCreate.setDisable(tfNom.getText() == null || newValue == null || tfTarif.getText() == null
                        || cbCategorie.getValue() == null);
            });
            tfTarif.textProperty().addListener((observable, oldValue, newValue) -> {
                btnCreate.setDisable(tfNom.getText() == null || taDescription.getText() == null || newValue == null
                        || cbCategorie.getValue() == null);
            });
            cbCategorie.itemsProperty().addListener((observable, oldValue, newValue) -> {
                btnCreate.setDisable(tfNom.getText() == null || taDescription.getText() == null
                        || tfTarif.getText() == null || newValue == null);
            });
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void updateCategsBox() throws Exception {
        this.cbCategorie.setItems(FXCollections.observableArrayList(_daos.getCategoryDAO().getAll()));
    }

    public void createClick() {
        try {
            _daos.getProductDAO().create(new Product(tfNom.getText(), taDescription.getText(),
                    Float.parseFloat(tfTarif.getText()), "null", cbCategorie.getValue()));
            fermer();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }
}
