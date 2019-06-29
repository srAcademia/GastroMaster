package br.com.ufrpeuag.gastromaster.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class TelaInicialViewController implements Initializable {

	@FXML
	private PasswordField txtIndentificador;

	@FXML
	private Button buttonEntrar;

	@FXML
	public void acaoDoBotao(ActionEvent event) throws SQLException {
			Gerente gerente = new Gerente();
			Garcom garcom = new Garcom();
			gerente = Fachada.getSingleton().verificarIdentificadorGerente(txtIndentificador.getText());
			garcom = Fachada.getSingleton().verificarIdentificadorGarcom(txtIndentificador.getText());
			if(gerente == null && garcom == null) {
				CaixasDeAlerta.CaixaErro("Login", "Login Inválido", "Login não encontrado.");
			}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
