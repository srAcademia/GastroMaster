package br.com.ufrpeuag.gastromaster.ui;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaProdutoEdicaoControlador implements Initializable{
	
	@FXML
	private TextField nomeFieldProduto;
	@FXML
	private TextField precoFieldProduto;
	@FXML
	private TextField quantidadeFieldProduto;
	@FXML
	private Button concluirProdutoEdicao;
	@FXML
	private Button cancelarProdutoEdicao;
	private static Produto produto2;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initProduto();		
	}
	
	public void handleConcluirEdicao(ActionEvent event) throws ProdutoExistenteException {
		try {
			Produto produto = new Produto(nomeFieldProduto.getText(), Integer.parseInt(quantidadeFieldProduto.getText()), Double.parseDouble(precoFieldProduto.getText()));
			Fachada.getSingleton().alterarProduto(produto2, produto.getNome(), produto.getQuantidade(), produto.getPreco());
			CaixasDeAlerta.CaixaConcluido("Alterar Produto", "Produto alterado.");
		}catch(ProdutoExistenteException ex) {
			CaixasDeAlerta.CaixaErro("Alterar Produto", "Campo inválido.", ex.getLocalizedMessage());
		}catch(NumberFormatException ex) {
			CaixasDeAlerta.CaixaErro("Alterar Produto", "Campo inválido.", "Preencha os campos corretamente antes de concluir a edição.");
		}catch(Exception ex) {
			CaixasDeAlerta.CaixaErro("Alterar Produto", "Erro inesperado.", "Erro inesperado.");
		}
	}
	
	public void initProduto() {
		precoFieldProduto.setText(Double.toString(produto2.getPreco()));
		quantidadeFieldProduto.setText(Integer.toString(produto2.getQuantidade()));
	}
	
	@FXML
	public void handleCancelar(ActionEvent event) {
	}

	public static Produto getProduto2() {
		return produto2;
	}

	public static void setProduto2(Produto produto2) {
		TelaProdutoEdicaoControlador.produto2 = produto2;
	}

}
