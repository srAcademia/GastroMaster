package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConfiguracoesBanco {

	private final String url = "jdbc:sqlite:./banco/banco.db";

	private static ConfiguracoesBanco singleton = null;
	private Connection conn;

	public static ConfiguracoesBanco getSingleton() throws SQLException {
		if (singleton == null) {
			singleton = new ConfiguracoesBanco();
		}
		return singleton;
	}
	private void criarBanco() throws SQLException {
		String sql = " ";
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
	}

	private Connection connect() throws SQLException {
		this.conn = DriverManager.getConnection(url);
		return conn;
	}
	
	private ConfiguracoesBanco() throws SQLException {
		this.connect();
		this.criarBanco();
	}

	public Connection getConnection() {
		return this.conn;
	}
}
