package controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import models.*;

public class MainController extends BaseController {

    @FXML
    private Menu menuPersistance;
    @FXML
    private TabPane tpMain;
    // region Client Fields
    private FilteredList<Client> filteredClients;
    @FXML
    private Tab tabCli;
    @FXML
    private TextField tfSearchClient;
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
    @FXML
    private Button btnCliEdit;
    @FXML
    private Button btnCliRem;
    @FXML
    private Label lblCliInfoNom;
    @FXML
    private Label lblCliInfoPrenom;
    @FXML
    private Label lblCliInfoAdr;
    @FXML
    private Label lblCliInfoIdentifiant;
    @FXML
    private Label lblCliInfoMotDePasse;
    @FXML
    private Hyperlink hlCliInfoGetCmds;

    // endregion

    // region Category Fields
    private FilteredList<Category> filteredCategs;
    @FXML
    private Tab tabCateg;
    @FXML
    private TextField tfSearchCateg;
    @FXML
    private TableView<Category> tvCategories;
    @FXML
    private TableColumn<Category, String> colCategTitre;
    @FXML
    private Button btnCategEdit;
    @FXML
    private Button btnCategRem;
    @FXML
    private Label lblCategInfoTitre;
    @FXML
    private Label lblCategInfoVisuel;
    // endregion

    // region Products Fields
    private FilteredList<Product> filteredProds;
    @FXML
    private Tab tabProd;
    @FXML
    private TextField tfSearchProd;
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
    @FXML
    private Button btnProdEdit;
    @FXML
    private Button btnProdRem;
    @FXML
    private Label lblProdInfoTarif;
    @FXML
    private Hyperlink hlProdInfoCateg;
    @FXML
    private Label lblProdInfoNom;
    @FXML
    private Label lblProdInfoQuantityOrdered;
    @FXML
    private Label lblProdInfoDescription;
    @FXML
    private Label lblProdInfoVisuel;
    // endregion

    // region Commands Fields
    private FilteredList<Command> filteredCmds;
    @FXML
    private Tab tabCmd;
    @FXML
    private TextField tfSearchCommand;
    @FXML
    private TableView<Command> tvCommandes;
    @FXML
    private TableColumn<Command, Client> colCmdCli;
    @FXML
    private TableColumn<Command, java.time.LocalTime> colCmdDate;
    @FXML
    private Button btnCmdEdit;
    @FXML
    private Button btnCmdRem;
    @FXML
    private Hyperlink hlCmdInfoClient;
    @FXML
    private Label lblCmdInfoDateCommande;
    @FXML
    private Label lblCmdInfoTotalPrice;
    @FXML
    private TableView<ProductLine> tvCmdInfo;
    @FXML
    private TableColumn<ProductLine, Product> colCmdInfoProd;
    @FXML
    private TableColumn<ProductLine, Integer> colCmdInfoQuantite;
    @FXML
    private TableColumn<ProductLine, Float> colCmdInfoPrice;
    // endregion

    public MainController() {
        reloadPersistance(dao.Persistance.MYSQL);
    }

