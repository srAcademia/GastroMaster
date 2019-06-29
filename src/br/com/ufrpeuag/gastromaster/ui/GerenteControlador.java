package br.com.ufrpeuag.gastromaster.ui;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GerenteControlador {
	
	@FXML
	private TextField nomeFieldGerente;
	@FXML
	private TextField cpfFieldGerente;
	@FXML
	private TextField dataNascFieldGerente;
	@FXML
	private TextField telefoneFieldGerente;
	@FXML
	private TextField emailFieldGerente;
	@FXML
	private TextField salarioFieldGerente;
	@FXML
	private TextField senhaFieldGerente;
	@FXML
	private TextField cidadeFieldGerente;
	@FXML
	private TextField bairroFieldGerente;
	@FXML
	private TextField ruaFieldGerente;
	@FXML
	private TextField numeroFieldGerente;
	@FXML
	private TextField cepFieldGerente;
	@FXML
	private Button concluirGerente;
	@FXML
	private Button cancelarGerente;
	
	//, cidadeFieldGerente.getText(), bairroFieldGerente.getText(), ruaFieldGerente.getText(), numeroFieldGerente.getText(), cepFieldGerente.getText()
	//@FXML
	/*public void handleConcluir(ActionEvent event) {
		Endereco endereco = new Endereco(cidadeFieldGerente.getText(), bairroFieldGerente.getText(), ruaFieldGerente.getText(), Integer.parseInt(numeroFieldGerente.getText()), cepFieldGerente.getText());
		Gerente gerente = new Gerente(nomeFieldGerente.getText(), cpfFieldGerente.getText(), dataNascFieldGerente.getText(), 
				telefoneFieldGerente.getText(), emailFieldGerente.getText(), Double.parseDouble(salarioFieldGerente.getText()), 
				senhaFieldGerente.getText());
		
	}*/
	
}
