package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

import models.*;

public class MainController extends BaseController {

    // region Client Fields
    // endregion

    // region Category Fields
    @FXML
    private TableView<Category> tvCategories;
    @FXML
    private TableColumn<Category, String> colCategTitre;
    // endregion

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

    // region Commands Fields
    // endregion

    @Override
    public void initialize(URL location, ResourceBundle ressources) {
        try {
            initCategs();
            initProducts();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    // region Client Methods/Events
    // endregion

    // region Category Methods/Events
    private void updateCategTable() throws Exception {
        tvCategories.getItems().clear();
        tvCategories.setItems(FXCollections.observableArrayList(_daos.getCategoryDAO().getAll()));
    }

    private void initCategs() throws Exception {
        colCategTitre.setCellValueFactory(new PropertyValueFactory<>("title"));

        colCategTitre.setSortType(TableColumn.SortType.DESCENDING);

        updateCategTable();
    }

    public void createCategClick() {
        try {
            new views.javafx.NewCategoryView().showAndWait();
            updateCategTable();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void editCategClick() {
        try {
            new views.javafx.NewCategoryView(tvCategories.getSelectionModel().getSelectedItem()).showAndWait();
            updateCategTable();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void delCategClick() {
        try {
            if (!_daos.getCategoryDAO().delete(tvCategories.getSelectionModel().getSelectedItem()))
                showErrorAlert("On s'attendait à tout, sauf à ça.", "La supression n'a pas modifié les données");
            updateCategTable();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }
    // endregion

    // region Products Methods/Events
    private void updateProductTable() throws Exception {
        tvProduits.getItems().clear();
        tvProduits.setItems(FXCollections.observableArrayList(_daos.getProductDAO().getAll()));
    }

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
            new views.javafx.NewProductView().showAndWait();
            updateProductTable();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void editProdClick() {
        try {
            new views.javafx.NewProductView(tvProduits.getSelectionModel().getSelectedItem()).showAndWait();
            updateProductTable();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void delProdClick() {
        try {
            if (!_daos.getProductDAO().delete(tvProduits.getSelectionModel().getSelectedItem()))
                showErrorAlert("On s'attendait à tout, sauf à ça.", "La supression n'a pas modifié les données");
            updateProductTable();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }
    // endregion

    // region Commands Methods/Events
    // endregion
}
