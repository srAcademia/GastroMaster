package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class IDRecuperacaoInvalidaException extends Exception{
	
	public IDRecuperacaoInvalidaException() {
		super("ID do funcionário não encontrado.");
	}

}
