package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Data {

	public Data() {

	}

	public LocalDate mudarDataParaLocalDate(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(data, formatter);
		return localDate;
	}

	public String mudarDataParaString(LocalDate data) {
		java.sql.Date date = java.sql.Date.valueOf(data);
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formatador.format(date);
		return dataFormatada;
	}

}
