package br.com.ufrpeuag.gastromaster.ui;

import java.io.File;
import java.io.IOException;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditarCardapio extends Application {
	
	public EditarCardapio (Cardapio cardapio) {
		TelaCardapioEdicaoControlador.setCardapio2(cardapio);
	}

    public static void main(String[] args) {
        launch(args);
    } // main

	@Override
	public void start(Stage arg0) throws Exception {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
		Parent root = null;
        File css = new File("base16-google-dark.css");
        String fileURI = css.toURI().toString();
        
        try {
            root = FXMLLoader.load(getClass().getResource("TelaCardapioEdicao.fxml"));
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
	}

}
