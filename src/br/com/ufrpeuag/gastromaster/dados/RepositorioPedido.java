package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.interfaces.PedidoDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;

public class RepositorioPedido implements PedidoDao {

	@Override
	public void inserir(Pedido pedido) {
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			String inserirSql = "INSERT INTO Pedido (cod_produto, cod_cardapio, valor) VALUES(?,?,?)";
			pstmt = conn.prepareStatement(inserirSql);

			pstmt.setInt(1, pedido.getProduto().getId_produto());
			pstmt.setInt(2, pedido.getCardapio().getId_cardapio());
			pstmt.setDouble(3, pedido.getValor());
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
	public Pedido recuperar(Integer codigo) {

		String sqlRecuperar = "select * " + "from Pedido pe join Produto pr on (pe.cod_produto = pr.id_produto) "
				+ "JOIN Cardapio c on (pe.cod_cardapio= c.id_cardapio) " + "where id_pedido = ?";

		PreparedStatement pstmt = null;
		ResultSet result = null;

		Cardapio c = null;
		Produto p = null;
		Pedido pedido = null;

		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement(sqlRecuperar);
			pstmt.setInt(1, codigo);

			result = pstmt.executeQuery();

			if (result.next()) {

				c = new Cardapio();
				p = new Produto();
				pedido = new Pedido();

				pedido.setId_pedido(result.getInt("id_pedido"));

				c.setId_cardapio(result.getInt("Id_cardapio"));
				c.setPrato(result.getString("prato"));
				c.setPreco(result.getDouble("preco"));
				pedido.setCardapio(c);

				p.setId_produto(result.getInt("id_produto"));
				p.setNome(result.getString("nome"));
				p.setQuantidade(result.getInt("quantidade"));
				p.setPreco(result.getDouble("preco"));
				pedido.setProduto(p);

				pedido.setValor(result.getDouble("valor"));

				return pedido;
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
	public void alterar(Pedido pedido) {
		String alterarSql = "UPDATE Pedido SET cod_produto =?, cod_cardapio = ? , valor= ?  WHERE id_pedido = ?";
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement(alterarSql);

			pstmt.setInt(1, pedido.getProduto().getId_produto());
			pstmt.setInt(2, pedido.getCardapio().getId_cardapio());
			pstmt.setDouble(3, pedido.getValor());
			pstmt.setInt(4, pedido.getId_pedido());

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
	public void deletar(Pedido pedido) {
		String deletarSql = "DELETE FROM Pedido WHERE id_pedido = ?";
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement(deletarSql);

			pstmt.setInt(1, pedido.getId_pedido());
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
	public List<Pedido> listarTodos() {
		List<Pedido> lista = new ArrayList<>();
		String listarTodosSql = "select * " + "from Pedido pe join Produto pr on (pe.cod_produto = pr.id_produto) "
				+ "JOIN Cardapio c on (pe.cod_cardapio= c.id_cardapio)";

		ResultSet result = null;
		Statement stmt = null;

		Cardapio c = null;
		Produto p = null;
		Pedido pedido = null;

		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			stmt = conn.createStatement();
			result = stmt.executeQuery(listarTodosSql);

			while (result.next()) {

				c = new Cardapio();
				p = new Produto();
				pedido = new Pedido();

				pedido.setId_pedido(result.getInt("id_pedido"));

				c.setId_cardapio(result.getInt("Id_cardapio"));
				c.setPrato(result.getString("prato"));
				c.setPreco(result.getDouble("preco"));
				pedido.setCardapio(c);

				p.setId_produto(result.getInt("id_produto"));
				p.setNome(result.getString("nome"));
				p.setQuantidade(result.getInt("quantidade"));
				p.setPreco(result.getDouble("preco"));
				pedido.setProduto(p);

				pedido.setValor(result.getDouble("valor"));

				lista.add(pedido);

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

}
