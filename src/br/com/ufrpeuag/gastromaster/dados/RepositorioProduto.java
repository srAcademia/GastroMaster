package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.interfaces.IProdutoDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;

public class RepositorioProduto implements IProdutoDao {

	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet result;
	private Statement stmt;

	public RepositorioProduto() throws SQLException {
		this.conn = ConfiguracoesBanco.getSingleton().getConnection();
	}

	@Override
	public void inserir(Produto produto) {
		String inserirSql = "INSERT INTO Produto(nome, quantidade,preco) VALUES(?,?,?)";

		try {

			pstmt = conn.prepareStatement(inserirSql);
			pstmt.setString(1, produto.getNome());
			pstmt.setInt(2, produto.getQuantidade());
			pstmt.setDouble(3, produto.getPreco());

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
	public Produto recuperar(Integer codigo) {
		Produto p = null;

		String sqlRecuperar = "SELECT * from Produto where id_produto = ?;";

		try {

			pstmt = conn.prepareStatement(sqlRecuperar);
			pstmt.setInt(1, codigo);

			result = pstmt.executeQuery();

			if (result.next()) {

				p = new Produto();
				p.setId_produto(result.getInt("id_produto"));
				p.setNome(result.getString("nome"));
				p.setQuantidade(result.getInt("quantidade"));
				p.setPreco(result.getDouble("preco"));
				return p;
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
	public void alterar(Produto produto) {
		String alterarSql = "UPDATE Produto SET " + "nome = ? , " + "quantidade = ?," + "preco = ? "
				+ " WHERE id_produto = ?";
		try {

			pstmt = conn.prepareStatement(alterarSql);

			pstmt.setString(1, produto.getNome());
			pstmt.setInt(2, produto.getQuantidade());
			pstmt.setDouble(3, produto.getPreco());
			pstmt.setInt(4, produto.getId_produto());

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
	public void deletar(Produto produto) {
		String deletarSql = "DELETE FROM Produto WHERE id_produto = ?";

		try {
			pstmt = conn.prepareStatement(deletarSql);

			pstmt.setInt(1, produto.getId_produto());
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
	public List<Produto> listarTodos() {
		Produto p = null;

		List<Produto> lista = new ArrayList<>();
		String listarTodosSql = "Select * FROM Produto";

		try {

			stmt = conn.createStatement();
			result = stmt.executeQuery(listarTodosSql);

			while (result.next()) {

				p = new Produto();

				p.setId_produto(result.getInt("id_produto"));
				p.setNome(result.getString("nome"));
				p.setQuantidade(result.getInt("quantidade"));
				p.setPreco(result.getDouble("preco"));
				lista.add(p);

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
	public void removerQuantProduto(Produto produto, Integer quantidade) {
		String alterarSql = "UPDATE Produto SET quantidade = ? WHERE id_produto = ?";

		try {

			pstmt = conn.prepareStatement(alterarSql);

			pstmt.setInt(1, produto.getQuantidade() - quantidade);
			pstmt.setInt(2, produto.getId_produto());

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
	public int retornarQuantidadeProduto(Produto produto) {

		int quant = 0;
		String sql = "SELECT quantidade FROM Produto WHERE id_produto= ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, produto.getId_produto());

			result = pstmt.executeQuery();

			if (result != null) {
				if (result.next()) {
					quant = result.getInt("quantidade");
					return quant;
				}
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
		return 0;

	}

	@Override
	public Produto retornarProduto(String nome) {
		Produto p = null;

		String sql = "SELECT *  FROM Produto WHERE nome = ?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nome);

			result = pstmt.executeQuery();

			if (result.next()) {

				p = new Produto();
				p.setId_produto(result.getInt("id_produto"));
				p.setNome(result.getString("nome"));
				p.setQuantidade(result.getInt("quantidade"));
				p.setPreco(result.getDouble("preco"));
				return p;
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
	public void adicionarQuantProduto(Produto produto, Integer quantidade) {
		String alterarSql = "UPDATE Produto SET quantidade = ? WHERE id_produto = ?";
		try {

			pstmt = conn.prepareStatement(alterarSql);

			pstmt.setInt(1, produto.getQuantidade() + quantidade);
			pstmt.setInt(2, produto.getId_produto());

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
	public int retornarID(String nome) {

		String sql = "SELECT *  FROM Produto WHERE nome = ?";

		int id = 0;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nome);

			result = pstmt.executeQuery();

			if (result != null) {
				if (result.next()) {
					id = result.getInt("id_produto");

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

}
