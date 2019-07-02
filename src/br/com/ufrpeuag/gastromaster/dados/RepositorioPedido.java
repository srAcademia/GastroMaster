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
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet result;
	private Statement stmt;

	public RepositorioPedido() throws SQLException {
		this.conn = ConfiguracoesBanco.getSingleton().getConnection();
	}

	@Override
	public void inserir(Pedido pedido) {
		String inserirSql = "INSERT INTO Pedido (cod_produto, cod_cardapio, valor,cod_mesa) VALUES(?,?,?,?)";

		try {

			pstmt = conn.prepareStatement(inserirSql);

			if (pedido.getProduto() == null) {
				pstmt.setInt(1, 0);
				pstmt.setInt(2, pedido.getCardapio().getId_cardapio());
			} else if (pedido.getCardapio() == null) {
				pstmt.setInt(1, pedido.getProduto().getId_produto());
				pstmt.setInt(2, 0);
			} else {

				pstmt.setInt(1, pedido.getProduto().getId_produto());
				pstmt.setInt(2, pedido.getCardapio().getId_cardapio());
			}

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

		Cardapio c = null;
		Produto p = null;
		Pedido pedido = null;
		Mesa m = null;

		String recuperarCodCardapio = "SELECT cod_cardapio FROM Pedido where id_pedido = ?  and cod_cardapio = 0;";
		int idCardapio = 1;
		try {

			pstmt = conn.prepareStatement(recuperarCodCardapio);
			pstmt.setInt(1, codigo);
			result = pstmt.executeQuery();

			if (result != null) {
				if (result.next()) {
					idCardapio = result.getInt("cod_cardapio");

				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {

			try {
				result.close();
				pstmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}

		}

		String recuperarCodProduto = "SELECT cod_produto FROM Pedido where id_pedido = ?  and cod_produto = 0;";
		int idProduto = 1;
		try {

			pstmt = conn.prepareStatement(recuperarCodProduto);
			pstmt.setInt(1, codigo);
			result = pstmt.executeQuery();

			if (result != null) {
				if (result.next()) {
					idProduto = result.getInt("cod_produto");

				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {

			try {
				result.close();
				pstmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}

		}

		String id = "SELECT cod_cardapio, cod_produto FROM Pedido where id_pedido = ?  and cod_cardapio != 0 and cod_produto !=0 ;";
		int idCard = 0;
		int idProd = 0;
		try {

			pstmt = conn.prepareStatement(id);
			pstmt.setInt(1, codigo);
			result = pstmt.executeQuery();

			if (result != null) {
				if (result.next()) {
					idCard = result.getInt("cod_cardapio");
					idProd = result.getInt("cod_produto");
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {

			try {
				result.close();
				pstmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}

		}

		if (idCard != 0 && idProd != 0) {

			String sql = "select * \r\n" + "from Pedido pe join Produto pr on (pe.cod_produto = pr.id_produto) \r\n"
					+ "JOIN Cardapio c on (pe.cod_cardapio = c.id_cardapio) \r\n"
					+ "JOIN Mesa m on (pe.cod_mesa = m.id_mesa) \r\n" + "where pe.id_pedido = ? ;";

			try {

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, codigo);
				result = pstmt.executeQuery();

				if (result.next()) {

					c = new Cardapio();
					p = new Produto();
					pedido = new Pedido();
					m = new Mesa();

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

				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());

			} finally {

				try {
					result.close();
					pstmt.close();
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}

			}
		}
		if (idProduto == 0) {
			String sql = "SELECT * from Pedido pe\r\n" + "join cardapio c on pe.cod_cardapio = c.id_cardapio\r\n"
					+ "join mesa m on (pe.cod_mesa = m.id_mesa)\r\n" + "where pe.id_pedido = ?  and cod_produto = 0;";
			try {

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, codigo);
				result = pstmt.executeQuery();

				if (result.next()) {

					c = new Cardapio();
					pedido = new Pedido();
					m = new Mesa();

					pedido.setId_pedido(result.getInt("id_pedido"));

					c.setId_cardapio(result.getInt("Id_cardapio"));
					c.setPrato(result.getString("prato"));
					c.setPreco(result.getDouble("preco"));
					pedido.setCardapio(c);

					pedido.setProduto(null);

					pedido.setValor(result.getDouble("valor"));

					m.setId_mesa(result.getInt("id_mesa"));
					m.setNumero(result.getInt("numero"));
					m.setDisponibilidade(result.getInt("disponibilidade"));

					pedido.setMesa(m);

				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());

			} finally {

				try {
					result.close();
					pstmt.close();
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}

			}

		}
		if (idCardapio == 0) {
			String sql = "SELECT * " + "from Pedido pe join Produto p on pe.cod_produto = p.id_produto "
					+ "join mesa m on (pe.cod_mesa = m.id_mesa) " + "where pe.id_pedido = ? and pe.cod_cardapio = 0 ";

			try {

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, codigo);
				result = pstmt.executeQuery();

				if (result.next()) {

					p = new Produto();
					pedido = new Pedido();
					m = new Mesa();

					pedido.setId_pedido(result.getInt("id_pedido"));

					pedido.setCardapio(null);

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

				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());

			} finally {

				try {
					result.close();
					pstmt.close();
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}

			}

		}
		return pedido;
	}

	@Override
	public void alterar(Pedido pedido) {
		String alterarSql = "UPDATE Pedido SET cod_produto =?, cod_cardapio = ? , valor= ?, cod_mesa = ?  WHERE id_pedido = ?";
		try {

			pstmt = conn.prepareStatement(alterarSql);

			pstmt.setInt(1, pedido.getProduto().getId_produto());
			pstmt.setInt(2, pedido.getCardapio().getId_cardapio());
			pstmt.setDouble(3, pedido.getValor());
			pstmt.setInt(4, pedido.getMesa().getId_mesa());
			pstmt.setInt(5, pedido.getId_pedido());
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

		try {

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

		Cardapio c = null;
		Produto p = null;
		Pedido pedido = null;
		Mesa m = null;

		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(listarTodosSql);

			while (result.next()) {

				c = new Cardapio();
				p = new Produto();
				pedido = new Pedido();
				m = new Mesa();
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

		int id = 0;

		try {

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
		String recuperarUltimoIdSql = "SELECT *FROM Pedido WHERE id_pedido = (SELECT MAX(id_pedido) FROM Pedido);";
		int id = 0;
		try {

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
		try {

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

		Cardapio c = null;
		Produto p = null;
		Pedido pedido = null;
		Mesa m = null;
		List<Pedido> lista = new ArrayList<>();

		String recuperarCodCardapio = "SELECT cod_cardapio FROM Pedido where cod_mesa = ?  and cod_cardapio = 0;";
		int idCardapio = 1;
		try {

			pstmt = conn.prepareStatement(recuperarCodCardapio);
			pstmt.setInt(1, id_mesa);
			result = pstmt.executeQuery();

			if (result != null) {
				if (result.next()) {
					idCardapio = result.getInt("cod_cardapio");

				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {

			try {
				result.close();
				pstmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}

		}

		String recuperarCodProduto = "SELECT cod_produto FROM Pedido where cod_mesa = ?  and cod_produto = 0;";
		int idProduto = 1;
		try {

			pstmt = conn.prepareStatement(recuperarCodProduto);
			pstmt.setInt(1, id_mesa);
			result = pstmt.executeQuery();

			if (result != null) {
				if (result.next()) {
					idProduto = result.getInt("cod_produto");

				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {

			try {
				result.close();
				pstmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}

		}

		String id = "SELECT cod_cardapio, cod_produto FROM Pedido where cod_mesa = ?  and cod_cardapio != 0 and cod_produto !=0 ;";
		int idCard = 0;
		int idProd = 0;
		try {

			pstmt = conn.prepareStatement(id);
			pstmt.setInt(1, id_mesa);
			result = pstmt.executeQuery();

			if (result != null) {
				if (result.next()) {
					idCard = result.getInt("cod_cardapio");
					idProd = result.getInt("cod_produto");
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {

			try {
				result.close();
				pstmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}

		}

		if (idCard != 0 && idProd != 0) {

			String sql = "select * \r\n" + "from Pedido pe join Produto pr on (pe.cod_produto = pr.id_produto) \r\n"
					+ "JOIN Cardapio c on (pe.cod_cardapio = c.id_cardapio) \r\n"
					+ "JOIN Mesa m on (pe.cod_mesa = m.id_mesa) \r\n" + "where pe.cod_mesa = ? ;";

			try {

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id_mesa);
				result = pstmt.executeQuery();

				while (result.next()) {

					c = new Cardapio();
					p = new Produto();
					pedido = new Pedido();
					m = new Mesa();

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

			} catch (SQLException e) {
				System.out.println(e.getMessage());

			} finally {

				try {
					result.close();
					pstmt.close();
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}

			}
		}
		if (idProduto == 0) {

			String sql = "SELECT * from Pedido pe\r\n" + "join cardapio c on pe.cod_cardapio = c.id_cardapio\r\n"
					+ "join mesa m on (pe.cod_mesa = m.id_mesa)\r\n" + "where pe.cod_mesa = ?  and cod_produto = 0;";
			try {

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id_mesa);
				result = pstmt.executeQuery();

				while (result.next()) {

					c = new Cardapio();
					pedido = new Pedido();
					m = new Mesa();

					pedido.setId_pedido(result.getInt("id_pedido"));

					c.setId_cardapio(result.getInt("Id_cardapio"));
					c.setPrato(result.getString("prato"));
					c.setPreco(result.getDouble("preco"));
					pedido.setCardapio(c);

					pedido.setProduto(null);

					pedido.setValor(result.getDouble("valor"));

					m.setId_mesa(result.getInt("id_mesa"));
					m.setNumero(result.getInt("numero"));
					m.setDisponibilidade(result.getInt("disponibilidade"));

					pedido.setMesa(m);

					lista.add(pedido);

				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());

			} finally {

				try {
					result.close();
					pstmt.close();
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}

			}

		}
		if (idCardapio == 0) {
			String sql = "SELECT * " + "from Pedido pe join Produto p on pe.cod_produto = p.id_produto "
					+ "join mesa m on (pe.cod_mesa = m.id_mesa) " + "where pe.cod_mesa = ? and pe.cod_cardapio = 0 ";

			try {

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, id_mesa);
				result = pstmt.executeQuery();

				while (result.next()) {

					p = new Produto();
					pedido = new Pedido();
					m = new Mesa();

					pedido.setId_pedido(result.getInt("id_pedido"));

					pedido.setCardapio(null);

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
				;

			} catch (SQLException e) {
				System.out.println(e.getMessage());

			} finally {

				try {
					result.close();
					pstmt.close();
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}

			}

		}

		return lista;
	}

}
