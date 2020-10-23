package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Command;
import models.CommandLine;

public class CommandController extends BaseController {
    @FXML
    private ChoiceBox<models.Client> cbClient;
    @FXML
    private DatePicker dpDateCommand;
    @FXML
    private Button btnCreate;

    @FXML
    private TableView<ProductLine> tvProducts;
    @FXML
    private TableColumn<ProductLine, models.Product> colProdName;
    @FXML
    private TableColumn<ProductLine, Integer> colProdQuant;

    private ArrayList<ProductLine> prodLine;
    private Command command;

    @Override
    public void initialize(URL location, ResourceBundle ressources) {
        try {
            updateClientBox();

            colProdQuant.setCellFactory(col -> {
                TableCell<ProductLine, Integer> cell = new TableCell<>();
                Spinner<Integer> spin = new Spinner<>();
                spin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, 0));
                spin.setEditable(true);
                spin.valueProperty().addListener((observable, oldValue, newValue) -> {
                    prodLine.get(cell.getIndex()).setQuant(newValue);
                });
                cell.setGraphic(spin);
                return cell;
            });

            colProdName.setCellValueFactory(new PropertyValueFactory<>("prod"));
            colProdQuant.setCellValueFactory(new PropertyValueFactory<>("quant"));

            updateProductTable();
            if (command != null)
                dpDateCommand.setValue(LocalDate.now());
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void updateClientBox() throws Exception {
        this.cbClient.setItems(FXCollections.observableArrayList(_daos.getClientDAO().getAll()));
    }

    public void updateProductTable() throws Exception {
        var prods = _daos.getProductDAO().getAll();
        prodLine = new ArrayList<>();

        tvProducts.getItems().clear();
        for (var product : prods) {
            if (command != null && command.getCommandLines().size() > 0
                    && command.getCommandLines().containsKey(product))
                prodLine.add(new ProductLine(product, command.getCommandLines().get(product).getQuantite()));
            else
                prodLine.add(new ProductLine(product, 0));
        }
        tvProducts.setItems(FXCollections.observableArrayList(prodLine));
    }

    public void createClick() {
        try {
            System.out.println(prodLine);
            var cmd = new Command(dpDateCommand.getValue(), cbClient.getSelectionModel().getSelectedItem());
            var commandLines = new HashMap<models.Product, CommandLine>();
            for (var productLine : prodLine) {
                if (productLine.getQuant() > 0)
                    commandLines.put(productLine.getProd(),
                            new CommandLine(cmd, productLine.getQuant(), productLine.getProd().getTarif()));
            }
            cmd.setCommandLines(commandLines);
            if (command != null) {
                // if (!_daos.getCommandDAO().create(cmd))
                // showErrorAlert("On s'attendait à tout, sauf à ça.", "La création n'a
                // pas modifié les données");
            } else {
                cmd.setId(command.getId());
                // if (!_daos.getCommandDAO().update(cmd))
                // showErrorAlert("On s'attendait à tout, sauf à ça.", "La modification n'a pas
                // modifié les données");
            }
            fermer();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    public void setCommand(Command cmd) {
        cbClient.getSelectionModel().select(cmd.getClient());
        dpDateCommand.setValue(cmd.getDateCommand());
        btnCreate.setText("Editer");
        this.command = cmd;
        try {
            updateProductTable();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

}
