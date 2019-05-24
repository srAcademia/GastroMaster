package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.interfaces.MesaDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;

public class RepositorioMesa implements MesaDao {

	@Override
	public void inserir(Mesa mesa) {
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			String inserirSql = "INSERT INTO Mesa(numero, disponibilidade) VALUES(?,?)";
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
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement("SELECT * from Mesa where id_mesa = ?");
			pstmt.setInt(1, codigo);

			result = pstmt.executeQuery();

			Mesa m = null;

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
		String alterarSql = "UPDATE Mesa SET " + "numero = ? , " + "disponibilidade = ? " + " WHERE id_mesa = ?";
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

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
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

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
		List<Mesa> lista = new ArrayList<>();
		String listarTodosSql = "Select * FROM Mesa";
		ResultSet result = null;
		Statement stmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			stmt = conn.createStatement();
			result = stmt.executeQuery(listarTodosSql);
			Mesa m = null;
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
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement("SELECT * from Mesa where numero = ?");
			pstmt.setInt(1, numero);

			result = pstmt.executeQuery();

			Mesa m = null;

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
		String alterarSql = "UPDATE Mesa SET  disponibilidade = 0 " + " WHERE id_mesa = ?";
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement(alterarSql);

			pstmt.setInt(1, mesa.getDisponibilidade());
			pstmt.setInt(2, mesa.getId_mesa());

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

}
