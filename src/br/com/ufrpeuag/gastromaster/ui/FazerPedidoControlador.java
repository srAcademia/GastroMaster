package br.com.ufrpeuag.gastromaster.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoVazioException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class FazerPedidoControlador implements Initializable{
	
	@FXML
	private ListView<Cardapio> cardapioList;
	@FXML
	private ListView<Produto> produtoList;
	@FXML
	private Button concluiPedido;
	
	private List<Cardapio> pratos = new ArrayList<>();
	private List<Produto> produtos = new ArrayList<>();
    private ObservableList<Cardapio> listaObservabelCardapio;
    private ObservableList<Produto> listaObservabelProduto;
	private static Mesa mesa2;
	private static double valorMesa2;
    
    public void listarOpcoes() throws ListarTodosInvalidoException, SQLException {
    	pratos = Fachada.getSingleton().listarTodosCardapios();
    	listaObservabelCardapio = FXCollections.observableArrayList(pratos);
    	cardapioList.setItems(listaObservabelCardapio);
    	
    	produtos = Fachada.getSingleton().listarTodosProdutos();
    	listaObservabelProduto = FXCollections.observableArrayList(produtos);
    	produtoList.setItems(listaObservabelProduto);
		
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			listarOpcoes();
		} catch (ListarTodosInvalidoException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleConcluirPedido(ActionEvent event)throws PedidoInvalidoException, PedidoVazioException, SQLException  {
		try {
			Produto produto = new Produto();
			produto = produtoList.getSelectionModel().getSelectedItem();
			Cardapio cardapio = new Cardapio();
			cardapio = cardapioList.getSelectionModel().getSelectedItem();
			Pedido pedido = new Pedido(cardapio, produto, valorMesa2, mesa2);
			Fachada.getSingleton().cadastrarPedido(pedido);
		}catch( PedidoInvalidoException | PedidoVazioException ex) {
			CaixasDeAlerta.CaixaErro("Realizar Pedido", "Campo inv�lido.", ex.getLocalizedMessage());
		}catch(Exception ex) {
			CaixasDeAlerta.CaixaErro("Realizar Pedido", "Erro inesperado.", "Erro inesperado.");
	
		}
	}

	public static Mesa getMesa2() {
		return mesa2;
	}

	public static void setMesa2(Mesa mesa2) {
		FazerPedidoControlador.mesa2 = mesa2;
	}

	public static double getValorMesa2() {
		return valorMesa2;
	}

	public static void setValorMesa2(double valorMesa2) {
		FazerPedidoControlador.valorMesa2 = valorMesa2;
	}

}
