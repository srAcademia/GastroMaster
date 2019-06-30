package br.com.ufrpeuag.gastromaster.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaProdutoControlador implements Initializable{
	
	@FXML
	private TableView<Produto> produtoList;
	@FXML
	private TableColumn<Produto, String> nomeColunaProduto;
	@FXML
	private TableColumn<Produto, String> precoColunaProduto;
	private List<Produto> produto = new ArrayList<>();
    private ObservableList<Produto> listaObservabelProduto;
    
    @FXML
	private Button deletarProduto;
	@FXML
	private Button cadastrarProduto;
	@FXML
	private Button alterarProduto;
	
	@FXML
	private Label nomeLabel;
	@FXML
	private Label precoLabel;
	@FXML
	private Label quantidadeLabel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			listarProduto();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			mostarDetalhesProduto(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		produtoList.getSelectionModel().selectedItemProperty().addListener((observable, odlValue, newValeu) -> 	{
			try {
				mostarDetalhesProduto(newValeu);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	public void listarProduto() throws SQLException{
		produto = Fachada.getSingleton().listarTodosProdutos();
		listaObservabelProduto = FXCollections.observableArrayList(produto);
		produtoList.setItems(listaObservabelProduto);
		nomeColunaProduto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
		precoColunaProduto.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData.getValue().getPreco())));
	}
	
	public void mostarDetalhesProduto(Produto produto) throws SQLException {
		if(produto != null) {
			nomeLabel.setText(produto.getNome());
			precoLabel.setText(Double.toString(produto.getPreco()));
			quantidadeLabel.setText(Integer.toString(produto.getQuantidade()));
		} else {
			nomeLabel.setText("");
			precoLabel.setText("");
			quantidadeLabel.setText("");
		}
	}
	
	@FXML
	public void handleDeletarProduto(ActionEvent event) throws ProdutoInexistenteException, NomeInvalidoException{
		try {
			boolean confirmacao = CaixasDeAlerta.CaixaConfirmar("Deletar Produto", "Tem certeza de que deseja deletar o produto?");
			Produto produto = new Produto();
			produto = produtoList.getSelectionModel().getSelectedItem();
			if (confirmacao == true) {
				Fachada.getSingleton().deletarProduto(produto);;
				listarProduto();
				CaixasDeAlerta.CaixaConcluido("Deletar Produto", "Produto deletado.");
			}
		}catch(ProdutoInexistenteException | NomeInvalidoException ex) {
			CaixasDeAlerta.CaixaErro("Deletar Produto", ex.getLocalizedMessage(), "Selecione um produto para deletar.");
		}catch(Exception ex) {
			CaixasDeAlerta.CaixaErro("Deletar Produto", "Erro inesperado.", "Erro inesperado.");
		}
	}
	
	@FXML
	public void handleCadastrarProduto(ActionEvent event) throws SQLException {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
		Parent root = null;
        File css = new File("base16-google-dark.css");
        String fileURI = css.toURI().toString();
        
        try {
            root = FXMLLoader.load(getClass().getResource("TelaProdutoCadastro.fxml"));
            root.getStylesheets().clear();
            root.getStylesheets().add(fileURI);

        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        window.setTitle("Produto");
		Scene scene = new Scene(root, 300, 300);
		window.setScene(scene);
		window.setResizable(false);
		window.showAndWait();
		listarProduto();
	}
	
	@FXML
	public void handleAlterarProduto(ActionEvent event) throws Exception {
		Produto produto = new Produto();
		produto = produtoList.getSelectionModel().getSelectedItem();
		if(produto != null) {
			EditarProduto editar = new EditarProduto(produto);
			editar.start(new Stage());
		} else {
			CaixasDeAlerta.CaixaErro("Alterar Produto", "Produto não encontrado", "Selecione um produto para alterar.");
		}
		listarProduto();
	}

}
