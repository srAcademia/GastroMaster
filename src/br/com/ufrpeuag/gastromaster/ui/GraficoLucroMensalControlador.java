package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

import br.com.ufrpeuag.gastromaster.dados.RepositorioGerenciamentoContas;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GraficoLucroMensalControlador extends Application {

	private static Stage stage = new Stage();

	public void mostrarGrafico(LocalDate data) throws SQLException {
		
		RepositorioGerenciamentoContas gn = new RepositorioGerenciamentoContas();
		
		Map<String, Integer> example = gn.recuperarPorMes("2019");

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

		stage.setTitle("Vendas por mês");
		Scene scene = new Scene(vbox, 400, 200);

		stage.setScene(scene);
		stage.setHeight(300);
		stage.setWidth(400);

		stage.show();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		LocalDate data = LocalDate.now();
		primaryStage.setTitle("creating date picker");
		TilePane r = new TilePane();
		Label l = new Label(Fachada.getSingleton().mudarDataParaString(data));
		DatePicker d = new DatePicker();
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				LocalDate i = d.getValue();
				l.setText("Date :" + i);

			}
		};
		data = Fachada.getSingleton().mudarDataParaLocalDate(l.getText());
		d.setShowWeekNumbers(true);
		d.setOnAction(event);
		r.getChildren().add(d);
		r.getChildren().add(l);
		Scene sc = new Scene(r, 200, 200);
		primaryStage.setScene(sc);
		primaryStage.show();
		mostrarGrafico(data);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
