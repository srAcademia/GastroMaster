package br.com.ufrpeuag.gastromaster.ui;

import java.io.File;
import java.io.IOException;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainAppGarcom extends Application {
	
	public MainAppGarcom(Garcom garcom) {
		TelaHomeGarcomControlador.setGarcom2(garcom);
	}
	
	private BorderPane rootLayout1;
	private BorderPane rootLayout2;
	private BorderPane rootLayout3;
	
	private AnchorPane fillLayout1;
    private AnchorPane fillLayout2;
    private AnchorPane fillLayout3;
    
    private static Stage stage;
    private static Scene mesas;
    private static Scene estoque;
    private static Scene cardapio;
    
    public static void main(String[] args) {
        launch(args);
    } // main

    @Override
    public void start(Stage PrimaryStage){
    	stage = PrimaryStage;
        File css = new File("base16-google-dark.css");
        String fileURI = css.toURI().toString();
        
        try {
        	rootLayout1 = FXMLLoader.load(getClass().getResource("TelaHomeGarcom.fxml"));
        	rootLayout2 = FXMLLoader.load(getClass().getResource("TelaHomeGarcom.fxml"));
        	rootLayout3 = FXMLLoader.load(getClass().getResource("TelaHomeGarcom.fxml"));
        	fillLayout1 = FXMLLoader.load(getClass().getResource("Mesas.fxml"));
        	fillLayout2 = FXMLLoader.load(getClass().getResource("TelaCardapio.fxml"));
        	fillLayout3 = FXMLLoader.load(getClass().getResource("TelaProduto.fxml"));
        	rootLayout1.getStylesheets().clear();
        	rootLayout2.getStylesheets().clear();
        	rootLayout3.getStylesheets().clear();
        	fillLayout1.getStylesheets().clear();
        	fillLayout2.getStylesheets().clear();
        	fillLayout3.getStylesheets().clear();
        	rootLayout1.getStylesheets().add(fileURI);
        	rootLayout2.getStylesheets().add(fileURI);
        	rootLayout3.getStylesheets().add(fileURI);
        	fillLayout1.getStylesheets().add(fileURI);
        	fillLayout2.getStylesheets().add(fileURI);
        	fillLayout3.getStylesheets().add(fileURI);


        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    	rootLayout1.setCenter(fillLayout1);
    	rootLayout2.setCenter(fillLayout2);
    	rootLayout3.setCenter(fillLayout3);
    	
    	mesas = new Scene(rootLayout1, 800, 470);
    	cardapio = new Scene(rootLayout2, 800, 470);
    	estoque = new Scene(rootLayout3, 800, 470);
        
        PrimaryStage.setTitle("GastroMaster");
        PrimaryStage.setScene(mesas);
        PrimaryStage.show();
    }
    
    public static void changeScreen(String troca) {
    	switch(troca) {
	    	case "mesas":
	    		stage.setScene(mesas);
	    		break;
	    	case "cardapio":
	    		stage.setScene(cardapio);
	    		break;
	    	case "estoque":
	    		stage.setScene(estoque);
	    		break;
    	}
    	
    }

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		MainAppGarcom.stage = stage;
	}

}
