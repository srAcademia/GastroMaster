package br.com.ufrpeuag.gastromaster.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.PratoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaCardapioEdicaoControlador implements Initializable{
	
	@FXML
	private TextField nomeFieldCardapio;
	@FXML
	private TextField precoFieldCardapio;
	@FXML
	private Button concluirCardapioEdicao;
	@FXML
	private Button cancelarCardapioEdicao;
	private static Cardapio cardapio2;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initCardapio();
	}
	public void handleConcluirEdicao(ActionEvent event)throws PratoExistenteException, SQLException{
		try {
			Cardapio cardapio = new Cardapio(nomeFieldCardapio.getText(), Double.parseDouble(precoFieldCardapio.getText()));
			Fachada.getSingleton().alterarCardapio(cardapio2, cardapio.getPrato(), cardapio.getPreco());
			CaixasDeAlerta.CaixaConcluido("Alterar Cardapio", "Cardapio alterado.");
		}catch(PratoExistenteException ex) {
			CaixasDeAlerta.CaixaErro("Alterar Cardapio", "Campo inválido.", ex.getLocalizedMessage());
		}catch(NumberFormatException ex) {
			CaixasDeAlerta.CaixaErro("Alterar Cardapio", "Campo inválido.", "Preencha os campos corretamente antes de concluir a edição.");
		}catch(Exception ex) {
			CaixasDeAlerta.CaixaErro("Alterar Cardapio", "Erro inesperado.", "Erro inesperado.");
		}
	}
	
	public void initCardapio() {
		precoFieldCardapio.setText(Double.toString(cardapio2.getPreco()));
	}
	
	@FXML
	public void handleCancelar(ActionEvent event) {
	}
	
	public static Cardapio getCardapio2() {
		return cardapio2;
	}
	public static void setCardapio2(Cardapio cardapio2) {
		TelaCardapioEdicaoControlador.cardapio2 = cardapio2;
	}


}
