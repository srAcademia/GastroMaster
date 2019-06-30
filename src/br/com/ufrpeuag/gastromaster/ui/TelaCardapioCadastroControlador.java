package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PratoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PrecoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaCardapioCadastroControlador {
	@FXML
	private TextField nomeFieldCardapio;
	@FXML
	private TextField precoFieldCardapio;
	@FXML
	private Button concluirCardapioCadastro;
	@FXML
	private Button cancelarCardapioCadastro;
	
	@FXML
	public void handleConcluirCadastro(ActionEvent event) throws PratoExistenteException, NomeInvalidoException, PrecoInvalidoException, SQLException{
		try {
			Cardapio cardapio = new Cardapio(nomeFieldCardapio.getText(), Double.parseDouble(precoFieldCardapio.getText()));
			Fachada.getSingleton().cadastrarCardapio(cardapio);
			CaixasDeAlerta.CaixaConcluido("Cadastrar Cardapio", "Prato cadastrado.");
		}catch(PratoExistenteException | NomeInvalidoException | PrecoInvalidoException ex) {
			CaixasDeAlerta.CaixaErro("Cadastrar Cardapio", "Campo inválido.", ex.getLocalizedMessage());
		}catch(NumberFormatException ex) {
			CaixasDeAlerta.CaixaErro("Cadastrar Cardapio", "Campo inválido.", "Preencha os campos corretamente antes de concluir o cadastro.");
		}catch(Exception ex) {
			System.out.println(ex.getStackTrace());
			CaixasDeAlerta.CaixaErro("Cadastrar Cardapio", "Erro inesperado.", "Erro inesperado.");
		}
	}
	
	@FXML
	public void handleCancelar(ActionEvent event) {
		
	}
}
