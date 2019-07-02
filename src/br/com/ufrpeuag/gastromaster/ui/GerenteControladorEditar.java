package br.com.ufrpeuag.gastromaster.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GerenteControladorEditar implements Initializable{
	
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
	private Button concluirGerenteEdicao;
	@FXML
	private Button cancelarGerenteCadastro;
	private static Gerente gerente2;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			initGerente();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleConcluirEdicao(ActionEvent event) throws CPFInvalidoException, DataNascimentoInvalidaException,
	GerenteExistenteException, SQLException {
		try {
			Endereco endereco = new Endereco(cidadeFieldGerente.getText(), bairroFieldGerente.getText(), ruaFieldGerente.getText(), Integer.parseInt(numeroFieldGerente.getText()), cepFieldGerente.getText());
			Gerente gerente = new Gerente(nomeFieldGerente.getText(), cpfFieldGerente.getText(), Fachada.getSingleton().ValidarData(dataNascFieldGerente.getText()), 
					telefoneFieldGerente.getText(), emailFieldGerente.getText(), Double.parseDouble(salarioFieldGerente.getText()), 
					senhaFieldGerente.getText(), "", endereco);
			Fachada.getSingleton().alterarGerente(gerente);
			CaixasDeAlerta.CaixaConcluido("Alterar Gerente", "Gerente alterado.");
		}catch(CPFInvalidoException | DataNascimentoInvalidaException | GerenteExistenteException ex) {
			CaixasDeAlerta.CaixaErro("Alterar Gerente", "Campo inválido.", ex.getLocalizedMessage());
		}catch(NumberFormatException ex) {
			CaixasDeAlerta.CaixaErro("Alterar Gerente", "Campo inválido.", "Preencha os campos corretamente antes de concluir a edição.");
		}catch(Exception ex) {
			CaixasDeAlerta.CaixaErro("Alterar Gerente", "Erro inesperado.", "Erro inesperado.");
		}
	}
	
	public void initGerente() throws SQLException {
		nomeFieldGerente.setText(gerente2.getNome());
		dataNascFieldGerente.setText(Fachada.getSingleton().mudarDataParaString((gerente2.getDataNasc())));
		telefoneFieldGerente.setText(gerente2.getTelefone());
		emailFieldGerente.setText(gerente2.getEmail()); 
		salarioFieldGerente.setText(Double.toString(gerente2.getSalario()));
		senhaFieldGerente.setText(gerente2.getSenha());
		cidadeFieldGerente.setText(gerente2.getEndereco().getCidade());
		bairroFieldGerente.setText(gerente2.getEndereco().getBairro());
		ruaFieldGerente.setText(gerente2.getEndereco().getRua());
		numeroFieldGerente.setText(Integer.toString(gerente2.getEndereco().getNumero()));
		cepFieldGerente.setText(gerente2.getEndereco().getCep());			
	}
	
	@FXML
	public void handleCancelar(ActionEvent event) {
	}
	
	public static Gerente getGerente2() {
		return gerente2;
	}

	public static void setGerente2(Gerente gerente2) {
		GerenteControladorEditar.gerente2 = gerente2;
	}

}
