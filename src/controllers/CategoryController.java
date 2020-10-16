package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import models.Category;

public class CategoryController implements Initializable {
    @FXML
    private TextField tfTitre;

    private dao.DAOFactory daos;

    @Override
    public void initialize(URL location, ResourceBundle ressources) {
        try {
            daos = dao.DAOFactory.getDAOFactory(dao.Persistance.MYSQL);
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
