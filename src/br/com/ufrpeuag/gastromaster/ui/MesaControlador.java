package br.com.ufrpeuag.gastromaster.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.ConcluirPagamentoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ContaGerarException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
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
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MesaControlador implements Initializable{

	@FXML
	private ListView<Pedido> pedidoList;
	@FXML
	private Button pedir;
	@FXML
	private Button pagar;
	@FXML
	private Label valor;

	private List<Pedido> pedidos = new ArrayList<>();
	private double valorMesa = 0;
    private ObservableList<Pedido> listaObservabelPedido;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	try {
			listarPedidos();
			gerarValorTotal();
		} catch (SQLException | ConcluirPagamentoException e) {
			e.printStackTrace();
		} catch (ContaGerarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}

	public void listarPedidos() throws SQLException, ConcluirPagamentoException {
		pedidos = Fachada.getSingleton().listarPedidosPorMesa(1);
		listaObservabelPedido = FXCollections.observableArrayList(pedidos);
		pedidoList.setItems(listaObservabelPedido);
    }

	public void gerarValorTotal() throws SQLException, ContaGerarException {
		List<Conta> contas = new ArrayList<>();
		contas = Fachada.getSingleton().recuperarContaPorMesa(1);
		if (contas != null) {
			valorMesa = Fachada.getSingleton().mostrarValorConta(contas.get(0));
			valor.setText(Double.toString(valorMesa));
		}
	}
	
	@FXML
	public void handleFazerPedido(ActionEvent event) throws ListarTodosInvalidoException, SQLException {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
		Parent root = null;
        File css = new File("base16-google-dark.css");
        String fileURI = css.toURI().toString();
        
        try {
            root = FXMLLoader.load(getClass().getResource("TelaFazerPedido.fxml"));
            root.getStylesheets().clear();
            root.getStylesheets().add(fileURI);

        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        window.setTitle("Realizar Pedido");
		Scene scene = new Scene(root, 500, 400);
		window.setScene(scene);
		window.setResizable(false);;
		window.showAndWait();
	}
	
	@FXML
	public void handlePagar(ActionEvent event) throws ContaGerarException, SQLException, PedidoInexistenteException {
		List<Conta> contas = new ArrayList<>();
		contas = Fachada.getSingleton().recuperarContaPorMesa(1);
		Fachada.getSingleton().deletarTodasContasPorMesa(contas.get(0));
		Fachada.getSingleton().deletarTodosPedidosPelaMesa(1);
	}
}