package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	private PasswordField pfMotDePasse;
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
	@FXML
	private Button btnCreate;

	private int idCli = -1;

	@Override
	public void initialize(URL location, ResourceBundle ressources) {
		try {
		} catch (Exception e) {
			showErrorAlert(e.getClass().getSimpleName(), e.getMessage());
		}
	}

	public void createClick() {
		new Thread(() -> {
			try {
				if (idCli == -1) {
					if (!_daos.getClientDAO()
							.create(new Client(tfNom.getText(), tfPrenom.getText(), tfIdentifiant.getText(),
									pfMotDePasse.getText(), Integer.parseInt(tfAdrNumero.getText()),
									tfAdrVoie.getText(), Integer.parseInt(tfAdrCodePostal.getText()),
									tfAdrVille.getText(), tfAdrPays.getText())))
						Platform.runLater(() -> showErrorAlert("On s'attendait à tout, sauf à ça.",
								"La création n'a pas modifié les données"));
				} else {
					if (!_daos.getClientDAO()
							.update(new Client(idCli, tfNom.getText(), tfPrenom.getText(), tfIdentifiant.getText(),
									pfMotDePasse.getText(), Integer.parseInt(tfAdrNumero.getText()),
									tfAdrVoie.getText(), Integer.parseInt(tfAdrCodePostal.getText()),
									tfAdrVille.getText(), tfAdrPays.getText())))
						Platform.runLater(() -> showErrorAlert("On s'attendait à tout, sauf à ça.",
								"La modification n'a pas modifié les données"));
				}
				Platform.runLater(() -> fermer());
			} catch (Exception e) {
				Platform.runLater(() -> showErrorAlert(e.getClass().getSimpleName(), e.getMessage()));
			}
		}).start();
	}

	public void setClient(Client cli) {
		tfNom.setText(cli.getNom());
		tfPrenom.setText(cli.getPrenom());
		tfIdentifiant.setText(cli.getIdentifiant());
		pfMotDePasse.setText(cli.getMotDePasse());
		tfAdrNumero.setText(Integer.toString(cli.getAdrNumero()));
		tfAdrVoie.setText(cli.getAdrVoie());
		tfAdrCodePostal.setText(Integer.toString(cli.getAdrCodePostal()));
		tfAdrVille.setText(cli.getAdrVille());
		tfAdrPays.setText(cli.getAdrPays());
		btnCreate.setText("Editer");
		this.idCli = cli.getId();
	}
}
