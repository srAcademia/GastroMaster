package br.com.ufrpeuag.gastromaster.ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaInicialLoginControler implements Initializable {

	@FXML
	private PasswordField txtIndentificador;

	@FXML
	private Button buttonEntrar;
	private static Stage window = new Stage();
	MainAppGerente mainAppGerente = new MainAppGerente();

	@FXML
	public void acaoDoBotao(ActionEvent event) throws SQLException {
			Gerente gerente = new Gerente();
			Garcom garcom = new Garcom();
			gerente = Fachada.getSingleton().verificarIdentificadorGerente(txtIndentificador.getText());
			garcom = Fachada.getSingleton().verificarIdentificadorGarcom(txtIndentificador.getText());
			
			if(gerente == null && garcom == null) {
				CaixasDeAlerta.CaixaErro("Login", "Login Inválido", "Login não encontrado.");
			}
			else if(gerente != null){
				window.initModality(Modality.APPLICATION_MODAL);
				Parent root = null;
		        File css = new File("base16-google-dark.css");
		        String fileURI = css.toURI().toString();
		        
		        try {
		            root = FXMLLoader.load(getClass().getResource("TelaLogarGerente.fxml"));
		            root.getStylesheets().clear();
		            root.getStylesheets().add(fileURI);

		        } catch (IOException e) {
		            System.out.println(e);
		            System.exit(1);
		        }
		        window.setTitle("LogarGerente");
				Scene scene = new Scene(root, 300, 300);
				window.setScene(scene);
				window.setResizable(false);
				window.showAndWait();
			}
			else if(garcom != null){
				MainAppGarcom mainAppGarcom = new MainAppGarcom(garcom);
				mainAppGarcom.start(new Stage());
				TelaInicialLoginControler.getWindow().close();
				MainAppLogin.getStage().close();
			}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public static Stage getWindow() {
		return window;
	}
	public static void setWindow(Stage window) {
		TelaInicialLoginControler.window = window;
	}

}
