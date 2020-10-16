package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import models.Client;

public class ClientController implements Initializable {
	@FXML
	private TextField tfNom;
	@FXML
	private TextField tfPrenom;
	@FXML
	private TextField tfIdentifiant;
	@FXML
	private PasswordField pfPassword;
	@FXML
	private TextField tfNumero;
	@FXML
	private TextField tfVoie;
	@FXML
	private TextField tfPostal;
	@FXML
	private TextField tfVille;
	@FXML
	private TextField tfPays;

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
			var cli = new Client(tfNom.getText(), tfPrenom.getText(), tfIdentifiant.getText(), pfPassword.getText(),
					Integer.parseInt(tfNumero.getText()), tfVoie.getText(), Integer.parseInt(tfPostal.getText()),
					tfVille.getText(), tfPays.getText());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
