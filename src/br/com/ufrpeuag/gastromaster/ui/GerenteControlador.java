package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.BairroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CEPInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SenhaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
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
	private Button concluirGerenteCadastro;
	@FXML
	private Button cancelarGerenteCadastro;
		
	@FXML
	public void handleConcluirCadastro(ActionEvent event) throws CPFInvalidoException, DataNascimentoInvalidaException, NomeInvalidoException,
	GerenteExistenteException, SalarioInvalidoException, SenhaInvalidaException, BairroInvalidoException,
	CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException, SQLException {
		try {
			Endereco endereco = new Endereco(cidadeFieldGerente.getText(), bairroFieldGerente.getText(), ruaFieldGerente.getText(), Integer.parseInt(numeroFieldGerente.getText()), cepFieldGerente.getText());
			Gerente gerente = new Gerente(nomeFieldGerente.getText(), cpfFieldGerente.getText(), Fachada.getSingleton().ValidarData(dataNascFieldGerente.getText()), 
					telefoneFieldGerente.getText(), emailFieldGerente.getText(), Double.parseDouble(salarioFieldGerente.getText()), 
					senhaFieldGerente.getText(), "", endereco);
			Fachada.getSingleton().cadastrarGerente(gerente);
			CaixasDeAlerta.CaixaConcluido("Cadastro Gerente", "Gerente cadastrado.");
		
		}catch(BairroInvalidoException | CEPInvalidoException | CidadeInvalidaException | NumeroInvalidoException | RuaInvalidaException | CPFInvalidoException | DataNascimentoInvalidaException | NomeInvalidoException | GerenteExistenteException ex) {
			CaixasDeAlerta.CaixaErro("Cadastrar Gerente", "Campo inv�lido.", ex.getLocalizedMessage());
		}catch(NumberFormatException ex) {
			CaixasDeAlerta.CaixaErro("Cadastrar Gerente", "Campo inv�lido.", "Preencha os campos corretamente antes de concluir o cadastro.");
		}catch(Exception ex) {
			CaixasDeAlerta.CaixaErro("Cadastrar Gerente", "Erro inesperado.", "Erro inesperado.");
		}
	}

	@FXML
	public void handleCancelar(ActionEvent event) {
	}

	
	
}
