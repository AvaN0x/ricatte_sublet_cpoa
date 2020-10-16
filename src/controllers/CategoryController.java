package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import models.Category;

public class CategoryController extends BaseController {
    @FXML
    private TextField tfTitre;

    @Override
    public void initialize(URL location, ResourceBundle ressources) {
        try {

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void createClick() {
        try {
            var cat = new Category(tfTitre.getText(), "null");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
