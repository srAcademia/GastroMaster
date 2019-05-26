package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.interfaces.GerenciamentoContasDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.GerenciamentoContas;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;

public class RepositorioGerenciamentoContas implements GerenciamentoContasDao {

	private final Connection conn;

	public RepositorioGerenciamentoContas() throws SQLException {
		conn = ConfiguracoesBanco.getSingleton().getConnection();
	}

	@Override
	public void inserir(GerenciamentoContas g) {
		PreparedStatement pstmt = null;
		try {
			String inserirSql = "INSERT INTO  Conta(cod_garcom , cod_mesa, valorTotal , data) VALUES(?,?,?,?)";
			pstmt = this.conn.prepareStatement(inserirSql);

			String data = "" + g.getData();

			pstmt.setInt(1, g.getGarcom().getId_garcom());
			pstmt.setInt(2, g.getMesa().getId_mesa());
			pstmt.setDouble(3, g.getValorTotal());
			pstmt.setString(4, data);

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
	public GerenciamentoContas recuperar(Integer codigo) {
		PreparedStatement pstmt = null;
		ResultSet result = null;
		Mesa m = null;
		Garcom g = null;
		Endereco e = null;
		GerenciamentoContas gc = null;
		String data;
		String sql = "SELECT * FROM GerenciamentoContas gc JOIN Garcom g"
				+ " ON (gc.cod_garcom = g.id_garcom)  JOIN  Mesa m ON (gc.cod_mesa = m.id_mesa) "
				+ "WHERE id_gerenc = ?";
		try {

			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, codigo);

			result = pstmt.executeQuery();

			if (result.next()) {

				gc = new GerenciamentoContas();
				m = new Mesa();
				g = new Garcom();
				e = new Endereco();
				data = result.getString("data");
				LocalDate localDate = LocalDate.parse(data);

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
				g.setIdentificador(result.getString("identificador"));
				g.setEndereco(e);

				gc.setGarcom(g);

				m.setId_mesa(result.getInt("id_mesa"));
				m.setNumero(result.getInt("numero"));
				m.setDisponibilidade(result.getInt("disponibilidade"));

				gc.setMesa(m);
				gc.setValorTotal(result.getDouble("valorTotal"));
				gc.setData(localDate);

				return gc;

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
	public void alterar(GerenciamentoContas g) {
		String alterarSql = "UPDATE GerenciamentoContas SET " + " cod_garcom= ? , " + "cod_mesa= ?," + " valorTotal= ?,"
				+ "data = ? " + "WHERE id_gerenc = ?";
		PreparedStatement pstmt = null;

		try {

			pstmt = this.conn.prepareStatement(alterarSql);
			String data = "" + g.getData();

			pstmt.setInt(1, g.getGarcom().getId_garcom());
			pstmt.setInt(2, g.getMesa().getId_mesa());
			pstmt.setDouble(3, g.getValorTotal());
			pstmt.setString(4, data);

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
	public void deletar(GerenciamentoContas g) {
		String deletarSql = "DELETE FROM GerenciamentoContas  WHERE id_gerenc = ?";
		PreparedStatement pstmt = null;
		try {

			pstmt = this.conn.prepareStatement(deletarSql);

			pstmt.setInt(1, g.getId_gerenc());
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
	public List<GerenciamentoContas> listarTodos() {
		List<GerenciamentoContas> lista = new ArrayList<>();
		ResultSet result = null;
		Statement stmt = null;
		Mesa m = null;
		Garcom g = null;
		Endereco e = null;
		GerenciamentoContas gc = null;
		String data;
		String sql = "SELECT * FROM GerenciamentoContas gc JOIN Garcom g"
				+ " ON (gc.cod_garcom = g.id_garcom)  JOIN  Mesa m ON (gc.cod_mesa = m.id_mesa) ";
		try {

			stmt = this.conn.createStatement();
			result = stmt.executeQuery(sql);

			while (result.next()) {

				gc = new GerenciamentoContas();
				m = new Mesa();
				g = new Garcom();
				e = new Endereco();
				data = result.getString("data");
				LocalDate localDate = LocalDate.parse(data);

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
				g.setIdentificador(result.getString("identificador"));
				g.setEndereco(e);

				gc.setGarcom(g);

				m.setId_mesa(result.getInt("id_mesa"));
				m.setNumero(result.getInt("numero"));
				m.setDisponibilidade(result.getInt("disponibilidade"));

				gc.setMesa(m);
				gc.setValorTotal(result.getDouble("valorTotal"));
				gc.setData(localDate);
				lista.add(gc);

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

}