    @Override
    public void initialize(URL location, ResourceBundle ressources) {
        try {
            for (var mode : EnumSet.allOf(dao.Persistance.class)) {
                var cmi = new CheckMenuItem(mode.toString());
                cmi.setOnAction(e -> changeMode(mode, cmi));
                if (mode == dao.Persistance.MYSQL)
                    cmi.setSelected(true);
                menuPersistance.getItems().add(cmi);
            }

            initClients();
            initCategs();
            initProducts();
            initCommands();
        } catch (Exception e) {
            showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    private void changeMode(dao.Persistance persistance, CheckMenuItem sender) {
        for (var cmi : menuPersistance.getItems()) {
            if (cmi instanceof CheckMenuItem)
                ((CheckMenuItem) cmi).setSelected(false);
        }
        sender.setSelected(true);

        reloadPersistance(persistance);
        try {
            updateClientTable();
            updateCategTable();
            updateCommandTable();
            updateProductTable();
        } catch (Exception e) {
            showErrorAlert("On s'attendait à tout, sauf à ça.", "Certains éléments n'ont pû être rechargés.");
        }
    }

    // region Client Methods/Events
    public void updateClientTable() throws Exception {
        new Thread(() -> {
            try {
                var filteredList = new FilteredList<>(FXCollections.observableArrayList(_daos.getClientDAO().getAll()),
                        p -> true);
                SortedList<Client> sortedData = new SortedList<>(filteredList);
                sortedData.comparatorProperty().bind(tvClients.comparatorProperty());
                Platform.runLater(() -> {
                    filteredClients = filteredList;
                    tvClients.setItems(sortedData);
                    tvClients.refresh();
                });
            } catch (Exception e) {
                Platform.runLater(() -> showErrorAlert(e.getClass().getSimpleName(), e.getMessage()));
            }
        }).start();
    }

    private void initClients() throws Exception {
        colCliNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCliPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colCliVille.setCellValueFactory(new PropertyValueFactory<>("adrVille"));
        colCliPays.setCellValueFactory(new PropertyValueFactory<>("adrPays"));

        btnCliEdit.setDisable(true);
        btnCliRem.setDisable(true);
        hlCliInfoGetCmds.setVisible(false);

        tfSearchClient.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredClients.setPredicate(client -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                try {
                    if (lowerCaseFilter.startsWith(":")) {
                        return new IFilterRunnable() {
                            @Override
                            public boolean isObjectSearched(String toSearch) {
                                Matcher matcher = Pattern.compile(
                                        "^(?<field>:[a-z]+:)(?<value>[^0-9\\n|&]+)(?<modifier>&|\\|)?(?<next>.+)?")
                                        .matcher(toSearch);
                                boolean isSearched = false;

                                if (matcher.matches()) {
                                    String field = matcher.group("field").replace(':', ' ').trim();
                                    if (colCliNom.getText().toLowerCase().startsWith(field))
                                        isSearched = client.getNom().toLowerCase().contains(matcher.group("value"));
                                    else if (colCliPrenom.getText().toLowerCase().startsWith(field))
                                        isSearched = client.getPrenom().toLowerCase().contains(matcher.group("value"));
                                    else if (colCliVille.getText().toLowerCase().startsWith(field))
                                        isSearched = client.getAdrVille().toLowerCase()
                                                .contains(matcher.group("value"));
                                    else if (colCliPays.getText().toLowerCase().startsWith(field))
                                        isSearched = client.getAdrPays().toLowerCase().contains(matcher.group("value"));

                                    if ((matcher.group("modifier") != null && !matcher.group("modifier").isEmpty())
                                            && (matcher.group("next") != null && !matcher.group("next").isEmpty())) {
                                        if (matcher.group("modifier").startsWith("|"))
                                            return isSearched | isObjectSearched(matcher.group("next"));
                                        else
                                            return isSearched && isObjectSearched(matcher.group("next"));
                                    }
                                }
                                return isSearched;
                            }
                        }.isObjectSearched(lowerCaseFilter);
                    }
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    return false;
                }

                return (client.getPrenom().toLowerCase().contains(lowerCaseFilter)
                        || client.getNom().toLowerCase().contains(lowerCaseFilter));
            });
        });

        tvClients.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnCliEdit.setDisable(false);
                btnCliRem.setDisable(false);

                lblCliInfoNom.setText(newSelection.getNom());
                lblCliInfoPrenom.setText(newSelection.getPrenom());
                lblCliInfoAdr.setText(newSelection.getAdrNumero() + ", " + newSelection.getAdrVoie() + ", "
                        + newSelection.getAdrCodePostal() + ", " + newSelection.getAdrVille() + ", "
                        + newSelection.getAdrPays());
                lblCliInfoIdentifiant.setText(newSelection.getIdentifiant());
                // lblCliInfoMotDePasse.setText(newSelection.getMotDePasse());
                lblCliInfoMotDePasse.setText("********");

