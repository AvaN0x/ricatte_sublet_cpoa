package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;

import models.Command;

public class CommandController extends BaseController {
    @FXML
    private DateCell dcDate;
    @FXML
    private ChoiceBox<models.Client> cbClient;
    @FXML
    private ChoiceBox<models.Product> cbProduits;

    @Override
    public void initialize(URL location, ResourceBundle ressources) {
        try {

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void updateClientBox() {

    }

    public void createClick() {
        try {

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void setCommand(Command cmd) {

    }

}
