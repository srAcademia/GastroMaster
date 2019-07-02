package br.com.ufrpeuag.gastromaster.ui;

import java.util.Map;

import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraficoLucroMensalControlador extends Application {
	
	private String anoLucro;
	
	public GraficoLucroMensalControlador (String ano) {
		this.anoLucro = ano;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Map<String, Integer> example = Fachada.getSingleton().recuperarPorMes(anoLucro);

		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Mês ");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Valor Total R$");

		BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

		XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();

		for (String key : example.keySet()) {
			Integer value = example.get(key);
			dataSeries1.getData().add(new XYChart.Data<String, Number>(key, value));
		}

		barChart.getData().add(dataSeries1);

		barChart.setTitle("Vendas por mês");
		barChart.setLegendSide(null);

		VBox vbox = new VBox(barChart);

		primaryStage.setTitle("Vendas por mês");
		Scene scene = new Scene(vbox, 400, 200);

		primaryStage.setScene(scene);
		primaryStage.setHeight(300);
		primaryStage.setWidth(400);

		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public String getAnoLucro() {
		return anoLucro;
	}

	public void setAnoLucro(String anoLucro) {
		this.anoLucro = anoLucro;
	}

}
