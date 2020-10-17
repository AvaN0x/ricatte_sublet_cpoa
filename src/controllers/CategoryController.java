package controllers;

import java.net.URL;
import java.util.ResourceBundle;

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
            btnCreate.setDisable(true);

            tfTitre.textProperty().addListener((observable, oldValue, newValue) -> {
                btnCreate.setDisable(newValue == null || tfVisuel.getText() == null);
            });
            tfVisuel.textProperty().addListener((observable, oldValue, newValue) -> {
                btnCreate.setDisable(tfTitre.getText() == null || newValue == null);
            });
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void createClick() {
        try {
            if (idCateg == -1) {
                if (!_daos.getCategoryDAO().create(new Category(tfTitre.getText(), tfVisuel.getText())))
                    showErrorAlert("On s'attendait à tout, sauf à ça.", "La création n'a pas modifié les données");
            } else {

                if (!_daos.getCategoryDAO().update(new Category(idCateg, tfTitre.getText(), tfVisuel.getText())))
                    showErrorAlert("On s'attendait à tout, sauf à ça.", "La modification n'a pas modifié les données");
            }
            fermer();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void setCategory(Category categ) {
        tfTitre.setText(categ.getTitle());
        tfVisuel.setText(categ.getVisuel());
        btnCreate.setText("Modifier");
        this.idCateg = categ.getId();
    }
}
