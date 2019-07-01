package br.com.ufrpeuag.gastromaster.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
	}
	

}
