package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.interfaces.IGarcomDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;

public class RepositorioGarcom implements IGarcomDao {

	@Override
	public void inserir(Garcom garcom) {
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			String inserirSql = "INSERT INTO Garcom (nome, cpf, dataNasc, telefone, email, salario,"
					+ " identificador ,cod_endereco) VALUES(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(inserirSql);

			pstmt.setString(1, garcom.getNome());
			pstmt.setString(2, garcom.getCpf());
			pstmt.setString(3, garcom.getDataNasc());
			pstmt.setString(4, garcom.getTelefone());
			pstmt.setString(5, garcom.getEmail());
			pstmt.setDouble(6, garcom.getSalario());
			pstmt.setString(7, garcom.getIdentificador());
			pstmt.setInt(8, garcom.getEndereco().getId_endereco());
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
	public Garcom recuperar(Integer codigo) {

		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement("SELECT *\r\n"
					+ "from Garcom g join Endereco e on g.cod_endereco=e.id_endereco\r\n" + "where id_garcom = ?");
			pstmt.setInt(1, codigo);

			result = pstmt.executeQuery();

			Garcom g = null;
			Endereco e = null;

			if (result.next()) {

				g = new Garcom();
				e = new Endereco();
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
				return g;
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
	public void alterar(Garcom garcom) {
		String alterarSql = "UPDATE Garcom SET " + "nome = ? , " + "cpf = ?, " + "dataNasc = ?," + "telefone = ?,"
				+ "email = ?," + "salario = ? , identificador = ?" + " WHERE id_garcom = ?";
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement(alterarSql);

			pstmt.setString(1, garcom.getNome());
			pstmt.setString(2, garcom.getCpf());
			pstmt.setString(3, garcom.getDataNasc());
			pstmt.setString(4, garcom.getTelefone());
			pstmt.setString(5, garcom.getEmail());
			pstmt.setDouble(6, garcom.getSalario());
			pstmt.setString(7, garcom.getIdentificador());
			pstmt.setInt(8, garcom.getId_garcom());

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
	public void deletar(Garcom garcom) {
		String deletarSql = "DELETE FROM Garcom WHERE id_garcom = ?";
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement(deletarSql);

			pstmt.setInt(1, garcom.getId_garcom());
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
	public List<Garcom> listarTodos() {
		List<Garcom> lista = new ArrayList<>();
		String listarTodosSql = "Select * FROM Garcom join endereco  on cod_endereco = id_endereco ";
		ResultSet result = null;
		Statement stmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			stmt = conn.createStatement();
			result = stmt.executeQuery(listarTodosSql);
			Garcom g = null;
			Endereco e = null;

			while (result.next()) {

				g = new Garcom();
				e = new Endereco();

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

				lista.add(g);

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
	public Garcom recuperar(String cpf) {

		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement("SELECT *\r\n"
					+ "from Garcom g join Endereco e on g.cod_endereco=e.id_endereco\r\n" + "where cpf = ?;");
			pstmt.setString(1, cpf);

			result = pstmt.executeQuery();

			Garcom g = null;
			Endereco e = null;

			if (result.next()) {

				g = new Garcom();
				e = new Endereco();
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
				return g;
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
	public Garcom verificar(String identificador) {
		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();

			pstmt = conn.prepareStatement("SELECT *\r\n"
					+ "from Garcom g join Endereco e on g.cod_endereco=e.id_endereco\r\n" + "where identificador = ?;");
			pstmt.setString(1, identificador);

			result = pstmt.executeQuery();

			Garcom g = null;
			Endereco e = null;

			if (result.next()) {

				g = new Garcom();
				e = new Endereco();
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
				return g;
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

}
