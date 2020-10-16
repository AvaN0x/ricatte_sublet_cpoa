package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import models.*;

public class MainController extends BaseController {

    // region Products Fields
    @FXML
    private TableView<Product> tvProduits;
    @FXML
    private TableColumn<Product, String> colProdNom;
    @FXML
    private TableColumn<Product, String> colProdDescription;
    @FXML
    private TableColumn<Product, Float> colProdTarif;
    @FXML
    private TableColumn<Product, String> colProdCategorie;
    // endregion

    @Override
    public void initialize(URL location, ResourceBundle ressources) {
        try {
            initProducts();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    private void updateProductTable() throws Exception {
        tvProduits.setItems(FXCollections.observableArrayList(_daos.getProductDAO().getAll()));
    }

    // region Products Methods/Events
    private void initProducts() throws Exception {
        colProdNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colProdDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colProdTarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        colProdCategorie.setCellValueFactory(new PropertyValueFactory<>("category"));

        colProdNom.setSortType(TableColumn.SortType.DESCENDING);
        colProdDescription.setSortable(false);

        updateProductTable();
    }

    public void createProdClick() {
        try {
            _daos.getCategoryDAO().create(new models.Category("debug", "null"));
            new views.javafx.ProductView().showAndWait();
            updateProductTable();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }
    // endregion

}
