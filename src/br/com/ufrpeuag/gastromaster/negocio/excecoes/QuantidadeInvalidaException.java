package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class QuantidadeInvalidaException extends Exception{
	
	public QuantidadeInvalidaException() {
		super("Quantidade de produtos a serem retirados é maior que o possível.");
	}
}
