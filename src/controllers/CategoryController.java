package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import models.Category;

public class CategoryController extends BaseController {
    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfVisuel;
    @FXML
    private Button btnCreate;

    private int idCateg = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void createClick() {
        new Thread(() -> {
            try {
                if (idCateg == -1) {
                    if (!_daos.getCategoryDAO().create(new Category(tfTitre.getText(), tfVisuel.getText())))
                        Platform.runLater(() -> showErrorAlert("On s'attendait à tout, sauf à ça.",
                                "La création n'a pas modifié les données"));
                } else {

                    if (!_daos.getCategoryDAO().update(new Category(idCateg, tfTitre.getText(), tfVisuel.getText())))
                        Platform.runLater(() -> showErrorAlert("On s'attendait à tout, sauf à ça.",
                                "La modification n'a pas modifié les données"));
                }
                Platform.runLater(() -> fermer());
            } catch (Exception e) {
                Platform.runLater(() -> showErrorAlert(e.getClass().getSimpleName(), e.getMessage()));
            }
        }).start();
    }

    public void setCategory(Category categ) {
        tfTitre.setText(categ.getTitle());
        tfVisuel.setText(categ.getVisuel());
        btnCreate.setText("Modifier");
        this.idCateg = categ.getId();
    }
}
