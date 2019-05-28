package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class ConfiguracoesBanco {

	private final String url = "jdbc:sqlite:./banco.db";

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
				+ "nome TEXT ,"
				+ "cpf TEXT ,"
				+ "dataNasc TEXT,"
				+ "telefone TEXT,"
				+ "email TEXT,"
				+ "salario REAL,"
				+ "identificador TEXT,"
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
				+ "identificador TEXT,"
				+ "cod_endereco INTEGER,"
				+ "CONSTRAINT fk_endereco FOREIGN KEY(cod_endereco) REFERENCES Endereco(id_endereco) ON DELETE CASCADE ON UPDATE CASCADE );";

		String produto ="CREATE TABLE IF NOT EXISTS Produto("
				+ "id_produto INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "nome TEXT,"
				+ "quantidade INTERGER,"
				+ "preco REAL);";

		String cardapio = "CREATE TABLE IF NOT EXISTS Cardapio("
				+ "id_cardapio INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "prato TEXT,"
				+ "preco REAL);";

		String mesa = "CREATE TABLE IF NOT EXISTS Mesa("
				+ "id_mesa INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "numero INTEGER,"
				+ "disponibilidade INTEGER"
				+ ");";
		
		String pedido = "CREATE TABLE IF NOT EXISTS Pedido("
				+ "id_pedido INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "cod_produto INTEGER,"
				+ "cod_cardapio INTEGER,"
				+ "valor REAL,"
				+ "cod_mesa INTEGER,  "
				+ " CONSTRAINT fk_produto FOREIGN KEY(cod_produto) REFERENCES Produto(id_produto)"
				+ " ON DELETE CASCADE ON UPDATE CASCADE "
				+ " CONSTRAINT fk_mesa FOREIGN KEY(cod_mesa) REFERENCES mesa(id_mesa)"
				+ " ON DELETE CASCADE ON UPDATE CASCADE "
				+ " CONSTRAINT fk_cardapio FOREIGN KEY(cod_cardapio) REFERENCES Cardapio(id_cardapio) "
				+ " ON DELETE CASCADE ON UPDATE CASCADE "
				+ ");";	

		String conta ="CREATE TABLE IF NOT EXISTS Conta("
				+ "id_conta INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "data TEXT,"
				+ "cod_pedido INTEGER,"
				+ "cod_garcom INTEGER,"
				+ "cod_mesa INTEGER,"
				+ "valor REAL,"
				+ "CONSTRAINT fk_pedido FOREIGN KEY(cod_pedido) REFERENCES Pedido(id_pedido)"
				+ " ON DELETE CASCADE ON UPDATE CASCADE "
				+ "CONSTRAINT fk_garcom  FOREIGN KEY(cod_garcom) REFERENCES Garcom(id_garcom)"
				+ " ON DELETE CASCADE ON UPDATE CASCADE "
				+ "CONSTRAINT fk_mesa FOREIGN KEY(cod_mesa) REFERENCES Mesa(id_mesa) "
				+ " ON DELETE CASCADE ON UPDATE CASCADE "
				+ ");";

		String gerenciamentoContas = "CREATE TABLE IF NOT EXISTS GerenciamentoContas("
				+ "id_gerenc INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "cod_garcom INTEGER,"
				+ "cod_mesa INTEGER,"
				+ "valorTotal REAL,"
				+ "data TEXT,"
				+ "CONSTRAINT fk_garcom  FOREIGN KEY(cod_garcom) REFERENCES Garcom(id_garcom)"
				+ " ON DELETE CASCADE ON UPDATE CASCADE "
				+ "CONSTRAINT fk_mesa FOREIGN KEY(cod_mesa) REFERENCES Mesa(id_mesa) "
				+ " ON DELETE CASCADE ON UPDATE CASCADE "
				+ ");";
				

		Statement stmt = conn.createStatement();
		stmt.execute(endereco);
		stmt.execute(garcom);
		stmt.execute(gerente);
		stmt.execute(produto);
		stmt.execute(cardapio);
		stmt.execute(mesa);
		stmt.execute(pedido);
		stmt.execute(conta);
		stmt.execute(gerenciamentoContas);

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
