package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.interfaces.EnderecoDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;

public class RepositorioEndereco implements EnderecoDao {

	@Override
	public void inserir(Endereco end) {
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			String inserirSql = "INSERT INTO Endereco (cidade, bairro, rua, numero, cep) VALUES(?,?,?,?,?)";
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
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			pstmt = conn.prepareStatement("SELECT * from Endereco where id_endereco = ?;");
			pstmt.setInt(1, codigo);
			result = pstmt.executeQuery();
			Endereco e = null;
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
		PreparedStatement pstmt = null;
		String alterarSql = "UPDATE Endereco SET " + "cidade = ? , " + "bairro = ?, " + "rua = ?," + "numero = ?,"
				+ "cep = ?" + " WHERE id_endereco = ?;";

		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
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
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

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
		String listarTodosSql = "Select * FROM Endereco";
		ResultSet result = null;
		Statement stmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			stmt = conn.createStatement();

			result = stmt.executeQuery(listarTodosSql);

			Endereco e = null;

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

		} catch (SQLException e) {
			System.out.println(e.getMessage());

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
		Connection conn;
		int id = 0;
		try {
			conn = ConfiguracoesBanco.getSingleton().getConnection();
			String recuperarUltimoIdSql = "SELECT *FROM Endereco WHERE id_endereco= (SELECT MAX(id_endereco) FROM Endereco);";
			PreparedStatement pstmt = conn.prepareStatement(recuperarUltimoIdSql);
			ResultSet rs = pstmt.executeQuery();

			if (rs != null) {
				if (rs.next()) {
					id = (rs.getInt("id_endereco"));
				}
			}
			rs.close();
			pstmt.close();

			return id;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}
}
