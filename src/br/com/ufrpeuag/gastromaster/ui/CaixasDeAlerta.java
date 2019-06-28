package br.com.ufrpeuag.gastromaster.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CaixasDeAlerta {
	
	public static void CaixaErro(String titulo, String cabecalho, String mensagem) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setHeaderText(cabecalho);
		alert.setContentText(mensagem);
		alert.showAndWait();
	}
	public static void CaixaConcluido(String titulo, String mensagem) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setContentText(mensagem);
		alert.showAndWait();
	}
}
