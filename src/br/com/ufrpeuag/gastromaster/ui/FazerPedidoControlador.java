package br.com.ufrpeuag.gastromaster.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class FazerPedidoControlador implements Initializable{
	
	@FXML
	private ListView<Cardapio> cardapioList;
	@FXML
	private ListView<Produto> produtoList;
	
	private List<Cardapio> pratos = new ArrayList<>();
	private List<Produto> produtos = new ArrayList<>();
    private ObservableList<Cardapio> listaObservabelCardapio;
    private ObservableList<Produto> listaObservabelProduto;
    
    public void fazerPedido() throws ListarTodosInvalidoException, SQLException {
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
			fazerPedido();
		} catch (ListarTodosInvalidoException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
