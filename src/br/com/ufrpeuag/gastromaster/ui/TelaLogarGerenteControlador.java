package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.SenhaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class TelaLogarGerenteControlador {
	
	@FXML
	private PasswordField senhaField;
	@FXML
	private Button concluirLogin;
	@FXML
	private Button cancelarLogin;
	Gerente gerente;
	MainAppGerente mainAppGerente = new MainAppGerente();
	
	@FXML
	public void handleconcluirLogin(ActionEvent event) throws SenhaInvalidaException, SQLException{
		try {
			gerente = Fachada.getSingleton().logarGerente(senhaField.getText());
			mainAppGerente.start(new Stage());
			CaixasDeAlerta.CaixaConcluido("Login Gerente", "Bem-vindo(a), "+gerente.getNome()+".");
			TelaInicialLoginControler.getWindow().close();
			MainAppLogin.getStage().close();
		}catch(SenhaInvalidaException ex) {
			CaixasDeAlerta.CaixaErro("Login Gerente", "Campo inválido.", ex.getLocalizedMessage());
		}
	}
	
	@FXML
	public void handleCancelar(ActionEvent event) {
		TelaInicialLoginControler.getWindow().close();
	}
}
