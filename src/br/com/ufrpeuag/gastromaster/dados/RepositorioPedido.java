package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.interfaces.IPedidoDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;

public class RepositorioPedido implements IPedidoDao {

	@Override
	public void inserir(Pedido pedido) {
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			String inserirSql = "INSERT INTO Pedido (cod_produto, cod_cardapio, valor,cod_mesa) VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(inserirSql);

			pstmt.setInt(1, pedido.getProduto().getId_produto());
			pstmt.setInt(2, pedido.getCardapio().getId_cardapio());
			pstmt.setDouble(3, pedido.getValor());
			pstmt.setInt(4, pedido.getMesa().getId_mesa());
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
				+ "JOIN Cardapio c on (pe.cod_cardapio = c.id_cardapio) JOIN Mesa m on (pe.cod_mesa = m.id_mesa) "
				+ "where id_pedido = ?";

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
				Mesa m = new Mesa();

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

				m.setId_mesa(result.getInt("id_mesa"));
				m.setNumero(result.getInt("numero"));
				m.setDisponibilidade(result.getInt("disponibilidade"));

				pedido.setMesa(m);

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
		String alterarSql = "UPDATE Pedido SET cod_produto =?, cod_cardapio = ? , valor= ?, cod_mesa = ?  WHERE id_pedido = ?";
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement(alterarSql);

			pstmt.setInt(1, pedido.getProduto().getId_produto());
			pstmt.setInt(2, pedido.getCardapio().getId_cardapio());
			pstmt.setDouble(3, pedido.getValor());
			pstmt.setInt(4, pedido.getId_pedido());
			pstmt.setInt(4, pedido.getMesa().getId_mesa());

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
				+ "JOIN Cardapio c on (pe.cod_cardapio= c.id_cardapio) JOIN Mesa m on (pe.cod_mesa = m.id_mesa) ";

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
				Mesa m = new Mesa();
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

				m.setId_mesa(result.getInt("id_mesa"));
				m.setNumero(result.getInt("numero"));
				m.setDisponibilidade(result.getInt("disponibilidade"));

				pedido.setMesa(m);

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

	@Override
	public int retornarId(Integer id_cardapio, Integer id_produto, Integer id_mesa) {

		String sql = "SELECT *  FROM Pedido WHERE (cod_cardapio= ? ) and (cod_produto = ? ) and (cod_mesa = ? )";
		PreparedStatement pstmt = null;
		ResultSet result = null;
		int id = 0;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id_cardapio);
			pstmt.setInt(2, id_produto);
			pstmt.setInt(3, id_mesa);

			result = pstmt.executeQuery();

			if (result != null) {
				if (result.next()) {
					id = result.getInt("id_pedido");

				}
			}
			return id;

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

		return 0;
	}

	@Override
	public int recuperarUltimoID() {
		String recuperarUltimoIdSql = "SELECT *FROM Pedido WHERE id_pedido= (SELECT MAX(id_pedido) FROM Pedido);";
		PreparedStatement pstmt = null;
		ResultSet result = null;
		int id = 0;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement(recuperarUltimoIdSql);

			result = pstmt.executeQuery();

			if (result != null) {
				if (result.next()) {
					id = result.getInt("id_pedido");

				}
			}
			return id;

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

		return 0;
	}

	@Override
	public void deletarPedidosPelaMesa(Integer id_mesa) {
		String deletarSql = "DELETE FROM Pedido WHERE cod_mesa = ?";
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement(deletarSql);

			pstmt.setInt(1, id_mesa);
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
	public List<Pedido> listarPorMesa(Integer id_mesa) {
		List<Pedido> lista = new ArrayList<>();
		String sql = "select * \r\n" + "from Pedido pe join Produto pr on (pe.cod_produto = pr.id_produto) \r\n"
				+ "JOIN Cardapio c on (pe.cod_cardapio = c.id_cardapio) \r\n"
				+ "JOIN Mesa m on (pe.cod_mesa = m.id_mesa) \r\n" + "where pe.cod_mesa = ? ;";

		ResultSet result = null;
		Statement stmt = null;

		Cardapio c = null;
		Produto p = null;
		Pedido pedido = null;

		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			stmt = conn.createStatement();
			result = stmt.executeQuery(sql);

			while (result.next()) {

				c = new Cardapio();
				p = new Produto();
				pedido = new Pedido();
				Mesa m = new Mesa();

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

				m.setId_mesa(result.getInt("id_mesa"));
				m.setNumero(result.getInt("numero"));
				m.setDisponibilidade(result.getInt("disponibilidade"));

				pedido.setMesa(m);

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
