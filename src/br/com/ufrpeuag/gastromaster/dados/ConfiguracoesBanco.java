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

		String endereco = "CREATE TABLE IF NOT EXISTS Endereco("
				+ "id_endereco INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "cidade TEXT,"
				+ "bairro TEXT,"
				+ "rua TEXT,"
				+ "numero INTEGER,"
				+ "cep TEXT);";
		String garcom = "CREATE TABLE IF NOT EXISTS Garcom("
				+ "id_garcom INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "nome TEXT,"
				+ "cpf TEXT,"
				+ "dataNasc TEXT,"
				+ "telefone TEXT,"
				+ "email TEXT,"
				+ "salario REAL,"
				+ "cod_endereco INTEGER,"
				+ "CONSTRAINT fk_endereco FOREIGN KEY(cod_endereco) REFERENCES Endereco(id_endereco) ON DELETE CASCADE ON UPDATE CASCADE );";

		String gerente = "CREATE TABLE IF NOT EXISTS Gerente("
				+ "id_gerente INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "nome TEXT,"
				+ "cpf TEXT,"
				+ "dataNasc TEXT,"
				+ "telefone TEXT,"
				+ "email TEXT,"
				+ "salario REAL,"
				+ "senha TEXT,"
				+ "cod_endereco INTEGER,"
				+ "CONSTRAINT fk_endereco FOREIGN KEY(cod_endereco) REFERENCES Endereco(id_endereco) ON DELETE CASCADE ON UPDATE CASCADE );";

		Statement stmt = conn.createStatement();
		stmt.execute(endereco);
		stmt.execute(garcom);
		stmt.execute(gerente);
		

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
