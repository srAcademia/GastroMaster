package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.interfaces.CardapioDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;


public class RepositorioCardapio implements CardapioDao {

	@Override
	public void inserir(Cardapio cardapio) {
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			String inserirSql = "INSERT INTO Cardapio(prato, preco) VALUES(?,?)";
			pstmt = conn.prepareStatement(inserirSql);

			pstmt.setString(1, cardapio.getPrato());
			pstmt.setDouble(2, cardapio.getPreco());

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
	public Cardapio recuperar(Integer codigo) {
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement("SELECT * from Cardapio where id_cardapio = ?");

			pstmt.setInt(1, codigo);

			result = pstmt.executeQuery();

			Cardapio c = null;

			if (result.next()) {

				c = new Cardapio();
				c.setId_cardapio(result.getInt("id_cardapio"));
				c.setPrato(result.getString("prato"));
				c.setPreco(result.getDouble("preco"));
				return c;
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
	public void alterar(Cardapio cardapio) {
		String alterarSql = "UPDATE Cardapio SET " + "prato = ? , " + "preco = ? " + " WHERE id_cardapio = ?";

		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement(alterarSql);

			pstmt.setString(1, cardapio.getPrato());
			pstmt.setDouble(2, cardapio.getPreco());
			pstmt.setInt(3, cardapio.getId_cardapio());

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
	public void deletar(Cardapio cardapio) {
		String deletarSql = "DELETE FROM Cardapio WHERE id_cardapio = ?";
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement(deletarSql);

			pstmt.setInt(1, cardapio.getId_cardapio());
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
	public List<Cardapio> listarTodos() {
		List<Cardapio> listaCardapio = new ArrayList<>();
		String listarTodosSql = "Select * FROM Produto";
		ResultSet result = null;
		Statement stmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			stmt = conn.createStatement();
			result = stmt.executeQuery(listarTodosSql);

			Cardapio c = null;

			while (result.next()) {

				c = new Cardapio();

				c.setId_cardapio(result.getInt("id_cardapio"));
				c.setPrato(result.getString("prato"));
				c.setPreco(result.getDouble("preco"));
				listaCardapio.add(c);

			}
			return listaCardapio;

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

}
