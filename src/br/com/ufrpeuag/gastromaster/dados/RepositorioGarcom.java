package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.interfaces.IGarcomDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Data;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;

public class RepositorioGarcom implements IGarcomDao {

	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet result;
	private Statement stmt;

	public RepositorioGarcom() throws SQLException {
		this.conn = ConfiguracoesBanco.getSingleton().getConnection();
	}

	@Override
	public void inserir(Garcom garcom) {
		Data d = new Data();
		String inserirSql = "INSERT INTO Garcom (nome, cpf, dataNasc, telefone, email, salario,"
				+ " identificador ,cod_endereco) VALUES(?,?,?,?,?,?,?,?)";

		try {

			pstmt = conn.prepareStatement(inserirSql);

			pstmt.setString(1, garcom.getNome());
			pstmt.setString(2, garcom.getCpf());
			pstmt.setString(3, d.mudarDataParaString(garcom.getDataNasc()));
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
		Garcom g = null;
		Endereco e = null;
		Data d = new Data();
		String sql = "SELECT *\r\n" + "from Garcom g join Endereco e on g.cod_endereco=e.id_endereco\r\n"
				+ "where id_garcom = ?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, codigo);

			result = pstmt.executeQuery();

			if (result.next()) {

				g = new Garcom();
				e = new Endereco();
				g.setId_garcom(result.getInt("id_garcom"));
				g.setNome(result.getString("nome"));
				g.setCpf(result.getString("cpf"));
				g.setDataNasc(d.mudarDataParaLocalDate(result.getString("dataNasc")));
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
		Data d = new Data();
		String alterarSql = "UPDATE Garcom SET " + "nome = ? , " + "cpf = ?, " + "dataNasc = ?," + "telefone = ?,"
				+ "email = ?," + "salario = ? , identificador = ?" + " WHERE id_garcom = ?";

		try {

			pstmt = conn.prepareStatement(alterarSql);

			pstmt.setString(1, garcom.getNome());
			pstmt.setString(2, garcom.getCpf());
			pstmt.setString(3, d.mudarDataParaString(garcom.getDataNasc()));
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

		try {
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
		Garcom g = null;
		Endereco e = null;
		Data d = new Data();
		List<Garcom> lista = new ArrayList<>();
		String listarTodosSql = "Select * FROM Garcom join endereco  on cod_endereco = id_endereco ";

		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(listarTodosSql);

			while (result.next()) {

				g = new Garcom();
				e = new Endereco();

				g.setId_garcom(result.getInt("id_garcom"));
				g.setNome(result.getString("nome"));
				g.setCpf(result.getString("cpf"));
				g.setDataNasc(d.mudarDataParaLocalDate(result.getString("dataNasc")));
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
	public Garcom recuperarCPF(String cpf) {
		Garcom g = null;
		Endereco e = null;
		Data d = new Data();
		String sqlRecuperarCpf = "SELECT * from Garcom g join Endereco e on g.cod_endereco=e.id_endereco where cpf = ?";

		try {

			pstmt = conn.prepareStatement(sqlRecuperarCpf);
			pstmt.setString(1, cpf);

			result = pstmt.executeQuery();

			if (result.next()) {

				g = new Garcom();
				e = new Endereco();
				g.setId_garcom(result.getInt("id_garcom"));
				g.setNome(result.getString("nome"));
				g.setCpf(result.getString("cpf"));
				g.setDataNasc(d.mudarDataParaLocalDate(result.getString("dataNasc")));
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
	public Garcom verificarIdentificador(String identificador) {
		Garcom g = null;
		Endereco e = null;
		Data d = new Data();
		String sqlVerificado = "SELECT * from Garcom g join Endereco e on g.cod_endereco = e.id_endereco where identificador = ?;";

		try {

			pstmt = conn.prepareStatement(sqlVerificado);
			pstmt.setString(1, identificador);

			result = pstmt.executeQuery();

			if (result.next()) {

				g = new Garcom();
				e = new Endereco();
				g.setId_garcom(result.getInt("id_garcom"));
				g.setNome(result.getString("nome"));
				g.setCpf(result.getString("cpf"));
				g.setDataNasc(d.mudarDataParaLocalDate(result.getString("dataNasc")));
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
