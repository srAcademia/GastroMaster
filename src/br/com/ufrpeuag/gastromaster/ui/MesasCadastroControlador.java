package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaCadastradaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaDisponibilidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class MesasCadastroControlador {
	
	@FXML
	private TextField numeroFieldMesa;
	@FXML
	private Button concluirMesaCadastro;
	@FXML
	private Button cancelarMesaCadastro;
	
	@FXML
	public void handleConcluirCadastro(ActionEvent event) throws MesaCadastradaException, NumeroInvalidoException, MesaDisponibilidadeInvalidaException, SQLException{
		try {
			Mesa mesa = new Mesa(Integer.parseInt(numeroFieldMesa.getText()), 1);
			Fachada.getSingleton().cadastrarMesa(mesa);
			CaixasDeAlerta.CaixaConcluido("Cadastro Mesa", "Mesa cadastrada.");
		}catch(MesaCadastradaException | NumeroInvalidoException ex) {
			CaixasDeAlerta.CaixaErro("Cadastrar Mesa", "Campo inválido.", ex.getLocalizedMessage());
		}catch(NumberFormatException ex) {
			CaixasDeAlerta.CaixaErro("Cadastrar Mesa", "Campo inválido.", "Preencha os campos corretamente antes de concluir o cadastro.");
		}catch(Exception ex) {
			CaixasDeAlerta.CaixaErro("Cadastrar Mesa", "Erro inesperado.", "Erro inesperado.");
		}
	}
	
	@FXML
	public void handleCancelar(ActionEvent event) {
		
	}
}
