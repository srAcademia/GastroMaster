package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class SenhaInvalidaException extends Exception{
	
	public SenhaInvalidaException() {
		super("Senha inválida.");
	}
}
