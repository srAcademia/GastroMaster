package br.com.ufrpeuag.gastromaster.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.PratoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
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

public class TelaCardapioControlador implements Initializable{
	
	@FXML
	private TableView<Cardapio> cardapioList;
	@FXML
	private TableColumn<Cardapio, String> nomeColunaCardapio;
	@FXML
	private TableColumn<Cardapio, String> precoColunaCardapio;
	private List<Cardapio> cardapio = new ArrayList<>();
    private ObservableList<Cardapio> listaObservabelCardapio;
    
    @FXML
	private Button deletarCardapio;
	@FXML
	private Button cadastrarCardapio;
	@FXML
	private Button alterarCardapio;
	
	@FXML
	private Label nomeLabel;
	@FXML
	private Label precoLabel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			listarCardapio();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mostarDetalhesCardapio(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cardapioList.getSelectionModel().selectedItemProperty().addListener((observable, odlValue, newValeu) -> {
			try {
				mostarDetalhesCardapio(newValeu);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		

	}
	
	public void listarCardapio() throws SQLException{
		cardapio = Fachada.getSingleton().listarTodosCardapios();
		listaObservabelCardapio = FXCollections.observableArrayList(cardapio);
		cardapioList.setItems(listaObservabelCardapio);
		nomeColunaCardapio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrato()));
		precoColunaCardapio.setCellValueFactory(cellData -> new SimpleStringProperty(Double.toString(cellData.getValue().getPreco())));
	}
	
	public void mostarDetalhesCardapio(Cardapio cardapio) throws SQLException {
		if(cardapio != null) {
			nomeLabel.setText(cardapio.getPrato());
			precoLabel.setText(Double.toString(cardapio.getPreco()));
		} else {
			nomeLabel.setText("");
			precoLabel.setText("");
		}
	}
	
	@FXML
	public void handleDeletarCardapio(ActionEvent event) throws PratoInexistenteException, SQLException{
		try {
			boolean confirmacao = CaixasDeAlerta.CaixaConfirmar("Deletar Cardapio", "Tem certeza de que deseja deletar o prato?");
			Cardapio cardapio = new Cardapio();
			cardapio = cardapioList.getSelectionModel().getSelectedItem();
			if (confirmacao == true) {
				Fachada.getSingleton().deletarCardapio(cardapio);;
				listarCardapio();
				CaixasDeAlerta.CaixaConcluido("Deletar Cardapio", "Prato deletado.");
			}
		}catch(PratoInexistenteException ex) {
			CaixasDeAlerta.CaixaErro("Deletar Cardapio", ex.getLocalizedMessage(), "Selecione um prato para deletar.");
		}catch(Exception ex) {
			CaixasDeAlerta.CaixaErro("Deletar Cardapio", "Erro inesperado.", "Erro inesperado.");
		}
	}
	
	@FXML
	public void handleCadastrarCardapio(ActionEvent event) throws SQLException {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
		Parent root = null;
        File css = new File("base16-google-dark.css");
        String fileURI = css.toURI().toString();
        
        try {
            root = FXMLLoader.load(getClass().getResource("TelaCardapioCadastro.fxml"));
            root.getStylesheets().clear();
            root.getStylesheets().add(fileURI);

        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        window.setTitle("Cardapio");
		Scene scene = new Scene(root, 300, 300);
		window.setScene(scene);
		window.setResizable(false);
		window.showAndWait();
		listarCardapio();
	}
	
	@FXML
	public void handleAlterarCardapio(ActionEvent event) throws Exception {
		Cardapio cardapio = new Cardapio();
		cardapio = cardapioList.getSelectionModel().getSelectedItem();
		if(cardapio != null) {
			EditarCardapio editar = new EditarCardapio(cardapio);
			editar.start(new Stage());
		} else {
			CaixasDeAlerta.CaixaErro("Alterar Cardapio", "Prato não encontrado", "Selecione um prato para alterar.");
		}
		listarCardapio();
	}

}
