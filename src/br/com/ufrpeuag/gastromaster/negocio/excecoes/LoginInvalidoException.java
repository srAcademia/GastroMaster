package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class LoginInvalidoException extends Exception{
	
	public LoginInvalidoException() {
		super("Login inexistente.");
	}

}
