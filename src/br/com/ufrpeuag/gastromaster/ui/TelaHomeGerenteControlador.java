package br.com.ufrpeuag.gastromaster.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TelaHomeGerenteControlador {
	
	@FXML
	private Button funcionarios;
	@FXML
	private Button mesas;
	@FXML
	private Button cardapio;
	@FXML
	private Button estoque;
	@FXML
	private Button relatorios;
	@FXML
	private Button sair;
	
	MainAppLogin mainAppLogin = new MainAppLogin();
	
	public void handleTelaFuncionarios(ActionEvent event) {
		MainAppGerente.changeScreen("funcionarios");
	}
	public void handleTelaMesas(ActionEvent event) {
		MainAppGerente.changeScreen("mesas");
	}
	public void handleTelaCardapio(ActionEvent event) {
		MainAppGerente.changeScreen("cardapio");
	}
	public void handleTelaEstoque(ActionEvent event) {
		MainAppGerente.changeScreen("estoque");
	}
	public void handleTelaRelatorios(ActionEvent event) {
		MainAppGerente.changeScreen("relatorios");
	}
	public void handleSair(ActionEvent event) {
		boolean confirmacao = CaixasDeAlerta.CaixaConfirmar("Sair Gerente", "Tem certeza de que deseja sair?");
		if (confirmacao == true) {
			mainAppLogin.start(new Stage());
			MainAppGerente.getStage().close();
		}
	}	

}
