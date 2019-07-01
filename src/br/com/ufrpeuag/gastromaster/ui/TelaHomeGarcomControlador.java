package br.com.ufrpeuag.gastromaster.ui;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TelaHomeGarcomControlador implements Initializable {

	@FXML
	private Button mesas;
	@FXML
	private Button cardapio;
	@FXML
	private Button estoque;
	@FXML
	private Button sair;
	@FXML
	private Label garcomLabel;
	private static Garcom garcom2;

	MainAppLogin mainAppLogin = new MainAppLogin();

	
	public void handleTelaMesas(ActionEvent event) {
		MainAppGarcom.changeScreen("mesas");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initGarcom();		
	}
	
	public void handleTelaCardapio(ActionEvent event) {
		MainAppGarcom.changeScreen("cardapio");
	}
	public void handleTelaEstoque(ActionEvent event) {
		MainAppGarcom.changeScreen("estoque");
	}
	public void handleSair(ActionEvent event) {
		boolean confirmacao = CaixasDeAlerta.CaixaConfirmar("Sair Garcom", "Tem certeza de que deseja sair?");
		if (confirmacao == true) {
			mainAppLogin.start(new Stage());
			MainAppGarcom.getStage().close();
		}
	}
	public void initGarcom() {
		garcomLabel.setText(garcom2.getCpf());
	}

	public static Garcom getGarcom2() {
		return garcom2;
	}
	public static void setGarcom2(Garcom garcom2) {
		TelaHomeGarcomControlador.garcom2 = garcom2;
	}

}
