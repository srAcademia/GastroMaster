package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class QuantidadeProdutoInvalidaException extends Exception{
	
	public QuantidadeProdutoInvalidaException() {
		super("A quantidade do produto deve ser um numero natural.");
	}
}
