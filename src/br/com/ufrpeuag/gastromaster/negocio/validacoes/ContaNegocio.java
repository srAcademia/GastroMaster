package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.sql.SQLException;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioConta;
import br.com.ufrpeuag.gastromaster.dados.interfaces.IContaDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ContaGerarException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;

public class ContaNegocio {
	
	private IContaDao repConta;

	public ContaNegocio() throws SQLException {
		repConta = new RepositorioConta();
	}

	public void cadastrarConta(Conta conta) {
		repConta.inserir(conta);
	}

	public void deletarConta(Conta conta) {
		repConta.deletar(conta);
	}

	public Conta recuperarContaID(Integer codigo) {
		return repConta.recuperar(codigo);
	}

	public double mostrarValorConta(Conta conta) {
		return repConta.mostrarValorConta(conta);
	}

	public List<Conta> recuperarContaPorMesa(Integer codigo) throws ContaGerarException {
		if (repConta.recuperarPorMesa(codigo) == null || repConta.recuperarPorMesa(codigo).isEmpty()) {
			throw new ContaGerarException();
		}
		return repConta.recuperarPorMesa(codigo);
	}

	public void deletarTodasContasPorMesa(Conta conta) {
		repConta.deletarTodasContas(conta);
	}
}
