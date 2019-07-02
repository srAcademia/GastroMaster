package br.com.ufrpeuag.gastromaster.ui;

import java.time.LocalDate;
import java.util.Map;

import br.com.ufrpeuag.gastromaster.dados.RepositorioGerenciamentoContas;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraficoLucroDiarioControlador extends Application {
	
	public GraficoLucroDiarioControlador (String ano, String mes) {
		TelaRelatoriosControlador.setAno2(ano);
		TelaRelatoriosControlador.setMes2(mes);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		RepositorioGerenciamentoContas gn = new RepositorioGerenciamentoContas();
		LocalDate data = LocalDate.now();

		Map<String, Integer> example = gn.recuperarPorDia("jul","2019");

		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Dias ");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Valor Total R$");

		
		BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

		
		XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();

		
		for (String key : example.keySet()) {
			Integer value = example.get(key);
			dataSeries1.getData().add(new XYChart.Data<String, Number>(key, value));
		}

		barChart.getData().add(dataSeries1);

		barChart.setTitle("Vendas por dia");
		barChart.setLegendSide(null);
	       
		VBox vbox = new VBox(barChart);

		primaryStage.setTitle("Vendas por dia");
		Scene scene = new Scene(vbox, 400, 200);

		primaryStage.setScene(scene);
		primaryStage.setHeight(300);
		primaryStage.setWidth(400);

		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
