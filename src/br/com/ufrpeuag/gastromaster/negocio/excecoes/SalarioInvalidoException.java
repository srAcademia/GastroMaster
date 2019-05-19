package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class SalarioInvalidoException extends Exception{
	
	public SalarioInvalidoException() {
		super("Salário inválido.");
	}
}
