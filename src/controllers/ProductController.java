package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private Label lblResult;
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
            colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colTarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
            colCategorie.setCellValueFactory(new PropertyValueFactory<>("category"));

            colNom.setSortType(TableColumn.SortType.DESCENDING);
            colDescription.setSortable(false);

            updateProductTable();
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
            lblResult.setText(e.getMessage());
            lblResult.getStyleClass().add("exception");
        }
    }

    public void updateProductTable() throws Exception {
        tvProduits.setItems(FXCollections.observableArrayList(_daos.getProductDAO().getAll()));
    }

    public void updateCategsBox() throws Exception {
        this.cbCategorie.setItems(FXCollections.observableArrayList(_daos.getCategoryDAO().getAll()));
    }

    public void createClick() {
        try {
            var prod = new Product(tfNom.getText(), taDescription.getText(), Float.parseFloat(tfTarif.getText()),
                    "null", cbCategorie.getValue());

            tfNom.setText("");
            taDescription.setText("");
            tfTarif.setText("");
            cbCategorie.setValue(null);
            btnCreate.setDisable(true);

            lblResult.getStyleClass().remove("exception");
            lblResult.setText(prod.toString());

            _daos.getProductDAO().create(prod);

            updateProductTable();
        } catch (Exception e) {
            lblResult.setText(e.getClass().getSimpleName() + " : " + e.getMessage());
            if (tfTarif.getText().trim().isEmpty())
                lblResult.setText("\"Tarif\" must be specified");
            lblResult.getStyleClass().add("exception");
        }
    }
}
