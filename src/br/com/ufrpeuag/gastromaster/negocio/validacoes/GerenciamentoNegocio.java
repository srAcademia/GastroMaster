package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.sql.SQLException;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioGerenciamentoContas;
import br.com.ufrpeuag.gastromaster.dados.interfaces.IGerenciamentoContasDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RelatorioVazioException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.GerenciamentoContas;

public class GerenciamentoValidacao {
	private IGerenciamentoContasDao repGerenciamento;
	
	public GerenciamentoValidacao() throws SQLException {
		repGerenciamento = new RepositorioGerenciamentoContas();
	}
	
	public void gerenciamentoCadastroValidacao(GerenciamentoContas gerenciamento) {
		repGerenciamento.inserir(gerenciamento);
	}
	
	public List<GerenciamentoContas> gerenciamentoListarTodosValidacao() throws RelatorioVazioException {
		if(repGerenciamento.listarTodos() == null || repGerenciamento.listarTodos().isEmpty()) {
			throw new RelatorioVazioException();
		}
		return repGerenciamento.listarTodos();
	}
}
