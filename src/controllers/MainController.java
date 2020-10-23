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
    @FXML
    private TableView<Client> tvClients;
    @FXML
    private TableColumn<Client, String> colCliNom;
    @FXML
    private TableColumn<Client, String> colCliPrenom;
    @FXML
    private TableColumn<Client, Float> colCliVille;
    @FXML
    private TableColumn<Client, String> colCliPays;
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
    @FXML
    private TableView<Command> tvCommandes;
    @FXML
    private TableColumn<Command, Client> colCmdCli;
    @FXML
    private TableColumn<Command, java.time.LocalTime> colCmdDate;
    // endregion

    @Override
    public void initialize(URL location, ResourceBundle ressources) {
        try {
            initClients();
            initCategs();
            initProducts();
            initCommands();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    // region Client Methods/Events
    private void updateClientTable() throws Exception {
        tvClients.getItems().clear();
        tvClients.setItems(FXCollections.observableArrayList(_daos.getClientDAO().getAll()));
    }

    private void initClients() throws Exception {
        colCliNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCliPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colCliVille.setCellValueFactory(new PropertyValueFactory<>("adrVille"));
        colCliPays.setCellValueFactory(new PropertyValueFactory<>("adrPays"));

        colCliNom.setSortType(TableColumn.SortType.DESCENDING);

        updateClientTable();
    }

    public void createClientClick() {
        try {
            new views.javafx.NewClientView().showAndWait();
            updateClientTable();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void editClientClick() {
        try {
            new views.javafx.NewClientView(tvClients.getSelectionModel().getSelectedItem()).showAndWait();
            updateClientTable();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void delClientClick() {
        try {
            if (!_daos.getClientDAO().delete(tvClients.getSelectionModel().getSelectedItem()))
                showErrorAlert("On s'attendait à tout, sauf à ça.", "La supression n'a pas modifié les données");
            updateClientTable();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }
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
    private void updateCommandTable() throws Exception {
        tvCommandes.getItems().clear();
        tvCommandes.setItems(FXCollections.observableArrayList(_daos.getCommandDAO().getAll()));
    }

    private void initCommands() throws Exception {
        colCmdCli.setCellValueFactory(new PropertyValueFactory<>("client"));
        colCmdDate.setCellValueFactory(new PropertyValueFactory<>("dateCommand"));

        colCmdCli.setSortType(TableColumn.SortType.DESCENDING);

        updateCommandTable();
    }

    public void createCmdClick() {
        try {
            new views.javafx.NewCommandView().showAndWait();
            updateCommandTable();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void editCmdClick() {
        try {
            new views.javafx.NewCommandView(tvCommandes.getSelectionModel().getSelectedItem()).showAndWait();
            updateCommandTable();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void delCmdClick() {
        try {
            if (!_daos.getCommandDAO().delete(tvCommandes.getSelectionModel().getSelectedItem()))
                showErrorAlert("On s'attendait à tout, sauf à ça.", "La supression n'a pas modifié les données");
            updateCommandTable();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }
    // endregion
}
