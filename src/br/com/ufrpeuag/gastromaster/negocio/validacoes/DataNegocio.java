package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Data;

public class DataNegocio {
	
	public LocalDate ValidarData(String data) throws DataNascimentoInvalidaException {
		try {
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			 sdf.setLenient(false);
	         sdf.parse(data);
	         return Data.mudarDataParaLocalDate(data);
	     }catch(ParseException ex) {
	    	 throw new DataNascimentoInvalidaException();
	     }
	}

}