package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class CidadeInvalidaException extends Exception{
	
	public CidadeInvalidaException() {
		super("Cidade não pode ser vazia.");
	}
}
