package br.com.ufrpeuag.gastromaster.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class TelaRelatoriosControlador implements Initializable{
	
	@FXML
	private ComboBox<String> meses;
	@FXML
	private ComboBox<String> anos;
	@FXML
	private Button gerarGrafico;
    private ObservableList<String> listaObservabelAnos;
    private ObservableList<String> listaObservabelMeses;
    private static String ano2;
	private static String mes2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			listarAnos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listarAnos() throws SQLException {
		Map<String, Integer> mapaAnos = new HashMap<String, Integer>();
		mapaAnos = Fachada.getSingleton().recuperarPorAno();
		ArrayList<String> anosArray = new ArrayList<>(); 
		for (String key : mapaAnos.keySet()){ 
			anosArray.add(key);	
		}
		listaObservabelAnos = FXCollections.observableArrayList(anosArray);
		anos.setItems(listaObservabelAnos);
    }
	public void listarMeses(String ano) throws SQLException {
		Map<String, Integer> mapaMeses = new HashMap<String, Integer>();
		mapaMeses = Fachada.getSingleton().recuperarPorMes(ano);
		ArrayList<String> mesesArray = new ArrayList<>(); 
		for (String key : mapaMeses.keySet()){ 
			mesesArray.add(key);	
		}
		listaObservabelMeses = FXCollections.observableArrayList(mesesArray);
		meses.setItems(listaObservabelMeses);
	}
	
	@FXML
	public void handleGerarGrafico(ActionEvent event) throws Exception {
		if(listaObservabelAnos != null && listaObservabelMeses != null) {
			GraficoLucroDiarioControlador gerarGraficoDias = new GraficoLucroDiarioControlador(ano2, mes2);
			gerarGraficoDias.start(new Stage());
		}
	}

	public static String getAno2() {
		return ano2;
	}

	public static void setAno2(String ano2) {
		TelaRelatoriosControlador.ano2 = ano2;
	}

	public static String getMes2() {
		return mes2;
	}

	public static void setMes2(String mes2) {
		TelaRelatoriosControlador.mes2 = mes2;
	}

}
