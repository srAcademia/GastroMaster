package br.com.ufrpeuag.gastromaster.ui;

import java.io.File;
import java.io.IOException;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FazerPedido extends Application {
	
	public FazerPedido (Mesa mesa, double valorMesa, Garcom garcom) {
		FazerPedidoControlador.setMesa2(mesa);
		FazerPedidoControlador.setValorMesa2(valorMesa);
		FazerPedidoControlador.setGarcom2(garcom);
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
	
}
