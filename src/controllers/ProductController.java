package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private TextField tfVisuel;
    @FXML
    private Button btnCreate;

    private int idProd = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            updateCategsBox();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void updateCategsBox() throws Exception {
        this.cbCategorie.setItems(FXCollections.observableArrayList(_daos.getCategoryDAO().getAll()));
    }

    public void createClick() {
        new Thread(() -> {
            try {
                if (idProd == -1) {
                    if (!_daos.getProductDAO().create(new Product(tfNom.getText(), taDescription.getText(),
                            Float.parseFloat(tfTarif.getText()), tfVisuel.getText(), cbCategorie.getValue())))
                        Platform.runLater(() -> {
                            showErrorAlert("On s'attendait à tout, sauf à ça.",
                                    "La création n'a pas modifié les données");
                        });
                } else {
                    if (!_daos.getProductDAO().update(new Product(idProd, tfNom.getText(), taDescription.getText(),
                            Float.parseFloat(tfTarif.getText()), tfVisuel.getText(), cbCategorie.getValue())))
                        Platform.runLater(() -> {
                            showErrorAlert("On s'attendait à tout, sauf à ça.",
                                    "La modification n'a pas modifié les données");
                        });
                }
                Platform.runLater(() -> {
                    fermer();
                });
            } catch (Exception e) {
                Platform.runLater(() -> {
                    showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
                });
            }
        }).start();
    }

    public void setProduct(Product prod) {
        tfNom.setText(prod.getNom());
        taDescription.setText(prod.getDescription());
        tfTarif.setText(Float.toString(prod.getTarif()));
        cbCategorie.getSelectionModel().select(prod.getCategory());
        tfVisuel.setText(prod.getVisuel());
        btnCreate.setText("Modifier");
        this.idProd = prod.getId();
    }
}
