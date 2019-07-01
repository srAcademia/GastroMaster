package br.com.ufrpeuag.gastromaster.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TelaHomeGarcomControlador {
	

	@FXML
	private Button mesas;
	@FXML
	private Button cardapio;
	@FXML
	private Button estoque;
	@FXML
	private Button sair;
	
	MainAppLogin mainAppLogin = new MainAppLogin();

	
	public void handleTelaMesas(ActionEvent event) {
		MainAppGarcom.changeScreen("mesas");
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

}
