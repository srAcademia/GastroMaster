package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;
import java.time.LocalDate;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.RelatorioVazioException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.GerenciamentoContas;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;

public class MainGerenciamento {

	public void gerenciarCadastroGerenciamento(Garcom garcom, Mesa mesa, double valorTotal, LocalDate data)
			throws SQLException {
		try {
			GerenciamentoContas gerenciamento = new GerenciamentoContas(garcom, mesa, valorTotal, data);
			Fachada.getSingleton().cadastrarGerenciamentoContas(gerenciamento);
		} catch (Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}

	public void gerenciarListarGerenciamento() throws SQLException, RelatorioVazioException {
		try {
			System.out.println(Fachada.getSingleton().listarTodosGerenciamentoContas());
		} catch (RelatorioVazioException ex) {
			System.out.println(ex.getLocalizedMessage());
		} catch (Exception ex) {
			System.out.println("Erro inesperado.");
		}

	}

}
