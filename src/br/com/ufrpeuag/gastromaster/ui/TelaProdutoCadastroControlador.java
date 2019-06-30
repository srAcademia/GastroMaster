package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PrecoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeProdutoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaProdutoCadastroControlador {
	
	@FXML
	private TextField nomeFieldProduto;
	@FXML
	private TextField precoFieldProduto;
	@FXML
	private TextField quantidadeFieldProduto;
	@FXML
	private Button concluirProdutoCadastro;
	@FXML
	private Button cancelarProdutoCadastro;
	
	@FXML
	public void handleConcluirCadastro(ActionEvent event) throws PrecoInvalidoException, ProdutoExistenteException,
	QuantidadeProdutoInvalidaException, NomeInvalidoException, SQLException{
		try {
			Produto produto = new Produto(nomeFieldProduto.getText(), Integer.parseInt(quantidadeFieldProduto.getText()),Double.parseDouble(precoFieldProduto.getText()));
			Fachada.getSingleton().cadastrarProduto(produto);
			CaixasDeAlerta.CaixaConcluido("Cadastrar Produto", "Produto cadastrado.");
		}catch( PrecoInvalidoException | ProdutoExistenteException |
				QuantidadeProdutoInvalidaException | NomeInvalidoException ex) {
			CaixasDeAlerta.CaixaErro("Cadastrar Produto", "Campo inválido.", ex.getLocalizedMessage());
		}catch(NumberFormatException ex) {
			CaixasDeAlerta.CaixaErro("Cadastrar Produto", "Campo inválido.", "Preencha os campos corretamente antes de concluir o cadastro.");
		}catch(Exception ex) {
			System.out.println(ex.getStackTrace());
			CaixasDeAlerta.CaixaErro("Cadastrar Produto", "Erro inesperado.", "Erro inesperado.");
		}
	}
	
	
	@FXML
	public void handleCancelar(ActionEvent event) {
		
	}
}