                hlCliInfoGetCmds.setVisible(true);
                hlCliInfoGetCmds.setOnAction(e -> {
                    if (filteredCmds != null) {
                        tpMain.getSelectionModel().select(tabCmd);
                        tfSearchCommand.setText(":client:" + newSelection.getNom() + " " + newSelection.getPrenom());
                    }
                });

            } else {
                lblCliInfoNom.setText("");
                lblCliInfoPrenom.setText("");
                lblCliInfoAdr.setText("");
                lblCliInfoIdentifiant.setText("");
                lblCliInfoMotDePasse.setText("");
                hlCliInfoGetCmds.setVisible(false);
            }
        });

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
        new Thread(() -> {
            try {
                if (!_daos.getClientDAO().delete(tvClients.getSelectionModel().getSelectedItem()))
                    Platform.runLater(() -> showErrorAlert("On s'attendait à tout, sauf à ça.",
                            "La supression n'a pas modifié les données"));

                updateClientTable();
            } catch (Exception e) {
                Platform.runLater(() -> showErrorAlert(e.getClass().getSimpleName(), e.getMessage()));
            }
        }).start();
    }
    // endregion

    // region Category Methods/Events
    public void updateCategTable() throws Exception {
        new Thread(() -> {
            try {
                var filteredList = new FilteredList<>(
                        FXCollections.observableArrayList(_daos.getCategoryDAO().getAll()), p -> true);
                SortedList<Category> sortedData = new SortedList<>(filteredList);
                sortedData.comparatorProperty().bind(tvCategories.comparatorProperty());
                Platform.runLater(() -> {
                    filteredCategs = filteredList;
                    tvCategories.setItems(sortedData);
                    tvCategories.refresh();
                });
            } catch (Exception e) {
                Platform.runLater(() -> showErrorAlert(e.getClass().getSimpleName(), e.getMessage()));
            }
        }).start();
    }

    private void initCategs() throws Exception {
        colCategTitre.setCellValueFactory(new PropertyValueFactory<>("title"));

        btnCategEdit.setDisable(true);
        btnCategRem.setDisable(true);

        tfSearchCateg.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCategs.setPredicate(categ -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                try {
                    if (lowerCaseFilter.startsWith(":")) {
                        return new IFilterRunnable() {
                            @Override
                            public boolean isObjectSearched(String toSearch) {
                                Matcher matcher = Pattern.compile(
                                        "^(?<field>:[a-z]+:)(?<value>[^0-9\\n|&]+)(?<modifier>&|\\|)?(?<next>.+)?")
                                        .matcher(toSearch);
                                boolean isSearched = false;

                                if (matcher.matches()) {
                                    String field = matcher.group("field").replace(':', ' ').trim();
                                    if (colCategTitre.getText().toLowerCase().startsWith(field))
                                        isSearched = categ.getTitle().toLowerCase().contains(matcher.group("value"));

                                    if ((matcher.group("modifier") != null && !matcher.group("modifier").isEmpty())
                                            && (matcher.group("next") != null && !matcher.group("next").isEmpty())) {
                                        if (matcher.group("modifier").startsWith("|"))
                                            return isSearched | isObjectSearched(matcher.group("next"));
                                        else
                                            return isSearched && isObjectSearched(matcher.group("next"));
                                    }
                                }
                                return isSearched;
                            }
                        }.isObjectSearched(lowerCaseFilter);
                    }
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    return false;
                }

                if (categ.getTitle().toLowerCase().contains(lowerCaseFilter))
                    return true;
                return false;
            });
        });

        tvCategories.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnCategEdit.setDisable(false);
                btnCategRem.setDisable(false);

                lblCategInfoTitre.setText(newSelection.getTitle());
                lblCategInfoVisuel.setText(newSelection.getVisuel());
            } else {
                lblCategInfoTitre.setText("");
                lblCategInfoVisuel.setText("");
            }
        });

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
        new Thread(() -> {
            try {
                if (!_daos.getCategoryDAO().delete(tvCategories.getSelectionModel().getSelectedItem()))
                    Platform.runLater(() -> showErrorAlert("On s'attendait à tout, sauf à ça.",
                            "La supression n'a pas modifié les données"));

                updateCategTable();
            } catch (Exception e) {
                Platform.runLater(() -> showErrorAlert(e.getClass().getSimpleName(), e.getMessage()));
            }
        }).start();
    }
    // endregion

    // region Products Methods/Events
    public void updateProductTable() throws Exception {
        new Thread(() -> {
            try {
                var filteredList = new FilteredList<>(FXCollections.observableArrayList(_daos.getProductDAO().getAll()),
                        p -> true);
                SortedList<Product> sortedData = new SortedList<>(filteredList);
                sortedData.comparatorProperty().bind(tvProduits.comparatorProperty());
                Platform.runLater(() -> {
                    filteredProds = filteredList;
                    tvProduits.setItems(sortedData);
                    tvProduits.refresh();
                });
            } catch (Exception e) {
                Platform.runLater(() -> showErrorAlert(e.getClass().getSimpleName(), e.getMessage()));
            }
        }).start();
    }

    private void initProducts() throws Exception {
        colProdNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colProdDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colProdDescription.setSortable(false);
        colProdTarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        colProdCategorie.setCellValueFactory(new PropertyValueFactory<>("category"));

        btnProdEdit.setDisable(true);
        btnProdRem.setDisable(true);

        tfSearchProd.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredProds.setPredicate(prod -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                try {
                    if (lowerCaseFilter.startsWith(":")) {
                        return new IFilterRunnable() {
                            @Override
                            public boolean isObjectSearched(String toSearch) {
                                Matcher matcher = Pattern.compile(
                                        "^(?<field>:[a-z]+:)(?<value>[^0-9\\n|&]+)(?<modifier>&|\\|)?(?<next>.+)?")
                                        .matcher(toSearch);
                                boolean isSearched = false;

                                if (matcher.matches()) {
                                    String field = matcher.group("field").replace(':', ' ').trim();
                                    if (colProdNom.getText().toLowerCase().startsWith(field))
                                        isSearched = prod.getNom().toLowerCase().contains(matcher.group("value"));
                                    else if (colProdDescription.getText().toLowerCase().startsWith(field))
                                        isSearched = prod.getDescription().toLowerCase()
                                                .contains(matcher.group("value"));
                                    else if (colProdCategorie.getText().toLowerCase().startsWith(field))
                                        isSearched = prod.getCategory().getTitle().toLowerCase()
                                                .contains(matcher.group("value"));

                                    if ((matcher.group("modifier") != null && !matcher.group("modifier").isEmpty())
                                            && (matcher.group("next") != null && !matcher.group("next").isEmpty())) {
                                        if (matcher.group("modifier").startsWith("|"))
                                            return isSearched | isObjectSearched(matcher.group("next"));
                                        else
                                            return isSearched && isObjectSearched(matcher.group("next"));
                                    }
                                }
                                return isSearched;
                            }
                        }.isObjectSearched(lowerCaseFilter);
                    }
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    return false;
                }

                try {
                    if (lowerCaseFilter.startsWith(">") || lowerCaseFilter.startsWith("<")) {
                        Matcher matcher = Pattern
                                .compile("^(?<comp1>[><]=?)(?<value1>[\\d.,]+)(?<comp2>[><]?=?)(?<value2>[\\d.|,]*)")
                                .matcher(lowerCaseFilter);
                        boolean isSearched = false;
                        if (matcher.matches()
                                && (matcher.group("value1") != null && !matcher.group("value1").isEmpty())) {
                            float value = Float.parseFloat(matcher.group("value1"));
                            switch (matcher.group("comp1")) {
                                case ">=":
                                    isSearched = (prod.getTarif() >= value);
                                    break;
                                case ">":
                                    isSearched = (prod.getTarif() > value);
                                    break;
                                case "<=":
                                    isSearched = (prod.getTarif() <= value);
                                    break;
                                case "<":
                                    isSearched = (prod.getTarif() < value);
                                    break;
                                default:
                                    break;
                            }

                            if (isSearched && (matcher.group("value2") != null && !matcher.group("value2").isEmpty())) {
                                value = Float.parseFloat(matcher.group("value2"));
                                switch (matcher.group("comp2")) {
                                    case ">=":
                                        isSearched = isSearched && (prod.getTarif() >= value);
                                        break;
                                    case ">":
                                        isSearched = isSearched && (prod.getTarif() > value);
                                        break;
                                    case "<=":
                                        isSearched = isSearched && (prod.getTarif() <= value);
                                        break;
                                    case "<":
                                        isSearched = isSearched && (prod.getTarif() < value);
                                        break;

                                    default:
                                        isSearched = false;
                                        break;
                                }
                            }
                        }
                        return isSearched;
                    } else if (lowerCaseFilter.startsWith("=")) {
                        Matcher matcher = Pattern.compile("^(=)(?<value>[\\d.,]+)").matcher(lowerCaseFilter);
                        if (matcher.matches() && (matcher.group("value") != null && !matcher.group("value").isEmpty()))
                            return prod.getTarif() == Float.parseFloat(matcher.group("value"));
                    }
                } catch (NumberFormatException e) {
                    return false;
                }

                return (prod.getNom().toLowerCase().contains(lowerCaseFilter)
                        || prod.getCategory().getTitle().toLowerCase().contains(lowerCaseFilter));
            });
        });

        tvProduits.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnProdEdit.setDisable(false);
                btnProdRem.setDisable(false);

                lblProdInfoTarif.setText(Float.toString(newSelection.getTarif()) + " €");
                hlProdInfoCateg.setText(newSelection.getCategory().getTitle());
                hlProdInfoCateg.setId(Integer.toString(newSelection.getId()));
                hlProdInfoCateg.setOnAction(e -> {
                    tpMain.getSelectionModel().select(tabCateg);
                    tvCategories.getSelectionModel().select(newSelection.getCategory());
                });
                lblProdInfoNom.setText(newSelection.getNom());
                lblProdInfoDescription.setText(newSelection.getDescription());
                lblProdInfoVisuel.setText(newSelection.getVisuel());
                if (filteredCmds != null) {
                    var quantityOrdered = new Object() {
                        int value = 0;
                    };
                    filteredCmds.forEach(cmd -> {
                        var cmdLine = cmd.getCommandLines().getOrDefault(newSelection, null);
                        quantityOrdered.value += (cmdLine != null ? cmdLine.getQuantite() : 0);
                    });
                    lblProdInfoQuantityOrdered.setText(Integer.toString(quantityOrdered.value));
                } else
                    lblProdInfoQuantityOrdered.setText("?");
            } else {
                lblProdInfoTarif.setText("");
                hlProdInfoCateg.setText("");
                hlProdInfoCateg.setOnAction(e -> {
                });
                lblProdInfoNom.setText("");
                lblProdInfoDescription.setText("");
                lblProdInfoVisuel.setText("");
                lblProdInfoQuantityOrdered.setText("");
            }
        });

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
        new Thread(() -> {

            try {
                if (!_daos.getProductDAO().delete(tvProduits.getSelectionModel().getSelectedItem()))
                    Platform.runLater(() -> showErrorAlert("On s'attendait à tout, sauf à ça.",
                            "La supression n'a pas modifié les données"));

                updateProductTable();
            } catch (Exception e) {
                Platform.runLater(() -> showErrorAlert(e.getClass().getSimpleName(), e.getMessage()));
            }
        }).start();
    }
    // endregion

    // region Commands Methods/Events
    public void updateCommandTable() throws Exception {
        new Thread(() -> {
            try {
                var filteredList = new FilteredList<>(FXCollections.observableArrayList(_daos.getCommandDAO().getAll()),
                        p -> true);
                SortedList<Command> sortedData = new SortedList<>(filteredList);
                sortedData.comparatorProperty().bind(tvCommandes.comparatorProperty());
                Platform.runLater(() -> {
                    filteredCmds = filteredList;
                    tvCommandes.setItems(sortedData);
                    tvCommandes.refresh();
                });
            } catch (Exception e) {
                Platform.runLater(() -> showErrorAlert(e.getClass().getSimpleName(), e.getMessage()));
            }
        }).start();
    }

    private void initCommands() throws Exception {
        colCmdCli.setCellValueFactory(new PropertyValueFactory<>("client"));
        colCmdDate.setCellValueFactory(new PropertyValueFactory<>("dateCommand"));

        btnCmdEdit.setDisable(true);
        btnCmdRem.setDisable(true);

        tfSearchCommand.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredCmds.setPredicate(cmd -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                try {
                    if (lowerCaseFilter.startsWith(":")) {
                        return new IFilterRunnable() {
                            @Override
                            public boolean isObjectSearched(String toSearch) {
                                Matcher matcher = Pattern
                                        .compile(
                                                "^(?<field>:[a-z]+:)(?<value>[^\\n|&]+)(?<modifier>&|\\|)?(?<next>.+)?")
                                        .matcher(toSearch);
                                boolean isSearched = false;

                                if (matcher.matches()) {
                                    String field = matcher.group("field").replace(':', ' ').trim();
                                    if (colCmdCli.getText().toLowerCase().startsWith(field))
                                        isSearched = (matcher.group("value")
                                                .contains(cmd.getClient().getPrenom().toLowerCase())
                                                || matcher.group("value")
                                                        .contains(cmd.getClient().getNom().toLowerCase()));
                                    else if (colCmdDate.getText().toLowerCase().startsWith(field))
                                        isSearched = (cmd.getDateCommand().toString().toLowerCase()
                                                .contains(matcher.group("value")));
                                    else if (colCmdInfoProd.getText().toLowerCase().startsWith(field)) {
                                        boolean prodIsPresent = false;
                                        for (Map.Entry<Product, CommandLine> entry : cmd.getCommandLines().entrySet()) {
                                            prodIsPresent = entry.getKey().getNom().toLowerCase()
                                                    .contains(matcher.group("value"));
                                            if (prodIsPresent)
                                                break;
                                        }
                                        isSearched = prodIsPresent;
                                    }

                                    if ((matcher.group("modifier") != null && !matcher.group("modifier").isEmpty())
                                            && (matcher.group("next") != null && !matcher.group("next").isEmpty())) {
                                        if (matcher.group("modifier").startsWith("|"))
                                            return isSearched | isObjectSearched(matcher.group("next"));
                                        else
                                            return isSearched && isObjectSearched(matcher.group("next"));
                                    }
                                }
                                return isSearched;
                            }
                        }.isObjectSearched(lowerCaseFilter);
                    }
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    return false;
                }

                if (lowerCaseFilter.contains(cmd.getClient().getPrenom().toLowerCase())
                        || lowerCaseFilter.contains(cmd.getClient().getNom().toLowerCase())
                        || cmd.getDateCommand().toString().toLowerCase().contains(lowerCaseFilter))
                    return true;
                return false;
            });
        });

        tvCmdInfo.setRowFactory(tv -> {
            TableRow<ProductLine> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    tpMain.getSelectionModel().select(tabProd);
                    tvProduits.getSelectionModel().select(row.getItem().getProd());
                }
            });
            return row;
        });

        tvCommandes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                btnCmdEdit.setDisable(false);
                btnCmdRem.setDisable(false);

                hlCmdInfoClient.setText(newSelection.getClient().toString());
                hlCmdInfoClient.setOnAction(e -> {
                    tpMain.getSelectionModel().select(tabCli);
                    tvClients.getSelectionModel().select(newSelection.getClient());
                });
                lblCmdInfoDateCommande.setText(newSelection.getDateCommand().toString());
                lblCmdInfoTotalPrice.setText(Float.toString(newSelection.getTotalPrice()) + " €");

                colCmdInfoProd.setCellValueFactory(new PropertyValueFactory<>("prod"));
                colCmdInfoQuantite.setCellValueFactory(new PropertyValueFactory<>("quant"));
                colCmdInfoPrice.setCellValueFactory(new PropertyValueFactory<>("total"));

                new Thread(() -> {
                    ArrayList<ProductLine> prodLines = new ArrayList<>();
                    newSelection.getCommandLines()
                            .forEach((prod, cmdline) -> prodLines.add(new ProductLine(prod, cmdline.getQuantite())));
                    Platform.runLater(() -> tvCmdInfo.setItems(FXCollections.observableArrayList(prodLines)));
                }).start();
            } else {
                hlCmdInfoClient.setText("");
                hlCmdInfoClient.setOnAction(e -> {
                });
                lblCmdInfoDateCommande.setText("");
                tvCmdInfo.getItems().clear();
            }
        });

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
        new Thread(() -> {
            try {
                if (!_daos.getCommandDAO().delete(tvCommandes.getSelectionModel().getSelectedItem()))
                    Platform.runLater(() -> showErrorAlert("On s'attendait à tout, sauf à ça.",
                            "La supression n'a pas modifié les données"));

                updateCommandTable();
            } catch (Exception e) {
                Platform.runLater(() -> showErrorAlert(e.getClass().getSimpleName(), e.getMessage()));
            }
        }).start();
    }
    // endregion
}
