package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.interfaces.IMesaDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;

public class RepositorioMesa implements IMesaDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet result;
	private Statement stmt;

	public RepositorioMesa() throws SQLException {
		conn = ConfiguracoesBanco.getSingleton().getConnection();
	}

	@Override
	public void inserir(Mesa mesa) {
		String inserirSql = "INSERT INTO Mesa(numero, disponibilidade) VALUES(?,?)";

		try {
			pstmt = conn.prepareStatement(inserirSql);

			pstmt.setInt(1, mesa.getNumero());
			pstmt.setInt(2, mesa.getDisponibilidade());

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
	public Mesa recuperar(Integer codigo) {
		Mesa m = null;

		String sqlRecuperar = "SELECT * from Mesa where id_mesa = ?";

		try {

			pstmt = conn.prepareStatement(sqlRecuperar);
			pstmt.setInt(1, codigo);

			result = pstmt.executeQuery();

			if (result.next()) {

				m = new Mesa();
				m.setId_mesa(result.getInt("id_mesa"));
				m.setNumero(result.getInt("numero"));
				m.setDisponibilidade(result.getInt("disponibilidade"));
				return m;
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
	public void alterar(Mesa mesa) {
		String alterarSql = "UPDATE Mesa SET numero = ? , disponibilidade = ? WHERE id_mesa = ?";

		try {

			pstmt = conn.prepareStatement(alterarSql);

			pstmt.setInt(1, mesa.getNumero());
			pstmt.setInt(2, mesa.getDisponibilidade());
			pstmt.setInt(3, mesa.getId_mesa());

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
	public void deletar(Mesa mesa) {
		String deletarSql = "DELETE FROM Mesa WHERE id_mesa = ?";

		try {

			pstmt = conn.prepareStatement(deletarSql);

			pstmt.setInt(1, mesa.getId_mesa());
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
	public List<Mesa> listarTodos() {
		Mesa m = null;
		List<Mesa> lista = new ArrayList<>();
		String listarTodosSql = "Select * FROM Mesa";

		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(listarTodosSql);

			while (result.next()) {

				m = new Mesa();

				m.setId_mesa(result.getInt("id_mesa"));
				m.setNumero(result.getInt("numero"));
				m.setDisponibilidade(result.getInt("disponibilidade"));
				lista.add(m);

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
	public Mesa recuperarNumeroMesa(Integer numero) {

		Mesa m = null;

		String sqlRecuperarNumMesa = "SELECT * from Mesa where numero = ? ;";
		try {
			pstmt = conn.prepareStatement(sqlRecuperarNumMesa);
			pstmt.setInt(1, numero);

			result = pstmt.executeQuery();

			if (result.next()) {

				m = new Mesa();
				m.setId_mesa(result.getInt("id_mesa"));
				m.setNumero(result.getInt("numero"));
				m.setDisponibilidade(result.getInt("disponibilidade"));
				return m;
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
	public void mudarDisponibilidade(Mesa mesa) {
		String alterarSql = "UPDATE Mesa SET  disponibilidade = ? " + " WHERE id_mesa = ?";

		try {

			pstmt = conn.prepareStatement(alterarSql);

			if (mesa.getDisponibilidade() == 1) {
				pstmt.setInt(1, 0);
				pstmt.setInt(2, mesa.getId_mesa());
				pstmt.executeUpdate();
			} else {
				pstmt.setInt(1, 1);
				pstmt.setInt(2, mesa.getId_mesa());
				pstmt.executeUpdate();
			}

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

}
