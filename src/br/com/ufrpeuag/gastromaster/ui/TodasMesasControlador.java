package br.com.ufrpeuag.gastromaster.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.ContaGerarException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TodasMesasControlador implements Initializable{
	
	@FXML
	private TableView<Mesa> mesaList;
	@FXML
	private TableColumn<Mesa, String> numeroColunaMesa;
	@FXML
	private TableColumn<Mesa, String> disponibilidadeColunaMesa;
	private List<Mesa> mesas = new ArrayList<>();
    private ObservableList<Mesa> listaObservabelMesa;
	
	
	@FXML
	private Button deletarMesa;
	@FXML
	private Button cadastrarMesa;
	@FXML
	private Button alterarMesa;
	@FXML
	private Button pedir;
	@FXML
	private Button pagar;
	@FXML
	private Button deletarPedido;
	@FXML
	private Label valorLabel;
	@FXML
	private Label numeroLabel;
	@FXML
	private Label disponibilidadeLabel;
	
	@FXML
	private ListView<Pedido> pedidoList;
	private List<Pedido> pedidos = new ArrayList<>();
	private double valorMesa = 0;
    private ObservableList<Pedido> listaObservabelPedido;
	private List<Conta> contas;

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			listarMesas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			mostarDetalhesMesa(null);
			gerarValorTotal(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mesaList.getSelectionModel().selectedItemProperty().addListener((observable, odlValue, newValeu) -> {
			try {
				mostarDetalhesMesa(newValeu);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		mesaList.getSelectionModel().selectedItemProperty().addListener((observable, odlValue, newValeu) -> {
			try {
				gerarValorTotal(newValeu);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		mesaList.getSelectionModel().selectedItemProperty().addListener((observable, odlValue, newValeu) -> {
			try {
				listarPedidos(newValeu);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}
	
	public void listarMesas() throws SQLException{
		mesas = Fachada.getSingleton().listarTodasMesas();
		listaObservabelMesa = FXCollections.observableArrayList(mesas);
		mesaList.setItems(listaObservabelMesa);
		numeroColunaMesa.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getNumero())));
		disponibilidadeColunaMesa.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getDisponibilidade())));
	}
	
	public void listarPedidos(Mesa mesa) throws SQLException  {
		if(mesa != null) {
			pedidos = Fachada.getSingleton().listarPedidosPorMesa(mesa.getNumero());
			listaObservabelPedido = FXCollections.observableArrayList(pedidos);
			pedidoList.setItems(listaObservabelPedido);
		}
    }
	
	public void mostarDetalhesMesa(Mesa mesa) throws SQLException {
		if(mesa != null) {
			numeroLabel.setText(Integer.toString(mesa.getNumero()));
			if(mesa.getDisponibilidade() == 1) {
				disponibilidadeLabel.setText("Disponível");
			} else {
				disponibilidadeLabel.setText("Indisponível");
			}
			
		} else {
			numeroLabel.setText("");
			disponibilidadeLabel.setText("");
		}

	}
	public void gerarValorTotal(Mesa mesa) throws SQLException {
		if(mesa != null) {
			contas = new ArrayList<>();
			contas = Fachada.getSingleton().recuperarContaPorMesa(Integer.parseInt(numeroLabel.getText()));
			if (contas == null || contas.isEmpty()) {
				valorLabel.setText("0.0");
			}else {
				valorMesa = Fachada.getSingleton().mostrarValorConta(contas.get(0));
				valorLabel.setText(Double.toString(valorMesa));
			}
		}else {
			valorLabel.setText("0.0");
		}
	}
	
	
	@FXML
	public void handleDeletarMesa(ActionEvent event) throws MesaInexistenteException, SQLException {
		try {
			boolean confirmacao = CaixasDeAlerta.CaixaConfirmar("Deletar Mesa", "Tem certeza de que deseja deletar a Mesa?");
			Mesa mesa = new Mesa();
			mesa = mesaList.getSelectionModel().getSelectedItem();
			if (confirmacao == true) {
				Fachada.getSingleton().deletarMesa(mesa);
				listarMesas();
				CaixasDeAlerta.CaixaConcluido("Deletar Mesa", "Mesa deletada.");
			}
		}catch(MesaInexistenteException ex) {
			CaixasDeAlerta.CaixaErro("Deletar Mesa", ex.getLocalizedMessage(), "Selecione uma mesa para deletar.");
		}catch(Exception ex) {
			CaixasDeAlerta.CaixaErro("Deletar Mesa", "Erro inesperado.", "Erro inesperado.");
		}
	}
	
	@FXML
	public void handleCadastarMesa(ActionEvent event) throws SQLException {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
		Parent root = null;
        File css = new File("base16-google-dark.css");
        String fileURI = css.toURI().toString();
        
        try {
            root = FXMLLoader.load(getClass().getResource("TelaMesaCadastro.fxml"));
            root.getStylesheets().clear();
            root.getStylesheets().add(fileURI);

        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        window.setTitle("Mesa");
		Scene scene = new Scene(root, 300, 300);
		window.setScene(scene);
		window.setResizable(false);
		window.showAndWait();
		listarMesas();
	}
	
	@FXML
	public void handleAlterarMesa(ActionEvent event) throws Exception {
		Mesa mesa = new Mesa();
		mesa = mesaList.getSelectionModel().getSelectedItem();
		if(mesa != null) {
			EditarTodasMesas editar = new EditarTodasMesas(mesa);
			editar.start(new Stage());
		} else {
			CaixasDeAlerta.CaixaErro("Alterar Mesa", "Mesa não encontrada", "Selecione uma mesa para alterar.");
		}
		listarMesas();
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
