package br.com.ufrpeuag.gastromaster.ui;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    } // main

    @Override
    public void start(Stage PrimaryStage) throws ListarTodosInvalidoException, SQLException {

        Parent root = null;
        File css = new File("base16-google-dark.css");
        String fileURI = css.toURI().toString();
        
        try {
            root = FXMLLoader.load(getClass().getResource("Mesas.fxml"));
            root.getStylesheets().clear();
            root.getStylesheets().add(fileURI);

        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
        PrimaryStage.setTitle("GastroMaster");
        PrimaryStage.setScene(new Scene(root, 800, 400));
        PrimaryStage.show();



    }

}