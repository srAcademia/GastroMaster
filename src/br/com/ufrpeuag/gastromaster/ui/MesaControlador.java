package br.com.ufrpeuag.gastromaster.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.ConcluirPagamentoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MesaControlador implements Initializable{

	@FXML
	private ListView<Pedido> pedidoList;
	private ListView<Cardapio> cardapioList;

	@FXML
	private Button pedir;
	
	private List<Pedido> pedidos = new ArrayList<>();
	private List<Cardapio> pratos = new ArrayList<>();

    private ObservableList<Pedido> listaObservabelPedido;
    private ObservableList<Cardapio> listaObservabelCardapio;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	try {
			listarPedidos();
		} catch (SQLException | ConcluirPagamentoException e) {
			e.printStackTrace();
		}
    	
	}

	public void listarPedidos() throws SQLException, ConcluirPagamentoException {
		pedidos = Fachada.getSingleton().listarPedidosPorMesa(1);
		listaObservabelPedido = FXCollections.observableArrayList(pedidos);
		pedidoList.setItems(listaObservabelPedido);
    }
	
	@FXML
	public void handleFazerPedido(ActionEvent event) throws ListarTodosInvalidoException, SQLException {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Realizar Pedido");
		StackPane layout = new StackPane();
		Scene scene = new Scene(layout, 300, 300);
		
//		layout.getChildren().add(cardapioList);
//		pratos = Fachada.getSingleton().listarTodosCardapios();
//		listaObservabelCardapio = FXCollections.observableArrayList(pratos);
//		cardapioList.setItems(listaObservabelCardapio);
		
		window.setScene(scene);
		window.showAndWait();
	}
	
}