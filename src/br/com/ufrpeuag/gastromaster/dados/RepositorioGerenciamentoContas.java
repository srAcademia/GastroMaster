package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.ufrpeuag.gastromaster.dados.interfaces.IGerenciamentoContasDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Data;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.GerenciamentoContas;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;

public class RepositorioGerenciamentoContas implements IGerenciamentoContasDao {

	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet result;
	private Statement stmt;

	public RepositorioGerenciamentoContas() throws SQLException {
		this.conn = ConfiguracoesBanco.getSingleton().getConnection();
	}

	@Override
	public void inserir(GerenciamentoContas g) {
		String inserirSql = "INSERT INTO  GerenciamentoContas(cod_garcom , cod_mesa, valorTotal , data) VALUES(?,?,?,?)";
		try {
			pstmt = this.conn.prepareStatement(inserirSql);

			pstmt.setInt(1, g.getGarcom().getId_garcom());
			pstmt.setInt(2, g.getMesa().getId_mesa());
			pstmt.setDouble(3, g.getValorTotal());
			pstmt.setString(4, Data.mudarDataParaString(g.getData()));

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

		Mesa m = null;
		Garcom g = null;
		Endereco e = null;
		GerenciamentoContas gc = null;

		String sql = "SELECT * FROM GerenciamentoContas gc JOIN Garcom g"
				+ " ON (gc.cod_garcom = g.id_garcom) JOIN Endereco e on (e.id_endereco =g.cod_endereco) "
				+ " JOIN  Mesa m ON (gc.cod_mesa = m.id_mesa) " + "WHERE id_gerenc = ?";
		try {

			pstmt = this.conn.prepareStatement(sql);
			pstmt.setInt(1, codigo);

			result = pstmt.executeQuery();

			if (result.next()) {

				gc = new GerenciamentoContas();
				m = new Mesa();
				g = new Garcom();
				e = new Endereco();

				gc.setId_gerenc(result.getInt("id_gerenc"));
				g.setId_garcom(result.getInt("id_garcom"));
				g.setNome(result.getString("nome"));
				g.setCpf(result.getString("cpf"));
				g.setDataNasc(Data.mudarDataParaLocalDate(result.getString("dataNasc")));
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
				gc.setData(Data.mudarDataParaLocalDate(result.getString("data")));

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

		try {

			pstmt = this.conn.prepareStatement(alterarSql);

			pstmt.setInt(1, g.getGarcom().getId_garcom());
			pstmt.setInt(2, g.getMesa().getId_mesa());
			pstmt.setDouble(3, g.getValorTotal());
			pstmt.setString(4, Data.mudarDataParaString(g.getData()));
			pstmt.setInt(5, g.getId_gerenc());

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
		Mesa m = null;
		Garcom g = null;
		Endereco e = null;
		GerenciamentoContas gc = null;
		String sql = "SELECT * FROM GerenciamentoContas gc JOIN Garcom g" + " ON (gc.cod_garcom = g.id_garcom) "
				+ "JOIN Endereco e on (e.id_endereco =g.cod_endereco) " + "JOIN  Mesa m ON (gc.cod_mesa = m.id_mesa) ";
		try {

			stmt = this.conn.createStatement();
			result = stmt.executeQuery(sql);

			while (result.next()) {

				gc = new GerenciamentoContas();
				m = new Mesa();
				g = new Garcom();
				e = new Endereco();

				gc.setId_gerenc(result.getInt("id_gerenc"));
				g.setId_garcom(result.getInt("id_garcom"));
				g.setNome(result.getString("nome"));
				g.setCpf(result.getString("cpf"));
				g.setDataNasc(Data.mudarDataParaLocalDate(result.getString("dataNasc")));
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
				gc.setData(Data.mudarDataParaLocalDate(result.getString("data")));
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

	@Override
	public Map<String, Integer> recuperarPorDia(LocalDate data) {

		String sql = "select data,sum(valorTotal) from GerenciamentoContas where data like ? GROUP by data ";

		Map<String, Integer> mapa = new HashMap<String, Integer>();

		String date = Data.mudarDataParaString(data);

		try {

			date = "%/" + date.charAt(3) + date.charAt(4) + "/%";
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, date);
			result = pstmt.executeQuery();

			while (result.next()) {

				String dat = "";
				int quant = 0;

				dat = result.getString("data");
				dat = "" + dat.charAt(0) + dat.charAt(1) + dat.charAt(2) + dat.charAt(3) + dat.charAt(4);
				quant = result.getInt("sum(valorTotal)");
				mapa.put(dat, quant);
			}
			
			return mapa;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {

			try {
				pstmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return mapa;
	}

	@Override
	public Map<String, Integer> recuperarPorAno() {

		int c = 0;
		String dat = "";
		int quant = 0;

		String sql = "select data,sum(valorTotal) from GerenciamentoContas where data like ? group by data ";

		Map<String, Integer> mapa = new HashMap<String, Integer>();

		try {

			String date = "%/20%";

			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, date);
			result = pstmt.executeQuery();

			while (result.next()) {

				dat = result.getString("data");
				dat = "" + dat.charAt(6) + dat.charAt(7) + dat.charAt(8) + dat.charAt(9);
				quant = result.getInt("sum(valorTotal)");

				for (String key : mapa.keySet()) {
					Integer value = mapa.get(key);
					if (dat.equals(key)) {

						value = value + quant;
						mapa.put(dat, value);
						c = 1;
					}
				}
				if (c == 0) {
					mapa.put(dat, quant);
				}
				dat = "";
				quant = 0;
				c = 0;
			}

			return mapa;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {

			try {
				pstmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return mapa;
	}

	@Override
	public Map<String, Integer> recuperarPorMes(LocalDate data) {

		int c = 0;
		String dat = "";
		int quant = 0;

		String sql = "select data,sum(valorTotal) from GerenciamentoContas where data like ? group by data ";

		Map<String, Integer> mapa = new HashMap<String, Integer>();


		String date = Data.mudarDataParaString(data);

		try {

			date = "%/" + date.charAt(6) + date.charAt(7) +  date.charAt(8) + date.charAt(9) + "%";

			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, date);
			result = pstmt.executeQuery();

			while (result.next()) {

				dat = result.getString("data");
				dat = "" + dat.charAt(3) + dat.charAt(4);
				quant = result.getInt("sum(valorTotal)");
				
				if(dat.equals("01")) {dat = "jan";
				}else if(dat.equals("02")) {dat = "fev";
				}else if(dat.equals("03")) {dat = "mar";
				}else if(dat.equals("04")) {dat = "abr";
				}else if(dat.equals("05")) {dat = "maio";
				}else if(dat.equals("06")) {dat = "jun";
				}else if(dat.equals("07")) {dat = "jul";
				}else if(dat.equals("08")) {dat = "ago";
				}else if(dat.equals("09")) {dat = "set";
				}else if(dat.equals("10")) {dat = "out";
				}else if(dat.equals("11")) {dat = "nov";
				}else {dat = "dez";
				}
				
				for (String key : mapa.keySet()) {
					Integer value = mapa.get(key);
					if (dat.equals(key)) {

						value = value + quant;
						mapa.put(dat, value);
						c = 1;
					}
				}
				if (c == 0) {
					mapa.put(dat, quant);
				}
				dat = "";
				quant = 0;
				c = 0;
			}

			return mapa;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {

			try {
				pstmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

		return mapa;
	}
}
