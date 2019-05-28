package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataValidacao {
	public static boolean ValidarData(String data) {
		try {
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 sdf.setLenient(false);
	         sdf.parse(data);
	         return true;
	     }catch(ParseException ex) {
	    	 System.out.println("Data inválida.");
	     }
		 return false;
	}

}