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
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GarcomControlador {

	@FXML
	private TextField nomeFieldGarcom;

	@FXML
	private TextField cpfFieldGarcom;

	@FXML
	private TextField dataNascFieldGarcom;

	@FXML
	private TextField telefoneFieldGarcom;

	@FXML
	private TextField emailFieldGarcom;

	@FXML
	private TextField cidadeFieldGarcom;

	@FXML
	private TextField bairroFieldGarcom;

	@FXML
	private TextField ruaFieldGarcom;

	@FXML
	private TextField numeroFieldGarcom;

	@FXML
	private TextField cepFieldGarcom;

	@FXML
	private TextField salarioFieldGarcom;

	@FXML
	private Button cadastrarGarcom;

	@FXML
	private Button cancelarGarcom;


	public void handleConcluirGarcom(ActionEvent event)
			throws CPFInvalidoException, DataNascimentoInvalidaException, NomeInvalidoException,
			GerenteExistenteException, SalarioInvalidoException, SenhaInvalidaException, BairroInvalidoException,
			CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException, SQLException {
		
		try {
			
			Endereco endereco = new Endereco(cidadeFieldGarcom.getText(), bairroFieldGarcom.getText(),
					ruaFieldGarcom.getText(), Integer.parseInt(numeroFieldGarcom.getText()),
					cepFieldGarcom.getText());
			
			Garcom garcom = new Garcom(nomeFieldGarcom.getText(), cpfFieldGarcom.getText(),
					Fachada.getSingleton().ValidarData(dataNascFieldGarcom.getText()), telefoneFieldGarcom.getText(),
					emailFieldGarcom.getText(), Double.parseDouble(salarioFieldGarcom.getText()), "", endereco);
			Fachada.getSingleton().cadastrarGarcom(garcom);
			
			CaixasDeAlerta.CaixaConcluido("Cadastro Garçom", "Garçom cadastrado.");
			
		} catch (BairroInvalidoException | CEPInvalidoException | CidadeInvalidaException | NumeroInvalidoException
				| RuaInvalidaException | CPFInvalidoException | DataNascimentoInvalidaException
				| NomeInvalidoException ex) {
			
			CaixasDeAlerta.CaixaErro("Cadastrar Garçom", "Campo inv�lido.", ex.getLocalizedMessage());
		
		} catch (NumberFormatException ex) {
		
			CaixasDeAlerta.CaixaErro("Cadastrar Garçom", "Campo inv�lido.",
					"Preencha os campos corretamente antes de concluir o cadastro.");
		} catch (Exception ex) {
			
			CaixasDeAlerta.CaixaErro("Cadastrar Garçom", "Erro inesperado.", "Erro inesperado.");
		}
	}
	@FXML
	public void handleCancelar(ActionEvent event) {
		
	}

}
