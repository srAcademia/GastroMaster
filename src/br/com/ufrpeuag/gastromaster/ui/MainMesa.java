package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;

public class MainMesa {

	public static void main(String[] args) throws SQLException {
		ConfiguracoesBanco.getSingleton().getConnection();
		
	}

}
