package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class RecuperarCPFException extends Exception{
	
	public RecuperarCPFException() {
		super("CPF não encontrado");
	}
}
