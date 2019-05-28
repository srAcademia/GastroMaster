package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.interfaces.IEnderecoDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;

public class RepositorioEndereco implements IEnderecoDao {

	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet result;
	private Statement stmt;

	public RepositorioEndereco() throws SQLException {
		this.conn = ConfiguracoesBanco.getSingleton().getConnection();

	}

	@Override
	public void inserir(Endereco end) {
		String inserirSql = "INSERT INTO Endereco (cidade, bairro, rua, numero, cep) VALUES(?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(inserirSql);

			pstmt.setString(1, end.getCidade());
			pstmt.setString(2, end.getBairro());
			pstmt.setString(3, end.getRua());
			pstmt.setInt(4, end.getNumero());
			pstmt.setString(5, end.getCep());
			pstmt.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {

			try {
				pstmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

	}

	@Override
	public Endereco recuperar(Integer codigo) {
		Endereco e = null;
		String sqlRecuperar = "SELECT * from Endereco where id_endereco = ?;";
		try {
			pstmt = conn.prepareStatement(sqlRecuperar);
			pstmt.setInt(1, codigo);
			result = pstmt.executeQuery();

			if (result.next()) {

				e = new Endereco();
				e.setId_endereco(result.getInt("id_endereco"));
				e.setCidade(result.getString("cidade"));
				e.setBairro(result.getString("Bairro"));
				e.setRua(result.getString("rua"));
				e.setNumero(result.getInt("numero"));
				e.setCep(result.getString("cep"));

				return e;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {

			try {
				result.close();
				pstmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}

		}
		return null;
	}

	@Override
	public void alterar(Endereco end) {
		String alterarSql = "UPDATE Endereco SET " + "cidade = ? , " + "bairro = ?, " + "rua = ?," + "numero = ?,"
				+ "cep = ?" + " WHERE id_endereco = ?;";

		try {
			pstmt = conn.prepareStatement(alterarSql);

			pstmt.setString(1, end.getCidade());
			pstmt.setString(2, end.getBairro());
			pstmt.setString(3, end.getRua());
			pstmt.setInt(4, end.getNumero());
			pstmt.setString(5, end.getCep());
			pstmt.setInt(6, end.getId_endereco());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			try {
				pstmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	@Override
	public void deletar(Endereco end) {
		String deletarSql = "DELETE FROM Endereco WHERE id_endereco = ?";
		try {

			pstmt = conn.prepareStatement(deletarSql);

			pstmt.setInt(1, end.getId_endereco());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {

			try {
				pstmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	@Override
	public List<Endereco> listarTodos() {
		List<Endereco> lista = new ArrayList<>();

		Endereco e = null;

		String listarTodosSql = "Select * FROM Endereco";
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			stmt = conn.createStatement();

			result = stmt.executeQuery(listarTodosSql);

			while (result.next()) {

				e = new Endereco();

				e.setId_endereco(result.getInt("id_endereco"));
				e.setCidade(result.getString("cidade"));
				e.setBairro(result.getString("bairro"));
				e.setRua(result.getString("rua"));
				e.setNumero(result.getInt("numero"));
				e.setCep(result.getString("cep"));

				lista.add(e);

			}
			return lista;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());

		} finally {

			try {
				result.close();
				stmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}

		}

		return null;
	}

	@Override
	public int recuperarUltimoID() {
		String recuperarUltimoIdSql = "SELECT *FROM Endereco WHERE id_endereco= (SELECT MAX(id_endereco) FROM Endereco);";

		int id = 0;
		try {
			pstmt = conn.prepareStatement(recuperarUltimoIdSql);
			result = pstmt.executeQuery();

			if (result != null) {
				if (result.next()) {
					id = (result.getInt("id_endereco"));
				}
			}

			return id;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				result.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return 0;
	}
}
