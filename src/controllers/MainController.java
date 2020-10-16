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

    // region Produits
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
            // region Tableau Produit
            colProdNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colProdDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colProdTarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
            colProdCategorie.setCellValueFactory(new PropertyValueFactory<>("category"));

            colProdNom.setSortType(TableColumn.SortType.DESCENDING);
            colProdDescription.setSortable(false);

            updateProductTable();
            // endregion

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void updateProductTable() throws Exception {
        tvProduits.setItems(FXCollections.observableArrayList(_daos.getProductDAO().getAll()));
    }

}
