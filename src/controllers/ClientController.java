package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import models.Client;

public class ClientController extends BaseController {
	@FXML
	private TextField tfNom;
	@FXML
	private TextField tfPrenom;
	@FXML
	private TextField tfIdentifiant;
	@FXML
	private PasswordField pfPassword;
	@FXML
	private TextField tfAdrNumero;
	@FXML
	private TextField tfAdrVoie;
	@FXML
	private TextField tfAdrCodePostal;
	@FXML
	private TextField tfAdrVille;
	@FXML
	private TextField tfAdrPays;

	@Override
	public void initialize(URL location, ResourceBundle ressources) {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void createClick() {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void setClient(Client cli) {

	}
}
