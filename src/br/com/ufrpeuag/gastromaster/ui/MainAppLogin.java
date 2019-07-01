package br.com.ufrpeuag.gastromaster.ui;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainAppLogin extends Application {
	
    private static Stage stage = new Stage();;
	
    public static void main(String[] args) {
        launch(args);
    } // main

    @Override
    public void start(Stage PrimaryStage) {
    	stage = PrimaryStage;
        Parent root = null;
        File css = new File("base16-google-dark.css");
        String fileURI = css.toURI().toString();
        
        try {
            root = FXMLLoader.load(getClass().getResource("TelaInicialLogin.fxml"));
            root.getStylesheets().clear();
            root.getStylesheets().add(fileURI);

        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        stage.setTitle("GastroMaster");
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
    }

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		MainAppLogin.stage = stage;
	}

}