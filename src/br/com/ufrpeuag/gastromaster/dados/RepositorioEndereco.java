package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.interfaces.EnderecoDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;

public class RepositorioEndereco implements EnderecoDao {

	@Override
	public void inserir(Endereco end) {
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			String sql = "INSERT INTO Endereco (cidade, bairro, rua, numero, cep) VALUES(?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, end.getCidade());
			pstmt.setString(2, end.getBairro());
			pstmt.setString(3, end.getRua());
			pstmt.setInt(4, end.getNumero());
			pstmt.setString(5, end.getCep());
			pstmt.executeUpdate();

		} catch (SQLException ex) {
			// throw new ExceptionErroNoBanco(ex.getMessage());
		}

	}

	@Override
	public Endereco recuperar(Integer codigo) {
		return null;
	}

	@Override
	public void alterar(Endereco d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletar(Endereco d) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Endereco> listarTodos() {
		return null;
	}

	@Override
	public int recuperarUltimoID() {
		Connection conn;
		int id = 0;
		try {
			conn = ConfiguracoesBanco.getSingleton().getConnection();
			String sql = "SELECT *FROM Endereco WHERE id_endereco= (SELECT MAX(id_endereco) FROM Endereco);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			if (rs != null) {
				if (rs.next()) {
					id = (rs.getInt("id_endereco"));
				}
			}
			rs.close();
			pstmt.close();

			return id;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}
}
