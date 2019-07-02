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
	@FXML
	private Button gerarGraficoAnual;
	
    private ObservableList<String> listaObservabelAnos;
    private ObservableList<String> listaObservabelMeses;
    private static String ano2 = null;
	private static String mes2 = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			listarAnos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			listarMeses(null);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		anos.getSelectionModel().selectedItemProperty().addListener((observable, odlValue, newValeu) -> {
			try {
				listarMeses(newValeu);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		
	}
	
	public void listarAnos() throws SQLException {
		Map<String, Integer> mapaAnos = new HashMap<String, Integer>();
		ArrayList<String> anosArray = new ArrayList<>(); 
		mapaAnos = Fachada.getSingleton().recuperarPorAno();
		for (String key : mapaAnos.keySet()){ 
			anosArray.add(key);	
		}
		listaObservabelAnos = FXCollections.observableArrayList(anosArray);
		anos.setItems(listaObservabelAnos);
    }
	public void listarMeses(String ano) throws SQLException {
		Map<String, Integer> mapaMeses = new HashMap<String, Integer>();
		ArrayList<String> mesesArray = new ArrayList<>();
		mapaMeses = Fachada.getSingleton().recuperarPorMes(ano);
		for (String key : mapaMeses.keySet()){ 
			mesesArray.add(key);	
		}
		listaObservabelMeses = FXCollections.observableArrayList(mesesArray);
		meses.setItems(listaObservabelMeses);
	}
	
	@FXML
	public void handleGerarGrafico(ActionEvent event) throws Exception {
		if(anos.getSelectionModel().getSelectedItem() != null && meses.getSelectionModel().getSelectedItem() != null) {
			ano2 = anos.getSelectionModel().getSelectedItem().toString();
			mes2 = meses.getSelectionModel().getSelectedItem().toString();
			GraficoLucroDiarioControlador gerarGraficoDias = new GraficoLucroDiarioControlador(ano2, mes2);
			gerarGraficoDias.start(new Stage());
		}
		else if (anos.getSelectionModel().getSelectedItem() != null && meses.getSelectionModel().getSelectedItem() == null) {
			ano2 = anos.getSelectionModel().getSelectedItem().toString();
			GraficoLucroMensalControlador gerarGraficoMensal = new GraficoLucroMensalControlador(ano2);
			gerarGraficoMensal.start(new Stage());
		}

	}
	@FXML
	public void handleGerarGraficoAnual(ActionEvent event) throws Exception {
		if (anos.getItems().isEmpty() == false) {
			GraficoLucroAnualControlador gerarGraficoAnual = new GraficoLucroAnualControlador();
			gerarGraficoAnual.start(new Stage());
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
