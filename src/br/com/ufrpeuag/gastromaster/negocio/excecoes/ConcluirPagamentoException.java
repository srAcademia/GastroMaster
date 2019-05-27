package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class ConcluirPagamentoException extends Exception{
	
	public ConcluirPagamentoException() {
		super("Impossível concluir pagamento.");
	}
}
