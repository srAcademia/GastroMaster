package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioConta;
import br.com.ufrpeuag.gastromaster.dados.interfaces.IContaDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ContaGerarException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;

public class ContaValidacao {
	private IContaDao repConta;
	
	public ContaValidacao() throws SQLException {
		repConta = new RepositorioConta();
	}
	
	public void contaCadastroContaValidacao(Conta conta) {
		repConta.inserir(conta);
	}
	
	public void contaRemocaoContaValidacao(Conta conta) {
		repConta.deletar(conta);
	}
	
	public Conta contaRecuperarContaValidacao(Integer codigo) {
		return repConta.recuperar(codigo);
	}
	
	public double contaGerarContaValidacao(Conta conta) {
		return repConta.fecharConta(conta);
	}
	
	public List<Conta> contaRetornarTodasContaPorMesaValidacao(Integer codigo) throws ContaGerarException {
		List<Conta> contas = new ArrayList<>();
		contas = repConta.recuperarPorMesa(codigo);
		if(contas == null || contas.isEmpty()) {
			throw new ContaGerarException();
		}
		return contas;
	}
	
	public void contaRemoverTodasContaValidacao(Conta conta) {
		repConta.deletarTodasContas(conta);
	}
}
