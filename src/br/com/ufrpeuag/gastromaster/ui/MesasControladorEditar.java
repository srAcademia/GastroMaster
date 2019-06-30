package br.com.ufrpeuag.gastromaster.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaCadastradaException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MesasControladorEditar implements Initializable{
	@FXML
	private TextField numeroFieldMesa;
	
	@FXML
	private Button concluirMesaEdicao;
	@FXML
	private Button cancelarMesaEdicao;
	private static Mesa mesa2;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initMesa();
	}
	
	public void handleConcluirEdicao(ActionEvent event) throws MesaCadastradaException, NumberFormatException, SQLException{
		try {
			Fachada.getSingleton().alterarMesa(mesa2, Integer.parseInt(numeroFieldMesa.getText()));
			CaixasDeAlerta.CaixaConcluido("Alterar Mesa", "Mesa alterada.");
		}catch(MesaCadastradaException ex) {
			CaixasDeAlerta.CaixaErro("Alterar Mesa", "Campo inválido.", ex.getLocalizedMessage());
		}catch(NumberFormatException ex) {
			CaixasDeAlerta.CaixaErro("Alterar Mesa", "Campo inválido.", "Preencha os campos corretamente antes de concluir a edição.");
		}catch(Exception ex) {
			CaixasDeAlerta.CaixaErro("Alterar Mesa", "Erro inesperado.", "Erro inesperado.");
		}
	}
	
	public void initMesa() {
		numeroFieldMesa.setText(Integer.toString(mesa2.getNumero()));
	}
	
	@FXML
	public void handleCancelar(ActionEvent event) {
	}

	public static Mesa getMesa2() {
		return mesa2;
	}

	public static void setMesa2(Mesa mesa2) {
		MesasControladorEditar.mesa2 = mesa2;
	}


}
