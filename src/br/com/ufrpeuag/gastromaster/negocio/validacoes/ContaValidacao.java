package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.dados.RepositorioConta;
import br.com.ufrpeuag.gastromaster.dados.interfaces.ContaDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;

public class ContaValidacao {
	private ContaDao repConta;
	
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
}
