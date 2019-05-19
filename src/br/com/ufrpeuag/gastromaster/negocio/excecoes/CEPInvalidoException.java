package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class CEPInvalidoException extends Exception{
	
	public CEPInvalidoException() {
		super("CEP não pode ser vazio.");
	}
}
