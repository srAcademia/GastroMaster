package br.com.ufrpeuag.gastromaster.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Data;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GarcomControladorAlterar {

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
	private TextField senhaFieldGarcom;

	@FXML
	private Button concluirAlterarGarcom;

	@FXML
	private Button cancelarGarcom;

	private static Garcom garcom;

	public void initialize(URL arg0, ResourceBundle arg1) {
		initGerente();
	}

	@FXML
	public void handleConcluirEdicao(ActionEvent event)
			throws CPFInvalidoException, DataNascimentoInvalidaException, GerenteExistenteException, SQLException {
		try {
			Endereco endereco = new Endereco(cidadeFieldGarcom.getText(), bairroFieldGarcom.getText(),
					ruaFieldGarcom.getText(), Integer.parseInt(numeroFieldGarcom.getText()), cepFieldGarcom.getText());
			Garcom g = new Garcom(nomeFieldGarcom.getText(), cpfFieldGarcom.getText(),
					Fachada.getSingleton().ValidarData(dataNascFieldGarcom.getText()), telefoneFieldGarcom.getText(),
					emailFieldGarcom.getText(), Double.parseDouble(salarioFieldGarcom.getText()), "", endereco);
			
			Fachada.getSingleton().alterarGarcom(g);
			
			CaixasDeAlerta.CaixaConcluido("Alterar Garcom", "Garcom alterado.");
		} catch (CPFInvalidoException | DataNascimentoInvalidaException ex) {
			
			CaixasDeAlerta.CaixaErro("Alterar Garcom", "Campo inv�lido.", ex.getLocalizedMessage());
			
		} catch (NumberFormatException ex) {
			
			CaixasDeAlerta.CaixaErro("Alterar Garcom", "Campo inv�lido.",
					"Preencha os campos corretamente antes de concluir o cadastro.");
			
		} catch (Exception ex) {
			
			CaixasDeAlerta.CaixaErro("Cadastrar Garcom", "Erro inesperado.", "Erro inesperado.");
		}
	}

	public void initGerente() {
		nomeFieldGarcom.setText(garcom.getNome());
		dataNascFieldGarcom.setText(Data.mudarDataParaString(garcom.getDataNasc()));
		telefoneFieldGarcom.setText(garcom.getTelefone());
		emailFieldGarcom.setText(garcom.getEmail());
		salarioFieldGarcom.setText(Double.toString(garcom.getSalario()));
		cidadeFieldGarcom.setText(garcom.getEndereco().getCidade());
		bairroFieldGarcom.setText(garcom.getEndereco().getBairro());
		ruaFieldGarcom.setText(garcom.getEndereco().getRua());
		numeroFieldGarcom.setText(Integer.toString(garcom.getEndereco().getNumero()));
		cepFieldGarcom.setText(garcom.getEndereco().getCep());
	}

	@FXML
	public void handleCancelar(ActionEvent event) {
	}

	public static Garcom getGarcom() {
		return garcom;
	}

	public static void setGarcom(Garcom garcom) {
		GarcomControladorAlterar.garcom = garcom;
	}
}
