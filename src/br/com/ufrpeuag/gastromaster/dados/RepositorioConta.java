package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.interfaces.ContaDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;

public class RepositorioConta implements ContaDao {

	private final Connection conn;

	public RepositorioConta() throws SQLException {
		conn = ConfiguracoesBanco.getSingleton().getConnection();
	}

	@Override
	public void inserir(Conta conta) {
		PreparedStatement pstmt = null;
		try {
			String inserirSql = "INSERT INTO  Conta(pagamento , data, cod_pedido , cod_garcom , cod_mesa, valor) VALUES(?,?,?,?,?,?)";
			pstmt = this.conn.prepareStatement(inserirSql);

			String data = "" + conta.getData();

			pstmt.setInt(1, conta.getPagamento());
			pstmt.setString(2, data);
			pstmt.setInt(3, conta.getPedido().getId_pedido());
			pstmt.setInt(4, conta.getGarcom().getId_garcom());
			pstmt.setInt(5, conta.getMesa().getId_mesa());
			pstmt.setDouble(6, conta.getValor());
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
	public Conta recuperar(Integer codigo) {
		String sql = "SELECT *\r\n" + "FROM Conta c join pedido p on (c.cod_pedido=p.id_pedido) \r\n"
				+ "JOIN Cardapio card on (p.cod_cardapio=card.id_cardapio)  \r\n"
				+ "JOIN Produto prod on(p.cod_produto =Prod.id_produto) \r\n"
				+ "JOIN Garcom g on (c.cod_garcom = g.id_garcom)\r\n"
				+ "JOIN Endereco e on (g.cod_endereco=e.id_endereco)\r\n"
				+ "JOIN Mesa m on ( c.cod_mesa =m.id_mesa )\r\n" + "WHERE id_conta = ?";

		PreparedStatement pstmt = null;
		ResultSet result = null;
		Conta c = null;
		Mesa m = null;
		Pedido pedido = null;
		Garcom g = null;
		Produto prod = null;
		Cardapio card = null;
		Endereco e = null;
		String data;
		try {

			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, codigo);

			result = pstmt.executeQuery();

			if (result.next()) {

				c = new Conta();
				m = new Mesa();
				pedido = new Pedido();
				g = new Garcom();
				card = new Cardapio();
				prod = new Produto();
				e = new Endereco();

				data = result.getString("data");
				LocalDate localDate = LocalDate.parse(data);
				c.setId_conta(result.getInt("id_conta"));
				c.setPagamento(result.getInt("pagamento"));
				c.setData(localDate);

				// Pedido
				pedido.setId_pedido(result.getInt("id_pedido"));

				card.setId_cardapio(result.getInt("Id_cardapio"));
				card.setPrato(result.getString("prato"));
				card.setPreco(result.getDouble("preco"));
				pedido.setCardapio(card);

				prod.setId_produto(result.getInt("id_produto"));
				prod.setNome(result.getString("nome"));
				prod.setQuantidade(result.getInt("quantidade"));
				prod.setPreco(result.getDouble("preco"));
				pedido.setProduto(prod);
				c.setPedido(pedido);

				// Garcom
				g.setId_garcom(result.getInt("id_garcom"));
				g.setNome(result.getString("nome"));
				g.setCpf(result.getString("cpf"));
				g.setDataNasc(result.getString("dataNasc"));
				g.setTelefone(result.getString("telefone"));
				g.setEmail(result.getString("email"));
				g.setSalario(result.getDouble("salario"));
				e.setId_endereco(result.getInt("id_endereco"));
				e.setCidade(result.getString("cidade"));
				e.setBairro(result.getString("Bairro"));
				e.setRua(result.getString("rua"));
				e.setNumero(result.getInt("numero"));
				e.setCep(result.getString("cep"));
				g.setEndereco(e);

				c.setGarcom(g);

				// Mesa
				m.setId_mesa(result.getInt("id_mesa"));
				m.setNumero(result.getInt("numero"));
				m.setDisponibilidade(result.getInt("disponibilidade"));

				c.setMesa(m);

				c.setValor(result.getDouble("valor"));

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
	public void alterar(Conta conta) {
		String alterarSql = "UPDATE Conta SET pagamento= ? , data= ? , "
				+ "cod_pedido= ? , cod_garcom= ? , cod_mesa= ?," + " valor= ?  WHERE id_conta = ?";
		PreparedStatement pstmt = null;

		try {

			pstmt = this.conn.prepareStatement(alterarSql);
			String data = "" + conta.getData();

			pstmt.setInt(1, conta.getPagamento());
			pstmt.setString(2, data);
			pstmt.setInt(3, conta.getPedido().getId_pedido());
			pstmt.setInt(4, conta.getGarcom().getId_garcom());
			pstmt.setInt(5, conta.getMesa().getId_mesa());
			pstmt.setDouble(6, conta.getValor());
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
	public void deletar(Conta conta) {
		String deletarSql = "DELETE FROM Conta WHERE cod_mesa = ?";
		PreparedStatement pstmt = null;
		try {

			pstmt = this.conn.prepareStatement(deletarSql);

			pstmt.setInt(1, conta.getMesa().getId_mesa());
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
	public List<Conta> listarTodos() {
		List<Conta> lista = new ArrayList<>();
		String listarTodosSql = "SELECT *\r\n" + "FROM Conta c join pedido p on (c.cod_pedido=p.id_pedido) \r\n"
				+ "JOIN Cardapio card on (p.cod_cardapio=card.id_cardapio)  \r\n"
				+ "JOIN Produto prod on(p.cod_produto =Prod.id_produto) \r\n"
				+ "JOIN Garcom g on (c.cod_garcom = g.id_garcom)\r\n"
				+ "JOIN Endereco e on (g.cod_endereco=e.id_endereco)\r\n"
				+ "JOIN Mesa m on ( c.cod_mesa =m.id_mesa )\r\n";

		ResultSet result = null;
		Statement stmt = null;

		Conta c = null;
		Mesa m = null;
		Pedido pedido = null;
		Garcom g = null;
		Produto prod = null;
		Cardapio card = null;
		Endereco e = null;
		String data;
		try {

			stmt = this.conn.createStatement();
			result = stmt.executeQuery(listarTodosSql);

			while (result.next()) {

				c = new Conta();
				m = new Mesa();
				pedido = new Pedido();
				g = new Garcom();
				card = new Cardapio();
				prod = new Produto();
				e = new Endereco();

				data = result.getString("data");
				LocalDate localDate = LocalDate.parse(data);
				c.setId_conta(result.getInt("id_conta"));
				c.setPagamento(result.getInt("pagamento"));
				c.setData(localDate);

				// Pedido
				pedido.setId_pedido(result.getInt("id_pedido"));

				card.setId_cardapio(result.getInt("Id_cardapio"));
				card.setPrato(result.getString("prato"));
				card.setPreco(result.getDouble("preco"));
				pedido.setCardapio(card);

				prod.setId_produto(result.getInt("id_produto"));
				prod.setNome(result.getString("nome"));
				prod.setQuantidade(result.getInt("quantidade"));
				prod.setPreco(result.getDouble("preco"));
				pedido.setProduto(prod);
				c.setPedido(pedido);

				// Garcom
				g.setId_garcom(result.getInt("id_garcom"));
				g.setNome(result.getString("nome"));
				g.setCpf(result.getString("cpf"));
				g.setDataNasc(result.getString("dataNasc"));
				g.setTelefone(result.getString("telefone"));
				g.setEmail(result.getString("email"));
				g.setSalario(result.getDouble("salario"));
				e.setId_endereco(result.getInt("id_endereco"));
				e.setCidade(result.getString("cidade"));
				e.setBairro(result.getString("Bairro"));
				e.setRua(result.getString("rua"));
				e.setNumero(result.getInt("numero"));
				e.setCep(result.getString("cep"));
				g.setEndereco(e);

				c.setGarcom(g);

				// Mesa
				m.setId_mesa(result.getInt("id_mesa"));
				m.setNumero(result.getInt("numero"));
				m.setDisponibilidade(result.getInt("disponibilidade"));

				c.setMesa(m);

				c.setValor(result.getDouble("valor"));
				lista.add(c);
				return lista;
			}
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
	public double fecharConta(Conta conta) {
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String sql = "SELECT *\r\n" + "FROM Conta\r\n" + "WHERE cod_mesa = ? ;";

		double valor = 0;
		try {

			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, conta.getMesa().getId_mesa());

			result = pstmt.executeQuery();
			while (result.next()) {
				valor += (result.getDouble("valor"));

			}
			return valor;

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
	public Conta recuperarPorMesa(Integer codigo) {
		String sql = "SELECT *\r\n" + "FROM Conta c join pedido p on (c.cod_pedido=p.id_pedido) \r\n"
				+ "JOIN Cardapio card on (p.cod_cardapio=card.id_cardapio)  \r\n"
				+ "JOIN Produto prod on(p.cod_produto =Prod.id_produto) \r\n"
				+ "JOIN Garcom g on (c.cod_garcom = g.id_garcom)\r\n"
				+ "JOIN Endereco e on (g.cod_endereco=e.id_endereco)\r\n"
				+ "JOIN Mesa m on ( c.cod_mesa =m.id_mesa )\r\n" + "WHERE cod_mesa = ?";

		PreparedStatement pstmt = null;
		ResultSet result = null;
		Conta c = null;
		Mesa m = null;
		Pedido pedido = null;
		Garcom g = null;
		Produto prod = null;
		Cardapio card = null;
		Endereco e = null;
		String data;
		try {

			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, codigo);

			result = pstmt.executeQuery();

			while (result.next()) {

				c = new Conta();
				m = new Mesa();
				pedido = new Pedido();
				g = new Garcom();
				card = new Cardapio();
				prod = new Produto();
				e = new Endereco();

				data = result.getString("data");
				LocalDate localDate = LocalDate.parse(data);
				c.setId_conta(result.getInt("id_conta"));
				c.setPagamento(result.getInt("pagamento"));
				c.setData(localDate);

				// Pedido
				pedido.setId_pedido(result.getInt("id_pedido"));

				card.setId_cardapio(result.getInt("Id_cardapio"));
				card.setPrato(result.getString("prato"));
				card.setPreco(result.getDouble("preco"));
				pedido.setCardapio(card);

				prod.setId_produto(result.getInt("id_produto"));
				prod.setNome(result.getString("nome"));
				prod.setQuantidade(result.getInt("quantidade"));
				prod.setPreco(result.getDouble("preco"));
				pedido.setProduto(prod);
				c.setPedido(pedido);

				// Garcom
				g.setId_garcom(result.getInt("id_garcom"));
				g.setNome(result.getString("nome"));
				g.setCpf(result.getString("cpf"));
				g.setDataNasc(result.getString("dataNasc"));
				g.setTelefone(result.getString("telefone"));
				g.setEmail(result.getString("email"));
				g.setSalario(result.getDouble("salario"));
				e.setId_endereco(result.getInt("id_endereco"));
				e.setCidade(result.getString("cidade"));
				e.setBairro(result.getString("Bairro"));
				e.setRua(result.getString("rua"));
				e.setNumero(result.getInt("numero"));
				e.setCep(result.getString("cep"));
				g.setEndereco(e);

				c.setGarcom(g);

				// Mesa
				m.setId_mesa(result.getInt("id_mesa"));
				m.setNumero(result.getInt("numero"));
				m.setDisponibilidade(result.getInt("disponibilidade"));

				c.setMesa(m);

				c.setValor(result.getDouble("valor"));

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
	public void concluirPagamento(Conta conta) {
		String alterarSql = "UPDATE Conta SET pagamento = 1  WHERE id_conta = ?";
		PreparedStatement pstmt = null;

		try {

			pstmt = this.conn.prepareStatement(alterarSql);

			pstmt.setInt(1, conta.getPagamento());
			pstmt.setInt(2, conta.getId_conta());
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
