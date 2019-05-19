package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class CPFInvalidoException extends Exception{
	
	public CPFInvalidoException() {
		super("CPF Inválido!");
	}
}
