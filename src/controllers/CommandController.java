package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import models.Command;

public class CommandController extends BaseController {
    @FXML
    private ChoiceBox<models.Client> cbClient;
    @FXML
    private DatePicker dpDateCommand;
    @FXML
    private Button btnCreate;

    private int idCommand = -1;

    @Override
    public void initialize(URL location, ResourceBundle ressources) {
        try {
            updateClientBox();
            if (idCommand == -1)
                dpDateCommand.setValue(LocalDate.now());

            btnCreate.setDisable(true);
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void updateClientBox() throws Exception {
        this.cbClient.setItems(FXCollections.observableArrayList(_daos.getClientDAO().getAll()));
    }

    public void createClick() {
        try {
            if (idCommand == -1) {
                if (!_daos.getCommandDAO()
                        .create(new Command(dpDateCommand.getValue(), cbClient.getSelectionModel().getSelectedItem())))
                    showErrorAlert("On s'attendait à tout, sauf à ça.", "La création n'a pas modifié les données");
            } else {

                if (!_daos.getCommandDAO().update(new Command(idCommand, dpDateCommand.getValue(),
                        cbClient.getSelectionModel().getSelectedItem())))
                    showErrorAlert("On s'attendait à tout, sauf à ça.", "La modification n'a pas modifié les données");
            }
            fermer();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void setCommand(Command cmd) {
        cbClient.getSelectionModel().select(cmd.getClient());
        dpDateCommand.setValue(cmd.getDateCommand());
        btnCreate.setText("Modififer");
        this.idCommand = cmd.getId();
    }

}
