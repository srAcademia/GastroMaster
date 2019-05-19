package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class NumeroInvalidoException extends Exception{
	
	public NumeroInvalidoException() {
		super("Numero deve ser um numero natural.");
	}
}
