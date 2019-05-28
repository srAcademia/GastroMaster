package br.com.ufrpeuag.gastromaster.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TratarData {
	public static void main(String[] args) {
		 
		try {
			 	String data = "30/02/2016";
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            sdf.setLenient(false);
	            sdf.parse(data);
	            System.out.println("valida");
	        
	        } catch (ParseException ex) {
	        	System.out.println("Não valida");
	            
	        }
	}

}
