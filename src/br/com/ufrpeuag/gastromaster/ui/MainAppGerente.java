package br.com.ufrpeuag.gastromaster.ui;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainAppGerente extends Application {
	private BorderPane rootLayout2;
	private BorderPane rootLayout3;
	private BorderPane rootLayout4;
	
    private AnchorPane fillLayout2;
    private AnchorPane fillLayout3;
    private AnchorPane fillLayout4;
    
    private static Stage stage;
    private static Scene funcionarios;
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
        	rootLayout2 = FXMLLoader.load(getClass().getResource("TelaHomeGerente.fxml"));
        	rootLayout3 = FXMLLoader.load(getClass().getResource("TelaHomeGerente.fxml"));
        	rootLayout4 = FXMLLoader.load(getClass().getResource("TelaHomeGerente.fxml"));
        	fillLayout2 = FXMLLoader.load(getClass().getResource("TelaFuncionarios.fxml"));
        	fillLayout3 = FXMLLoader.load(getClass().getResource("TelaCardapio.fxml"));
        	fillLayout4 = FXMLLoader.load(getClass().getResource("TelaProduto.fxml"));
        	rootLayout2.getStylesheets().clear();
        	rootLayout3.getStylesheets().clear();
        	rootLayout4.getStylesheets().clear();
        	fillLayout2.getStylesheets().clear();
        	fillLayout3.getStylesheets().clear();
        	fillLayout4.getStylesheets().clear();
        	rootLayout2.getStylesheets().add(fileURI);
        	rootLayout3.getStylesheets().add(fileURI);
        	rootLayout4.getStylesheets().add(fileURI);
        	fillLayout2.getStylesheets().add(fileURI);
        	fillLayout3.getStylesheets().add(fileURI);
        	fillLayout4.getStylesheets().add(fileURI);


        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    	rootLayout2.setCenter(fillLayout2);
    	rootLayout3.setCenter(fillLayout3);
    	rootLayout4.setCenter(fillLayout4);
    	
    	funcionarios = new Scene(rootLayout2, 800, 470);
    	cardapio = new Scene(rootLayout3, 800, 470);
    	estoque = new Scene(rootLayout4, 800, 470);
        
        PrimaryStage.setTitle("GastroMaster");
        PrimaryStage.setScene(funcionarios);
        PrimaryStage.show();
    }
    
    public static void changeScreen(String troca) {
    	switch(troca) {
	    	case "funcionarios":
	    		stage.setScene(funcionarios);
	    		break;
	    	case "mesas":
	    		CaixasDeAlerta.CaixaErro("Gastro Master", "Mesas", "Apenas garçom pode acessar mesas.");
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
		MainAppGerente.stage = stage;
	}
}